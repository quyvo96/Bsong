package models;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import models.Category;
import models.Song;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Adver {
	
	private int id;

	private String name;
	
	private String picture;
	
	private String web;
	
	private int count;

	public Adver(String name, String picture, String web, int count) {
		super();
		this.name = name;
		this.picture = picture;
		this.web = web;
		this.count = count;
	}

	public Adver(String name, String picture, String web) {
		super();
		this.name = name;
		this.picture = picture;
		this.web = web;
	}

	public Adver(int id, String name, String picture, String web) {
		super();
		this.id = id;
		this.name = name;
		this.picture = picture;
		this.web = web;
	}
	

	
}
