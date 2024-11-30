package bowling;

/**
 * Cette classe a pour but d'enregistrer le nombre de quilles abattues lors des
 * lancés successifs d'<b>un seul et même</b> joueur, et de calculer le score
 * final de ce joueur
 */
public class PartieMonoJoueur {

    //Déclaration de l'attribut "numPartie" permettant de distinguer chaque partie du Jeu correspondante à joueur spécifique
    private int numPartie;
    //Déclaration de l'attribut statique "attribut_statique"
    private static int attribut_statique=0;

    private final Tour premierTour; // Le premier tour
    private Tour tourCourant; // Le tour en cours
    
    //Ajout d'un attribut nomJoueur
    private String nomJoueur;

    /**
     * Constructeur
     */
    public PartieMonoJoueur() {
        // On initialise les 10 tours, en commençant par le dernier
        // Chaque tour (sauf le dernier) est initialisé avec son tour suivant
        // On construit ainsi une "liste chainée" de tours
        tourCourant = new DernierTour();
        for (int numero = 9; numero > 0; numero--) {
		tourCourant = new Tour(numero, tourCourant);
        }
        premierTour = tourCourant;
        this.numPartie = ++attribut_statique;
    }

    /**
     * Cette méthode doit être appelée à chaque lancer de boule
     *
     * @param nombreDeQuillesAbattues le nombre de quilles abattues lors de ce lancer
     * @throws IllegalStateException si la partie est terminée
     * @return vrai si le joueur doit lancer à nouveau pour continuer son tour, faux sinon
     */
    public boolean enregistreLancer(int nombreDeQuillesAbattues) {
        if (estTerminee())
            throw new IllegalStateException("La partie est terminée");

        tourCourant.enregistreLancer(nombreDeQuillesAbattues);
        if (tourCourant.estTermine()) {
            tourCourant = tourCourant.getSuivant();
        }
        return ! (this.estTerminee() || tourCourant.getBoulesLancees() == 0);
    }

    /**
     * Cette méthode donne le score du joueur.
     * Si la partie n'est pas terminée, on considère que les lancers restants
     * abattent 0 quille.
     * 
     * @return Le score du joueur
     */
    public int score() {
        return premierTour.scoreCumule();
    }

    /**
     * Teste si la partie est terminée.
     * @return vrai si le jeu est finin faux sinon
     */
    public boolean estTerminee() {
        return (tourCourant == null);
    }

    /**
     * A quel tour en est-on ?
     * @return Le numéro du tour courant [1..10], ou 0 si le jeu est fini
     */
    public int numeroTourCourant() {
        return estTerminee() ? 0 : tourCourant.getNumero();
    }

    /**
     * Quel est le numéro du prochain lancer dans le tour courant ?
     * @return Le numéro du prochain lancer pour tour courant [1..3], ou 0 si le jeu
     *         est fini
     */
    public int numeroProchainLancer() {
        return estTerminee() ? 0 : tourCourant.getBoulesLancees() + 1;
    }

    //Définition du Getter pour l'attribut "nomJoueur"
    String getNomJoueur(){
        return nomJoueur;
    }

    //Définition du Setter pour l'attribut "nomJoueur"
    void setNomJoueur(String nom){
        nomJoueur= nom;
    }

    //Définition du Getter pour l'attribut "numPartie"
    int getNumPartie(){
        return this.numPartie;
    }

}
