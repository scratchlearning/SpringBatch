package com.scratchlearnprogrammer.springbatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class JobConfiguration {

	@Autowired private JobBuilderFactory jobBuilderFactory;
	@Autowired private Step dataAcquistionStep;
	@Autowired private Step referenceDataLoadStep;
	@Autowired private Step dataEnrichmentStep;
	@Autowired private Step dataAggregationStep;
	@Autowired private Step dataPersistenceStep;
	
	@Bean
	public Job dataAggregationJob() {
		return jobBuilderFactory.get("dataAggregationJob")
				.start(dataAcquistionStep)
				.next(referenceDataLoadStep)
				.next(dataEnrichmentStep)
				.next(dataAggregationStep)
				.next(dataPersistenceStep)
				.build();
	}

}
