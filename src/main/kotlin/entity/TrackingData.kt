/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package entity

import java.time.Instant

/** The data of the health professionals tracking.
 * A tracking is composed by
 * the [dateTime], the [roomId],
 * the [healthProfessionalId] and the [trackingType] of the event.
 */
data class TrackingData(
    val dateTime: Instant,
    val roomId: RoomId,
    val healthProfessionalId: HealthProfessionalId,
    val trackingType: TrackingType
)

/** The type of the tracking event. */
enum class TrackingType {
    ENTER, EXIT
}

/** The [id] of the Health Professional. */
data class HealthProfessionalId(val id: String)

/** The [id] of the room. */
data class RoomId(val id: String)
