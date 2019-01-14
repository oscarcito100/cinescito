package net.oscar.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.oscar.app.model.Banner;
import net.oscar.app.service.InterfaceBannerService;
import net.oscar.app.util.Utils;

/**
 * This controller process the Banner section in the home page
 * Method create() 	display the form to add a new element in the Banner
 * Method index() 	display of elements of the List<Banner>
 * Method save()	When button "submit" is clicked, it sends the data from the form to this url
 * @author Oscar Germade
 * 
 * Put @RequestMapping("/banner") to a level of the class, so the rest of the urls belong to this one
 * e.g. /banner/index
 *
 */
@Controller
@RequestMapping("/banner")
public class BannersController {
	//create an instance of the interface InterfaceBannerService to use its Service class
	@Autowired
	private InterfaceBannerService serviceBanner;
	
	
	/**
	 * display of elements of the List<Banner>
	 * It is called from ...
	 * @param model		allow us to use the Banner attributes in the view
	 * @return 		the view that display the list of banners
	 */
	@GetMapping("/index")
	public String displayIndex(Model model) {
		// create a list that holds the list of banners in the serviceBanner
		List<Banner> listOfBanner = serviceBanner.getAllBanners();
		// send the list to the model to use its elements in the view listOffilms.jsp
		model.addAttribute("banners", listOfBanner);
		
		return "banners/listOfBanners";
	}
		
	/**
	 * display the form to add a new element in the Banner
	 * It is called when we clicked the button "add new film" in formBanner.jsp
	 * @return		the view that has the form to add a new element in the banner
	 */
	@GetMapping("/create")
	public String crear() {
		// send us to the view formBanner.jsp to create a new Banner with its form
		return "banners/formBanner";

	}
		
	/**
	 * When button "submit" is clicked, it sends the data from the form to this url
	 * It is called from the form formBanner when the button is clicked
	 * @param banner	Model Class to do the Data binding
	 * @param result	holds any error during the data binding
	 * @param attributes	creates a attribute that we can use not only in this url, we use them to redirect the form and send a succesfull message
	 * @param multiPart		represents the binary file that the user sends with the form
	 * @param request	recover the absolute path to save the file in the correct folder
	 * @return	after successfully have saved a new element in the Banner, redirect to the url to show the list of all elements in the Banner
	 */
	@PostMapping("/save")
	public String save(Banner banner, BindingResult result, RedirectAttributes attributes,
			 @RequestParam("imageFile") MultipartFile multiPart, HttpServletRequest request) {

		// if there's any mistake during Data Binding, print it in console and return the form
		if(result.hasErrors()) {
			System.out.println("Hay un error");
			return "banners/formBanner";
		}
		
		// if any file is selected, save the image file selected by the user in the folder
		if (!multiPart.isEmpty()) {
			String imageName = Utils.saveImage(multiPart, request);
			banner.setFileName(imageName);
		}
		
		// insert the Banner in the list 
		serviceBanner.insert(banner);
		
		// display an message for success in the url /banners/index
		attributes.addFlashAttribute("message", "The movie was saved succesfully");
		
		// redirect to the url that display the list of all banners
		return "redirect:/banner/index";
	}
}
