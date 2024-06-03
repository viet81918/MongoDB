/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;


import java.util.Date;
import java.util.Properties;
import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
/**
 *
 * @author ASUS
 */
public class Email {
    //password: pjei jrna eizq ikgs
    
    public static void main(String[] args){
        final String from = "thienle2105@gmail.com";
        final String password ="pjei jrna eizq ikgs";
        
        
        
        Properties props = new Properties();
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.port","587");
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.starttls.enable","true");
        
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };  //Phiên làm việc
            Session session = Session.getInstance(props, auth);
            //Gửi mail
            final String to = "thienlcde170520@fpt.edu.vn";
            //tạo một tin nhắn 
            MimeMessage msg = new MimeMessage(session);
            
            try{
                //kiểu nội dung 
                msg.addHeader("content-type", "text/HTML; charset=UTF-8");
                //người gửi
                msg.setFrom(from);
                //người nhận
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to,false));
                //tiêu đề email
                msg.setSubject("Thu nghiem gui email");
                //Quy dinh ngay gui
                msg.setSentDate(new Date());
                //noi dung
                msg.setText("Day la phan noi dung");
                //gui mail
                Transport.send(msg);
                System.out.println("Thanh cong");
            }catch(Exception e){
                System.out.println("Gap loi");
                e.printStackTrace();
            }
    }
}
