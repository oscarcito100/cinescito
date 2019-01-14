package net.oscar.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.oscar.app.model.Films;

/**
 * INTERFACE to manage the data of the Films called for FilmsController
 * It's implemented in FilmsServiceImplement
 * We must declare all methods of the implementation of this Interface
 * @author Oscar Germade
 *
 */
public interface InterfaceFilmsService {
	
	void insert(Films film); //insert a film object in the List<Films>
	List<Films> getAllFilms(); // returns a List of type Films with the data of all films
	Page<Films> getAllWithPagination(Pageable page); // returns the results of all films in Pages
	Films searchById(int idFilm); // returns an Object of type Film	with the data of the film with that specific 'id'
	List<String> getGender();// return a list of all movie's genders like action, adventure... 
	void delete(int idFilm); // delete the film object with the id specified
}
