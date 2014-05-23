package be.cegeka.batchers.taxcalculator.batch.service.reporting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeJobExecutionListener implements org.springframework.batch.core.JobExecutionListener {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeJobExecutionListener.class);

    @Autowired
    private SumOfTaxes sumOfTaxes;

    @Override
    public void beforeJob(JobExecution jobExecution) {
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        LOG.info("\n\nSum of success taxes = {} \n\n ", sumOfTaxes.getSuccessSum());
        LOG.info("\n\nSum of failure taxes = {} \n\n ", sumOfTaxes.getFailedSum());
    }
}
