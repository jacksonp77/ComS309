package code.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.springframework.util.CollectionUtils;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

    @Column(nullable = false, unique = true)
    private String email;

	@ManyToMany
	@JoinTable(name = "user_friends", joinColumns = @JoinColumn(name = "userId") , inverseJoinColumns = @JoinColumn(name = "friendId") )
	private Set<User> userFriends;
	
	@ManyToMany
	@JoinTable(name = "removed_users", joinColumns = @JoinColumn(name = "removedUserId") , inverseJoinColumns = @JoinColumn(name = "targetUserId") )
	private Set<User> removedUsers;
	

	
    @Column(nullable = false, length = 64)
    private String password;
     
    @Column(name = "username", nullable = false, unique = true, length = 20)
    private String username;
    
    private int groupId;

    
//    @Column(name = "role", nullable = false, length = 10)
    private Role role;
    
    private boolean ifActive;
    
    public User(String name, String emailId) {
        this.username = name;
        this.email = emailId;
        this.ifActive = true;
    }

    public User() {
    }

    // =============================== Getters and Setters for each field ================================== //

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
    
 int getGroupId() {
    	return groupId;

    }
    
    public void setGroupId(int groupId){
        this.groupId = groupId;
    }


    public String getUserame(){
        return username;
    }

    public void setUsername(String name){
        this.username = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }
    
    public void setPassword(String password) {
    	this.password = password;
    }
    
    public void setRole(Role role) {
    	this.role = role;
    }
    
    public Role getRole() {
    	return role;
    }
    
    public String getPassword() {
    	return password;
    }

    public boolean getIsActive(){
        return ifActive;
    }

    public void setIfActive(boolean ifActive){
        this.ifActive = ifActive;
    }    
    
	public Set<User> getUserFriends() {
		return userFriends;
	}

	public void setUserFriends(Set<User> userFriends) {
		this.userFriends = userFriends;
	}
	
	public void addUserFriends(User user) {
		if (CollectionUtils.isEmpty(this.userFriends)) {
			this.userFriends = new HashSet<>();
		}
		this.userFriends.add(user);
	}
	
	public void removeUser(User user) {
		if(CollectionUtils.isEmpty(this.removedUsers)) {
			this.removedUsers = new HashSet<>();
		}
		this.removedUsers.add(user);
	}
    
}
