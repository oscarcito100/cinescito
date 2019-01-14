package net.oscar.app.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class Utils {
	
	/**
	 * Return a list of String with the next "n" dates from the current day with the format "dd-MM-yyyy"
	 * @param numOfDays		number of days we are going to pass in order to get this number of days apart from
	 * 						the current day
	 * @return
	 */
	public static List<String> getNextDays(int numOfDays){
		// make a format for the dates
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		// Get current day
		Date currentDay = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, numOfDays); // add number of days to the calendar apart from today
		Date endDate = calendar.getTime(); // returns the calendar
		
		// create an gregorian calendar and add the current date
		GregorianCalendar gregorianCalendar = new GregorianCalendar();
		gregorianCalendar.setTime(currentDay); // add current day
		
		// create a new list to hold the current day plus the next days depending on the num of Days we want to add in the <select> date
		List<String> nextDays = new ArrayList<String>();
		while(!gregorianCalendar.getTime().after(endDate)) {
			Date today = gregorianCalendar.getTime();
			gregorianCalendar.add(Calendar.DATE, 1);
			nextDays.add(sdf.format(today));
		}
		return nextDays;
	}
	
	/**
	 * Save an image file that is uploaded when we add a new Film
	 * It is used in the formFilm.jsp
	 * @param multiPart
	 * @param request
	 * @return
	 */
	public static String saveImage(MultipartFile multiPart, HttpServletRequest request) {    
		// get the original name of the file
		String fileName = multiPart.getOriginalFilename();  
		fileName = fileName.replace(" ", "-");
		
		//get the absolute path where to save the image
		// e.g. apache-tomcat/webapps/cineapp/resources/images/
		String rutaFinal = request.getServletContext().getRealPath("/resources/images/"); 
		
		// new variable that add random characters to fileName var, so any file could be repeated
		String finalFileName = randomAlphaNumeric(8) + fileName; 
		
		try { 
			// create a File object to save the file with the specified route and file name
			File imageFile = new File(rutaFinal + finalFileName); 
			//System.out.println(imageFile.getAbsolutePath());
			multiPart.transferTo(imageFile); // save the file in the hard disk
			return finalFileName; 
		} catch (IOException e) { 
			System.out.println("Error " + e.getMessage()); 
			return null;
		}
	}
	
	/**
	 * Generate a String with random characters and "count" length
	 * @param count 	the length of the String to generate
	 * @return	the new String generated
	 */
	public static String randomAlphaNumeric(int count) {
		String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"; // asign all leters from a-z and numbers from 0-9
		StringBuilder sb = new StringBuilder(); // to generate the final string that is return
		while(count-- != 0) { // first, decrement the length of the string and compare if it is different than 0
			int character = (int) (Math.random() * CHARACTERS.length()); // generate a random position from the String CHARACTERS
			sb.append(CHARACTERS.charAt(character)); // add the character of the position selected to the stringBuilder
		}
		return sb.toString(); // return the generated StringBuilder
	}
}
