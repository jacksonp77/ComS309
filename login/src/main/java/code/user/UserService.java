package code.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;

    public User validateUser(LoginService loginServ){
        return userRepository.findByEmailAndPassword(loginServ.getEmail(), loginServ.getPassword());
    }

	
}
