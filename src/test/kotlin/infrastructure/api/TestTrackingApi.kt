/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.api

import application.controller.StaffTrackingController
import application.service.TrackingServices
import entity.HealthProfessionalId
import entity.RoomId
import entity.TrackingData
import entity.TrackingType
import infrastructure.api.KtorTestUtility.apiTestApplication
import infrastructure.database.MongoClient
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import java.time.Instant

class TestTrackingApi : StringSpec({

    val trackingData = TrackingData(
        Instant.now(),
        RoomId("room1"),
        HealthProfessionalId("12345678"),
        TrackingType.ENTER,
    )

    val staffTrackingController = StaffTrackingController(MongoClient("mongodb://localhost:27017"))

    "It should be possible to obtain tracking data from room id" {
        apiTestApplication {
            TrackingServices.AddTrackingData(trackingData, staffTrackingController).execute()
            val response = client.get("/api/v1/rooms-tracking-data/${trackingData.roomId.id}")
            response.status shouldBe HttpStatusCode.OK
        }
    }

    "It should be possible to obtain tracking data from health professional id" {
        apiTestApplication {
            TrackingServices.AddTrackingData(trackingData, staffTrackingController).execute()
            val response =
                client.get("/api/v1/health-professionals-tracking-data/${trackingData.healthProfessionalId.id}")
            response.status shouldBe HttpStatusCode.OK
        }
    }

    "It should be possible to obtain tracking data of the whole operating block" {
        apiTestApplication {
            TrackingServices.AddTrackingData(trackingData, staffTrackingController).execute()
            val response =
                client.get("/api/v1/block-tracking-data")
            response.status shouldBe HttpStatusCode.OK
        }
    }
})
