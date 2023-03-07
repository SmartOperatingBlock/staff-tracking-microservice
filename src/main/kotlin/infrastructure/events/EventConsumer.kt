/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.events

/** The consumer of events. */
interface EventConsumer<in E> {

    /** Start consuming events on the broker. */
    fun start()

    /** Consume an event. */
    fun consume(event: E): Boolean
}
