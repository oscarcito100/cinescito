package net.oscar.app.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.oscar.app.model.Contact;
import net.oscar.app.model.Films;
import net.oscar.app.service.InterfaceFilmsService;

@Controller
@RequestMapping("/contact")
public class ContactController {
	//create an instance of the interface InterfaceFilmsService to use a Service class
	@Autowired
	private InterfaceFilmsService serviceFilm;
	
	
	/**
	 * Show the page with the Form contact
	 * @ModelAttribute	pass the class Model Contact to the form of Contact for better Data Binding
	 * @return	to the view with the form contact
	 */
	@GetMapping("/create")
	public String showForm(@ModelAttribute Contact contact, Model model) {
		// create an attribute to show the genders in the <select> genders once it was redirect
		// to the form again		
		model.addAttribute("gender", serviceFilm.getGender());
		// create an attribute to send a List with the types of notifications
		model.addAttribute("typesOfNotifications", typesOfNotifications());
		
		return "contact/formContact";
	}
	
	
	
	/**
	 * Save the form data when de button "Send" is clicked
	 * Has the same url however this is different because the method is Post, so it is called just
	 * when the "Send" button is clicked
	 * @ModelAttribute	bind the form data with the Model Contact
	 * @return
	 */
	@PostMapping("/save")
	public String save(@ModelAttribute Contact contact, BindingResult result, RedirectAttributes attributes) {
		// verify if there is an error during Data Binding or not, if there's an error it holds it 
		if(result.hasErrors()) {
		  return "/time/timeForm";
		}
		
		// display an message for success in the url /films/index after creating a new instance of the Object Film
		attributes.addFlashAttribute("message", "The contact form was sent succesfully");
				
		// redirect to the form contact
		return "redirect:/contact/create";
	}
	
	/**
	 * Hold a List with all types of notifications
	 * @return	the List with all type of notifications
	 */
	private List<String> typesOfNotifications(){
		// declaration of a List to hold the types of notifications
		List<String> types = new LinkedList<String>();
		
		// add the elements to the List
		types.add("Premier");
		types.add("Promos");
		types.add("News");
		types.add("PreSales");
		
		return types;
	}
}
