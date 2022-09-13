package code.friend;

import org.springframework.data.repository.CrudRepository;

import code.user.User;

public interface FriendDAO extends CrudRepository<User, Long> {

	User findByEmail(String email);
}
