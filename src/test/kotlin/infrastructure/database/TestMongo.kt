/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.database

import entity.HealthProfessionalId
import entity.RoomId
import entity.TrackingData
import entity.TrackingType
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import java.time.Instant

class TestMongo : StringSpec({
    val trackingDataWithEnterType = TrackingData(
        Instant.now(),
        RoomId("room1"),
        HealthProfessionalId("hp1"),
        TrackingType.ENTER,
    )
    val trackingDataWithExitType = TrackingData(
        Instant.now().plusSeconds(100),
        RoomId("room1"),
        HealthProfessionalId("hp1"),
        TrackingType.EXIT,
    )

    "Test tracking data creation on mongo db" {
        withMongo {
            val mongoClient = MongoClient("mongodb://localhost:27017").also {
                it.database.drop()
            }
            mongoClient.addTrackingData(trackingDataWithEnterType) shouldBe true
        }
    }

    "Test get patient tracking data from db" {
        withMongo {
            val mongoClient = MongoClient("mongodb://localhost:27017").also {
                it.database.drop()
            }
            mongoClient.addTrackingData(trackingDataWithEnterType)
            mongoClient.getHealthProfessionalTrackingData(HealthProfessionalId("hp1"), null, null).also {
                it shouldNotBe null
                it.first().healthProfessionalId.id shouldBe trackingDataWithEnterType.healthProfessionalId.id
            }
        }
    }

    "Test get room tracking data from db" {
        withMongo {
            val mongoClient = MongoClient("mongodb://localhost:27017").also {
                it.database.drop()
            }
            mongoClient.addTrackingData(trackingDataWithEnterType)
            mongoClient.getRoomTrackingData(RoomId("room1"), null, null).also {
                it shouldNotBe null
                it.first().roomId.id shouldBe trackingDataWithEnterType.roomId.id
            }
        }
    }

    "Test get latest tracking data without time specified with person inside" {
        withMongo {
            val mongoClient = MongoClient("mongodb://localhost:27017").also {
                it.database.drop()
            }
            mongoClient.addTrackingData(trackingDataWithEnterType)
            mongoClient.getLatestTrackingData(null, null).also {
                it shouldNotBe null
                it.first().healthProfessionalId.id shouldBe trackingDataWithEnterType.healthProfessionalId.id
            }
        }
    }

    "Test get latest tracking data without time specified without person inside" {
        withMongo {
            val mongoClient = MongoClient("mongodb://localhost:27017").also {
                it.database.drop()
            }
            mongoClient.addTrackingData(trackingDataWithEnterType)
            mongoClient.addTrackingData(trackingDataWithExitType)
            mongoClient.getLatestTrackingData(null, null).also {
                it shouldBe emptySet()
            }
        }
    }
})
