/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.events

import io.confluent.kafka.serializers.KafkaJsonDeserializer

/** Load the properties needed to initialize the Kafka consumer. */
fun KafkaClient.loadConsumerProperties(): Map<String, Any> = mapOf(
    "bootstrap.servers" to System.getenv("BOOTSTRAP_SERVER_URL"),
    "schema.registry.url" to System.getenv("SCHEMA_REGISTRY_URL"),
    "group.id" to "tracking-data-consumer",
    "key.deserializer" to "org.apache.kafka.common.serialization.StringDeserializer",
    "value.deserializer" to KafkaJsonDeserializer::class.java
)
