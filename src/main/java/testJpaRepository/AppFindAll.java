package testJpaRepository;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.oscar.app.model.News;
import net.oscar.app.repository.NewsRepository;

public class AppFindAll {

	public static void main(String[] args) {
		// 1- create an instance of ClassPathXmlApplicationContext
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");

		// 2- create an instance of 'NewsRepository' from the ClassPathXmlApplicationContext
		NewsRepository repo = context.getBean("newsRepository", NewsRepository.class);

		// 3- find all entities in a table putting them in a List
		List<News> list = repo.findAll();
		for(News n: list) {
			System.out.println(n);
		}
		
		// 4- close the connection
		context.close();

	}

}
