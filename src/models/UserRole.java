package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRole {
	
	private int id;
	
	private String role;

	public UserRole(int id) {
		super();
		this.id = id;
	}
	
}
