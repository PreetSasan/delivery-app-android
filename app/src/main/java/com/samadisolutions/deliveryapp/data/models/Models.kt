package com.samadisolutions.deliveryapp.data.models

data class Delivery(
    val id: String,
    val recipientName: String,
    val recipientPhone: String,
    val deliveryAddress: String,
    val deliveryLatitude: Double,
    val deliveryLongitude: Double,
    val pickupAddress: String? = null,
    val pickupLatitude: Double? = null,
    val pickupLongitude: Double? = null,
    val status: DeliveryStatus = DeliveryStatus.PENDING,
    val priority: Priority = Priority.MEDIUM,
    val scheduledDate: String? = null,
    val completedAt: String? = null,
    val notes: String? = null,
    val proofImageUrl: String? = null,
    val createdAt: String,
    val updatedAt: String
)

enum class DeliveryStatus {
    PENDING, ASSIGNED, IN_TRANSIT, COMPLETED, FAILED
}

enum class Priority {
    LOW, MEDIUM, HIGH
}

data class DeliveryStats(
    val totalDeliveries: Int,
    val completedDeliveries: Int,
    val failedDeliveries: Int,
    val pendingDeliveries: Int,
    val totalDistanceKm: Double,
    val averageRating: Double,
    val totalEarnings: Double? = null
)

data class User(
    val id: String,
    val email: String,
    val name: String,
    val phone: String,
    val avatarUrl: String? = null,
    val createdAt: String
)

data class Location(
    val latitude: Double,
    val longitude: Double,
    val accuracy: Float? = null,
    val altitude: Double? = null,
    val heading: Float? = null,
    val speed: Float? = null
)

data class DeliveryBlock(
    val id: String,
    val name: String,
    val centerLatitude: Double,
    val centerLongitude: Double,
    val radiusKm: Double,
    val deliveryCount: Int,
    val estimatedDurationMinutes: Int,
    val availableSlots: Int,
    val createdAt: String
)

data class Rating(
    val id: String,
    val deliveryId: String,
    val userId: String,
    val rating: Int, // 1-5
    val comment: String? = null,
    val createdAt: String
)
