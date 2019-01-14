package net.oscar.app.service;

import java.util.Date;
import java.util.List;

import net.oscar.app.model.Time;

/**
 * INTERFACE to manage the Time of the movies called from HomeController
 * It's implemented in TimeServiceJPA
 * We must declare all methods of the implementation of this Interface
 * @author Oscar Germade
 *
 */
public interface InterfaceTime {
	// 1. return list of all the times of a movie for an specified date
	List<Time> searchByIdFilmAndDate(int idFilm, Date date);
}
