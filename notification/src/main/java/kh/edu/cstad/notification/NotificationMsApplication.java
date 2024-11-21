package kh.edu.cstad.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;


@SpringBootApplication
public class NotificationMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationMsApplication.class, args);
	}

//	@Bean
//	public Supplier<String> producerBinding() {
//		return () -> {
//			try {
//				Thread.sleep(1500);
//			} catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//			return "new data";
//        };
//	}
//
//	@Bean
//	public Function<Flux<Message>, Flux<Message>> processBinding() {
//		return flux -> flux
//				.map(message -> {
//					message.setContent(message.getContent().toUpperCase());
//					message.setProcessedTimestamp(System.currentTimeMillis());
//					message.setStatus("PROCESSED");
//					return message;
//				});
//	}
//
//	@Bean
//	public Consumer<String> consumerBinding() {
//		return s -> System.out.println("Data consumed :: " + s.toUpperCase());
//	}

}
