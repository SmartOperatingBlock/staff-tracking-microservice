/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.events

import application.controller.manager.StaffTrackingDatabaseManager
import application.service.TrackingServices
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import infrastructure.events.model.TrackingEventDto
import infrastructure.events.model.toTrackingData
import org.apache.kafka.clients.consumer.KafkaConsumer
import java.time.Duration

/** The Kafka Client to consume staff tracking events.
 * @param dbManager the database manager for database operations.
 */
class KafkaClient(private val dbManager: StaffTrackingDatabaseManager) : EventConsumer<TrackingEventDto> {

    init {
        listOf(System.getenv("BOOTSTRAP_SERVER_URL"), System.getenv("SCHEMA_REGISTRY_URL")).forEach {
            requireNotNull(it) {
                """
                Invalid environment variable: $it
                Check out the documentation here:
                https://github.com/SmartOperatingBlock/bootstrap"""
                    .trimIndent()
            }
        }
    }

    private val kafkaConsumer: KafkaConsumer<String, String> = KafkaConsumer(loadConsumerProperties())

    override fun start() {
        kafkaConsumer.subscribe(listOf(topic)).run {
            while (true) {
                kafkaConsumer.poll(Duration.ofMillis(pollingTime)).forEach { event ->
                    val trackingEvent = jacksonObjectMapper().convertValue(event.value(), TrackingEventDto::class.java)
                    if (trackingEvent != null) {
                        consume(trackingEvent)
                    }
                }
            }
        }
    }

    override fun consume(event: TrackingEventDto): Boolean =
        TrackingServices.AddTrackingData(event.toTrackingData(), dbManager).execute()

    companion object {
        /** The topic on which consume tracking events. */
        const val topic = "tracking-events"
        /** The polling time. */
        const val pollingTime: Long = 100
    }
}
