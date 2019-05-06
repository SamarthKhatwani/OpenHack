package sjsu.edu.cmpe275;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OpenHackApplicationTests {

	@Test
	public void contextLoads() {
		
		try {
		FileInputStream serviceAccount = new FileInputStream("src\\main\\resources\\cmpe-256-open-hack-firebase-adminsdk-apq9y-1291eec13e.json");
		FirebaseOptions options = new FirebaseOptions.Builder()
				  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
				  .setDatabaseUrl("https://cmpe-256-open-hack.firebaseio.com")
				  .build();

				FirebaseApp.initializeApp(options);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void firebaseSetup() {
		
			// String name=FirebaseAuth.getInstance().verifyIdTokenAsync("buk5J9nvmmMbMy3BOUJlR9V0ySo1").get().getName();
			String name=null;
			try {
				System.out.println("before fetching user");
				UserRecord user = FirebaseAuth.getInstance().getUser("buk5J9nvmmMbMy3BOUJlR9V0ySo1");
				name=FirebaseAuth.getInstance().verifyIdToken("AA").getEmail();
				System.out.println(name);
			} catch (FirebaseAuthException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			assertEquals("samsetional74@gmail.com", name);
		
	}

}
