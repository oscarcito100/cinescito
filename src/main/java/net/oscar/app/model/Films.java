/**
 * Film class that represents the data of a film
 */
package net.oscar.app.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @Entiy	creates an Entity Class 
 * @Table	bind this Entity class with a specific table naming its name
 * @author Oscar
 *
 */
@Entity
@Table(name="peliculas")
public class Films {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) // is autoincrement in mysql
	private int id;
	
	@Column(name="titulo", length=150, nullable=false)
	private String title;
	
	@Column(name="duracion")
	private int duration;
	
	@Column(name="clasificacion")
	private String classification;
	
	@Column(name="genero", length=45, nullable=false)
	private String gender;
	
	@Column(name="imagen", length=200, nullable=false)
	private String image = "powerRangers.jpg";
	
	@Column(name="fechaEstreno")
	private Date premierDate;
	
	@Column(name="estatus")
	private String status = "Active"; // value by default
	
	//@Transient // it is ignored when we create an Object of Films
	@OneToOne
	@JoinColumn(name="idDetalle") // column's name in the table 'peliculas' in the DB
	private Detail detail; // it is a compound Object and also needs getters and setters
	
	// configurate an inverse relationship between Film and Time entities
	@OneToMany(mappedBy="film", fetch=FetchType.EAGER) // one film has many times
	private List<Time> time; 
	// getters and setters
	
	public List<Time> getTime() {
		return time;
	}
	public void setTime(List<Time> time) {
		this.time = time;
	}
	public int getId() {
		return id;
	}
	public Detail getDetail() {
		return detail;
	}
	public void setDetail(Detail detail) {
		this.detail = detail;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Date getPremierDate() {
		return premierDate;
	}
	public void setPremierDate(Date premierDate) {
		this.premierDate = premierDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * printing all the fields in one method
	 */
	@Override
	public String toString() {
		return "Films [id=" + id + ", title=" + title + ", duration=" + duration + ", classification=" + classification
				+ ", gender=" + gender + ", image=" + image + ", premierDate=" + premierDate + ", status=" + status
				+ ", detail=" + detail + "]";
	}
}
