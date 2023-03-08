/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.controller

import application.controller.manager.StaffTrackingDatabaseManager
import entity.HealthProfessionalId
import entity.RoomId
import entity.TrackingData
import usecase.repository.TrackingDataRepository
import java.time.Instant

/**
 * The controller of Tracking Data operations.
 * It contains the logic to access and update patients data.
 */
class StaffTrackingController(private val dbManager: StaffTrackingDatabaseManager) : TrackingDataRepository {
    override fun addTrackingData(trackingData: TrackingData) =
        dbManager.addTrackingData(trackingData)

    override fun getHealthProfessionalTrackingData(healthProfessionalId: HealthProfessionalId, from: Instant?) =
        dbManager.getHealthProfessionalTrackingData(healthProfessionalId, from)

    override fun getRoomTrackingData(roomId: RoomId, from: Instant?) =
        dbManager.getRoomTrackingData(roomId, from)

    override fun getLatestTrackingData(from: Instant?): Set<TrackingData> =
        dbManager.getLatestTrackingData(from)
}
