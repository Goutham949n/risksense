package com.risk.sense.kafka.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
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
		
		Gson gson = new Gson();
		
		Job jobObj = gson.fromJson(content, Job.class);
		
		log.info("received content = '{}'", content);
		
		storage.put(content);
		
		jobRepository.save(jobObj);
    }
}