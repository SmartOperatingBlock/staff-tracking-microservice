/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.api

import entity.TrackingData
import entity.TrackingType
import kotlinx.serialization.Serializable

/**
 * Object to serialize the Tracking Data in API.
 * It serializes the [dateTime], the [roomId], the [healthProfessionalId] and the [trackingType].
 */
@Serializable
data class TrackingDataApiDto(
    val dateTime: String,
    val roomId: String,
    val healthProfessionalId: String,
    val trackingType: TrackingType,
)

/** Extension method to convert a [TrackingData] to a [TrackingDataApiDto]. */
fun TrackingData.toTrackingDataApiDto() =
    TrackingDataApiDto(
        this.dateTime.toString(),
        this.roomId.id,
        this.healthProfessionalId.id,
        this.trackingType,
    )
