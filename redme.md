Run a Zookeeper Server
zkserver

Run a Kafka Server
C:\kafka_2.11-0.9.0.0\
.\bin\windows\kafka-server-start.bat .\config\server.properties

Creating Topics
.\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic test

Producer
.\bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic test

Consumer
.\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test --from-beginning