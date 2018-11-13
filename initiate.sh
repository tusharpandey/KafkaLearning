#!/usr/bin/env bash

#kafka location
readonly zookeeper=apache-Kafka/kafka_2.11-2.0.0/kafka/kafka

#Zookeeper
readonly zookeeperCommand=zkserver

#kafkaServer
readonly kafkaServer=".\\bin\\windows\\kafka-server-start.bat"
readonly kafkaConfig=".\\config\\server.properties"

#Topic
readonly topicBat=".\\bin\\windows\kafka-topics.bat"
readonly create="--create"
readonly zookeeper_t="--zookeeper"
readonly localhost_2181="localhost:2181"
readonly replication="--replication-factor"
readonly factor="1"
readonly partitions="--partitions"
readonly partition="1"
readonly topic="--topic"
readonly test="test"

#Producer
readonly producerBat=".\\bin\\windows\\kafka-console-producer.bat"
readonly brokerList="--broker-list"
readonly localhost_9092="localhost:9092"

#Consumer
readonly consumerBat=".\\bin\\windows\\kafka-console-consumer.bat"
readonly bootstrapSever="--bootstrap-server"
readonly fromBeginning="--from-beginning"

cd ..
cd ..
cd ..
cd "$zookeeper"

echo "Please wait from 10-15 seconds to complete one task completely : -"
read -p "Press enter to start Zookeeper Server"
start "$zookeeperCommand"
read -p "Press enter to start Kafka Server"
start "$kafkaServer" "$kafkaConfig"
read -p "Press enter to create Kafka Topic"
start "$topicBat" "$create" "$zookeeper_t" "$localhost_2181" "$replication" "$factor" "$partitions" "$partition" "$topic" "$test"
read -p "Press enter to start producer"
start "$producerBat" "$brokerList" "$localhost_9092" "$topic" "$test"
read -p "Press enter to start consumer"
start "$consumerBat" "$bootstrapSever" "$localhost_9092" "$topic" "$test" "$fromBeginning"
read -p "Press enter to continue"