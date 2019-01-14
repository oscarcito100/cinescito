package testCrudRepository;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.oscar.app.repository.NewsRepository;

public class AppExists {

	public static void main(String[] args) {
		// 1- create an instance of ClassPathXmlApplicationContext
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");

		// 2- create an instance of 'NewsRepository' from the ClassPathXmlApplicationContext
		NewsRepository repo = context.getBean("newsRepository", NewsRepository.class);
		
		// 3- Verify if a row exists in the table
		int newsId = 1;
		System.out.println(repo.existsById(newsId));
		
		// 4- close the connection
		context.close();
	}

}
