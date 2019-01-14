package net.oscar.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.oscar.app.model.News;
import net.oscar.app.service.InterfaceNewsService;

/**
 * This controller process the News section in the home page
 * Method createNews() creates a new news
 * Method saveNews() 	save a news in the database
 * @author Oscar Germade
 * 
 * Put @RequestMapping("/news") to a level of the class, so the rest of the urls belong to this one
 * e.g. /news/createNews
 *
 */
@Controller
@RequestMapping("/news")
public class NewsController {
	
	//create an instance of the interface InterfaceNewsService to use a Service class
	@Autowired
	private InterfaceNewsService serviceNews;
	
	/**
	 * Display all elements of the Object News
	 * It is called from the navbar, <li> News
	 * @param model		Model: allow us to send the data of the films to the view
	 * @return 	the view that display the list of news
	 */
	@GetMapping("/index")
	public String displayIndex(Model model) {
		// 1. create the list that hold all news return them by the method getAllNews()
		List<News> listOfNews = serviceNews.getAllNews();
		System.out.println(listOfNews);
		// 2. send the News object to the view 'listOfNews'
		model.addAttribute("news", listOfNews);
		
		return "news/listOfNews";
	}
	
	
	/**
	 * Display all movies in pages: Page<Films>
	 * It is called from the navbar, <li> Films
	 * @param model		Model: allow us to send the data of the films to the view
	 * @param pageable	to be able hold the list of movies hold in pages
	 * @return 	the view that display the list of films
	 */
	@GetMapping("/indexPaginate")
	public String displayIndex(Model model, Pageable page) {
		// 1- create a page that holds all the News in pages
		Page<News> listOfNews = serviceNews.getAlNewsWithPagination(page);
		// 2- send the page to the model to use its elements in the view listOfFilms.jsp
		model.addAttribute("news", listOfNews);
		
		// 3- return the view of all the news
		return "news/listOfNews";
	}// end of @GetMapping("/index")
	
	
	
	/**
	 * It is called when we clicked the button "Create News" in listOfNews.jsp
	 * @ModelAttribute	pass the class Model News to the form of for better Data Binding
	 * @return		the view that has the form to add a new film
	 */
	@GetMapping(value="/create")
	public String create(@ModelAttribute News news) {
		 
		return "news/formNews";
	}
	
	
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
	@PostMapping(value="/save")
	public String save(@ModelAttribute News news, BindingResult result, RedirectAttributes attributes) {
		// verify if there is an error during Data Binding or not, if there's an error it holds it 
		if(result.hasErrors()) {
			//System.out.println("Hay un error");
			return "news/formNews";
		}
		
		serviceNews.insert(news);
		
		// display an message for success in the url /films/index after creating a new instance of the Object Film
		attributes.addFlashAttribute("message", "The news was saved succesfully");
		
		return "redirect:/news/indexPaginate"; 
	}
	
	
	
	/**
	 * Edit the data of a News given its id
	 * Search for the data of that specified News and render it to the view with the model
	 * @param idNews	the id of the movie passed as a parameter in the url
	 * @param model		to render the News object to the view
	 * @return		news/formFilms
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") int idNews, Model model) {
		News news = serviceNews.searchById(idNews);
		model.addAttribute("news", news);
		
		return "news/formNews";
	}
	
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int idNews, RedirectAttributes attributes) {
		// delete the News
		serviceNews.delete(idNews);
		// send an message to the redirected view
		attributes.addFlashAttribute("message", "The News with id=" + idNews + " was deleted succesfully");
		
		return "redirect:/news/indexPaginate";
	}
}
