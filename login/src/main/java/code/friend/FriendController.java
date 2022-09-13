package code.friend;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FriendController {

    @Autowired
    private FriendService friendService;
    
    @Autowired
    private RemoveService removeService;
    
    @GetMapping("/friend")
    public String viewHomePage() {
    	return "friend path works";
    }
    
	@RequestMapping(path = "/userFriendRequest")
	public ResponseEntity<Map<String, Object>> userFriendRequest(@RequestBody UserFriendsRequestEntity userFriendsRequestEntity) {
		return this.friendService.addUserFriends(userFriendsRequestEntity);
	}

	@RequestMapping(path = "/userFriendList")
	public ResponseEntity<Map<String, Object>> getUserFriendList(@RequestBody UserFriendsListRequestEntity userFriendsListRequestEntity) {
		return this.friendService.getUserFriendsList(userFriendsListRequestEntity);
	}
	
	@RequestMapping(path = "/userFriendRemoval")
	public ResponseEntity<Map<String, Object>> userFriendRemoval(@RequestBody UserFriendRemoval userFriendRemoval) {
		return this.removeService.removeUser(userFriendRemoval);
	}
	
	
}
