package srl.ios.demospringbatch;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.*;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBatchTest
@SpringBootTest
class JobCompletionTests {
    @Autowired
    JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private Job helloJob;

    @Autowired
    private Job goodbyeJob;

    @Autowired
    private Job tellDateJob;

    @Test
    void testHelloJob_doesComplete() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder().toJobParameters();
        jobLauncherTestUtils.setJob(helloJob);
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
        assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);
    }

    @Test
    void testGoodbyeJob_doesComplete() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder().toJobParameters();
        jobLauncherTestUtils.setJob(goodbyeJob);
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
        assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);
    }

    @Test
    void testTellDateJob_doesComplete() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder().toJobParameters();
        jobLauncherTestUtils.setJob(tellDateJob);
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);
        assertThat(jobExecution.getStatus()).isEqualTo(BatchStatus.COMPLETED);
    }
}

