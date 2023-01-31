package service;

import config.EmailService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import javax.inject.Inject;

public class EmailJob implements Job {
    @Inject
    public EmailJob(EmailService emailService) {
        this.emailService = emailService;
    }

    EmailService emailService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        emailService.sendEmail();
    }
}
