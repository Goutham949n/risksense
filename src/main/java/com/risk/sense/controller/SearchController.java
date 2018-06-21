package com.risk.sense.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.risk.sense.domain.SearchCriteria;
import com.risk.sense.entity.Job;
import com.risk.sense.repository.JobRepository;

@Controller
public class SearchController {

	@Autowired 
	private JobRepository jobRepository;
	
    @RequestMapping(value = "/search/all", method = RequestMethod.POST)
    @ResponseBody
    public Iterable<Job> search(@RequestBody SearchCriteria searchCriteria) {
        return jobRepository.findAll();
    }

}