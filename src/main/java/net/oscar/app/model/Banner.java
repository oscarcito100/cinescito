/**
 * Banner class entity that holds the data of every image in the banner in home.jsp
 */
package net.oscar.app.model;

import java.util.Date;

public class Banner {
	private int id;
	private String title;
	private Date publishedDate; // when the banner was created
	private String fileName; // name of the file that save the image file
	private String status;

	/**
	 * Constructor where initialise by default the value of puclishedDate and status
	 */
	public Banner() {
		this.publishedDate = new Date(); // current date when the the banner object is created
		this.status = "Active";
	}

	public int getId() {
		return id;
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

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Banner [id=" + id + ", title=" + title + ", publishedDate=" + publishedDate + ", fileName=" + fileName
				+ ", status=" + status + "]";
	}

}
