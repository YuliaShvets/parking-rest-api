package ua.lviv.iot.parkingServer.model

import ua.lviv.iot.parkingServer.model.enums.VehicleType
import java.time.Duration

data class Vehicle(
    override var id: Long,
    var number: String,
    var typeOfVehicle: VehicleType,
    var durationOfUseOfParkingSpot: Duration,
    var isTicketReceived: Boolean
) : CsvData {


    override fun getHeaders(): String =
        listOf(
            "Id",
            "Number",
            "Vehicle Type",
            " Duration of use parking spot",
            "Is ticket received"
        ).joinToString(separator = ", ")

    override fun toCSV(): String = "$id, $number, $typeOfVehicle, $durationOfUseOfParkingSpot, $isTicketReceived"
}