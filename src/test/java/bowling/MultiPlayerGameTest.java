package bowling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MultiPlayerGameTest {

	private PartieMultiJoueurs partie;

	@BeforeEach
	public void setUp() {
		partie = new PartieMultiJoueurs();
	}

	//Test 1 : Vérifier le démarrage d'une nouvelle partie avec succès
	@Test
	void testBeginGame() {
		String[] Joueurs = {"Pierre", "Paul"};
		String message = partie.demarreNouvellePartie(Joueurs);
		assertEquals("Prochain tir : joueur Pierre, tour n° 1, boule n° 1", message, "Probleme lors de la création d'une nouvelle partie");
	}

	//2 tests permettant de vérifier le démarrage avec exceptions
	@Test
	void testBeginGameWithException_null() {
		
		String[] Joueurs= null;
		Throwable exception= Assertions.assertThrows(IllegalArgumentException.class, () -> {
			partie.demarreNouvellePartie(Joueurs);
		});
		assertEquals("Nouvelle partie ne peut pas être créée", exception.getMessage());
	}

	@Test
	void testBeginGameWithException_empty() {
		
		String[] Joueurs= {};
		Throwable exception= Assertions.assertThrows(IllegalArgumentException.class, () -> {
			partie.demarreNouvellePartie(Joueurs);
		});
		assertEquals("Nouvelle partie ne peut pas être créée", exception.getMessage());
	}


	//Test permettant de vérifier le bon enregistrement de chaque lancer, avec le message adéquat à retourner
	@Test
	void testEnregistrementLancer(){

		String[] Joueurs = {"Pierre", "Paul"};
		partie.demarreNouvellePartie(Joueurs);
		assertEquals("Prochain tir : joueur Pierre, tour n° 1, boule n° 2", partie.enregistreLancer(5), "Message retourné incorrect");

	}

	//2 tests permettant de vérifier l'enregistrement de lancers avec exceptions
	@Test
	void testEnregistrementLancerWithException1() {
		
		String[] Joueurs= {"Pierre", "Paul"};
		partie.demarreNouvellePartie(Joueurs);
		// 1er tour du joueur 1
		partie.enregistreLancer(10);
		// 1er tour du joueur 2
		partie.enregistreLancer(10);
		// 2ème tour du joueur 1
		partie.enregistreLancer(10);
		//Suite des lancers des deux joueurs
		for(int i=0;i<21;i++)
			partie.enregistreLancer(10);

		Throwable exception= Assertions.assertThrows(IllegalStateException.class, () -> {
			partie.enregistreLancer(5);
		});
		assertEquals("Partie terminée", exception.getMessage());
	}

	// @Test
	// void testScoreIndividuelPourChaqueJoueur() {
	// 	String[] joueurs = {"Pierre", "Paul"};
	// 	partie.demarreNouvellePartie(joueurs);

	// 	// Lancers : joueur 1 (strike), joueur 2 (spare), joueur 1 (strike)
	// 	partie.enregistreLancer(10); // Pierre
	// 	partie.enregistreLancer(5);  // Paul
	// 	partie.enregistreLancer(5);  // Paul
	// 	partie.enregistreLancer(10); // Pierre

	// 	// Score individuel
	// 	int scorePierre = partie.scorePour("Pierre");
	// 	int scorePaul = partie.scorePour("Paul");

	// 	// Assertions
	// 	assertEquals(20, scorePierre, "Le score de Pierre doit être 20.");
	// 	assertEquals(10, scorePaul, "Le score de Paul doit être 10.");

	// 	// Vérifie qu'un joueur inexistant lève une exception
	// 	Assertions.assertThrows(IllegalArgumentException.class, () -> {
	// 		partie.scorePour("Jacques");
	// 	});
	// }

}
