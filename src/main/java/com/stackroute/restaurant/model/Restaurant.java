package com.stackroute.restaurant.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "restaurants")
public class Restaurant {
	@Id
	int id;
	String name;
	String location;
	String cuisines;
	String image;
	String review;

		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCuisines() {
		return cuisines;
	}

	public void setCuisines(String cuisines) {
		this.cuisines = cuisines;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Restaurant(int id, String name, String location, String cuisines, String image, String review) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.cuisines = cuisines;
		this.image = image;
		this.review = review;
	}

	public Restaurant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void thenReturn(boolean b) {
		// TODO Auto-generated method stub
		
	}

	
	
}
