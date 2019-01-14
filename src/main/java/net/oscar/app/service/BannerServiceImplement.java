/**
 * implements the Interface BannerService
 */
package net.oscar.app.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import net.oscar.app.model.Banner;

//add annotation @Service in order to use their methods in the controller by means of creating a new instance of this class
@Service
public class BannerServiceImplement implements InterfaceBannerService{

	private List<Banner> listOfBanners = null; // list to hold every element of a banner
	
	/**
	 * create linked list with objects of type Banner
	 */
	public BannerServiceImplement() {		
		// instantiate the List
		listOfBanners = new LinkedList<>();
		
		// construct some static objects of the class Banner
		// premierDate and status are set by default
		Banner banner1 = new Banner();
		banner1.setId(1);
		banner1.setTitle("Kong La Isla Calavera");
		banner1.setFileName("slide1.jpg");
		
		Banner banner2 = new Banner();
		banner2.setId(2);
		banner2.setTitle("La bella y la bestia");
		banner2.setFileName("slide2.jpg");
		
		Banner banner3 = new Banner();
		banner3.setId(3);
		banner3.setTitle("Rapidos y Furiosos 8");
		banner3.setFileName("slide3.jpg");
		
		Banner banner4 = new Banner();
		banner4.setId(4);
		banner4.setTitle("Estreno en Agosto - Jefe en Pañales");
		banner4.setFileName("slide4.jpg");
		banner4.setStatus("Inactive");
		
		// Add the objects to the list
		listOfBanners.add(banner1);
		listOfBanners.add(banner2);
		listOfBanners.add(banner3);
		listOfBanners.add(banner4);
	}
	
	/**
	 * add a object Banner to the List of Banner Objects
	 */
	@Override
	public void insert(Banner banner) {
		listOfBanners.add(banner);	
	}
	
	/**
	 * return the List with all Banners
	 */
	@Override
	public List<Banner> getAllBanners() {
		return listOfBanners;
	}

}
