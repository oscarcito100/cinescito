package net.oscar.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.oscar.app.model.Films;
import net.oscar.app.model.Time;
import net.oscar.app.service.InterfaceFilmsService;

/**
 * This controller process the creation of a time for a film
 * Method create() a new time for the chosen film
 * Method save() a new time for the chosen film in the database
 * Method initBinder() that Convert the parameter String date to a type Date
 * @author Oscar Germade
 * 
 * Put @RequestMapping("/time") to a level of the class, so the rest of the urls belong to this one
 * e.g. /time/create
 *
 */
@Controller
@RequestMapping("/time")
public class TimeController {
    
	//create an instance of the interface InterfaceNewsService to use a Service class
	@Autowired
	private InterfaceFilmsService serviceFilms;
	
	/**
	 * Show the form to create a new time for a movie
	 * @return	the page where is the form to add a time to a movie
	 */
	@GetMapping("/create")
	public String create(@ModelAttribute Time time, Model model) {
		// getting the list of all movies
		List<Films> listOfFilms = serviceFilms.getAllFilms();
		// sending the films data to the view
		model.addAttribute("films", listOfFilms);
		
		return "time/timeForm";
	}
	
	
	
	/**
	 * Send the form when the button "Save" is clicked
	 * @param result	BindingResult: holds all errors during the data binding, goes after the Model Class
	 * @param attributes	RedirectAttributes: creates a flash attribute that we can use them in different urls after making an redirect,
	 * 						we use them to redirect the form and send a successful message
	 * @return	a redirection to the form page
	 */
	@PostMapping("/save")
	public String save(@ModelAttribute Time time, BindingResult result, RedirectAttributes attributes, Model model) {
		
		// verify if there is an error during Data Binding or not, if there's an error it holds it 
		if(result.hasErrors()) {
			// show again the list of all movies in the <select> after an error is detected
			List<Films> listOfFilms = serviceFilms.getAllFilms();
			// sending the films data to the view
			model.addAttribute("films", listOfFilms);
			return "/time/timeForm";
		}
		
		// display an message for success in the url /films/index after creating a new instance of the Object Film
		attributes.addFlashAttribute("message", "The time for this movie was saved succesfully");
		//System.out.println(time);
		return "redirect:/time/create";
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
