package net.oscar.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.oscar.app.model.News;

/**
 * INTERFACE to manage the data of the 'News' called for NewsController
 * It's implemented in NewsServiceImplement
 * we must declare all methods of the implementation of this Interface
 * @author Oscar Germade
 *
 */
public interface InterfaceNewsService {
	
	void insert(News news);	// insert a News object in the DB
	List<News> getAllNews(); // find all News object
	Page<News> getAlNewsWithPagination(Pageable page); // return all News in pages
	News searchById(int idNews); // search and return the object News with the specified id
	void delete(int idNews); // delete a News object by its id
	List<News> getLast3Active(); // get the last 3 active News depending of the date
}
