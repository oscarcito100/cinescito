package net.oscar.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.oscar.app.model.News;
/**
 * Spring Repository to be able to use CRUD operations with the 'News Entity Class'.
 * Declare methods to make some queries that are implemented automatically by Spring Data JPA
 * and we can use them to make queries.
 * @Repository	allow us to create a new bean in the application context.
 * JpaRepository	allow us to use CRUD operations with the 'News' Entity Class having
 * 					an integer type as the primary key. Also, it extends CrudRepository and
 * 					PagingAndOrderingRepository Interfaces.
 * @author Oscar
 */
@Repository
//public interface NewsRepository extends CrudRepository<News, Integer> {
public interface NewsRepository extends JpaRepository<News, Integer> { 

	List<News> findBy(); // select * from noticias
	List<News> findByStatus(String status); // select * from noticias where status = ?
	List<News> findByDatePublished(Date date); // select * from noticias where fecha = ?	
	List<News> findByStatusAndDatePublished(String status, Date date); // select * from noticias where status = ? and fecha = ?
	
	// select * from noticias where estatus="activa" order by fecha desc limit 3;
	List<News> findTop3StatusOrderByDatePublishedDesc(String status); 

	List<News> findByStatusOrDatePublished(String status, Date date); // select * from noticias where status = ? or fecha = ?
	List<News> findByDatePublishedBetween(Date date1, Date date2); // select * from noticias where fecha between ? and ?

}
