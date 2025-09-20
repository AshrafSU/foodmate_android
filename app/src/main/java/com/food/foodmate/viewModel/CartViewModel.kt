package com.food.foodmate.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.food.foodmate.network.ApiClient
import com.food.foodmate.network.Order
import com.food.foodmate.network.OrderData
import com.food.foodmate.network.PlaceOrderRequest
import com.food.foodmate.utility.DataStoreManager
import com.google.gson.annotations.SerializedName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton


sealed class OrderState {
    object Idle : OrderState()
    object Loading : OrderState()
    data class Success(val message: String) : OrderState()
    data class Error(val errorMessage: String) : OrderState()
}


@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: CartRepository,
    private val dataStoreManager: DataStoreManager
) : ViewModel() {
    val cartItems = repository.allItems
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = emptyList()
        )
    private var currentShopId: String? = null

    fun addToCart(item: CartItem, onShowClearCartDialog: () -> Unit) {
        viewModelScope.launch {
            val cartList = cartItems.firstOrNull() ?: emptyList()

            if (cartList.isNotEmpty()) {
                val existingShopId = cartList.first().shopId
                if (existingShopId != item.shopId) {
                    onShowClearCartDialog()
                    return@launch
                }
            }
            repository.addToCart(item)
            currentShopId = item.shopId
        }
    }

    fun removeFromCart(item: CartItem) {
        viewModelScope.launch {
            repository.removeFromCart(item)
        }
    }

    fun replaceCartWithNewItem(item: CartItem) {
        viewModelScope.launch {
            repository.clearCart()
            repository.addToCart(item)
            currentShopId = item.shopId
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            repository.clearCart()
            currentShopId = null
        }
    }

    fun getDeliveryCharge(): Double {
        return cartItems.value.firstOrNull()?.deliveryCharge ?: 0.0
    }


    private val _orderState = MutableStateFlow<OrderState>(OrderState.Idle)
    val orderState: StateFlow<OrderState> get() = _orderState
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading
    fun placeOrder(
        deliveryType: String,
        paymentMethod: String,
        reOrderDays: List<String>? = null,
        seatNumber: Int? = null
    ) {
        viewModelScope.launch {
            _orderState.value = OrderState.Loading
            _isLoading.value = true
            try {
                val orderItems = cartItems.value.map { cartItem ->
                    FoodItemRequest(
                        foodId = cartItem.foodId,
                        quantity = cartItem.quantity
                    )
                }

                val request = PlaceOrderRequest(
                    deliveryType = deliveryType,
                    foodItems = orderItems,
                    paymentMethod = paymentMethod,
                    reOrderWeekDayName = reOrderDays,
                    seatNumber = seatNumber

                )

                val token = dataStoreManager.getAuthToken.firstOrNull() ?: ""
                val bearerToken = "Bearer $token"

                val response = ApiClient.orderApiService.placeOrder(bearerToken, request)
                if (response.isSuccessful && response.body() != null) {
                    repository.clearCart()
                    _orderState.value = OrderState.Success("Order placed successfully!")
                } else {
                    val errorBody = response.errorBody()?.string() ?: "Unknown error"
                    _orderState.value = OrderState.Error(errorBody)
                }
            } catch (e: Exception) {
                _orderState.value = OrderState.Error(e.localizedMessage ?: "An error occurred")
            } finally {
            _isLoading.value = false
            }
        }
    }


    private val _orderList = MutableStateFlow<List<Order>>(emptyList())
    val myOrderList: StateFlow<List<Order>> get() = _orderList
    fun getMyOrders(
        page: Int = 0,
        limit: Int = 20
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            try {

                val token = dataStoreManager.getAuthToken.firstOrNull() ?: ""
                val bearerToken = "Bearer $token"

                val response = ApiClient.orderApiService.getMyOrderList(bearerToken, page = page, limit = limit)

                if (response.isSuccessful && response.body() != null) {
                    _orderList.value = response.body()!!.orders
                } else {
                    println("Error: ${response.errorBody()?.string()}")
                }
            } catch (e: HttpException) {
                println("API Error: ${e.localizedMessage}")
            } catch (e: IOException) {
                println("Network Error: ${e.localizedMessage}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    private val _orderDetails = MutableStateFlow<OrderData?>(null)
    val orderDetails: StateFlow<OrderData?> get() = _orderDetails

    fun getOrderDetailsByOrderId(orderId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val token = dataStoreManager.getAuthToken.firstOrNull() ?: ""
                val bearerToken = "Bearer $token"

                val response = ApiClient.orderApiService.getOrderDetails(bearerToken, orderId)
                if (response.isSuccessful && response.body() != null) {
                    _orderDetails.value = response.body()!!.order
                } else {
                    println("Error fetching order details: ${response.errorBody()?.string()}")
                }
            } catch (e: HttpException) {
                println("API Error: ${e.localizedMessage}")
            } catch (e: IOException) {
                println("Network Error: ${e.localizedMessage}")
            } finally {
                _isLoading.value = false
            }
        }
    }





}

