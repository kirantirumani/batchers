package be.cegeka.batchers.taxcalculator.batch.tasklet;

import be.cegeka.batchers.taxcalculator.application.domain.pdf.PDFGenerationException;
import be.cegeka.batchers.taxcalculator.batch.service.reporting.MonthlyTaxReportService;
import fr.opensagres.xdocreport.core.XDocReportException;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static org.springframework.batch.repeat.RepeatStatus.FINISHED;

@Component
@StepScope
public class JobResultsTasklet implements Tasklet {
    @Autowired
    MonthlyTaxReportService monthlyTaxReportService;
    @Value("#{jobParameters[year]}")
    private Long year;
    @Value("#{jobParameters[month]}")
    private Long month;

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws PDFGenerationException {
        Long jobExecutionId = chunkContext.getStepContext().getStepExecution().getJobExecutionId();
        monthlyTaxReportService.generateReport(jobExecutionId, year.intValue(), month.intValue());
        return FINISHED;
    }
}
