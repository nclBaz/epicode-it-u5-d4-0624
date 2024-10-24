package riccardogulin.u5d4.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import riccardogulin.u5d4.entities.User;
import riccardogulin.u5d4.exceptions.ValidationException;
import riccardogulin.u5d4.repositories.UsersRepository;

import java.util.List;

@Service // Specializzazione di @Component
// All'avvio dell'applicazione mi verrà creato un oggetto di tipo
// UsersService
// Un Service è una classe che ci consente di aggiungere ulteriore logica custom nelle varie interazioni col db
// Ad esempio, quando salvo un nuovo un nuovo utente potrei voler controllare se quell'indirizzo email è già presente nel DB prima di salvarlo. Oppure
// in generale potrei voler validare gli attributi che mi vengono passati o anche aggiungere anche dei valori generati dal nostro codice prima di salvarlo
@Slf4j
public class UsersService {
	@Autowired
	private UsersRepository usersRepository;

	public void saveUser(User newUser){
		// Nelle save solitamente è opportuno andare ad effettuare tutta una serie di controlli prima di salvare effettivamente a DB
		// Durante una save posso anche aggiungere ulteriori campi sul record, dati "Server-Generated"

		// 1. Controllo che l'email fornita non sia già nel db
		// 2. Effettuo ulteriori controlli di validazione dei campi forniti
		if(newUser.getName().length() < 2) throw new ValidationException("Il nome non può essere più corto di 2 caratteri");
		// 3. Aggiungo ulteriori dati "Server-Generated"
		newUser.setProfileImg("https://placebear.com/200/300");
		// 4. Salvo l'utente
		usersRepository.save(newUser);
		// 5. Log di avvenuto salvataggio
		log.info("L'utente " + newUser.getName() + " è stato salvato correttamente!");
	}

	public void saveMany(List<User> newUsers) {
		for (User user: newUsers) {
			try {
				this.saveUser(user);
			} catch (ValidationException ex) {
				log.error(ex.getMessage());
			}
		}
	}

	public List<User> findAll(){
		return usersRepository.findAll();
	}
}
