/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.events

import kotlinx.serialization.Serializable

/** The Tracking Event composed by a [key], a [healthProfessionalId],
 * a [roomId], the [data] and the [dateTime]. */
@Serializable
data class TrackingEventDto(
    val key: String,
    val healthProfessionalId: String,
    val roomId: String,
    val data: Boolean,
    val dateTime: String
)
