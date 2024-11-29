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
	// @Test
	// void testBeginGameWithException_null() {
		
	// 	String[] Joueurs= null;
	// 	Throwable exception= Assertions.assertThrows(IllegalArgumentException.class, () -> {
	// 		partie.demarreNouvellePartie(Joueurs);
	// 	});
	// 	assertEquals("Nouvelle partie ne peut pas être créée", exception.getMessage());
	// }

	// @Test
	// void testBeginGameWithException_empty() {
		
	// 	String[] Joueurs= {};
	// 	Throwable exception= Assertions.assertThrows(IllegalArgumentException.class, () -> {
	// 		partie.demarreNouvellePartie(Joueurs);
	// 	});
	// 	assertEquals("Nouvelle partie ne peut pas être créée", exception.getMessage());
	// }



}
