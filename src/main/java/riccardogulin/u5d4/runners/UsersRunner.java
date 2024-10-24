package riccardogulin.u5d4.runners;

import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import riccardogulin.u5d4.entities.User;
import riccardogulin.u5d4.exceptions.ValidationException;
import riccardogulin.u5d4.repositories.UsersRepository;
import riccardogulin.u5d4.services.UsersService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Component
@Slf4j
public class UsersRunner implements CommandLineRunner {
	/*@Autowired
	private UsersRepository usersRepository;
	MAI UTILIZZARE DIRETTAMENTE UNA REPOSITORY (Solo i Service possono farlo perché
	essi fanno dei controlli prima di utilizzare i metodi della repository)
	*/
	@Autowired
	private UsersService usersService;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(Locale.ITALY);

		List<User> newUsers = new ArrayList<>();

		for(int i=0; i < 100; i++) {
			User newUser = new User(faker.lordOfTheRings().character(), faker.name().lastName(), faker.internet().emailAddress(), faker.random().nextInt(100));
			newUsers.add(newUser);
		}

/*		try {
			usersService.saveMany(newUsers);
		} catch (ValidationException ex) {
			log.error(ex.getMessage());
		}*/

		usersService.findAll().forEach(System.out::println);


	}
}
