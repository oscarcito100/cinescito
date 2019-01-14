package testJpaRepository;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import net.oscar.app.model.News;
import net.oscar.app.repository.NewsRepository;

public class AppPagination {

	public static void main(String[] args) {
		// 1- create an instance of ClassPathXmlApplicationContext
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");

		// 2- create an instance of 'NewsRepository' from the ClassPathXmlApplicationContext
		NewsRepository repo = context.getBean("newsRepository", NewsRepository.class);

		// 3- Find all elements and page them
		//Page<News> page = repo.findAll(PageRequest.of(0, 5)); // return the first page with 5 elements
		Page<News> page = repo.findAll(PageRequest.of(0, 5, Sort.by("titulo")));
		
		for(News n : page) {
			System.out.println(n);
		}
		// 4- close the connection
		context.close();

	}

}
