/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.api.routes

import application.controller.StaffTrackingController
import application.controller.manager.StaffTrackingDatabaseManager
import application.presenter.api.toTrackingDataApiDto
import application.service.TrackingServices
import entity.HealthProfessionalId
import entity.RoomId
import infrastructure.api.util.ResponseEntryList
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import java.time.Instant

/** The API of the Staff Tracking Microservice. */
fun Route.trackingAPI(databaseManager: StaffTrackingDatabaseManager, apiPath: String) {

    get("$apiPath/health-professionals-tracking-data/{healthProfessionalId}") {
        call.respondWithList(
            TrackingServices.GetHealthProfessionalTrackingData(
                HealthProfessionalId(call.parameters["healthProfessionalId"].orEmpty()),
                call.request.queryParameters["from"]?.let { from -> Instant.parse(from) },
                call.request.queryParameters["to"]?.let { to -> Instant.parse(to) },
                StaffTrackingController(databaseManager)
            ).execute().map { data ->
                data.toTrackingDataApiDto()
            }.toList()
        )
    }

    get("$apiPath/rooms-tracking-data/{roomId}") {
        call.respondWithList(
            TrackingServices.GetRoomTrackingData(
                RoomId(call.parameters["roomId"].orEmpty()),
                call.request.queryParameters["from"]?.let { from -> Instant.parse(from) },
                call.request.queryParameters["to"]?.let { to -> Instant.parse(to) },
                StaffTrackingController(databaseManager)
            ).execute().map { data ->
                data.toTrackingDataApiDto()
            }.toList()
        )
    }

    get("$apiPath/block-tracking-data") {
        call.respondWithList(
            TrackingServices.GetLatestTrackingData(
                call.request.queryParameters["from"]?.let { from -> Instant.parse(from) },
                call.request.queryParameters["to"]?.let { to -> Instant.parse(to) },
                StaffTrackingController(databaseManager)
            ).execute().map { data ->
                data.toTrackingDataApiDto()
            }.toList()
        )
    }
}

/** Create the API response given the [list] of elements. */
suspend fun <T> ApplicationCall.respondWithList(list: List<T>) {
    this.response.status(if (list.isEmpty()) HttpStatusCode.NoContent else HttpStatusCode.OK)
    this.respond(ResponseEntryList(list))
}
