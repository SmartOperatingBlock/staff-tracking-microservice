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
import application.presenter.database.toTrackingData
import entity.HealthProfessionalId
import entity.RoomId
import entity.TrackingData
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

    private val database = KMongo.createClient(connectionString).getDatabase(databaseName)

    private val trackingDataCollection =
        database.getCollection<TimeSeriesTrackingData>("tracking_data")

//    override fun insertPatient(patient: Patient): Boolean =
//        patientsCollection.insertOne(patient).run {
//            getPatient(patient.taxCode) != null
//        }
//
//    override fun deletePatient(taxCode: PatientData.TaxCode): Boolean =
//        patientsCollection.deleteOne(Patient::taxCode eq taxCode).deletedCount > 0
//
//    override fun getPatient(taxCode: PatientData.TaxCode): Patient? =
//        patientsCollection.findOne(Patient::taxCode eq taxCode)

    override fun addTrackingData(trackingData: TrackingData): Boolean {
        TODO("Not yet implemented")
    }

    override fun getHealthProfessionalTrackingData(
        healthProfessionalId: HealthProfessionalId,
        from: Instant?
    ): Set<TrackingData> =
        trackingDataCollection.find().filter {
            it.metadata.healthProfessionalId.equals(healthProfessionalId)
        }.map {
            it.toTrackingData()
        }.toSet()

    override fun getRoomTrackingData(roomId: RoomId, from: Instant?): Set<TrackingData> {
        TODO("Not yet implemented")
    }

    override fun getLatestTrackingData(from: Instant?): Set<TrackingData> {
        TODO("Not yet implemented")
    }

    companion object {
        /**
         * The name of the database.
         */
        const val databaseName = "staff_tracking"
    }
}
