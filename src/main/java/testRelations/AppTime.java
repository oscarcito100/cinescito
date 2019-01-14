package testRelations;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.oscar.app.model.Time;
import net.oscar.app.repository.TimesRepository;

public class AppTime {

	public static void main(String[] args) {
		// 1- create an instance of ClassPathXmlApplicationContext
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");

		// 2- create an instance of 'DetailsRepository' from the ClassPathXmlApplicationContext
		TimesRepository repo = context.getBean("timesRepository", TimesRepository.class);
		
		// 3- find all entities in a table putting them in a List
		List<Time> list = repo.findAll();
		for(Time n: list) {
			System.out.println(n);
		}
		// 4- Close the context
		context.close();

	}

}
