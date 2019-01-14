package net.oscar.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.oscar.app.model.News;
import net.oscar.app.repository.NewsRepository;

/**
 * This Service class implements the Interface InterfaceNewsService
 * 
 * @Service in order to use their methods in the controller by means of creating a new instance of this class
 */ 
@Service
public class NewsServiceJPA implements InterfaceNewsService{

	// declaring an instance of the Repository to be able to use it
	@Autowired // to allow dependency injection
	private NewsRepository newsRepo;
	
	/**
	 * Create and Save a new News Object
	 */
	@Override
	public void insert(News news) {
		newsRepo.save(news);		
	}

	/**
	 * Delete the specified News given by its id
	 */
	@Override
	public void delete(int idNews) {
		newsRepo.deleteById(idNews);		
	}

	@Override
	public List<News> getAllNews() {
		return newsRepo.findAll();
	}

	
	/**
	 * return a Page of News
	 */
	@Override
	public Page<News> getAlNewsWithPagination(Pageable page) {		
		return newsRepo.findAll(page);
	}

	
	/**
	 * return an Optional of a News given an specific id
	 */
	@Override
	public News searchById(int idNews) {
		// 1- Search for the 'film' using findById() creating an Optional class of the Films Object
		// bc first it tell us if the element exist or not
		Optional<News> optional = newsRepo.findById(idNews);
		
		// 2- Return the result if the element exists or send a message if it isn't
		if(optional.isPresent()) {
			return optional.get();
		}
		
		return null;
	}

	@Override
	public List<News> getLast3Active() {
		List<News> last3ActiveNews = newsRepo.findTop3StatusOrderByDatePublishedDesc("activa");
		System.out.println(last3ActiveNews);
		return last3ActiveNews;
	}	
}
