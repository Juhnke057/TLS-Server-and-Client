package pack;
import java.io.*;
import java.net.*;
import java.security.*;
import javax.net.ssl.*;

public class tlsclient {
	private static final String HOST = "localhost"; 
	  private static final int PORT = 8043; 
	  public static void main(String[] args) throws Exception { 
		// TrustStore
		    char[] passphrase_ts = "TrustPass".toCharArray(); 
		    KeyStore ts = KeyStore.getInstance("JKS"); 
		    ts.load(new FileInputStream("C:\\temp\\tlstest\\truststore.jks"), passphrase_ts); 
		    //ts.load(new FileInputStream("C\\temp\\tlstest\\clientKeyStore.jks"), passphrase_ts);
		    TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509"); 
		    tmf.init(ts); 
		    
		 // Keystore  ???? 
		  
		  
		    SSLContext context = SSLContext.getInstance("TLSv1.3"); 
		    TrustManager[] trustManagers = tmf.getTrustManagers();
		    KeyManager[] keyManagers   = null; // kmf.getKeyManagers();
		    
		    context.init(keyManagers, trustManagers, new SecureRandom()); 
		    SSLSocketFactory sf = context.getSocketFactory(); 
		    Socket s = sf.createSocket(HOST,PORT); 
		    
		    OutputStream toserver = s.getOutputStream(); 
		    
		    
		    toserver.write("\nConnection established.\n\n".getBytes()); 
		    System.out.print("\nConnection established.\n\n"); 
		    
		    int inCharacter=0; 
		    inCharacter = System.in.read(); 
		    while (inCharacter != '~')  
		    { 
		    	 toserver.write(inCharacter); 
		    	 toserver.flush();
		        inCharacter = System.in.read(); 
		    } 
		    toserver.close(); 
		    s.close(); 
	  }

}
