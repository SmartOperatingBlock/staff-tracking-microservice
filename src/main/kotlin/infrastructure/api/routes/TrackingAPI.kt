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
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import java.time.Instant

/** The API of the Staff Tracking Microservice. */
fun Route.trackingAPI(databaseManager: StaffTrackingDatabaseManager, apiPath: String) {
    fun String.toDateTime(): Instant = Instant.parse(this)

    get("$apiPath/health-professionals-tracking-data/{healthProfessionalId}") {
        TrackingServices.GetHealthProfessionalTrackingData(
            HealthProfessionalId(call.parameters["healthProfessionalId"].orEmpty()),
            call.request.queryParameters["from"]?.toDateTime(),
            call.request.queryParameters["to"]?.toDateTime(),
            StaffTrackingController(databaseManager),
        ).execute().map { data ->
            data.toTrackingDataApiDto()
        }.toList().also { list ->
            call.response.status(if (list.isEmpty()) HttpStatusCode.NoContent else HttpStatusCode.OK)
            call.respond(ResponseEntryList(list))
        }
    }

    get("$apiPath/rooms-tracking-data/{roomId}") {
        TrackingServices.GetRoomTrackingData(
            RoomId(call.parameters["roomId"].orEmpty()),
            call.request.queryParameters["from"]?.toDateTime(),
            call.request.queryParameters["to"]?.toDateTime(),
            StaffTrackingController(databaseManager),
        ).execute().map { data ->
            data.toTrackingDataApiDto()
        }.toList().run {
            call.response.status(if (this.isEmpty()) HttpStatusCode.NoContent else HttpStatusCode.OK)
            call.respond(ResponseEntryList(this))
        }
    }

    get("$apiPath/block-tracking-data") {
        val currentBlockTrackingData = TrackingServices.GetLatestTrackingData(
            call.request.queryParameters["from"]?.toDateTime(),
            call.request.queryParameters["to"]?.toDateTime(),
            StaffTrackingController(databaseManager),
        ).execute()
        currentBlockTrackingData.map { data ->
            data.toTrackingDataApiDto()
        }.toList().run {
            call.response.status(if (this.isEmpty()) HttpStatusCode.NoContent else HttpStatusCode.OK)
            call.respond(ResponseEntryList(this))
        }
    }
}
