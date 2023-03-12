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
) {
    override fun equals(other: Any?): Boolean = when {
        other === this -> true
        other is TrackingData -> (
            this.dateTime == other.dateTime && this.roomId.id == other.roomId.id &&
                this.healthProfessionalId.id == other.healthProfessionalId.id
            )
        else -> false
    }

    override fun hashCode(): Int = this.dateTime.hashCode()
}

/** The type of the tracking event. */
enum class TrackingType {
    ENTER, EXIT
}

/** The [id] of the Health Professional. */
data class HealthProfessionalId(val id: String) {
    init {
        require(id.isNotEmpty())
    }
}

/** The [id] of the room. */
data class RoomId(val id: String) {
    init {
        require(id.isNotEmpty())
    }
}
