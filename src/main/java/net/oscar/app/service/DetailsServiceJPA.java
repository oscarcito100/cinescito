package net.oscar.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.oscar.app.model.Detail;
import net.oscar.app.repository.DetailsRepository;

/**
 * This Service class implements the Interface InterfaceDetails
 * 
 * @Service in order to use their methods in the controller by means of creating a new instance of this class
 */
@Service
public class DetailsServiceJPA implements InterfaceDetails{
	
	// creating an instance of the Repository to be able to use it
	@Autowired
	private DetailsRepository detailsRepo;
	
	@Override
	public void insert(Detail detail) {
		detailsRepo.save(detail);
	}

	@Override
	public void delete(int idDetail) {
		detailsRepo.deleteById(idDetail);		
	}

}
