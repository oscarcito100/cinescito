package net.oscar.app.service;

import net.oscar.app.model.Detail;

/**
 * INTERFACE to manage the data of the Details Model Class called from some controllers
 * It's implemented in DetailsServiceJPA
 * 
 * don't have method to update becasue it is done when we update its film
 * @author Oscar Germade
 *
 */
public interface InterfaceDetails {
	// 1. we must declare all methods of the implementation of this Interface
	void insert(Detail detail); //insert a detail object in the table Detail
	void delete(int idDetail); // delete the detail object with the id specified
}
