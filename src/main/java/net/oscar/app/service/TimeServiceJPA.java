package net.oscar.app.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.oscar.app.model.Time;
import net.oscar.app.repository.TimesRepository;

/**
 * This Service class implements the Interface InterfaceTime
 * 
 * @Service in order to use their methods in the controller by means of creating a new instance of this class
 */ 
@Service
public class TimeServiceJPA implements InterfaceTime{
	
	// declaring an instance of the Repository to be able to use it
	@Autowired // to allow dependency injection
	private TimesRepository timesRepo;
	
	/**
	 * Return the list of the times of an specific Film and date
	 * This find method must be created in the Repository bc it does not come by default
	 */
	@Override
	public List<Time> searchByIdFilmAndDate(int idFilm, Date date) {
		return timesRepo.findByFilm_IdAndDateOrderByTime(idFilm, date);
	}

}
