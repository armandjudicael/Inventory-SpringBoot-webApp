//package mg.imwa.admin.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//import java.time.Clock;
//import java.time.Instant;
//import java.time.LocalDate;
//import java.util.Date;
//
//@Service
//public class EmailService{
//
//    @Autowired
//    private JavaMailSender javaMailSender;
//
//    final String DEFAULT_MAIL= "armandjudicaelratombotiana@gmail.com";
//    public void sendEmail(String to ,String body,String subject){
//        SimpleMailMessage smm = new SimpleMailMessage();
//        smm.setFrom(DEFAULT_MAIL);
//        smm.setText(body);
//        smm.setTo(to);
//        smm.setSentDate(Date.from(Instant.now()));
//        smm.setSubject(subject);
//        javaMailSender.send(smm);
//    }
//}
