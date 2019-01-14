package net.oscar.app.service;

import java.text.ParseException;

import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.oscar.app.model.Films;

/**
 * this Service class implements the Interface InterfaceFilmsService
 */
// add annotation @Service in order to use their methods in the controller by means of creating a new instance of this class
//@Service
public class FilmsServiceImplement implements InterfaceFilmsService{
	// make the list of the Films Object global in order to be able to use in in all the methods of this class
	private List<Films> listOfFilms = null;
	
	/**
	 * create linked list with objects of type Films in the Constructor of the class
	 */
	public FilmsServiceImplement() {
		// System.out.println("Creando instancia de la clase FilmsServiceImplement");
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");	
		
		try {
			// instantiate the List
			listOfFilms = new LinkedList<>();
			
			// construct some static objects of the class Films
			Films film1 = new Films();
			film1.setId(1);
			film1.setTitle("Power Rangers");
			film1.setDuration(120);
			film1.setClassification("B");
			film1.setGender("Adventure");
			film1.setPremierDate(formatter.parse("11-06-2018"));
			// image and status are by default
			
			Films film2 = new Films();
			film2.setId(2);
			film2.setTitle("La Bella y la Bestia");
			film2.setDuration(132);
			film2.setClassification("A");
			film2.setGender("Infantil");
			film2.setPremierDate(formatter.parse("12-06-2018"));
			film2.setImage("bella.jpg");
			
			Films film3 = new Films();
			film3.setId(3);
			film3.setTitle("Kong: La isla calavera");
			film3.setDuration(118);
			film3.setClassification("B");
			film3.setGender("Accion");
			film3.setPremierDate(formatter.parse("13-06-2018"));
			film3.setImage("kong.jpg");
			film3.setStatus("Inactive");
			
			// adding the Film objects into the List
			listOfFilms.add(film1);
			listOfFilms.add(film2);
			listOfFilms.add(film3);
			

			
		}catch(ParseException pe) {
			System.out.println("Error: " + pe.getMessage());
		}
	}
	
	/**
	 * returns to the controller a List with all the films
	 */
	@Override
	public List<Films> getAllFilms() {
		return listOfFilms;
	}

	/**
	 * Search for the Film with the given 'id' as a parameter. If it exist, return all the attributes
	 * of this Film, if not, return null
	 */
	@Override
	public Films searchById(int idFilm) {
		// loop through the List with the films and when its id == idFilm, we return this element
		for(Films film : listOfFilms) {
			if(film.getId() == idFilm)
				return film;
		}
		// if the idFilm we have to search, does not exist, we return null
		return null;
	}
	
	/**
	 * insert in the list a new Film object
	 */
	@Override
	public void insert(Films film) {
		listOfFilms.add(film);
		
	}
	
	/**
	 * return a List with the name of all the genders that a movie can have
	 * Better if we get them from a DB
	 */
	@Override
	public List<String> getGender(){
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

	@Override
	public void delete(int idFilm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page<Films> getAllWithPagination(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}
}
