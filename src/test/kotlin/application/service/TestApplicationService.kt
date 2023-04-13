/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.service

import application.controller.StaffTrackingController
import application.service.mock.MockDbManager
import entity.HealthProfessionalId
import entity.RoomId
import entity.TrackingData
import entity.TrackingType
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.time.Instant

class TestApplicationService : StringSpec({

    val mockTrackingData = TrackingData(
        Instant.now(),
        RoomId("room#1"),
        HealthProfessionalId("12345678"),
        TrackingType.ENTER,
    )
    var staffTrackingController = StaffTrackingController(MockDbManager())

    beforeEach {
        staffTrackingController = StaffTrackingController(MockDbManager())
    }

    "The application service should be able to add tracking data" {
        staffTrackingController.addTrackingData(mockTrackingData) shouldBe true
    }

    "The application service should be able to get tracking data from a room id" {
        staffTrackingController.addTrackingData(mockTrackingData)
        staffTrackingController.getRoomTrackingData(
            mockTrackingData.roomId,
            null,
            null,
        ).isNotEmpty() shouldBe true
    }

    "The application service should be able to get tracking data from a health professional id" {
        staffTrackingController.addTrackingData(mockTrackingData)
        staffTrackingController.getHealthProfessionalTrackingData(
            mockTrackingData.healthProfessionalId,
            null,
            null,
        ).isNotEmpty() shouldBe true
    }

    "The application service should be able to get tracking data from a certain moment" {
        val beforeInstant = Instant.now()
        val trackingData = TrackingData(
            Instant.now(),
            RoomId("room#1"),
            HealthProfessionalId("12345678"),
            TrackingType.ENTER,
        )
        staffTrackingController.addTrackingData(trackingData)
        staffTrackingController.getLatestTrackingData(beforeInstant, null).isNotEmpty() shouldBe true
        staffTrackingController.getLatestTrackingData(beforeInstant, null).first() shouldBe trackingData
    }
})
