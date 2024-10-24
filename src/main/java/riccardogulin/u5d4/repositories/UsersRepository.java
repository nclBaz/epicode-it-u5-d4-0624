package riccardogulin.u5d4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import riccardogulin.u5d4.entities.User;

import java.util.List;

@Repository // Specializzazione di @Component però dedicata al mondo dei DB
public interface UsersRepository extends JpaRepository<User, Long> {
	// Estendendo JpaRepository ottengo tutti i metodi CRUD già pronti, cioè non dovrò neanche implementarli
	// Eventualmente posso aggiungere ulteriori metodi personalizzati per fare operazioni non CRUD
	// Nelle parentesi angolari dovrò inserire <Classe dell'entità, Tipo dell'id di quell'entità>

	// ************************** DERIVED QUERIES **************************************
	List<User> findBySurname(String surname); // SELECT * FROM users WHERE surname = :surname

	List<User> findByNameAndSurname(String name, String surname); // SELECT * FROM users WHERE name = :name AND surname = :surname

	List<User> findByNameStartingWithIgnoreCase(String partialName); // SELECT * FROM users WHERE name ILIKE CONCAT(:partialName, "%")

	List<User> findByAgeLessThan(int age);

	List<User> findByNameIn(List<String> names);

	List<User> findByEmailIsNull();

	boolean existsByEmail(String email);

	// Link alla documentazione Derived Queries
	// https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html

	// ******************************************** JPQL QUERIES ********************************
	@Query("SELECT u FROM User u WHERE u.age >= 18")
	List<User> filterBySoloMaggiorenni();

	@Query("SELECT u FROM User u WHERE u.age >= :age")
	List<User> filterByAge(int age);

	// ********************************************* NATIVE QUERIES *****************************
	@Query(value = "SELECT * FROM users WHERE age >= 18", nativeQuery = true)
	List<User> filterBySoloMaggiorenniNative();
}
