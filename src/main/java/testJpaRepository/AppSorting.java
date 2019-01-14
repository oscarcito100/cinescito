package testJpaRepository;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Sort;

import net.oscar.app.model.News;
import net.oscar.app.repository.NewsRepository;

public class AppSorting {

	public static void main(String[] args) {
		// 1- create an instance of ClassPathXmlApplicationContext
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");

		// 2- create an instance of 'NewsRepository' from the ClassPathXmlApplicationContext
		NewsRepository repo = context.getBean("newsRepository", NewsRepository.class);

		// 3- Find all elements and sort them by a field for their name of the attribute in the Entity class
		//List<News> list = repo.findAll(Sort.by("titulo")); // by default is in ascendent order
		//List<News> list = repo.findAll(Sort.by("titulo").descending());
		List<News> list = repo.findAll(Sort.by("datePublished").descending().and(Sort.by("titulo").ascending()));
		for(News n : list) {
			System.out.println(n);
		}
		// 4- close the connection
		context.close();

	}

}
