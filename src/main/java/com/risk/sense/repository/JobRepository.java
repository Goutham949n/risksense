package com.risk.sense.repository;

import org.springframework.data.repository.CrudRepository;

import com.risk.sense.entity.Job;

public interface JobRepository extends CrudRepository<Job, Integer>{

	Iterable<Job> findByCountry(String country);

	Iterable<Job> findByKeyword(String keyword);

	Iterable<Job> findByLanguage(String language);

	Iterable<Job> findByRate(String rate);

	Iterable<Job> findBySkills(String skills);

	Iterable<Job> findByType(String type);

	Iterable<Job> findByAvailability(String availability);

	Iterable<Job> findByExperiencelevel(String experiencelevel);

	Iterable<Job> findByAvailabilityOrCountryOrExperiencelevelOrKeywordOrLanguageOrRateOrSkillsOrType(
			String availability, String country, String experiencelevel, String keyword, String language, String rate,
			String skills, String type);
}
