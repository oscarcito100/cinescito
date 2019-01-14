package net.oscar.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.WhereJoinTable;

/**
 * Holds a specific time data for a movie
 * @Entiy	creates an Entity Class 
 * @Table	bind this Entity class with a specific table naming its name
 * @author Oscar
 *
 */
@Entity
@Table(name="horarios")
public class Time {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="fecha")
	private Date date;
	
	@Column(name="hora")
	private String time; // HH:mm
	
	@Column(name="sala", length=100, nullable=false)
	private String room;
	
	@Column(name="precio")
	private double price;
	
	@ManyToOne // many times for one film
	@JoinColumn(name="idPelicula") // column's name of the foreign key in 'peliculas' table
	private Films film; // it is a compound Object and also needs getters and setters
	
	//Getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Films getFilm() {
		return film;
	}
	public void setFilm(Films film) {
		this.film = film;
	}
	
	// toString to show all the fields
	@Override
	public String toString() {
		return "Time [id=" + id + ", date=" + date + ", time=" + time + ", room=" + room + ", price=" + price
				+ ", film=" + film + "]";
	}

	
	
}
