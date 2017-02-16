package com.sandeep.kafka.kafkaexp;

import java.io.FileInputStream;
import java.util.Properties;

import lombok.SneakyThrows;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class SimpleProducer {
	private static final String TOPIC_NAME = "TestTopic";

	@SneakyThrows
	public static void main(String[] args) {
		// Assign topicName to string variable
		String topicName = TOPIC_NAME;

		// create instance for properties to access producer configs
		Properties props = new Properties();
		props.load(new FileInputStream("src/main/resources/producer.properties"));
		props.put("key.serializer", org.apache.kafka.common.serialization.StringSerializer.class.getName());
		props.put("value.serializer", org.apache.kafka.common.serialization.StringSerializer.class.getName());

		Producer<String, String> producer = new KafkaProducer<String, String>(
				props);
		int numMsgs = 10;
		for (int i = 0; i < numMsgs; i++) {
			producer.send(new ProducerRecord<String, String>(topicName, Integer
					.toString(i), Integer.toString(i)));
			System.out.println("Message " + (i+1) + " of " + numMsgs + " sent successfully");
		}
		producer.close();
	}
}
