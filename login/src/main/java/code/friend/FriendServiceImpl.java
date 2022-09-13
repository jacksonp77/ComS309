package code.friend;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import code.user.User;

@Service
public class FriendServiceImpl implements FriendService {

	@Autowired
	private FriendDAO friendDAO;

	private User saveIfNotExist(String email) {

		User existingUser = this.friendDAO.findByEmail(email);
		if (existingUser == null) {
			existingUser = new User();
			existingUser.setEmail(email);
			return this.friendDAO.save(existingUser);
		} else {
			return existingUser;
		}

	}

	@Override
	public ResponseEntity<Map<String, Object>> addUserFriends(UserFriendsRequestEntity userFriendsRequestEntity) {

		Map<String, Object> result = new HashMap<String, Object>();

		if (userFriendsRequestEntity == null) {
			result.put("Error : ", "Invalid request");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
		}
		if (userFriendsRequestEntity.getFriends().size() != 2) {
			result.put("Info : ", "Please provide 2 emails to make them friends");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
		}

		String email1 = userFriendsRequestEntity.getFriends().get(0);
		String email2 = userFriendsRequestEntity.getFriends().get(1);

		if (email1.equals(email2)) {
			result.put("Info : ", "Cannot make friends, if users are same");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
		}

		User user1 = null;
		User user2 = null;
		user1 = this.saveIfNotExist(email1);
		user2 = this.saveIfNotExist(email2);

		if (user1.getUserFriends().contains(user2)) {
			result.put("Info : ", "Can't add, they are already friends");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		}

		user1.addUserFriends(user2);
		this.friendDAO.save(user1);
		result.put("Success", true);

		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Map<String, Object>> getUserFriendsList(UserFriendsListRequestEntity userFriendsListRequestEntity) {

		Map<String, Object> result = new HashMap<String, Object>();

		if (userFriendsListRequestEntity == null) {
			result.put("Error : ", "Invalid request");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
		}

		User user = this.friendDAO.findByEmail(userFriendsListRequestEntity.getEmail());
		if(user == null) {
			result.put("Error : ", "user not found");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
		}
		List<String> friendList = user.getUserFriends().stream().map(User::getEmail).collect(Collectors.toList());
		
		result.put("success", true);
		result.put("friends", friendList);
		result.put("count", friendList.size());

		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);

	}

}
