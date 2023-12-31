package ua.lviv.iot.infrastructure.grpc.observer

import io.grpc.ManagedChannel
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import ua.lviv.iot.ParkingSpotOuterClass
import ua.lviv.iot.ReactorParkingSpotServiceGrpc

@Component
class EventObserver(private val grpcChannel: ManagedChannel) {

    private lateinit var stub: ReactorParkingSpotServiceGrpc.ReactorParkingSpotServiceStub

    fun observe() {
        stub = ReactorParkingSpotServiceGrpc.newReactorStub(grpcChannel)
        stub.createParkingSpot(Flux.from(Mono.just(ParkingSpotOuterClass.CreateParkingSpotRequest.getDefaultInstance())))
            .doOnNext { println(it) }
            .subscribe()
        grpcChannel.shutdown()
    }
}
