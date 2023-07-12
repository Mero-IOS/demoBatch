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
public class HelloJobConfig{
        @Bean
        Tasklet helloTask() {
            return (contribution, context) -> {
                System.out.println("HELLO WORLD");
                return RepeatStatus.FINISHED;
            };
        }
        @Bean
        Job helloJob(JobRepository jobRepository, Step helloStep) {
            return new JobBuilder("helloJob", jobRepository).incrementer(new RunIdIncrementer()).start(helloStep).build();
        }

        @Bean
        Step helloStep(JobRepository jobRepository, Tasklet helloTask, PlatformTransactionManager transactionManager) {
            return new StepBuilder("helloStep", jobRepository).tasklet(helloTask, transactionManager).build();
        }
}
