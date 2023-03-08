/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.provider

import application.controller.manager.StaffTrackingDatabaseManager
import infrastructure.database.MongoClient

/**
 * The implementation of the [Provider] interface.
 */
class ProviderImpl : Provider {

    init {
        checkNotNull(System.getenv("STAFF_TRACKING_MONGODB_URL")) {
            "Please provide a valid MongoDB connection String! "
        }
    }

    override val staffTrackingDatabaseManager: StaffTrackingDatabaseManager by lazy {
        MongoClient(System.getenv("STAFF_TRACKING_MONGODB_URL"))
    }
}
