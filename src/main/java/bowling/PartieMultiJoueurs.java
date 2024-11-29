package bowling;

import java.util.ArrayList;

public class PartieMultiJoueurs implements IPartieMultiJoueurs {
    
    //Définition de l'attribut définissant le nombre de joueurs
    private ArrayList<PartieMonoJoueur> Joueur_PartieMono= new ArrayList<>();

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
        message= "Prochain tir : joueur " + Joueur_PartieMono.get(0).getNomJoueur() + ", tour n° "
         + Joueur_PartieMono.get(0).numeroTourCourant() + ", boule n° " + Joueur_PartieMono.get(0).numeroProchainLancer();
        return message;
    }

    //Implémentation de la méthode enregistreLancer()
	public String enregistreLancer(int nombreDeQuillesAbattues) throws IllegalStateException {
        String message="";
        return message;
    }

    //Implémentation de la méthode scorePour()
	public int scorePour(String nomDuJoueur) throws IllegalArgumentException {
        int score= 0;
        return score;
    }


}
