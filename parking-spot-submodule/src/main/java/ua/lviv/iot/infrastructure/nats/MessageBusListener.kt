package ua.lviv.iot.infrastructure.nats

import reactor.core.publisher.Sinks
import ua.lviv.iot.ParkingSpotOuterClass

interface MessageBusListener {

    val responseSink: Sinks.Many<ParkingSpotOuterClass.CreateParkingSpotResponse>

    fun listenToEvents()
}
