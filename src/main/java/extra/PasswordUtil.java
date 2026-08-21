package extra;

import java.security.MessageDigest;

public class PasswordUtil {
	
	public static String hashedPassword(String password) {
		
		String hashed="";
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[]bytes=md.digest(password.getBytes());
			
			StringBuilder sb=new StringBuilder();
			for(byte b:bytes) {
				sb.append(String.format("%02x", b));
			}
			
			hashed=sb.toString();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return hashed;
	}
}
