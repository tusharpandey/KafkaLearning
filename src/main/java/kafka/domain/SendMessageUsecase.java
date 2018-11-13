package kafka.domain;

import kafka.initialization.KafkaProducer;
import kafka.initialization.util.ProducerCreator;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class SendMessageUsecase implements KafkaProducer {

    private String readFileAsString(String filePath) {
        try {
            StringBuffer fileData = new StringBuffer();
            BufferedReader reader = new BufferedReader(
                    new FileReader(filePath));
            char[] buf = new char[1024];
            int numRead = 0;
            while ((numRead = reader.read(buf)) != -1) {
                String readData = String.valueOf(buf, 0, numRead);
                fileData.append(readData);
            }
            reader.close();
            return fileData.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void sendMessage() {
        org.apache.kafka.clients.producer.Producer producer = ProducerCreator.getProperties();


        ProducerRecord<Long, String> record = new ProducerRecord<Long, String>(ProducerCreator.TOPIC_NAME,
                "This is record " + readFileAsString("./data.txt"));
        try {
            RecordMetadata metadata = (RecordMetadata) producer.send(record).get();
            System.out.println("Record sent with key " + " to partition " + metadata.partition()
                    + " with offset " + metadata.offset());
        } catch (ExecutionException e) {
            System.out.println("Error in sending record");
            System.out.println(e);
        } catch (InterruptedException e) {
            System.out.println("Error in sending record");
            System.out.println(e);
        }
        producer.close();

    }
}
