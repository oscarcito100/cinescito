package testCrudRepository;

import java.util.LinkedList;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.oscar.app.model.News;
import net.oscar.app.repository.NewsRepository;

public class AppFindAllById {

	public static void main(String[] args) {
		// 1- create an instance of ClassPathXmlApplicationContext
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");

		// 2- create an instance of 'NewsRepository' from the ClassPathXmlApplicationContext
		NewsRepository repo = context.getBean("newsRepository", NewsRepository.class);

		// 3- find some entities by their ids holding the ids in a list and their entities in a Iterable
		List<Integer> list = new LinkedList<Integer>();
		list.add(3);
		list.add(4);
		
		Iterable<News> it = repo.findAllById(list);
		for(News n: it) {
			System.out.println(n);
		}
		
		// 4- close the connection
		context.close();

	}

}
