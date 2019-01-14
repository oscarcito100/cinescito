package net.oscar.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Compound Class that is called for the Class Films
 * @Entiy	creates an Entity Class 
 * @Table	bind this Entity class with a specific table naming its name
 * @author Oscar
 *
 */
@Entity
@Table(name="detalles")
public class Detail {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
    @Column(name="director", length=100, nullable=false)	
	private String director;
	
    @Column(name="actores", length=255, nullable=false)
    private String actors;
	
    private String sinopsis; 
	
    @Column(name="trailer", length=255, nullable=false)
    private String trailer; // url of the trailer in youtube
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getActors() {
		return actors;
	}
	public void setActors(String actors) {
		this.actors = actors;
	}
	public String getSinopsis() {
		return sinopsis;
	}
	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}
	public String getTrailer() {
		return trailer;
	}
	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}
	@Override
	public String toString() {
		return "Detail [id=" + id + ", director=" + director + ", actors=" + actors + ", sinopsis=" + sinopsis
				+ ", trailer=" + trailer + "]";
	}

	
}
