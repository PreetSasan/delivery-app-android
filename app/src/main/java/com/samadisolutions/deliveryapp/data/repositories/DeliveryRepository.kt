package com.samadisolutions.deliveryapp.data.repositories

import com.samadisolutions.deliveryapp.data.api.DeliveryApi
import com.samadisolutions.deliveryapp.data.models.Delivery
import com.samadisolutions.deliveryapp.data.models.DeliveryStats
import com.samadisolutions.deliveryapp.data.models.Rating
import com.samadisolutions.deliveryapp.data.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeliveryRepository @Inject constructor(
    private val api: DeliveryApi
) {

    fun getDeliveries(status: String? = null): Flow<Result<List<Delivery>>> = flow {
        try {
            emit(Result.Loading)
            val deliveries = api.getDeliveries(status)
            emit(Result.Success(deliveries))
        } catch (e: Exception) {
            Timber.e(e, "Error fetching deliveries")
            emit(Result.Error(e))
        }
    }

    fun getDeliveryById(deliveryId: String): Flow<Result<Delivery>> = flow {
        try {
            emit(Result.Loading)
            val delivery = api.getDeliveryById(deliveryId)
            emit(Result.Success(delivery))
        } catch (e: Exception) {
            Timber.e(e, "Error fetching delivery")
            emit(Result.Error(e))
        }
    }

    suspend fun updateDeliveryStatus(
        deliveryId: String,
        status: String,
        notes: String? = null
    ): Result<Delivery> = try {
        val update = com.samadisolutions.deliveryapp.data.api.DeliveryStatusUpdate(
            status = status,
            notes = notes
        )
        Result.Success(api.updateDeliveryStatus(deliveryId, update))
    } catch (e: Exception) {
        Timber.e(e, "Error updating delivery status")
        Result.Error(e)
    }

    suspend fun acceptDelivery(deliveryId: String): Result<Delivery> = try {
        Result.Success(api.acceptDelivery(deliveryId))
    } catch (e: Exception) {
        Timber.e(e, "Error accepting delivery")
        Result.Error(e)
    }

    suspend fun completeDelivery(
        deliveryId: String,
        proofImageUrl: String,
        notes: String? = null
    ): Result<Delivery> = try {
        val completion = com.samadisolutions.deliveryapp.data.api.DeliveryCompletion(
            proofImageUrl = proofImageUrl,
            notes = notes,
            completedAt = System.currentTimeMillis().toString()
        )
        Result.Success(api.completeDelivery(deliveryId, completion))
    } catch (e: Exception) {
        Timber.e(e, "Error completing delivery")
        Result.Error(e)
    }

    fun getDeliveryStats(): Flow<Result<DeliveryStats>> = flow {
        try {
            emit(Result.Loading)
            val stats = api.getDeliveryStats()
            emit(Result.Success(stats))
        } catch (e: Exception) {
            Timber.e(e, "Error fetching stats")
            emit(Result.Error(e))
        }
    }

    suspend fun submitRating(
        deliveryId: String,
        rating: Int,
        comment: String? = null
    ): Result<Rating> = try {
        val ratingObj = Rating(
            id = "",
            deliveryId = deliveryId,
            userId = "",
            rating = rating,
            comment = comment,
            createdAt = System.currentTimeMillis().toString()
        )
        Result.Success(api.submitRating(deliveryId, ratingObj))
    } catch (e: Exception) {
        Timber.e(e, "Error submitting rating")
        Result.Error(e)
    }
}

sealed class Result<out T> {
    object Loading : Result<Nothing>()
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}
