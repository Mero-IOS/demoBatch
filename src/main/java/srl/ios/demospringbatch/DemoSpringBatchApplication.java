package srl.ios.demospringbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;


import java.time.LocalDate;
import java.util.UUID;

@SpringBootApplication
@PropertySource("classpath:config.ini")
public class DemoSpringBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoSpringBatchApplication.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner(JobLauncher jobLauncher, Job helloJob, Job goodbyeJob, Job tellDateJob, @Value("${date}") LocalDate date) {
        return args -> {
            JobParameters paramsWithDate = new JobParametersBuilder()
                    .addString("id", UUID.randomUUID().toString())
                    .addLocalDate("date", date).toJobParameters();
            JobParameters params = new JobParameters();

            var run = jobLauncher.run(helloJob, params);
            System.out.println("I said hello with this id: " + run.getJobId());

            run = jobLauncher.run(tellDateJob, paramsWithDate);
            System.out.println("I told you the date with this id: " + run.getJobId());

            run = jobLauncher.run(goodbyeJob, params);
            System.out.println("I said bye with this id: " + run.getJobId());
        };
    }
}
