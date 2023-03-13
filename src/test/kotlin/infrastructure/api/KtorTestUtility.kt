/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.api

import infrastructure.api.routes.trackingAPI
import infrastructure.database.MongoClient
import infrastructure.database.withMongo
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.install
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.routing.routing
import io.ktor.server.testing.ApplicationTestBuilder
import io.ktor.server.testing.testApplication

object KtorTestUtility {
    fun apiTestApplication(tests: suspend ApplicationTestBuilder.() -> Unit) {
        val apiPath = "/api/v1"
        val staffTrackingDatabaseManager by lazy { MongoClient("mongodb://localhost:27017") }

        withMongo {
            testApplication {
                application {
                    install(ContentNegotiation) {
                        json()
                    }
                    routing {
                        trackingAPI(staffTrackingDatabaseManager, apiPath)
                    }
                }
                tests()
            }
        }
    }
}
