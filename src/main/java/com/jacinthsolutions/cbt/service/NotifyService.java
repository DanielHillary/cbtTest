package com.jacinthsolutions.cbt.service;


import java.util.Map;

import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.linkbuilder.ILinkBuilder;

import org.jobrunr.scheduling.JobScheduler;

import com.jacinthsolutions.cbt.entity.User;

@Service
public class NotifyService {


    private TemplateEngine templateEngine;
//    @Autowired
//    private JavaMailSender javaMailSender;

    @Autowired
    private JobScheduler jobScheduler;

    @Autowired
    private SendGridEmailService sendGrid;


    @Value("${spring.mail.username}")
    private String EMAIL_SENDER;

    @Autowired
    public NotifyService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
//        this.javaMailSender = javaMailSender;
        templateEngine.addLinkBuilder(new ILinkBuilder() {

            @Override
            public String getName() {
                return null;
            }

            @Override
            public Integer getOrder() {
                return null;
            }

            @Override
            public String buildLink(IExpressionContext context, String base, Map<String, Object> parameters) {
                return null;
            }


        });
    }

    public void sendRegistrationNotification(User user) {

        try {


            Context context = new Context();
            context.setVariable("user", user);
            String message = "Your Verification OTP is : " + user.getVerificationOtp();


            this.sendNotification( "emails/registration", "CHUMAG Registration", message , user , context);
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public void sendNotificationResetPassword(User user, String OTP) {

        try {


            Context context = new Context();

            String message = "Your Password Reset OTP is : " + user.getVerificationOtp();


            this.sendNotification( "emails/otp", "CHUMAG -- Password Reset", message, user , context);
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public void sendNotificationOTP(User user, String OTP) {

        try {

            Context context = new Context();
            context.setVariable("user", user);
            this.sendNotification( "emails/registration", "CHUMAG -- OTP", OTP, user , context);

        } catch (MessagingException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    public void sendNotificationWelcome(User user) {

        try {


            Context context = new Context();
            String message = "Thank you for successful login " ;


            this.sendNotification( "emails/registration", "CHUMAG Mobile App Login",message, user , context);
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    private void sendNotification(String template, String subject, String messages,  User recipientuser, Context context ) throws MessagingException {
        try {

            jobScheduler.enqueue(() -> sendGrid.sendGridEmail(subject, recipientuser.getEmail() ,
                    recipientuser.getFirstName(), messages, template ,  context));
        }  catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
