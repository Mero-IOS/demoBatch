package srl.ios.demospringbatch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.LocalDate;

@Configuration
public class DatedJobConfig {
        @Bean
        Tasklet getDateTask(@Value("${date}") LocalDate date) {
            return (contribution, context) -> {
                System.out.println(" Date to check is " + date);
                return RepeatStatus.FINISHED;
            };
        }

        @Bean
        Job tellDateJob(JobRepository jobRepository, Step printDateStep, @Value("${date}") LocalDate date){
            return new JobBuilder("helloJob", jobRepository).incrementer(new RunIdIncrementer()).start(printDateStep).build();
        }

        @Bean
        Step printDateStep(JobRepository jobRepository, Tasklet getDateTask, PlatformTransactionManager transactionManager) {
            return new StepBuilder("helloStep", jobRepository).tasklet(getDateTask, transactionManager).build();
        }
}
