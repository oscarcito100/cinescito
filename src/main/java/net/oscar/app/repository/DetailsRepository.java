package net.oscar.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.oscar.app.model.Detail;

/**
 * Spring Repository to be able to use CRUD operations with 'Detail Entity Class'.
 * Declare methods to make some queries that are implemented automatically by Spring Data JPA
 * and we can use them to make queries.
 * @Repository	allow us to create a new bean in the application context.
 * JpaRepository	allow us to use CRUD operations with 'Detail' Entity Class having
 * 					an integer type as the primary key. Also, it extends CrudRepository and
 * 					PagingAndOrderingRepository Interfaces.
 * @author Oscar
 */
@Repository
public interface DetailsRepository extends JpaRepository<Detail, Integer> {

}
