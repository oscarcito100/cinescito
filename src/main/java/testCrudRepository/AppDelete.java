package testCrudRepository;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.oscar.app.repository.NewsRepository;

public class AppDelete {

	public static void main(String[] args) {
		// 1- create an instance of ClassPathXmlApplicationContext
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");

		// 2- create an instance of 'NewsRepository' from the ClassPathXmlApplicationContext
		NewsRepository repo = context.getBean("newsRepository", NewsRepository.class);
		
		// 3- Delete a row with a conditional so that an exeption does't jump
		int id = 1;		
		if(repo.existsById(id)) {
			repo.deleteById(id);
		}
		
		// 4- close the connection
		context.close();
	}

}
