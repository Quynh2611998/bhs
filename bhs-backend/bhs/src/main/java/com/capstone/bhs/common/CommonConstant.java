package com.capstone.bhs.common;

public class CommonConstant {

	public static class Email {
		
		public static final String  HOST				= "smtp.gmail.com";
		public static final Integer PORT				= 587;
		public static final String  EMAIL_USER			= "bhsmailsender@gmail.com";
		public static final String  EMAIL_PWD			= "Abc123456@";
		public static final String  PROTOCAL			= "smtp";
		public static final boolean TLS					= true;
		public static final boolean AUTH				= true;
		public static final boolean START_TLS_ENABLE	= true;
		public static final String  SSL_TRUST			= "smtp.gmail.com";

		public static String buildActivationEmail(String activationLink) {
			StringBuilder content = new StringBuilder("");
			content.append("<html lang=\"en\">");
			content.append("<head>");
			content.append("<title>BHS Account actovation</title>");
			content.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
			content.append("</head>");
			content.append("<body>");
			content.append("<p>Dear User,</p>");
			content.append("<p>Your BHS account has been created, please click on the URL below to activate it: </p>");
			content.append("<p><a href=").append(activationLink).append(">").append(activationLink).append("</a></p>");
			content.append("<p><span> Regards, </span> <br/> BHS Team </p>");
			content.append("</body>");
			content.append("</html>");
			return content.toString();
		}
		
		public static String buildPasswordResetEmail(String resetLink) {
			StringBuilder content = new StringBuilder("");
			content.append("<html lang=\"en\">");
			content.append("<head>");
			content.append("<title>BHS Account actovation</title>");
			content.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
			content.append("</head>");
			content.append("<body>");
			content.append("<p>Dear User,</p>");
			content.append("<p>For your BHS account a password reset was requested, please click on the URL below to reset it: </p>");
			content.append("<p><a href=").append(resetLink).append(">").append(resetLink).append("</a></p>");
			content.append("<p><span> Regards, </span> <br/> BHS Team </p>");
			content.append("</body>");
			content.append("</html>");
			return content.toString();
		}
	}

	public static class Authorities {
		public static final String ROLE_USER 	= "User";
		public static final String ROLE_ADMIN 	= "Admin";
	}

}
