/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.provider

import application.controller.manager.StaffTrackingDatabaseManager

/**
 * It's responsible to provide the [StaffTrackingDatabaseManager]
 * to Staff Tracking Controller.
 */
interface Provider {

    /** The manager of staff tracking inside database. */
    val staffTrackingDatabaseManager: StaffTrackingDatabaseManager
}
