package testRelations;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.oscar.app.model.Detail;
import net.oscar.app.repository.DetailsRepository;

public class AppDetail {

	public static void main(String[] args) {
		// 1- create an instance of ClassPathXmlApplicationContext
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");

		// 2- create an instance of 'DetailsRepository' from the ClassPathXmlApplicationContext
		DetailsRepository repo = context.getBean("detailsRepository", DetailsRepository.class);
		
		// 3- find all entities in a table putting them in a List
		List<Detail> list = repo.findAll();
		for(Detail n: list) {
			System.out.println(n);
		}
		// 4- Close the context
		context.close();
	}

}
