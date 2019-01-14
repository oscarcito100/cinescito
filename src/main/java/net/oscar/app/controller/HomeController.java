package net.oscar.app.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import net.oscar.app.model.Banner;
import net.oscar.app.model.Films;
import net.oscar.app.model.News;
import net.oscar.app.model.Time;
import net.oscar.app.service.InterfaceBannerService;
import net.oscar.app.service.InterfaceFilmsService;
import net.oscar.app.service.InterfaceNewsService;
import net.oscar.app.service.InterfaceTime;
import net.oscar.app.util.Utils;

// creating a controller for this class where all the methods must return a view
// also it creates a BEAN of type controller
@Controller
public class HomeController {
	
	// create an instance of the interface to use a Service class
	@Autowired
	private InterfaceFilmsService serviceFilms;
	
	@Autowired
	private InterfaceBannerService serviceBanner;
	
	@Autowired
	private InterfaceTime serviceTimes;
	
	
	private InterfaceNewsService serviceNews;
	
	// creating an SimpleDateFormat Object to give a specific date format to the date
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	
	/**
	 * This method is run when the url '/search' is called
	 * @param model allow to aggregate Objects to the Model
	 * @param date
	 * @return
	 */
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public String search(Model model, @RequestParam("dateSelected") String date) {
		
		// add the list of the days to show in the <select> to choose a date from the current day until +4
		List<String> listOfDays = Utils.getNextDays(4);
				
		// add the list of movies that are created in the service FilmsServiceImplement
		List<Films> listOfFilms = serviceFilms.getAllFilms();	
				
		// add the list of the elements in the Banner created in the service BannerServiceImplement
		List<Banner> listOfBanner = serviceBanner.getAllBanners();
				
		// sending the data of the list to the view "home" from the controller using the attributes 'films'... in the view
		// to be able to use the objects of the model
		model.addAttribute("films", listOfFilms);
		model.addAttribute("currentDate", date); // return the date selected
		model.addAttribute("dates", listOfDays);
		model.addAttribute("banners", listOfBanner);
		
		// the view that is return: home.jsp
		return "home";
	}
	
	/**
	 * When the main page is called, we show the list of days, movies, news and the banner
	 * @param model 	allow to aggregate Objects to the Model
	 * @return 			the view of home.jsp
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showMain(Model model) {
		
		// add the list of the days to show in the <select> to choose a date from the current day until +4
		List<String> listOfDays = Utils.getNextDays(4);
		
		// add the list of movies that are created in the service FilmsServiceImplement
		List<Films> listOfFilms = serviceFilms.getAllFilms();
		
		// add the list of the elements in the Banner created in the service BannerServiceImplement
		List<Banner> listOfBanner = serviceBanner.getAllBanners();
		
		List<News> listOfTheLast3ActiveNews = serviceNews.getLast3Active();
		
		// sending the data of the list to the view "home" from the controller using the attributes 'films'... in the view
		// to be able to use the objects of the model
		model.addAttribute("films", listOfFilms);
		model.addAttribute("currentDate", dateFormat.format(new Date()));
		model.addAttribute("dates", listOfDays);
		model.addAttribute("banners", listOfBanner);
		model.addAttribute("last3News", listOfTheLast3ActiveNews);
		
		/* Tarea pendiente: Buscar los horarios en la base de datos */
		
		// the view that is return: home.jsp
		return "home";
	}
	
	/**
	 * 
	 * @param model allow to aggregate Objects to the Model
	 * @param idFilm
	 * @param date
	 * @return
	 */
	@RequestMapping(value="/detail/{id}/{date}", method = RequestMethod.GET)
	public String showDetails(Model model, @PathVariable("id") int idFilm, @PathVariable("date") Date date) {
	//@RequestMapping(value="/detail", method = RequestMethod.GET) // parameters of type GET
	//public String showDetails(Model model, @RequestParam("idMovie") int idFilm, @RequestParam("date") String date) {
		
		// 1. get the list with the times for that specific movie and date
		List<Time> timesList = serviceTimes.searchByIdFilmAndDate(idFilm, date);
		
		// 2. sending the data from the controller to the view using the attribute 'film' in the view
		// to be able to use the objects of the model
		model.addAttribute("times", timesList);
		model.addAttribute("dateSelected", dateFormat.format(date));
		model.addAttribute("film", serviceFilms.searchById(idFilm));
		
		// the view that is return: detail.jsp
		return "detail";
	}
	
	
	
	/**
	 * Convert the parameter String date to a type Date
	 * @InitBinder	does not return any value and are declared as void with the paramater WebDataBinder
	 * to avoid errors of conversion the dates from String to Date
	 * @param binder
	 */
	@InitBinder 
	public void initBinder(WebDataBinder binder) { 
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy"); 
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false)); 
	}
}
