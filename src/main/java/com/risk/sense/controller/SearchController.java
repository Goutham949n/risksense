package com.risk.sense.controller;

import java.util.ArrayList;
import java.util.Iterator;

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

    @RequestMapping(value = "/search/byCountry", method = RequestMethod.POST)
    @ResponseBody
    public Iterable<Job> searchByCountry(@RequestBody Job searchCriteria) {
        return jobRepository.findByCountry(searchCriteria.getCountry());
    }
    
    @RequestMapping(value = "/search/byKeyword", method = RequestMethod.POST)
    @ResponseBody
    public Iterable<Job> searchByKeyword(@RequestBody Job searchCriteria) {
        return jobRepository.findByKeyword(searchCriteria.getKeyword());
    }    
    
    @RequestMapping(value = "/search/byLanguage", method = RequestMethod.POST)
    @ResponseBody
    public Iterable<Job> searchByLanguage(@RequestBody Job searchCriteria) {
        return jobRepository.findByLanguage(searchCriteria.getLanguage());
    }
    
    @RequestMapping(value = "/search/byRate", method = RequestMethod.POST)
    @ResponseBody
    public Iterable<Job> searchByRate(@RequestBody Job searchCriteria) {
        return jobRepository.findByRate(searchCriteria.getRate());
    }
    
    @RequestMapping(value = "/search/bySkills", method = RequestMethod.POST)
    @ResponseBody
    public Iterable<Job> searchBySkills(@RequestBody Job searchCriteria) {
        return jobRepository.findBySkills(searchCriteria.getSkills());
    }
    
    @RequestMapping(value = "/search/byType", method = RequestMethod.POST)
    @ResponseBody
    public Iterable<Job> searchByType(@RequestBody Job searchCriteria) {
        return jobRepository.findByType(searchCriteria.getType());
    }
    
    
 @RequestMapping(value = "/search/byExperienceLevel", method = RequestMethod.POST)
    @ResponseBody
    public Iterable<Job> searchByExperienceLevel(@RequestBody Job searchCriteria) {
        return jobRepository.findByExperiencelevel(searchCriteria.getExperiencelevel());
    }
    
    @RequestMapping(value = "/search/byAvailability", method = RequestMethod.POST)
    @ResponseBody
    public Iterable<Job> searchByAvailability(@RequestBody Job searchCriteria) {
        return jobRepository.findByAvailability(searchCriteria.getAvailability());
    }
    
    @RequestMapping(value = "/search/byAny", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<Job> searchByAny(@RequestBody Job searchCriteria) {
     
    	Iterable<Job> j1 = null;
    	Iterable<Job> j2 = null;
    	ArrayList<Job> searchList = new ArrayList<Job>();
    	
    	if(null != searchCriteria.getSkills()) {
    		j1 = jobRepository.findBySkills(searchCriteria.getSkills());
    		searchList= addSearchItems(j1);
    	}
    	if(null != searchCriteria.getLanguage()) {
    		j2 = jobRepository.findByLanguage(searchCriteria.getLanguage());
    		searchList = addSearchItems(j2);
    	}
        
    	return searchList;
 	
    }

	private ArrayList<Job> addSearchItems(Iterable<Job> j1) {
		
		ArrayList<Job> searchList = new ArrayList<Job>();  
    	
    	Iterator<Job> jobIterator = j1.iterator();
    	
    	while(jobIterator.hasNext()){
    		searchList.add(jobIterator.next());
        }
		return searchList;
	}
}