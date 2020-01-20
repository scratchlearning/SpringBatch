package com.scratchlearnprogrammer.springbatch.config;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;

public class StepConfiguration {

	@Autowired private JobRepository jobRepository;
	@Autowired private PlatformTransactionManager transactionManager;

	@Autowired private Tasklet dataAcquistionTasklet;
	@Autowired private Tasklet referenceDataLoadTasklet;
	@Autowired private Tasklet dataEnrichmentTasklet;
	@Autowired private Tasklet dataAggregationTasklet;
	@Autowired private Tasklet dataPersistenceTasklet;
	
	@Bean
	public Step dataAcquistionStep() {
		return getStepDefinition(dataAcquistionTasklet,"Data Acquistion Step");
	}
	
	@Bean
	public Step referenceDataLoadStep() {
		return getStepDefinition(referenceDataLoadTasklet,"Data Reference Step");
	}
	
	@Bean
	public Step dataEnrichmentStep() {
		return getStepDefinition(dataEnrichmentTasklet,"Data Enrichment Step");
	}
	
	@Bean
	public Step dataAggregationStep() {
		return getStepDefinition(dataAggregationTasklet,"Data Aggregation Step");
	}
	
	@Bean
	public Step dataPersistenceStep() {
		return getStepDefinition(dataPersistenceTasklet,"Data Persistence Step");
	}

	private Step getStepDefinition(final Tasklet tasklet, final String name) {
		return getStepDefinition(tasklet,name,new StepExecutionListener[] {});
	}

	private Step getStepDefinition(Tasklet tasklet, String name, StepExecutionListener[] stepExecutionListeners) {
		final TaskletStep step = new TaskletStep();
		step.setAllowStartIfComplete(false);
		step.setName(name);
		step.setTasklet(tasklet);
		step.setJobRepository(this.jobRepository);
		step.setTransactionManager(this.transactionManager);
		step.setStepExecutionListeners(stepExecutionListeners);
		return step;
	}
}
