package code.game;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import code.user.Role;
import code.user.User;
import code.user.UserRepository;

@RestController
public class GameController extends Game {
	
    private String success = "{\"message\":\"success\"}";
    private String failure = "{\"message\":\"failure\"}";
    
    @Autowired
    private GameRepository gameRepo;
    
    @Autowired
    private UserRepository userRepo;
    
    @GetMapping(path = "/game")
    public String gameHome() {
    	return "game home page";
    }
    
    @GetMapping(path = "/listgames")
    List<Game> listGames(){
        return gameRepo.findAll();
    }

    @PostMapping(path = "/addgame")
    String addGame(@RequestBody Game game) {
    	if(game == null) {
    		return failure;
    	}
    	game.setGameActive(true);
    	gameRepo.save(game);
    	return success;
    }
    
    boolean adminCheck(int id) {
    	User user = userRepo.findById(id);
    	if(user.getRole() == Role.ADMIN) {
    		return true;
    	}
    	return false;
    }
    
    @PostMapping(path = "/rotateout/{id}/{userId}")
  	List<Game> rotateOut(@PathVariable int id, @PathVariable int userId){
    	if(adminCheck(userId) == true) {
        	Game game = gameRepo.findById(id);
        	game.setGameActive(false);
    	}
  		return listGames();
    }
    
    @PostMapping(path = "/rotatein/{id}/{userId}")
  	List<Game> rotateIn(@PathVariable int id, @PathVariable int userId){
    	if(adminCheck(userId) == true) {
        	Game game = gameRepo.findById(id);
        	game.setGameActive(true);
    	}
  		return listGames();
    }
}
