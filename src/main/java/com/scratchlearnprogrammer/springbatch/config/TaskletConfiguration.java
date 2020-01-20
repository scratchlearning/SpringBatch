package com.scratchlearnprogrammer.springbatch.config;

import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.context.annotation.Bean;

import com.scratchlearnprogrammer.springbatch.tasklet.DataAcquistionTasklet;
import com.scratchlearnprogrammer.springbatch.tasklet.DataAggregationTasklet;
import com.scratchlearnprogrammer.springbatch.tasklet.DataEnrichmentTasklet;
import com.scratchlearnprogrammer.springbatch.tasklet.DataPersistenceTasklet;
import com.scratchlearnprogrammer.springbatch.tasklet.ReferenceDataLoadTasklet;

public class TaskletConfiguration {

	
	@Bean public Tasklet dataAcquistionTasklet() { return new DataAcquistionTasklet(); }
	@Bean public Tasklet referenceDataLoadTasklet() { return new ReferenceDataLoadTasklet(); }
	@Bean public Tasklet dataEnrichmentTasklet() { return new DataEnrichmentTasklet(); }
	@Bean public Tasklet dataAggregationTasklet() { return new DataAggregationTasklet(); }
	@Bean public Tasklet dataPersistenceTasklet() { return new DataPersistenceTasklet(); }
}
