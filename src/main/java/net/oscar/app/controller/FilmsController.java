package net.oscar.app.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.oscar.app.model.Films;
import net.oscar.app.service.InterfaceDetails;
import net.oscar.app.service.InterfaceFilmsService;
import net.oscar.app.util.Utils;

/**
 * This controller process the Films section
 * Method create() a new film
 * Method index() show all the films
 * Method save() a new film in the database
 * Method initBinder() that Convert the parameter String date to a type Date
 * @author Oscar Germade
 * 
 * Put @RequestMapping("/news") to a level of the class, so the rest of the urls belong to this one
 * e.g. /news/createNews
 *
 */
@Controller
@RequestMapping("/films")
public class FilmsController {
	
	//create an instance of the interface InterfaceDetails to use its Service class
	@Autowired
	private InterfaceDetails serviceDetails;
	
	//create an instance of the interface InterfaceNewsService to use its Service class
	@Autowired
	private InterfaceFilmsService serviceFilms;
	
	/**
	 * display all elements of the List<Films>
	 * It is called from the navbar, <li> Films
	 * @param model		Model: allow us to send the data of the films to the view
	 * @return 	the view that display the list of films
	 */
	@GetMapping("/index")
	public String displayIndex(Model model) {
		// create a list that holds the list of all movies in the serviceFilms
		List<Films> listOfFilms = serviceFilms.getAllFilms();
		System.out.println(listOfFilms);
		// send the list to the model to use its elements in the view listOfFilms.jsp
		model.addAttribute("films", listOfFilms);
		return "films/listOfFilms";
	}// end of @GetMapping("/index")
	
	
	
	/**
	 * Display all movies in pages: Page<Films>
	 * It is called from the navbar, <li> Films
	 * @param model		Model: allow us to send the data of the films to the view
	 * @param pageable	to be able hold the list of movies hold in pages
	 * @return 	the view that display the list of films
	 */
	@GetMapping("/indexPaginate")
	public String displayIndex(Model model, Pageable page) {
		// 1- create a list that holds all the movies in pages
		Page<Films> listOfFilms = serviceFilms.getAllWithPagination(page);
		// 2- send the list to the model to use its elements in the view listOfFilms.jsp
		model.addAttribute("films", listOfFilms);
		
		// 3- return the view of all the movies
		return "films/listOfFilms";
	}// end of @GetMapping("/index")
	
	

	/**
	 * It is called when we clicked the button "add new film" in formFils.jsp
	 * @ModelAttribute	pass the class Model Films to the form of Film for better Data Binding
	 * Model	aggregate a model to be able to add the movie's genders as attributes in the view
	 * 			However now we don't use it bc we are getting the gender with a method using @ModelAttribute as a method
	 * @return		the view that has the form to add a new film
	 */
	@GetMapping("/create")
	public String create(@ModelAttribute("film") Films film) {	
		// now the gender is getting with the method getGenders() typed down
		//model.addAttribute("gender", serviceFilms.getGender());
		// send us to the view formFilms.jsp to create a new Film with its form
		return "films/formFilms";
	}// End of @GetMapping("/create")
	
	/**
	 * It is called from the form formFilms when the button "submit" is clicked, it sends the data from the form to this url
	 * @param @ModelAttribute("film")	aggregate automatically the Model Class to the form to do the Data binding
	 * @param result	BindingResult: holds all errors during the data binding, goes after the Model Class
	 * @param attributes	RedirectAttributes: creates a flash attribute that we can use them in different urls after making an redirect,
	 * 						we use them to redirect the form and send a succesfull message
	 * @param multiPart		represents the binary file that the user sends with the form
	 * @param request	HttpServletRequest: recover the absolute path to save the file in the correct folder
	 * @return	after successfully have saved a new movie, redirect to the url to show the list of all the movies
	 */
	@PostMapping("/save")
	public String save(@ModelAttribute("film") Films film, BindingResult result, RedirectAttributes attributes,
			 @RequestParam("imageFile") MultipartFile multiPart, HttpServletRequest request) {
		
		// verify if there is an error during Data Binding or not, if there's an error it holds it 
		if(result.hasErrors()) {
			System.out.println("Hay un error");
			return "films/formFilms";
		}
		/* display the errors of the Data Binding in the console
		 * for(ObjectError error : result.getAllErrors()) {
			System.out.println(error.getDefaultMessage()); // show the description of every error
		}*/
		
		// if any file is selected, save the image file selected by the user in the folder
		if (!multiPart.isEmpty()) { 
			String imageName = Utils.saveImage(multiPart,request); 
			film.setImage(imageName);
		}
		
		// save the Detail object in the DB
		serviceDetails.insert(film.getDetail());
		
		// save the Film in the DB 
		serviceFilms.insert(film);
		
		//System.out.println("nummero films despues: " + serviceFilms.getAllFilms().size());
		
		// display an message for success in the url /films/index after creating a new instance of the Object Film
		attributes.addFlashAttribute("message", "The movie was saved succesfully");
		
		// redirect to an url instead of a view
		return "redirect:/films/indexPaginate";
	} // end of @PostMapping("/save")

	/**
	 * Edit the data of a movie given its id
	 * Search for the data of that movie and render it to the view with the model
	 * @param idFilm	the id of the movie passed as a parameter in the url
	 * @param model		to render the Film object to the view
	 * @return		films/formFilms
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") int idFilm, Model model) {
		Films film = serviceFilms.searchById(idFilm);
		model.addAttribute("film", film);
		// now the gender is getting with the method getGenders() typed down
		//model.addAttribute("gender", serviceFilms.getGender());
		return "films/formFilms";
	}
	
	/**
	 * Delete a movie from the DB, for that we also need to delete its Detail Object, so we
	 * must get the idDetail to delete it too
	 * @param idFilm
	 * @param attributes	send a message of success
	 * @return		"redirect to the list of movies: /films/index sending a message too
	 */
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int idFilm, RedirectAttributes attributes) {
		// get the data of the film to get its idDetail to delete it too
		Films film = serviceFilms.searchById(idFilm);
		// first delete the film and then the details
		serviceFilms.delete(idFilm);
		serviceDetails.delete(film.getDetail().getId());
		
		attributes.addFlashAttribute("message", "The film with id=" + idFilm + " was deleted succesfully");
		// redirect to an url instead of a view
		return "redirect:/films/indexPaginate";
	}
	/**
	 * Get the genders of a movie to place them in the <select>
	 * @ModelAttribute("gender") 	make the attribute 'gender' available for all the methods in this
	 * 								controller so we don't have to repeat the code in every method
	 * @return		a list with the genders
	 */
	@ModelAttribute("gender")
	public List<String> getGenders(){
		return serviceFilms.getGender();
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
