package com.risk.sense.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.risk.sense.domain.MessageStorage;
import com.risk.sense.domain.SearchCriteria;
import com.risk.sense.entity.Job;
import com.risk.sense.kafka.service.KafkaProducer;
 
@RestController
@RequestMapping(value="/risksense/kafka")
public class KafkaWebController {
	
	@Autowired
	KafkaProducer producer;
	
	@Autowired
	MessageStorage storage;
	
	@GetMapping(value="/producer")
	public String producer(@RequestParam("data")String data){
		producer.send(data);
		
		return "Done";
	}
	
	@GetMapping(value="/consumer")
	public String getAllRecievedMessage(){
		String messages = storage.toString();
		storage.clear();
		
		return messages;
	}
	
	@RequestMapping(value = "/postJob", method = RequestMethod.POST)
    @ResponseBody
    public String postJob(@RequestBody Job job) {
       
		// aJob aObject to aJSON  try aGSON api
		producer.send(job.toString());
		
		return "Published to  KAFKA Done";
    }
}