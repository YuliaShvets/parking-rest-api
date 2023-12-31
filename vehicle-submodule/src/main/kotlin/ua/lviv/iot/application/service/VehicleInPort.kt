package ua.lviv.iot.application.service

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import ua.lviv.iot.domain.Vehicle

interface VehicleInPort {

    fun findAllEntities(): Flux<Vehicle>

    fun findEntityById(id: String): Mono<Vehicle>

    fun addEntity(entity: Vehicle): Mono<Vehicle>

    fun updateEntity(entity: Vehicle): Mono<Vehicle>

    fun deleteEntity(id: String): Mono<Unit>

    fun findVehicleByNumber(number: String): Mono<Vehicle>
}
