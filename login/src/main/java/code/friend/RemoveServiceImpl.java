package code.friend;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import code.user.User;

@Service
public class RemoveServiceImpl implements RemoveService {
	@Autowired
	private FriendDAO friendDAO;

	@Override
	public ResponseEntity<Map<String, Object>> removeUser(UserFriendRemoval userFriendRemoval) {

		Map<String, Object> result = new HashMap<String, Object>();

		if (userFriendRemoval == null) {
			result.put("Error : ", "Invalid request");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
		}

		if (userFriendRemoval.getRequestor() == null || userFriendRemoval.getTarget() == null) {
			result.put("Error : ", "Requester or Target can not be empty");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
		}
		
		String email1 = userFriendRemoval.getRequestor();
		String email2 = userFriendRemoval.getTarget();

		if (email1.equals(email2)) {
			result.put("Info : ", "Cannot Remove , if users are same");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
		}
		
		User user1 = this.friendDAO.findByEmail(email1);
		User user2 = this.friendDAO.findByEmail(email2);

		user1.removeUser(user2);
		this.friendDAO.save(user1);

		result.put("Success", true);

		return new ResponseEntity<Map<String, Object>>(result, HttpStatus.OK);
	}
}
