package ua.lviv.iot.infrastructure.kafka

import com.google.protobuf.GeneratedMessageV3
import io.nats.client.Connection
import org.springframework.boot.CommandLineRunner
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import ua.lviv.iot.nats.NatsSubject

@Component
class KafkaEventListener(
    private val connection: Connection,
    private val reactiveKafkaConsumerTemplate: ReactiveKafkaConsumerTemplate<String, GeneratedMessageV3>
) : CommandLineRunner {

    fun listen(): Flux<GeneratedMessageV3> {
        return reactiveKafkaConsumerTemplate
            .receiveAutoAck()
            .map { it.value() }
            .doOnNext { connection.publish(NatsSubject.ADDED_AVAILABLE_PARKING_SPOT, it.toByteArray()) }
    }

    override fun run(vararg args: String?) {
        listen().subscribe()
    }
}
