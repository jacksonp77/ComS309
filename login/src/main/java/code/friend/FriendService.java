package code.friend;

import java.util.Map;
import org.springframework.http.ResponseEntity;

public interface FriendService {

	ResponseEntity<Map<String, Object>> addUserFriends(UserFriendsRequestEntity userFriendsRequestEntity);

	ResponseEntity<Map<String, Object>> getUserFriendsList(UserFriendsListRequestEntity userFriendsListRequestEntity);
}