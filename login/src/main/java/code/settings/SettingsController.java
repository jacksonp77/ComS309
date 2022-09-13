package code.settings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import code.user.*;

@RestController
public class SettingsController extends User {
	
	@Autowired
	UserRepository userRepo;
	
	
	@GetMapping(path = "/editPassword/{id}")
	String editPassword(@RequestBody String pw, @PathVariable int id) {
		
		User user = userRepo.findById(id);
		user.setPassword(pw);
		
		return "Password updated.";
	}
	
	
}
