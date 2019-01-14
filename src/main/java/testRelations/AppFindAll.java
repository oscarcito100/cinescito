package testRelations;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.oscar.app.model.Films;
import net.oscar.app.repository.FilmsRepository;

public class AppFindAll {

	public static void main(String[] args) {
		// 1- create an instance of ClassPathXmlApplicationContext
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");

		// 2- create an instance of 'FilmsRepository' from the ClassPathXmlApplicationContext
		FilmsRepository repo = context.getBean("filmsRepository", FilmsRepository.class);

		// 3- find all entities in a table putting them in a List
		List<Films> list = repo.findAll();
		for(Films n: list) {
			System.out.println(n);
		}
		
		// 4- close the connection
		context.close();

	}

}
