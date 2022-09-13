package code.friend;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import code.user.User;

@Entity
@Table(name = "removed_friends")

public class RemoveFriend {



		@Id
		@Column
		private int targetUserId;

		@ManyToOne
		@JoinColumn(name = "removeId", nullable = false)
		private User user;

		public int getTargetUserId() {
			return targetUserId;
		}

		public void setTargetUserId(int targetUserId) {
			this.targetUserId = targetUserId;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

	}

