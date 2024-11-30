package bowling;

import java.util.ArrayList;

public class PartieMultiJoueurs implements IPartieMultiJoueurs {
    
    //Définition de l'attribut définissant le nombre de joueurs
    //Necéssaire pour implémenter la méthode demarreNouvellePartie()
    private ArrayList<PartieMonoJoueur> Joueur_PartieMono= new ArrayList<>();

    //Déclaration de l'attribut permettant de déterminer la partie courante du Jeu
    //Necéssaire pour implémenter la méthode enregistreLancer()
    private PartieMonoJoueur partieCourante;

    //Implémentation de la méthode demarreNouvellePartie()
	public String demarreNouvellePartie(String[] nomsDesJoueurs) throws IllegalArgumentException {
        if (nomsDesJoueurs == null || nomsDesJoueurs.length==0 ) {
            throw new IllegalArgumentException("Nouvelle partie ne peut pas être créée");
        }
        String message="";
        int i=0;
        for(String nom : nomsDesJoueurs){
            Joueur_PartieMono.add(new PartieMonoJoueur());
            Joueur_PartieMono.get(i).setNomJoueur(nom);
            i++;
        }
        partieCourante = Joueur_PartieMono.get(0);
        message= "Prochain tir : joueur " + Joueur_PartieMono.get(0).getNomJoueur() + ", tour n° "
         + Joueur_PartieMono.get(0).numeroTourCourant() + ", boule n° " + Joueur_PartieMono.get(0).numeroProchainLancer();
        return message;
    }

    //Implémentation de la méthode enregistreLancer()
    //1ère méthode
    public String enregistreLancer(int nombreDeQuillesAbattues) throws IllegalStateException, IllegalArgumentException {
        // Vérifie si la partie est terminée
        if (Joueur_PartieMono.stream().allMatch(PartieMonoJoueur::estTerminee)) {
            throw new IllegalStateException("Partie terminée");
        }
    
        // Vérifie la validité du nombre de quilles abattues
        if (nombreDeQuillesAbattues < 0 || nombreDeQuillesAbattues > 10) {
            throw new IllegalArgumentException("Valeur non permise pour le nombre de quilles abattues");
        }
    
        // Récupère l'indice du joueur courant
        int indicePartieCourante = Joueur_PartieMono.indexOf(partieCourante);
    
        // Enregistre le lancer pour le joueur courant
        boolean finDuTour = !partieCourante.enregistreLancer(nombreDeQuillesAbattues);
    
        // Passe au joueur suivant si nécessaire
        if (finDuTour) {
            indicePartieCourante = (indicePartieCourante + 1) % Joueur_PartieMono.size();
            partieCourante = Joueur_PartieMono.get(indicePartieCourante);
        }
    
        // Prépare le message pour le prochain lancer
        String message = "Prochain tir : joueur " + partieCourante.getNomJoueur() +
            ", tour n° " + partieCourante.numeroTourCourant() +
            ", boule n° " + partieCourante.numeroProchainLancer();
    
        return message;
    }
    

    //2ème méthode
    // public String enregistreLancer(int nombreDeQuillesAbattues) throws IllegalStateException, IllegalArgumentException {
        
    //     //1ère méthode : Vérifier que toutes les parties MonoJoueurs des différents joueurs participants sont terminées
    //     // int S=0;
    //     // for(PartieMonoJoueur J_PM : Joueur_PartieMono){
    //     //     if(J_PM.estTerminee()){
    //     //         S += 1;
    //     //     }
    //     // }
    //     // if (S == Joueur_PartieMono.size()) {
    //     //     throw new IllegalStateException("Partie terminée"); 
    //     // }
    //     //2ème méthode : Création d'une variable "tester" permettant de vérifier 
    //     boolean tester = false;
    //     if(Joueur_PartieMono.get(Joueur_PartieMono.size()-1).estTerminee() ){
    //         tester= true;
    //     }
    //     if (tester) {
    //         throw new IllegalStateException("Partie terminée"); 
    //     }

    //     if (nombreDeQuillesAbattues<0 || nombreDeQuillesAbattues>10) {
    //         throw new IllegalArgumentException("Valeur non permises pour le nombre de quilles abbatues");
    //     }

    //     int indicePartieCourante = partieCourante.getNumPartie()-1;
    //     if (partieCourante.enregistreLancer(nombreDeQuillesAbattues) == false) {
    //         if (indicePartieCourante >= 0 || indicePartieCourante <= Joueur_PartieMono.size()-2) {
    //             partieCourante = Joueur_PartieMono.get(indicePartieCourante+1);
    //             indicePartieCourante++;
    //         }
    //         if (indicePartieCourante == Joueur_PartieMono.size()-1){
    //             partieCourante = Joueur_PartieMono.get(0);
    //             indicePartieCourante= 0;
    //         }
    //     }

    //     //Préparation du message à afficher suite à chaque lancer
    //     String message="";
    //     message = "Prochain tir : joueur " + Joueur_PartieMono.get(indicePartieCourante).getNomJoueur() + ", tour n° "
    //      + Joueur_PartieMono.get(indicePartieCourante).numeroTourCourant() + ", boule n° " + Joueur_PartieMono.get(indicePartieCourante).numeroProchainLancer();
    //     return message;
    // }


    //Implémentation de la méthode scorePour()
    @Override
    public int scorePour(String nomDuJoueur) throws IllegalArgumentException {
        if (nomDuJoueur == null || nomDuJoueur.trim().isEmpty()) {
            throw new IllegalArgumentException("Le nom du joueur ne peut pas être null ou vide.");
        }

        for (PartieMonoJoueur joueur : Joueur_PartieMono) {
            if (joueur.getNomJoueur().equals(nomDuJoueur)) {
                int score = joueur.score();
                System.out.println("Score pour " + nomDuJoueur + " : " + score);
                return score;
            }
        }

        throw new IllegalArgumentException("Le joueur " + nomDuJoueur + " ne participe pas à cette partie.");
    }




}
