/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.database

import entity.HealthProfessionalId
import entity.RoomId
import entity.TrackingData
import entity.TrackingType
import kotlinx.serialization.Contextual
import java.time.Instant

/** The Database Dto for tracking data
 *  composed by the [dateTime], the [metadata] and the [trackingType]. */
data class TimeSeriesTrackingData(
    @Contextual
    val dateTime: Instant,
    val metadata: TimeSeriesTrackingDataMetadata,
    val trackingType: String,
)

/** The metadata of the time series composed by the [roomId] and the [healthProfessionalId]. */
data class TimeSeriesTrackingDataMetadata(
    val roomId: String,
    val healthProfessionalId: String,
)

/** Extension function to convert a tracking data to a database time series. */
fun TrackingData.toTimeSeriesTrackingData(): TimeSeriesTrackingData =
    TimeSeriesTrackingData(
        dateTime = this.dateTime,
        metadata = TimeSeriesTrackingDataMetadata(
            this.roomId.id,
            this.healthProfessionalId.id,
        ),
        trackingType = this.trackingType.name,
    )

/** Extension function to convert a database time series to a tracking data. */
fun TimeSeriesTrackingData.toTrackingData(): TrackingData =
    TrackingData(
        dateTime = this.dateTime,
        roomId = RoomId(this.metadata.roomId),
        healthProfessionalId = HealthProfessionalId(this.metadata.healthProfessionalId),
        trackingType = if (this.trackingType == TrackingType.ENTER.name) TrackingType.ENTER else TrackingType.EXIT,
    )
