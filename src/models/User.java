package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
	
	private int id;
	
	private String userName;
	
	private String password;
	
	private String fullName;
	
	private UserRole userRole;

	public User(String userName, String password, String fullName) {
		super();
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
	}

	public User(String userName, String password, String fullName, UserRole userRole) {
		super();
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
		this.userRole = userRole;
	}

	public User(int id, String userName, String password, String fullName) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
	}
	
	
	
	

}
