package kafka.domain;

import kafka.initialization.KafkaProducer;
import kafka.initialization.util.ProducerCreator;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.concurrent.ExecutionException;

public class SendMessageUsecase implements KafkaProducer {
    public void sendMessage() {
        org.apache.kafka.clients.producer.Producer producer = ProducerCreator.getProperties();

        for (int index = 0; index < ProducerCreator.MESSAGE_COUNT; index++) {
            ProducerRecord<Long, String> record = new ProducerRecord<Long, String>(ProducerCreator.TOPIC_NAME,
                    "This is record " + index);
            try {
                RecordMetadata metadata = (RecordMetadata) producer.send(record).get();
                System.out.println("Record sent with key " + index + " to partition " + metadata.partition()
                        + " with offset " + metadata.offset());
            }
            catch (ExecutionException e) {
                System.out.println("Error in sending record");
                System.out.println(e);
            }
            catch (InterruptedException e) {
                System.out.println("Error in sending record");
                System.out.println(e);
            }
        }
        producer.close();

    }
}
