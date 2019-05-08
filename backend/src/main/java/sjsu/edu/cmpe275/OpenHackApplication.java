package sjsu.edu.cmpe275;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@SpringBootApplication
@ComponentScan(basePackages = { "sjsu.edu.cmpe275" })
public class OpenHackApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenHackApplication.class, args);
		
		FileInputStream serviceAccount;
		try {
			serviceAccount = new FileInputStream("src/main/resources/cmpe-256-open-hack-firebase-adminsdk-apq9y-1291eec13e.json");
			FirebaseOptions options = new FirebaseOptions.Builder()
					  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
					  .setDatabaseUrl("https://cmpe-256-open-hack.firebaseio.com")
					  .build();

			FirebaseApp.initializeApp(options);
		} catch (IOException e) {
			e.printStackTrace();
		}

				
	}

}
