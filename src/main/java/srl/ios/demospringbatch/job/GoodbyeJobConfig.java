package srl.ios.demospringbatch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class GoodbyeJobConfig {
    @Bean
    Tasklet goodbyeTask() {
        return (contribution, context) -> {
            System.out.println("GOODBYE WORLD");
            return RepeatStatus.FINISHED;
        };
    }
    @Bean
    Job goodbyeJob(JobRepository jobRepository, Step goodbyeStep) {
        return new JobBuilder("goodbyeJob", jobRepository).incrementer(new RunIdIncrementer()).start(goodbyeStep).build();
    }

    @Bean
    Step goodbyeStep(JobRepository jobRepository, Tasklet goodbyeTask, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step1", jobRepository).tasklet(goodbyeTask, transactionManager).build();
    }
}
