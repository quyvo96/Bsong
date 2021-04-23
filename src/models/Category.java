package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@AllArgsConstructor
@Data
/*
 * @Data ==
 * 	@Getter
	@Setter
	@ToString
 * 
 * */
public class Category {
	


	private int id;
	
	private String name;

	public Category(String name) {
		super();
		this.name = name;
	}
	public Category(int id) {
		super();
		this.id = id;
	}
	
}
