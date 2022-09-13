package code.friend;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface RemoveService {
	ResponseEntity<Map<String, Object>> removeUser(UserFriendRemoval userFriendRemoval);
}
