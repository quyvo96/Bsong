package models;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



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
public class Song {
	
	private int id;


	private String name;
	
	private String preview_text;
	
	private String detail_text;
	
	private Timestamp date_create;
	
	private String 	picture;
	
	private int counter;
	
	private Category cat;
	
	public Song(String name, String preview_text, String detail_text, String picture, Category cat) {
		super();
		this.name = name;
		this.preview_text = preview_text;
		this.detail_text = detail_text;
		this.picture = picture;
		this.cat = cat;
	}

	public Song(String name, String preview_text, String detail_text) {
		super();
		this.name = name;
		this.preview_text = preview_text;
		this.detail_text = detail_text;
	}

	public Song(int id, String name, String preview_text, String detail_text, String picture, Category cat) {
		super();
		this.id = id;
		this.name = name;
		this.preview_text = preview_text;
		this.detail_text = detail_text;
		this.picture = picture;
		this.cat = cat;
	}

	public Song(int id, String name, String preview_text, String detail_text, Timestamp date_create, String picture,
			int counter) {
		super();
		this.id = id;
		this.name = name;
		this.preview_text = preview_text;
		this.detail_text = detail_text;
		this.date_create = date_create;
		this.picture = picture;
		this.counter = counter;
	}

	public Song(String name, Category cat) {
		super();
		this.name = name;
		this.cat = cat;
	}
	
	

}
