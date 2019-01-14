package testRelations;

import java.util.List;
import java.util.Optional;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.oscar.app.model.Films;
import net.oscar.app.repository.FilmsRepository;

public class AppGetTimeFilm {

	public static void main(String[] args) {
		// 1- create an instance of ClassPathXmlApplicationContext
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");

		// 2- create an instance of 'FilmsRepository' from the ClassPathXmlApplicationContext
		FilmsRepository repo = context.getBean("filmsRepository", FilmsRepository.class);

		// 3- query the number of times that the movie with id=1 has
		Optional<Films> optional = repo.findById(1);
		System.out.println(optional.get().getTime().size());
		
		// 4- close the connection
		context.close();

	}

}
