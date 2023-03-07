/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.provider

import usecase.repository.TrackingDataRepository

/** The provider of the [TrackingDataRepository]. */
interface Provider {

    /** The manager of staff tracking database operations. */
    val trackingDatabaseManager: TrackingDataRepository
}
