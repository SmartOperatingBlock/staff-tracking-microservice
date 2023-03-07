/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package usecase.repository

import entity.HealthProfessionalId
import entity.RoomId
import entity.TrackingData
import java.time.Instant

/** The repository for tracking data management. */
interface TrackingDataRepository {

    /** Add some [trackingData]. */
    fun addTrackingData(trackingData: TrackingData): Boolean

    /** Get the health professional tracking data given its [healthProfessionalId].
     *  @param from the instant from which retrieve tracking data.
     */
    fun getHealthProfessionalTrackingData(
        healthProfessionalId: HealthProfessionalId,
        from: Instant?
    ): Set<TrackingData>

    /** Get the room tracking data given its [roomId].
     *  @param from the instant from which retrieve tracking data.
     */
    fun getRoomTrackingData(roomId: RoomId, from: Instant?): Set<TrackingData>

    /** Get the latest tracking data.
     *  @param from the instant from which retrieve tracking data.
     */
    fun getLatestTrackingData(from: Instant?): Set<TrackingData>
}
