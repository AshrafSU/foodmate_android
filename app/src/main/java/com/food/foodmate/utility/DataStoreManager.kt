package com.food.foodmate.utility

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


private val Context.dataStore by preferencesDataStore(name = "user_prefs")

class DataStoreManager @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        private val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
        private val USER_ID = stringPreferencesKey("user_id")
        private val USER_NAME = stringPreferencesKey("user_name")
        private val USER_EMAIL = stringPreferencesKey("user_email")
        private val USER_MOBILE = stringPreferencesKey("user_mobile")
        private val AUTH_TOKEN_KEY = stringPreferencesKey("auth_token")
        private val PROFILE_PICTURE = stringPreferencesKey("profile_picture")
        private val USER_BIO = stringPreferencesKey("user_bio")
        private val ADDRESS_LIST = stringPreferencesKey("address_list")

    }

    suspend fun setUserId(id: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_ID] = id
        }
    }
    val getUserId: Flow<String?> = context.dataStore.data
        .map {
                preferences -> preferences[USER_ID]
        }

    suspend fun setAuthToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[AUTH_TOKEN_KEY] = token
        }
    }
    val getAuthToken: Flow<String?> = context.dataStore.data
        .map {
                preferences -> preferences[AUTH_TOKEN_KEY]
        }

    // Set Login State
    suspend fun setIsLoggedIn(isLoggedIn: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[IS_LOGGED_IN] = isLoggedIn
        }
    }
    // Get Login State
    fun getIsLoggedIn(): Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[IS_LOGGED_IN] ?: false }

    // Set User Name
    suspend fun setUserName(name: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_NAME] = name
        }
    }
    // Get User Name
    fun getUserName(): Flow<String> = context.dataStore.data
        .map { preferences -> preferences[USER_NAME] ?: "" }

    // Set User Email
    suspend fun setUserEmail(email: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_EMAIL] = email
        }
    }
    // Get User Email
    fun getUserEmail(): Flow<String> = context.dataStore.data
        .map { preferences -> preferences[USER_EMAIL] ?: "" }

    // Set User Email
    suspend fun setUserMobile(mobileNumber: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_MOBILE] = mobileNumber
        }
    }
    // Get User Email
    fun getUserMobile(): Flow<String> = context.dataStore.data
        .map { preferences -> preferences[USER_MOBILE] ?: "" }

    // Set User Email
    suspend fun setUserBio(userBio: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_BIO] = userBio
        }
    }
    // Get User Email
    fun getUserBio(): Flow<String> = context.dataStore.data
        .map { preferences -> preferences[USER_BIO] ?: "" }

    suspend fun setProfilePicture(profilePicture: String) {
        context.dataStore.edit { preferences ->
            preferences[PROFILE_PICTURE] = profilePicture
        }
    }

    val getProfilePicture: Flow<String?> = context.dataStore.data
        .map { preferences -> preferences[PROFILE_PICTURE] }

    //Clear All Data (Logout)
    suspend fun clearData() {
        context.dataStore.edit { it.clear() }
    }

    //Function to Read Login State Synchronously
    fun getIsLoggedInSync(): Boolean = runBlocking {
        context.dataStore.data.first()[IS_LOGGED_IN] ?: false
    }


    suspend fun addAddress(newAddress: AddressData) {
        val currentJson = context.dataStore.data.first()[ADDRESS_LIST] ?: ""

        val addressListType = object : TypeToken<MutableList<AddressData>>() {}.type
        val currentList: MutableList<AddressData> = if (currentJson.isNotEmpty()) {
            Gson().fromJson(currentJson, addressListType)
        } else {
            mutableListOf()
        }

        currentList.add(newAddress)

        val updatedJson = Gson().toJson(currentList)
        context.dataStore.edit { preferences ->
            preferences[ADDRESS_LIST] = updatedJson
        }
    }

    fun getAddressList(): Flow<List<AddressData>> {
        val addressListType = object : TypeToken<List<AddressData>>() {}.type

        return context.dataStore.data.map { preferences ->
            val json = preferences[ADDRESS_LIST] ?: ""
            if (json.isNotEmpty()) {
                Gson().fromJson<List<AddressData>>(json, addressListType)
            } else {
                emptyList()
            }
        }
    }
    suspend fun removeAddress(addressToRemove: AddressData) {
        val currentJson = context.dataStore.data.first()[ADDRESS_LIST] ?: ""

        val addressListType = object : TypeToken<MutableList<AddressData>>() {}.type
        val currentList: MutableList<AddressData> = if (currentJson.isNotEmpty()) {
            Gson().fromJson(currentJson, addressListType)
        } else {
            mutableListOf()
        }

        currentList.removeIf {
            it.latitude == addressToRemove.latitude &&
                    it.longitude == addressToRemove.longitude &&
                    it.label == addressToRemove.label &&
                    it.street == addressToRemove.street &&
                    it.postCode == addressToRemove.postCode &&
                    it.apartment == addressToRemove.apartment &&
                    it.fullAddress == addressToRemove.fullAddress
        }

        val updatedJson = Gson().toJson(currentList)
        context.dataStore.edit { preferences ->
            preferences[ADDRESS_LIST] = updatedJson
        }
    }

}

data class AddressData(
    val latitude: Double,
    val longitude: Double,
    val label: String,      // e.g., "Home", "Work", "Other"
    val street: String,
    val postCode: String,
    val apartment: String,
    val fullAddress: String
)


