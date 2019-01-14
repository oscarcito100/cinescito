package net.oscar.app.model;

import java.util.Arrays;
import java.util.List;

public class Contact {
	// instance variables
	private int id;
	private String name;
	private String email;
	private String[] favouriteGenders;
	private int rating; // bind with the radio buttons that holds the user experience with the website because its values are numeric from 1 to 5
	private List<String> notifications; // but could be an array of Strings too as with String[] favouriteGenders
	private String comment;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String[] getFavouriteGenders() {
		return favouriteGenders;
	}
	public void setFavouriteGenders(String[] favouriteGenders) {
		this.favouriteGenders = favouriteGenders;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	public List<String> getNotifications() {
		return notifications;
	}
	public void setNotifications(List<String> notifications) {
		this.notifications = notifications;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "Contact [id=" + id + ", name=" + name + ", email=" + email + ", favouriteGenders="
				+ Arrays.toString(favouriteGenders) + ", rating=" + rating + ", notifications=" + notifications
				+ ", comment=" + comment + "]";
	}

	
	
}
