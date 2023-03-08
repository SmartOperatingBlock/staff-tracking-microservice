import infrastructure.api.ApiRouter
import infrastructure.events.KafkaClient
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
    ApiRouter(provider.staffTrackingDatabaseManager).start()
    KafkaClient(provider.staffTrackingDatabaseManager).start()
}
