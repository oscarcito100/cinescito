package testCrudRepository;

import java.util.Optional;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.oscar.app.model.News;
import net.oscar.app.repository.NewsRepository;

public class AppRead {

	public static void main(String[] args) {
		// 1- create an instance of ClassPathXmlApplicationContext
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");

		// 2- create an instance of 'NewsRepository' from the ClassPathXmlApplicationContext
		NewsRepository repo = context.getBean("newsRepository", NewsRepository.class);
		
		// 3- Search for the 'news' using findById() creating an Optional class of the News Object
		// bc first it tell us if the element exist or not
		Optional<News> news = repo.findById(1);
		
		// 4- Return the result if the element exists or send a message if it isn't
		if(news.isPresent()) {
			System.out.println(news.get());
		} else {
			System.out.println("News no found");
		}
			
		// 5- close the connection
		context.close();
	}

}
