package testQuery;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.oscar.app.model.News;
import net.oscar.app.repository.NewsRepository;

public class AppKeywordBetween {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");

		// 2- create an instance of 'NewsRepository' from the ClassPathXmlApplicationContext
		NewsRepository repo = context.getBean("newsRepository", NewsRepository.class);

		// 3- Query methods		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		List<News> list = null;
		try {
			list = repo.findByDatePublishedBetween(format.parse("2017-09-03"), format.parse("2017-09-06"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(News n : list) {
			System.out.println(n);
		}
		// 4- close the connection
		context.close();

	}

}
