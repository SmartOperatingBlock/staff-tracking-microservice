/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.presenter.api

import entity.HealthProfessionalId
import entity.RoomId
import entity.TrackingData
import entity.TrackingType
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import java.time.Instant

class TestApiSerialization : StringSpec({

    val roomId = RoomId("room#1")
    val healthProfessionalId = HealthProfessionalId("12345678")
    val dateTime = Instant.now()
    val trackingType = TrackingType.ENTER

    val trackingData = TrackingData(dateTime, roomId, healthProfessionalId, trackingType)

    "It should be possible to create a tracking data dto from a tracking data" {
        trackingData.toTrackingDataApiDto() shouldNotBe null
    }

    "The serialization should preserve tracking information" {
        val dto = trackingData.toTrackingDataApiDto()
        dto.roomId shouldBe trackingData.roomId.id
        dto.healthProfessionalId shouldBe trackingData.healthProfessionalId.id
        dto.trackingType shouldBe trackingData.trackingType
        dto.dateTime shouldBe trackingData.dateTime.toString()
    }
})
