package net.oscar.app.service;

import java.util.List;

import net.oscar.app.model.Banner;

/**
 * INTERFACE to manage the items of the Banner called for BannersController
 * It's implemented in BannerServiceImplement
 * 
 * @author Oscar Germade
 *
 */
public interface InterfaceBannerService {
	// 1. we must declare all methods of the implementation of this Interface
	void insert(Banner banner); // insert a film object in the List<Films>	
	List<Banner> getAllBanners(); // returns a List of type Banner

}
