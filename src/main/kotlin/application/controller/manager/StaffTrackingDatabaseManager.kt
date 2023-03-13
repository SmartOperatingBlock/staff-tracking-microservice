/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.controller.manager

import entity.HealthProfessionalId
import entity.RoomId
import entity.TrackingData
import java.time.Instant

/**
 * Interface for database operations on Tracking Data.
 */
interface StaffTrackingDatabaseManager {
    /**
     * Insert the [TrackingData] into the database.
     * @param trackingData the tracking data.
     */
    fun addTrackingData(trackingData: TrackingData): Boolean

    /**
     * Get the health professional tracking data.
     * @param healthProfessionalId the id of the health professional.
     * @param from the instant from which retrieve tracking data.
     */
    fun getHealthProfessionalTrackingData(
        healthProfessionalId: HealthProfessionalId,
        from: Instant?,
        to: Instant?
    ): Set<TrackingData>

    /**
     * Get the tracking data of a room.
     * @param roomId the room in which retrieve tracking data.
     */
    fun getRoomTrackingData(roomId: RoomId, from: Instant?, to: Instant?): Set<TrackingData>

    /**
     * Get the latest tracking data starting from a given instant.
     * @param from the instant from which retrieve tracking data.
     */
    fun getLatestTrackingData(from: Instant?, to: Instant? = Instant.now()): Set<TrackingData>
}
