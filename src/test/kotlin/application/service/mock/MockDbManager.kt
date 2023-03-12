/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.service.mock

import application.controller.manager.StaffTrackingDatabaseManager
import entity.HealthProfessionalId
import entity.RoomId
import entity.TrackingData
import java.time.Instant

class MockDbManager : StaffTrackingDatabaseManager {

    private val trackingDataCollection = mutableSetOf<TrackingData>()

    override fun addTrackingData(trackingData: TrackingData): Boolean =
        trackingDataCollection.add(trackingData)

    override fun getHealthProfessionalTrackingData(
        healthProfessionalId: HealthProfessionalId,
        from: Instant?
    ): Set<TrackingData> =
        trackingDataCollection.filter {
            it.healthProfessionalId == healthProfessionalId
        }.toSet()

    override fun getRoomTrackingData(roomId: RoomId, from: Instant?): Set<TrackingData> =
        trackingDataCollection.filter {
            it.roomId == roomId
        }.toSet()

    override fun getLatestTrackingData(from: Instant?): Set<TrackingData> =
        trackingDataCollection
}
