package net.oscar.app.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.oscar.app.model.Films;
import net.oscar.app.repository.FilmsRepository;

/**
 * This Service class implements the Interface InterfaceFilmsService
 * 
 * @Service in order to use their methods in the controller by means of creating a new instance of this class
 */ 
@Service
public class FilmsServiceJPA implements InterfaceFilmsService{

	// declaring an instance of the Repository to be able to use it
	@Autowired // to allow dependency injection
	private FilmsRepository filmsRepo;
	
	/**
	 * Create and Save a new Films Object
	 */
	@Override
	public void insert(Films film) {
		filmsRepo.save(film);
	}
	
	/**
	 * return a list with all films
	 */
	@Override
	public List<Films> getAllFilms() {
		return filmsRepo.findAll();
	}
	
	
	
	/**
	 * return an Optional of a film given an specific id
	 */
	@Override
	public Films searchById(int idFilm) {
		// 1- Search for the 'film' using findById() creating an Optional class of the Films Object
		// bc first it tell us if the element exist or not
		Optional<Films> optional = filmsRepo.findById(idFilm);
			
		// 2- Return the result if the element exists or send a message if it isn't
		if(optional.isPresent()) {
			return optional.get();
		} 
		
		return null;
	}

	@Override
	public List<String> getGender() {
		List<String> movieGenders = new LinkedList<>();
		
		movieGenders.add("Action");
		movieGenders.add("Adventure");
		movieGenders.add("Classic");
		movieGenders.add("Comedy");
		movieGenders.add("Drama");
		movieGenders.add("Terror");
		movieGenders.add("Kids");
		movieGenders.add("Action and Adventure");
		movieGenders.add("Romantic");
		movieGenders.add("Fiction");
		
		return movieGenders;
	}

	/**
	 * Delete the object film given by its id
	 */
	@Override
	public void delete(int idFilm) {
		filmsRepo.deleteById(idFilm);		
	}

	/**
	 * return the list of the movies in pages
	 */
	@Override
	public Page<Films> getAllWithPagination(Pageable page) {
		return filmsRepo.findAll(page);
	}

}
