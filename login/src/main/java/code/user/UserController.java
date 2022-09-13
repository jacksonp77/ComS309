package code.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController extends User {
	
    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";
    private String notFound = "{\"message\":\"email or password was incorrect\"}";

    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private UserService userService;
    
    @GetMapping(path = "/user")
    List<User> getAllUsers(){
        return userRepo.findAll();
    }
    
    //registration 
    @PostMapping(path = "/user")
    String createUser(@RequestBody User user, Role role){
    	
        if (user == null) {
            return failure;
        }
        if (role != null) {
        	user.setRole(role);
        }
        else {
        	user.setRole(Role.USER);
        }
        User user2 = userRepo.findByEmail(user.getEmail());
        if(user2 != null){
        }
    	else{
            user.setIfActive(true);
            user.setGroupId(1);
            userRepo.save(user);
            return success;
    	}

        return "Error: user with this email already exists.";
    }   
  

	@PostMapping(value = "/login", produces = "application/json")
    public @ResponseBody String login(@RequestBody LoginService login) {
    	if(userService.validateUser(login) == null) {
    		return notFound;
    	}
    	return success;
    }
    
    @PostMapping(value = "/role/{id}/{role}")
    Role userRole(@PathVariable Role role, @PathVariable int id) {
//    	if(role == null) {
//    		return failure;
//    	}
//    	if(id == 0) {
//    		return failure;
//    	}
    	User user = userRepo.findById(id);
    	user.setRole(role);
    	return user.getRole();
    }
    
    //delete user
    @DeleteMapping(path = "/user/{id}")
    String deleteUser(@PathVariable int id) {
    	userRepo.deleteById(id);
    	return success;
    }
    
    @PostMapping(path = "/groupAdd")
    String groupAdd(@RequestBody String email, String password) {
    	User user = userRepo.findByEmailAndPassword(email, password);
    	user.setGroupId(1);
    	return success;
    }
    
    @PostMapping(path = "/groupRemove")
    String groupRemove(@RequestBody String email, String password) {
    	User user = userRepo.findByEmailAndPassword(email, password);
    	user.setGroupId(0);
    	return success;
    }
    
}
