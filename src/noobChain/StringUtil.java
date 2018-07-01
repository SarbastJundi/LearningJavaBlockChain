package noobChain;
/*
 * we need a way to generate a digital signature, SHA256 maybe?
 * we need to import java.security.Messagedigest to get acess to SHA256 algorithm
 * 
 */
import java.security.MessageDigest;

public class StringUtil {
	/*
	 * this method takes a string and applies SHA256 algorithm to it, 
	 * and returns the generated signature as a string.
	 * 
	 */ 
	public static String applySha256(String input){		
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");	        
			//Applies sha256 to our input, 
			byte[] hash = digest.digest(input.getBytes("UTF-8"));	        
			StringBuffer hexString = new StringBuffer(); // This will contain hash as hexidecimal
			for (int i = 0; i < hash.length; i++) {
				String hex = Integer.toHexString(0xff & hash[i]);
				if(hex.length() == 1) hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		}
	}	
}
