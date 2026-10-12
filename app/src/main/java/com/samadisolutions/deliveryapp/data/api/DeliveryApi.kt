package com.samadisolutions.deliveryapp.data.api

import com.samadisolutions.deliveryapp.data.models.Delivery
import com.samadisolutions.deliveryapp.data.models.DeliveryStats
import com.samadisolutions.deliveryapp.data.models.Rating
import com.samadisolutions.deliveryapp.data.models.User
import retrofit2.http.*

interface DeliveryApi {
    
    // Delivery endpoints
    @GET("deliveries")
    suspend fun getDeliveries(@Query("status") status: String? = null): List<Delivery>

    @GET("deliveries/{id}")
    suspend fun getDeliveryById(@Path("id") deliveryId: String): Delivery

    @PATCH("deliveries/{id}")
    suspend fun updateDeliveryStatus(
        @Path("id") deliveryId: String,
        @Body update: DeliveryStatusUpdate
    ): Delivery

    @POST("deliveries/{id}/accept")
    suspend fun acceptDelivery(@Path("id") deliveryId: String): Delivery

    @POST("deliveries/{id}/complete")
    suspend fun completeDelivery(
        @Path("id") deliveryId: String,
        @Body completion: DeliveryCompletion
    ): Delivery

    @GET("deliveries/stats")
    suspend fun getDeliveryStats(): DeliveryStats

    @POST("deliveries/{id}/rating")
    suspend fun submitRating(
        @Path("id") deliveryId: String,
        @Body rating: Rating
    ): Rating

    // User endpoints
    @GET("user/profile")
    suspend fun getCurrentUser(): User

    @PUT("user/profile")
    suspend fun updateProfile(@Body user: User): User

    // Auth endpoints
    @POST("auth/signup")
    suspend fun signUp(@Body credentials: SignUpRequest): AuthResponse

    @POST("auth/signin")
    suspend fun signIn(@Body credentials: SignInRequest): AuthResponse

    @POST("auth/signout")
    suspend fun signOut(): Unit
}

data class DeliveryStatusUpdate(
    val status: String,
    val notes: String? = null,
    val completedAt: String? = null
)

data class DeliveryCompletion(
    val status: String = "completed",
    val proofImageUrl: String,
    val notes: String? = null,
    val completedAt: String
)

data class SignUpRequest(
    val email: String,
    val password: String,
    val name: String,
    val phone: String
)

data class SignInRequest(
    val email: String,
    val password: String
)

data class AuthResponse(
    val user: User,
    val token: String,
    val refreshToken: String? = null
)
