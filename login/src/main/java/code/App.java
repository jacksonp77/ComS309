package code;



import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"code.friend", "code.user", "code.game", "code.message"})
public class App {

	@PostConstruct
	public void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
	
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
    
     
}
