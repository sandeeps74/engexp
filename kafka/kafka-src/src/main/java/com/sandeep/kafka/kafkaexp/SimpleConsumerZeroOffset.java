package com.sandeep.kafka.kafkaexp;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;

import lombok.SneakyThrows;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.WakeupException;

public class SimpleConsumerZeroOffset {
	private static final String TOPIC_NAME = "TestTopic";

	@SneakyThrows
	public static void main(String[] args) {
		String topicName = TOPIC_NAME;

		Properties props = new Properties();
		props.load(new FileInputStream("src/main/resources/consumer.properties"));
		props.put("key.deserializer", org.apache.kafka.common.serialization.StringDeserializer.class.getName());
		props.put("value.deserializer", org.apache.kafka.common.serialization.StringDeserializer.class.getName());
		final Consumer<String, String> consumer = new KafkaConsumer<String, String>(
				props);

		System.out.println("Topics " + consumer.listTopics());
		System.out.println("Partitions " + consumer.partitionsFor(topicName));
		System.out.println("Assignemnets " + consumer.assignment());
		System.out.println("Subscriptions " + consumer.subscription());
		System.out.println("--------------------------");
		
		final int startingOffset = 0; // start from offset 0
		// Kafka Consumer subscribes list of topics here.
		consumer.subscribe(Arrays.asList(topicName), new ConsumerRebalanceListener() {
            public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
                System.out.printf("%s topic-partitions are revoked from this consumer\n", Arrays.toString(partitions.toArray()));
            }
            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
                System.out.printf("%s topic-partitions are assigned to this consumer\n", Arrays.toString(partitions.toArray()));
                for (TopicPartition topicPartition : partitions) {
                	System.out.println("Current offset is " + consumer.position(topicPartition) + " committed offset is ->" + consumer.committed(topicPartition) );
                    if(startingOffset ==0){
                        System.out.println("Setting offset to beginning");
                        consumer.seekToBeginning(Arrays.asList(topicPartition));
                    }else if(startingOffset == -1){
                        System.out.println("Setting it to the end ");
                        consumer.seekToEnd(Arrays.asList(topicPartition));
                    }else {
                        System.out.println("Resetting offset to " + startingOffset);
                        consumer.seek(topicPartition, startingOffset);
                    }
                }
            }
        });
		System.out.println("Subscribed to topic " + topicName);

		System.out.println("Topics " + consumer.listTopics());
		System.out.println("Partitions " + consumer.partitionsFor(topicName));
		System.out.println("Assignemnets " + consumer.assignment());
		System.out.println("Subscriptions " + consumer.subscription());
		
		//Start processing messages
        try {
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
        }catch(WakeupException ex){
            System.out.println("Exception caught " + ex.getMessage());
        }finally{
        	consumer.close();
            System.out.println("After closing KafkaConsumer");
        }
        
		// consumer.unsubscribe();
		// consumer.close();
	}
}
