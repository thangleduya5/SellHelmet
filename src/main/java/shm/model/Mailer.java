package shm.model;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service("Mailer")
public class Mailer {
	@Autowired
	JavaMailSender mailer;

	public void send(String from, String to, String subject, String body) throws MessagingException, UnsupportedEncodingException {

		MimeMessage mail = mailer.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail, true, "utf-8");
		helper.setFrom(from, from);
		helper.setTo(to);
		helper.setReplyTo(from, from);
		helper.setSubject(subject);
		helper.setText(body, true);
		mailer.send(mail);

	}
}
