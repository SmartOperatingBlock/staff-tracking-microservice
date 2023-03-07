import infrastructure.events.EventConsumer
import infrastructure.events.KafkaClient
import infrastructure.events.model.TrackingEventDto
import infrastructure.provider.Provider
import infrastructure.provider.ProviderImpl

/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

/**
 * The application launcher.
 */
fun main() {
    val provider: Provider = ProviderImpl()
    val eventConsumer: EventConsumer<TrackingEventDto> = KafkaClient(provider.trackingDatabaseManager)
    eventConsumer.start()
}
