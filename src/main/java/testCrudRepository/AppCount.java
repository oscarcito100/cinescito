package testCrudRepository;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.oscar.app.repository.NewsRepository;

public class AppCount {

	public static void main(String[] args) {
		// 1- create an instance of ClassPathXmlApplicationContext
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");

		// 2- create an instance of 'NewsRepository' from the ClassPathXmlApplicationContext
		NewsRepository repo = context.getBean("newsRepository", NewsRepository.class);
		
		// 3- Count the number of rows in a table using long to hold the number
		long numRows = repo.count();
		System.out.println("This table has " + numRows + " elements");

		// 4- close the connection
		context.close();
	}

}
