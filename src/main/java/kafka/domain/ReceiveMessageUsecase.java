package kafka.domain;

import kafka.initialization.KafkaConsumer;
import kafka.initialization.util.ConsumerCreator;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import javax.xml.datatype.Duration;
import java.util.Iterator;

import static kafka.initialization.util.Constants.MAX_NO_MESSAGE_FOUND_COUNT;

public class ReceiveMessageUsecase implements KafkaConsumer {
    public void receiveMessage() {
        Consumer<Long, String> consumer = ConsumerCreator.createConsumer();
        int noMessageFound = 0;
        while (true) {
            ConsumerRecords<Long, String> consumerRecords = consumer.poll(10000);

            if (consumerRecords.count() == 0) {
                noMessageFound++;
                if (noMessageFound > MAX_NO_MESSAGE_FOUND_COUNT)
                    break;
                else
                    continue;
            }

            Iterator<ConsumerRecord<Long, String>> iterator = consumerRecords.iterator();
            while (iterator.hasNext()) {
                ConsumerRecord<Long, String> value = iterator.next();
                System.out.println("Record Key " + value.key());
                System.out.println("Record value " + value.value());
                System.out.println("Record partition " + value.partition());
                System.out.println("Record offset " + value.offset());
            }

            consumer.commitAsync();
        }
        consumer.close();
    }
}
