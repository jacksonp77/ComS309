package code.friend;

import javax.persistence.*;

import code.user.User;

@Entity
@Table(name = "user_friends")
public class Friend {

	@Id
	@Column
	private int friendId;

	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private User user;

	public int getFriendId() {
		return friendId;
	}

	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
