/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.service

import entity.HealthProfessionalId
import entity.RoomId
import entity.TrackingData
import usecase.repository.TrackingDataRepository
import java.time.Instant

/** The module with all application services. */
object TrackingServices {

    /** The application service to add some [trackingData] given the [trackingDataRepository]. */
    class AddTrackingData(
        private val trackingData: TrackingData,
        private val trackingDataRepository: TrackingDataRepository
    ) : ApplicationService<Boolean> {
        override fun execute(): Boolean =
            trackingDataRepository.addTrackingData(trackingData)
    }

    /** The application service to get health professionals tracking data
     * given the [healthProfessionalId], the [trackingDataRepository] and [from] a certain moment. */
    class GetHealthProfessionalTrackingData(
        private val healthProfessionalId: HealthProfessionalId,
        private val from: Instant?,
        private val to: Instant?,
        private val trackingDataRepository: TrackingDataRepository
    ) : ApplicationService<Set<TrackingData>> {

        override fun execute(): Set<TrackingData> =
            trackingDataRepository.getHealthProfessionalTrackingData(healthProfessionalId, from, to)
    }

    /** The application service to get room tracking data
     * given the [roomId], the [trackingDataRepository] and [from] a certain moment. */
    class GetRoomTrackingData(
        private val roomId: RoomId,
        private val from: Instant?,
        private val to: Instant?,
        private val trackingDataRepository: TrackingDataRepository
    ) : ApplicationService<Set<TrackingData>> {

        override fun execute(): Set<TrackingData> =
            trackingDataRepository.getRoomTrackingData(roomId, from, to)
    }

    /** The application service to get the latest tracking data
     * given the [trackingDataRepository] and [from] a certain moment. */
    class GetLatestTrackingData(
        private val from: Instant?,
        private val to: Instant?,
        private val trackingDataRepository: TrackingDataRepository
    ) : ApplicationService<Set<TrackingData>> {

        override fun execute(): Set<TrackingData> =
            trackingDataRepository.getLatestTrackingData(from, to)
    }
}
