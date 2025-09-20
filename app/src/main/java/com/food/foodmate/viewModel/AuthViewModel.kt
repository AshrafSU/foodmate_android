package com.food.foodmate.viewModel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.food.foodmate.dataModel.auth.LoginResponse
import com.food.foodmate.dataModel.campaign.Campaign
import com.food.foodmate.dataModel.campaign.CampaignShops
import com.food.foodmate.dataModel.products.Food
import com.food.foodmate.dataModel.shop.Restaurant
import com.food.foodmate.dataModel.shop.Shop
import com.food.foodmate.network.ApiClient
import com.food.foodmate.network.LoginRequest
import com.food.foodmate.network.RegisterUserRequest
import com.food.foodmate.network.UpdateUserRequest
import com.food.foodmate.utility.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val message: String) : AuthState()
    data class Error(val errorMessage: String) : AuthState()
}

data class LoginUiState(
    val name: String = "",
    val mobileNumber: String = "",
    val password: String = "",
    val rememberMe: Boolean = false
)

@HiltViewModel
class AuthViewModel @Inject constructor(
     val dataStoreManager: DataStoreManager // ✅ Inject DataStoreManager properly
) : ViewModel() {

    val isLoggedIn: Flow<Boolean> = dataStoreManager.getIsLoggedIn()
    val userName: Flow<String> = dataStoreManager.getUserName()
    val userEmail: Flow<String> = dataStoreManager.getUserEmail()
    val userMobile: Flow<String> = dataStoreManager.getUserMobile()
    val userBio: Flow<String> = dataStoreManager.getUserBio()
    val userProfile: Flow<String?> = dataStoreManager.getProfilePicture

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> get() = _authState

    private val _loginUiState = mutableStateOf(LoginUiState())
    val loginUiState: State<LoginUiState> get() = _loginUiState

    private val _updateState = MutableStateFlow<AuthState>(AuthState.Idle)
    val updateState: StateFlow<AuthState> get() = _updateState

    // Function to update mobile
    fun onMobileNumber(newMobileNumber: String) {
        _loginUiState.value = _loginUiState.value.copy(mobileNumber = newMobileNumber)
    }
    fun onName(name: String) {
        _loginUiState.value = _loginUiState.value.copy(name = name)
    }
    // Function to update password
    fun onPasswordChange(newPassword: String) {
        _loginUiState.value = _loginUiState.value.copy(password = newPassword)
    }
    // Function to update remember me
    fun onRememberMeChange(checked: Boolean) {
        _loginUiState.value = _loginUiState.value.copy(rememberMe = checked)
    }
    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> get() = _loading


    fun registerUser(mobileNumber: String, name: String, password: String, onSuccess: () -> Unit) {
        _authState.value = AuthState.Loading

        viewModelScope.launch {
            _loading.value = true
            try {
                val request = RegisterUserRequest(
                    mobileNumber = mobileNumber,
                    name = name,
                    password = password
                )

                val response = ApiClient.authApiService.registerUser(request)

                if (response.isSuccessful && response.body() != null) {
                    val responseData = response.body()!!
                    println("Registration Success: ${responseData.message}")

                    _authState.value = AuthState.Success("Registration successful")
                    onSuccess()
                } else {
                    val errorBody = response.errorBody()?.string() ?: "Unknown error"
                    println("Registration Failed: $errorBody")
                    _authState.value = AuthState.Error("Registration failed: $errorBody")
                }
            } catch (e: Exception) {
                println("API Call Failed: ${e.localizedMessage}")
                _authState.value = AuthState.Error("Error: ${e.localizedMessage}")
            } finally {
                _loading.value = false
            }
        }
    }

    fun login(onSuccess: () -> Unit) {
        println("Call login")
        _authState.value = AuthState.Loading

        viewModelScope.launch {
            _loading.value = true
            try {
                println("🟡 Coroutine Started") // Debugging log

                val response = ApiClient.authApiService.login(
                    LoginRequest(_loginUiState.value.mobileNumber, _loginUiState.value.password)
                )

                if (response.isSuccessful && response.body() != null) {
                    val responseData: LoginResponse = response.body()!!

                    println("API Response received: $responseData") // ✅ Debugging log

                    if (responseData.token != null) {
                        println("Login Successful - Token: ${responseData.token}")
                        saveToken(responseData.id, responseData.token)
                        saveUserSession(responseData)
                        _authState.value = AuthState.Success("Login successful")
                        onSuccess()
                    } else {
                        println("Login Failed - No Token Received")
                        _authState.value = AuthState.Error("Login failed: No token received")
                    }
                } else {
                    val errorBody = response.errorBody()?.string() ?: "Unknown error"
                    println("API Error: $errorBody") // ✅ Log the actual error message
                    _authState.value = AuthState.Error("Login failed: $errorBody")
                }
            } catch (e: Exception) {
                println("Coroutine Failed: ${e.localizedMessage}") // ✅ Debugging log
                _authState.value = AuthState.Error("Error: ${e.localizedMessage}")
            } finally {
                _loading.value = false
            }
        }

    }

    fun updateUserProfile( name: String? = null,email: String? = null,mobileNumber: String? = null, bioDescription: String? = null, profilePicture: String? = null, onSuccess: () -> Unit) {
        viewModelScope.launch {
            _loading.value = true
            _authState.value = AuthState.Loading  // 🔄 Reusing existing state

            try {
                val userId = dataStoreManager.getUserId.firstOrNull() ?: ""
                val token = dataStoreManager.getAuthToken.firstOrNull() ?: ""

                println("UserId: $userId, Token: $token") // Debugging

                if (userId.isEmpty() || token.isEmpty()) {
                    _authState.value = AuthState.Error("User not logged in")
                    return@launch
                }

                val request = UpdateUserRequest(
                    id = userId,
                    name = name,
                    email = email,
                    mobileNumber = mobileNumber,
                    bioDescription = bioDescription,
                    profilePicture = profilePicture
                )

                val response = ApiClient.authApiService.updateUser("Bearer $token", request)

                if (response.isSuccessful) {
                    _authState.value = AuthState.Success("Profile updated successfully!")

                    name?.let { dataStoreManager.setUserName(it) }
                    email?.let { dataStoreManager.setUserEmail(it) }
                    mobileNumber?.let { dataStoreManager.setUserMobile(it) }
                    bioDescription?.let { dataStoreManager.setUserBio(it) }
                    profilePicture?.let { dataStoreManager.setProfilePicture(it) }

                    println("Profile updated: $name, $email, $mobileNumber")

                    onSuccess() // Navigate back after success
                } else {
                    val errorBody = response.errorBody()?.string() ?: "Unknown error"
                    _authState.value = AuthState.Error("Update failed: $errorBody")
                }
            } catch (e: Exception) {
                _authState.value = AuthState.Error("Error: ${e.localizedMessage}")
            }finally {
                _loading.value = false
            }
        }
    }

    fun saveToken(id: String,token: String) {
        viewModelScope.launch {
            dataStoreManager.setUserId(id)
            dataStoreManager.setAuthToken(token)// Save token
            println("🔐 Token saved successfully")
        }
    }

    fun saveUserSession(user: LoginResponse) {
        viewModelScope.launch {
            dataStoreManager.setIsLoggedIn(true) // ✅ Set user as logged in
            dataStoreManager.setUserName(user.name)
            dataStoreManager.setUserEmail(user.email ?: "")
            dataStoreManager.setUserMobile(user.mobileNumber ?: "")
            dataStoreManager.setProfilePicture(user.profilePicture ?: "")
            dataStoreManager.setUserBio(user.bioDescription ?: "")
            println("👤 User session saved: ${user.name}, ${user.email}, ${user.profilePicture}")
        }
    }

    fun logout() {
        viewModelScope.launch {
            dataStoreManager.clearData()
            dataStoreManager.setIsLoggedIn(false)
            _authState.value = AuthState.Idle // Reset auth state
        }
    }


    private val _restaurantList = MutableStateFlow<List<Restaurant>>(emptyList())
    val restaurantList: StateFlow<List<Restaurant>> get() = _restaurantList

    fun fetchRestaurants(key: String? = null, page: Int = 0, limit: Int = 20) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val response = ApiClient.authApiService.getRestaurantList(key, page, limit)
                if (response.isSuccessful && response.body() != null) {
                    _restaurantList.value = response.body()!!.shops
                } else {
                    println("Error: ${response.errorBody()?.string()}")
                }
            } catch (e: HttpException) {
                println("API Error: ${e.localizedMessage}")
            } catch (e: IOException) {
                println("Network Error: ${e.localizedMessage}")
            } finally {
                _loading.value = false
            }
        }
    }

    private val _shopDetails = MutableStateFlow<Shop?>(null)
    val shopDetails: StateFlow<Shop?> get() = _shopDetails
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading
    fun fetchShopDetails(shopId: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = ApiClient.shopApiService.getShopDetails(shopId)
                if (response.isSuccessful) {
                    _shopDetails.value = response.body()?.shop
                } else {
                    println("Error fetching shop details: ${response.errorBody()?.string()}")
                }
            } catch (e: HttpException) {
                println("HTTP Exception: ${e.message}")
            } catch (e: IOException) {
                println("⚠Network Error: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    private val _foodList = MutableStateFlow<List<Food>>(emptyList())
    val foodList: StateFlow<List<Food>> = _foodList
    fun fetchRestaurantFoods(shopId: String) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val response = ApiClient.foodApiService.getRestaurantFoods(shopId)
                if (response.isSuccessful) {
                    _foodList.value = response.body()?.foods ?: emptyList()
                } else {
                    Log.e("API_ERROR", "Failed to fetch foods: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("API_ERROR", "Exception: ${e.message}")
            } finally {
                _loading.value = false
            }
        }
    }

    private val _campaignList = MutableStateFlow<List<Campaign>>(emptyList())
    val campaignList: StateFlow<List<Campaign>> = _campaignList
    fun getCampaignList(key: String? = null, page: Int = 0, limit: Int = 20) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val response = ApiClient.authApiService.getAllCampaign(key, page, limit)
                if (response.isSuccessful && response.body() != null) {
                    _campaignList.value = response.body()!!.campaigns
                } else {
                    println(" Error: ${response.errorBody()?.string()}")
                }
            } catch (e: HttpException) {
                println("API Error: ${e.localizedMessage}")
            } catch (e: IOException) {
                println("Network Error: ${e.localizedMessage}")
            } finally {
                _loading.value = false
            }
        }
    }

    private val campaignShops = MutableStateFlow<List<CampaignShops>>(emptyList())
    val campaignShopList: StateFlow<List<CampaignShops>> = campaignShops

    private val _campaignDetails = MutableStateFlow<Campaign?>(null)
    open val campaignDetails: StateFlow<Campaign?> get() = _campaignDetails
    fun getCampaignShopList(campaignId: String, page: Int = 0, limit: Int = 20) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val response = ApiClient.authApiService.getCampaignShops(campaignId, page, limit)
                if (response.isSuccessful && response.body() != null) {
                    campaignShops.value = response.body()!!.campaignShops
                    _campaignDetails.value = response.body()!!.campaignDetails
                } else {
                    println(" Error: ${response.errorBody()?.string()}")
                }
            } catch (e: HttpException) {
                println("API Error: ${e.localizedMessage}")
            } catch (e: IOException) {
                println("Network Error: ${e.localizedMessage}")
            } finally {
                _loading.value = false
            }
        }
    }
}

