import javax.mail.Authenticator;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;
import java.util.Properties;

/**
 * 
 * @author veres.levente
 * @email: bergermanus@gmail.com
 */

public class Main {

	/**
	 * @param args
	 *            the command line arguments
	 */

	public static void main(String[] args) {
		Main m = new Main();
		m.SendMail();
	}

	String user_auth = "lirh90@gmail.com";
	String user_password = "×Ô¼º¸Ä";
	String host = "smtp.gmail.com";
	String port = "465";

	// String user_auth = "testbrg@codespring.ro";
	// String user_password = "*******";
	// String host="exchange.codespring.local";
	// String port = "25";

	public void SendMail() {

		// String from = "testbrg@codespring.ro";
		String from = "lirh90@gmail.com";
		String to = "juntao.gu@gmail.com";
		String Subject = "2B";
		String MessageText = "Exchange Test mail";

		Properties mailProp = new Properties();
		mailProp.put("mail.smtp.host", host);
		mailProp.put("mail.smtp.port", port);
		/*
		 * It¡¯s Comming the TLS settings
		 */
		mailProp.put("mail.smtp.starttls.enable", "true");
		mailProp.put("mail.smtp.auth", "true");
		mailProp.put("mail.smtp.socketFactory.port", port);
		mailProp.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		mailProp.put("mail.smtp.socketFactory.fallback", "false");

		// Optional debug
		mailProp.put("mail.debug", "true");

		try {
			/*
			 * Now comming the Real Authentication via the Autheticator Class,
			 * off course i need to overwrite
			 */
			Authenticator myAuth = new MyAuthenticator();
			Session session = Session.getDefaultInstance(mailProp, myAuth);

			// Session Debug ON
			session.setDebug(true);

			MimeMessage mymsg = new MimeMessage(session);
			mymsg.setText(MessageText);
			mymsg.setSubject(Subject);
			mymsg.setFrom(new InternetAddress(from));
			mymsg.setRecipient(Message.RecipientType.TO,
					new InternetAddress(to));
			Transport.send(mymsg);
			session.getDebugOut();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private class MyAuthenticator extends javax.mail.Authenticator {
		@Override
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(user_auth, user_password);
		}
	}

}