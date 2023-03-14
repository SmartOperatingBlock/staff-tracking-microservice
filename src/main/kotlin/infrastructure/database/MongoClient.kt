/*
 * Copyright (c) 2023. Smart Operating Block
 *
 * Use of this source code is governed by an MIT-style
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/MIT.
 */

package infrastructure.database

import application.controller.manager.StaffTrackingDatabaseManager
import application.presenter.database.TimeSeriesTrackingData
import application.presenter.database.toTimeSeriesTrackingData
import application.presenter.database.toTrackingData
import com.mongodb.MongoException
import com.mongodb.client.MongoDatabase
import entity.HealthProfessionalId
import entity.RoomId
import entity.TrackingData
import entity.TrackingType
import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollection
import java.time.Instant

/**
 * The Mongo client.
 */
class MongoClient(
    connectionString: String
) : StaffTrackingDatabaseManager {

    init {
        check(connectionString.isNotEmpty()) {
            "Please provide the Staff Tracking MongoDB connection string!"
        }
    }

    /**
     * The db of the application.
     */
    val database: MongoDatabase = KMongo.createClient(connectionString).getDatabase(databaseName)

    private val trackingDataCollection =
        database.getCollection<TimeSeriesTrackingData>("tracking_data")

    override fun addTrackingData(trackingData: TrackingData) =
        try {
            trackingDataCollection.insertOne(trackingData.toTimeSeriesTrackingData()).wasAcknowledged()
        } catch (exception: MongoException) {
            println(exception)
            false
        }

    override fun getHealthProfessionalTrackingData(
        healthProfessionalId: HealthProfessionalId,
        from: Instant?,
        to: Instant?
    ): Set<TrackingData> =
        getLatestTrackingData(from, to).filter {
            it.healthProfessionalId == healthProfessionalId
        }.toSet()

    override fun getRoomTrackingData(roomId: RoomId, from: Instant?, to: Instant?) =
        getLatestTrackingData(from, to).filter {
            it.roomId == roomId
        }.toSet()

    private fun getBlockCurrentTrackingData(): Set<TrackingData> {
        return trackingDataCollection.find().groupBy {
            it.metadata.healthProfessionalId
        }.map {
            it.value.sortedByDescending { list -> list.dateTime }.first()
        }.filter {
            it.trackingType == TrackingType.ENTER.name
        }.map {
            it.toTrackingData()
        }.toSet()
    }

    override fun getLatestTrackingData(from: Instant?, to: Instant?): Set<TrackingData> =
        if (from != null) {
            trackingDataCollection.find()
                .map {
                    it.toTrackingData()
                }.filter {
                    it.dateTime.isAfter(from) && it.dateTime.isBefore(to)
                }.toSet()
        } else {
            getBlockCurrentTrackingData()
        }

    companion object {
        /**
         * The name of the database.
         */
        const val databaseName = "staff_tracking"
    }
}
