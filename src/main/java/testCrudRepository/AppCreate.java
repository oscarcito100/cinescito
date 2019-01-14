package testCrudRepository;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.oscar.app.model.News;
import net.oscar.app.repository.NewsRepository;

/**
 * app to create in 'noticias' table a new News Object
 * @author Oscar
 *
 */
public class AppCreate {

	public static void main(String[] args) {
		// 1- create an object of the 'News' class and set a new title and detail
		// however, we don't set neither status nor date because they are given by default for the constructor
		// also, not set id bc is generated automatically by our db
		News news = new News();
		news.setTitulo("Game of thrones");
		news.setDetail("The most important families in Westeros fight for the control of the 7 kingdoms");
		
		// 2- create an instance of ClassPathXmlApplicationContext
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		
		// 3- create an instance of 'NewsRepository' from the ClassPathXmlApplicationContext
		NewsRepository repo = context.getBean("newsRepository", NewsRepository.class);
		
		// 4- save the object 'news'
		repo.save(news);
		
		// 5- close the connection
		context.close();
	}

}