class CartRepository @Inject constructor(
    private val cartDao: CartDao
) {
    val allItems: Flow<List<CartItem>> = cartDao.getAllItems()

    suspend fun addToCart(item: CartItem) {
        cartDao.insert(item)
    }

    suspend fun removeFromCart(item: CartItem) {
        cartDao.delete(item)
    }

    suspend fun clearCart() {
        cartDao.clearCart()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideCartRepository(cartDao: CartDao): CartRepository {
        return CartRepository(cartDao)
    }
}

// CartItem.kt
@Entity
data class CartItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val foodId: String,
    val name: String,
    var quantity: Int,
    val price: Double,
    val sellPrice: Double,
    val imageUrl: String?,

    val shopId: String,
    val shopName: String,
    val shopLogo: String,
    val deliveryCharge: Double,
    val allowMealOrder: Boolean,
    val allowDine: Boolean
    )

data class OrderRequest(
    @SerializedName("foodItems")
    val items: List<FoodItemRequest>
)

data class FoodItemRequest(
    @SerializedName("foodId")
    val foodId: String,

    @SerializedName("quantity")
    val quantity: Int
)


@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: CartItem)

    @Query("SELECT * FROM cartitem")
    fun getAllItems(): Flow<List<CartItem>>

    @Delete
    suspend fun delete(item: CartItem)

    @Query("DELETE FROM cartitem")
    suspend fun clearCart()
}



// AppDatabase.kt
@Database(entities = [CartItem::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
}


val MIGRATION_UPDATE = object : Migration(3, 4) { // ✅ Update version (2 -> 3)
    override fun migrate(database: SupportSQLiteDatabase) {
        val cursor = database.query("PRAGMA table_info(CartItem)")
        val existingColumns = mutableSetOf<String>()

        while (cursor.moveToNext()) {
            val columnName = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            existingColumns.add(columnName)
        }
        cursor.close()

        if (!existingColumns.contains("sellPrice")) {
            database.execSQL("ALTER TABLE CartItem ADD COLUMN sellPrice REAL NOT NULL DEFAULT 0.0")
        }
        if (!existingColumns.contains("shopId")) {
            database.execSQL("ALTER TABLE CartItem ADD COLUMN shopId TEXT NOT NULL DEFAULT ''")
        }
        if (!existingColumns.contains("shopName")) {
            database.execSQL("ALTER TABLE CartItem ADD COLUMN shopName TEXT NOT NULL DEFAULT ''")
        }
        if (!existingColumns.contains("shopLogo")) {
            database.execSQL("ALTER TABLE CartItem ADD COLUMN shopLogo TEXT NOT NULL DEFAULT ''")
        }
        if (!existingColumns.contains("deliveryCharge")) {
            database.execSQL("ALTER TABLE CartItem ADD COLUMN deliveryCharge REAL NOT NULL DEFAULT 0.0")
        }
        if (!existingColumns.contains("allowMealOrder")) {
            database.execSQL("ALTER TABLE CartItem ADD COLUMN allowMealOrder REAL NOT NULL DEFAULT 0.0")
        }
        if (!existingColumns.contains("allowDine")) {
            database.execSQL("ALTER TABLE CartItem ADD COLUMN allowDine REAL NOT NULL DEFAULT 0.0")
        }
    }
}


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "cart-database"
        )
            .addMigrations(MIGRATION_UPDATE) // ✅ Apply updated migration
            .fallbackToDestructiveMigration() // ✅ Auto-fix broken migrations (Optional)
            .build()
    }

    @Provides
    fun provideCartDao(database: AppDatabase): CartDao {
        return database.cartDao()
    }
}
