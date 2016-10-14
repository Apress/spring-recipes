package com.apress.springrecipes.springbatch.solution2;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;


public class ConditionalTasklet implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution arg0, ChunkContext arg1)
        throws Exception {
        RepeatStatus status = (Math.random() > .5) ? RepeatStatus.FINISHED : null;

        return status;
    }
}
