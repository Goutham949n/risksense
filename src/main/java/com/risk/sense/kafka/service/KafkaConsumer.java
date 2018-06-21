package com.risk.sense.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.risk.sense.domain.MessageStorage;
import com.risk.sense.entity.Job;
import com.risk.sense.repository.JobRepository;
 
 
@Component
public class KafkaConsumer {
	private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);
 
	@Autowired
	MessageStorage storage;
	
	@Autowired 
	private JobRepository jobRepository;
	
	@KafkaListener(topics="${risksense.kafka.topic}")
    public void processMessage(String content) {
		log.info("received content = '{}'", content);
		storage.put(content);
		
		
		// aCOnvert aBack the aGSON aJob aJSON into aJob aObject
		Job  j = new Job();
		j.setKeyword(content);
			
		
		
		jobRepository.save(j);
    }
}