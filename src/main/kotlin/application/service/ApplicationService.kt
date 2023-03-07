/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package application.service

/** The Application Service. */
interface ApplicationService<out E> {

    /** Execute the application service. */
    fun execute(): E
}
