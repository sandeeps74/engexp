package com.sandeep.kafka.kafkaexp;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Properties;

import lombok.SneakyThrows;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class SimpleConsumer {
	private static final String TOPIC_NAME = "TestTopic";

	@SneakyThrows
	public static void main(String[] args) {
		String topicName = TOPIC_NAME;

		Properties props = new Properties();
		props.load(new FileInputStream("src/main/resources/consumer.properties"));
		props.put("key.deserializer", org.apache.kafka.common.serialization.StringDeserializer.class.getName());
		props.put("value.deserializer", org.apache.kafka.common.serialization.StringDeserializer.class.getName());
		Consumer<String, String> consumer = new KafkaConsumer<String, String>(
				props);

		System.out.println("Topics " + consumer.listTopics());
		System.out.println("Partitions " + consumer.partitionsFor(topicName));
		System.out.println("Assignemnets " + consumer.assignment());
		System.out.println("Subscriptions " + consumer.subscription());
		System.out.println("--------------------------");
		// Kafka Consumer subscribes list of topics here.
		consumer.subscribe(Arrays.asList(topicName));
		System.out.println("Subscribed to topic " + topicName);

		System.out.println("Topics " + consumer.listTopics());
		System.out.println("Partitions " + consumer.partitionsFor(topicName));
		System.out.println("Assignemnets " + consumer.assignment());
		System.out.println("Subscriptions " + consumer.subscription());
		
		int i = 0;
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records) {
				// print the offset,key and value for the consumer records.
				System.out.printf(
						"number = %d offset = %d, key = %s, value = %s\n",
						(++i), record.offset(), record.key(), record.value());
			}
		}
		// consumer.unsubscribe();
		// consumer.close();
	}
}
