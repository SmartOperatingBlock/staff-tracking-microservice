/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.database

import entity.HealthProfessionalId
import entity.RoomId
import entity.TrackingData
import entity.TrackingType
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import java.time.Instant

class TestDatabaseSerialization : StringSpec({

    val roomId = RoomId("room#1")
    val healthProfessionalId = HealthProfessionalId("12345678")
    val dateTime = Instant.now()
    val trackingType = TrackingType.ENTER

    val trackingData = TrackingData(dateTime, roomId, healthProfessionalId, trackingType)
    val timeSerie = TimeSeriesTrackingData(
        dateTime,
        TimeSeriesTrackingDataMetadata(
            roomId.id,
            healthProfessionalId.id,
        ),
        trackingType.name,
    )

    "It should be possible to create a time series from a tracking data" {
        trackingData.toTimeSeriesTrackingData() shouldNotBe null
    }

    "It should be possible to create a trackingData from a TimeSerie" {
        timeSerie.toTrackingData() shouldNotBe null
    }

    "The serialization should preserve tracking information" {
        timeSerie.toTrackingData() shouldBe trackingData
    }
})
