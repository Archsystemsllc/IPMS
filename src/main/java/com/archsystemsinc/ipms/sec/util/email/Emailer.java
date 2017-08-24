package com.archsystemsinc.ipms.sec.util.email;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.archsystemsinc.ipms.common.INameableEntity;
import com.archsystemsinc.ipms.sec.util.GenericConstants;

public class Emailer {

	@Value("${emailer.sendArtifactEventsEmail}")
	private String sendArtifactEventsEmail;

	private JavaMailSenderImpl mailSender;

	public JavaMailSenderImpl getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSenderImpl mailSender) {
		this.mailSender = mailSender;
	}

	public String getSendArtifactEventsEmail() {
		return sendArtifactEventsEmail;
	}

	public void setSendArtifactEventsEmail(String sendArtifactEventsEmail) {
		this.sendArtifactEventsEmail = sendArtifactEventsEmail;
	}

	static Logger logger = Logger.getLogger(Emailer.class);

	public void sendMail(String[] toEmail, String subject, String body,
			String[] cc, String[] bcc, String replyTo) {
		MimeMessage message = mailSender.createMimeMessage();
		// use the true flag to indicate you need a multipart message
		MimeMessageHelper helper;
		try {

			helper = new MimeMessageHelper(message, true);
			helper.setTo(toEmail);
			helper.setReplyTo("zingwebapp@gmail.com");
			helper.setFrom("zingwebapp@gmail.com");
			if (bcc != null)
				helper.setBcc(bcc);
			if (cc != null)
				helper.setCc(cc);
			helper.setSubject(subject);
			helper.setText(body, true);
			//mailSender.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MailException ex) {
			ex.printStackTrace();
		} catch (Exception e) {

		}
	}

	public void sendEmail(INameableEntity entity, String string) {
		try {
			String subject = "Zing's " + entity.getClass().getSimpleName() + " " + entity.getName() + " "+string;
			String body = "Hi <br/><br/> "+ entity.getClass().getSimpleName() + " has been " + string +"<br/><br/>Id :" + entity.getId() + "<br/>Name: "
					+ entity.getName();

			String[] to = new String[1];
			String[] cc = null;
			try {
				cc = (String[]) entity.getStakeHolders().toArray();
			} catch (Exception e) {
			}
			try {
				to[0] = entity.getOwner().getEmail();
			} catch (Exception e) {
			}

			if (sendArtifactEventsEmail.equalsIgnoreCase("true"))
				sendMail(to, subject, body, null, null, null);
		} catch (Exception e) {
            e.printStackTrace();
		}
	}

	public void postMail(String recipients[], String subject, String message,
			String from, boolean isHtml) throws Exception {
		boolean debug = false;

		Properties smtpProps = new Properties();

		try {
			smtpProps.put("mail.smtp.host", GenericConstants.EMAIL_SMTP_HOST);
			smtpProps.put("mail.smtp.port", GenericConstants.EMAIL_SMTP_PORT);
			smtpProps.put("mail.smtp.connectiontimeout",
					GenericConstants.SMTP_CONNECTION_TIMEOUT);
			smtpProps.put("mail.smtp.timeout",
					GenericConstants.SMTP_CONNECTION_TIMEOUT);

			logger.debug("SMTP host: "
					+ smtpProps.getProperty("mail.smtp.host")
					+ " and port is : "
					+ smtpProps.getProperty("mail.smtp.port"));

		

			for (int i = 0; i < recipients.length; i++) {
	
			}


			logger.debug("Emailer.postMail() ::: Mail Sent Successfully");
		} catch (Exception ex) {
			logger.error("Emailer.postMail() ::: " + ex.getMessage());
		}
	}

}