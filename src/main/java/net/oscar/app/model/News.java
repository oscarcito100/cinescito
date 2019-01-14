/**
 * News entity class that represents the data of News
 */
package net.oscar.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @Entiy	creates an Entity Class 
 * @Table	bind this Entity class with a specific table naming its name
 * @author Oscar
 *
 */
@Entity
@Table(name="noticias")
public class News {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) // is autoincrement in mysql
	private int id;
	
	@Column(name="titulo", length=150, nullable=false)
	private String titulo;
	
	@Column(name="fecha")
	private Date datePublished;
	
	@Column(name="detalle", length=150, nullable=false)
	private String detail;
	
	@Column(name="estatus")
	private String status;
	
		
	/**
	 * A constructor with two attributes by default
	 */
	public News() {
		this.status = "Activa";
		this.datePublished = new Date();		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDatePublished() {
		return datePublished;
	}

	public void setDatePublished(Date datePublished) {
		this.datePublished = datePublished;
	}

	@Override
	public String toString() {
		return "News [id=" + id + ", titulo=" + titulo + ", datePublished=" + datePublished + ", detail=" + detail
				+ ", status=" + status + "]";
	}


	
	
}
