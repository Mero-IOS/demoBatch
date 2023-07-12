package srl.ios.demospringbatch;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.batch.core.*;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


@SpringBootTest
@SpringBatchTest
class DemoSpringBatchApplicationTest {
    @Autowired
    private JobExplorer jobExplorer;

    @Autowired
    JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private Job helloJob;

    @Autowired
    private Job goodbyeJob;

    @Autowired
    private Job tellDateJob;
    @Test
    void contextLoads() {
        assertDoesNotThrow(() -> DemoSpringBatchApplication.main(new String[]{}));
    }
    @Test
    void testHelloJob_doesComplete() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder().toJobParameters();
        jobLauncherTestUtils.setJob(helloJob);
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
        Assertions.assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);
    }

    @Test
    void testGoodbyeJob_doesComplete() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder().toJobParameters();
        jobLauncherTestUtils.setJob(goodbyeJob);
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
        Assertions.assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);
    }

    @Test
    void testTellDateJob_doesComplete() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder().addLocalDate("date",  LocalDate.of(1111,1,11)).toJobParameters();
        jobLauncherTestUtils.setJob(tellDateJob);
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
        Assertions.assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);
    }
    @ParameterizedTest
    @ValueSource(strings = {"helloJob", "goodbyeJob","tellDateJob"})
    void test_not_null() {
        List<String> jobNames = jobExplorer.getJobNames();
        assertThat("tellDateJob").isIn(jobNames);
    }
}
