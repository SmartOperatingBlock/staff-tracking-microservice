/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package entity

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldNotBe
import java.time.Instant

class TestTrackingData : StringSpec({

    "Tracking data should not have empty room id" {
        shouldThrow<IllegalArgumentException> {
            RoomId("")
        }
    }

    "Tracking data should not have empty health professional id" {
        shouldThrow<IllegalArgumentException> {
            HealthProfessionalId("")
        }
    }

    "Tracking events with same room id and health professional id should not be equals" {
        val roomId = RoomId("room#1")
        val healthProfessionalId = HealthProfessionalId("12345678")
        TrackingData(Instant.now(), roomId, healthProfessionalId, TrackingType.ENTER) shouldNotBe
            TrackingData(Instant.now(), roomId, healthProfessionalId, TrackingType.ENTER)
    }
})
