package riccardogulin.u5d4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import riccardogulin.u5d4.entities.User;

@Repository // Specializzazione di @Component però dedicata al mondo dei DB
public interface UsersRepository extends JpaRepository<User, Long> {
	// Estendendo JpaRepository ottengo tutti i metodi CRUD già pronti, cioè non dovrò neanche implementarli
	// Eventualmente posso aggiungere ulteriori metodi personalizzati per fare operazioni non CRUD
	// Nelle parentesi angolari dovrò inserire <Classe dell'entità, Tipo dell'id di quell'entità>

}
