package Bibliotheque;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Créer une instance de Bibliotheque
        Bibliotheque bibliotheque = new Bibliotheque();

        // Créer des livres
        Roman roman1 = new Roman("1", "Le Feu sous la Soutane", "Benjamin Halevy", "L'Harmattan", "Historique");
        Biographie biographie1 = new Biographie("2", "Patrice Lumumba", "Georges Nzongola-Ntalaja",
                "Ohio University Press", "Politique");
        ScienceFiction sf1 = new ScienceFiction("3", "Mamiwata", "Sylvie Boki", "L'Harmattan", "Fantastique");

        Roman roman2 = new Roman("4", "Congo Inc.", "In Koli Jean Bofane", "Actes Sud", "Contemporain");
        Biographie biographie2 = new Biographie("5", "Mobutu", "Pierre Péan", "Fayard", "Politique");
        ScienceFiction sf2 = new ScienceFiction("6", "Kinshasa jusqu'au cou", "Fiston Mwanza Mujila", "Métailié",
                "Futuriste");

        Roman roman3 = new Roman("7", "Tram 83", "Fiston Mwanza Mujila", "Métailié", "Contemporain");
        Biographie biographie3 = new Biographie("8", "Joseph Kabila", "Pierre Boisselet", "Karthala", "Politique");

        // Ajouter des livres à la bibliothèque
        bibliotheque.ajouterLivre(roman1);
        bibliotheque.ajouterLivre(roman2);
        bibliotheque.ajouterLivre(sf1);
        bibliotheque.ajouterLivre(biographie1);
        bibliotheque.ajouterLivre(biographie2);
        bibliotheque.ajouterLivre(sf2);
        bibliotheque.ajouterLivre(roman3);
        bibliotheque.ajouterLivre(biographie3);


        Scanner scanner = new Scanner(System.in);
        String choix;

        do {
            System.out.println("\n1. Obtenir un livre par son code");
            System.out.println("2. Modifier un livre");
            System.out.println("3. Rechercher un livre par son nom");
            System.out.println("4. Lister les livres par lettre");
            System.out.println("5. Obtenir le nombre total de livres");
            System.out.println("6. Obtenir les livres par catégorie");
            System.out.println("7. Supprimer un livre");
            System.out.println("8. Ajouter un livre");
            System.out.println("9. Quitter");
            System.out.print("Choisissez une option : ");
            choix = scanner.nextLine();

            switch (choix) {
                case "1":
                    System.out.print("Entrez le code du livre : ");
                    String code = scanner.nextLine();
                    Livre livre = bibliotheque.getLivre(code);
                    System.out.println("Le livre obtenu est : " + livre.getNom());
                    break;
                case "2":
    System.out.print("Entrez le code du livre à modifier : ");
    String codeModif = scanner.nextLine();
    System.out.print("Entrez le nouveau nom du livre : ");
    String nouveauNom = scanner.nextLine();
    System.out.print("Entrez le nouvel auteur du livre : ");
    String nouvelAuteur = scanner.nextLine();
    System.out.print("Entrez la nouvelle maison d'édition du livre : ");
    String nouvelleMaisonEdition = scanner.nextLine();
    System.out.print("Entrez la nouvelle catégorie du livre : ");
    String nouvelleCategorie = scanner.nextLine();
    Livre nouveauLivre = new Livre(codeModif, nouveauNom, nouvelAuteur, nouvelleMaisonEdition, nouvelleCategorie);
    bibliotheque.modifierLivre(codeModif, nouveauLivre);
    break;
case "3":
    System.out.print("Entrez le nom du livre à rechercher : ");
    String nomRecherche = scanner.nextLine();
    Livre livreRecherche = bibliotheque.rechercherParNom(nomRecherche);
    System.out.println("Le livre recherché est : " + livreRecherche.getNom());
    break;
case "4":
    System.out.print("Entrez la lettre par laquelle les livres commencent : ");
    char lettre = scanner.nextLine().charAt(0);
    Map<Object, Object> livresParLettre = bibliotheque.listerLivresParLettre(lettre);
    System.out.println("Les livres commençant par " + lettre + " sont : " + livresParLettre);
    break;
case "6":
    System.out.print("Entrez la catégorie de livres à afficher : ");
    String categorie = scanner.nextLine();
    Map<Object, Object> livresParCategorie = bibliotheque.livresParCategorie(categorie);
    System.out.println("Les livres de la catégorie '" + categorie + "' sont : " + livresParCategorie);
    break;
case "7":
    System.out.print("Entrez le code du livre à supprimer : ");
    String codeSuppr = scanner.nextLine();
    bibliotheque.supprimerLivre(codeSuppr);
    System.out.println("Le livre avec le code '" + codeSuppr + "' a été supprimé.");
    break;
                case "8":
    System.out.print("Entrez le code du livre : ");
    String codeAjout = scanner.nextLine();
    System.out.print("Entrez le nom du livre : ");
    String nomAjout = scanner.nextLine();
    System.out.print("Entrez l'auteur du livre : ");
    String auteurAjout = scanner.nextLine();
    System.out.print("Entrez la maison d'édition du livre : ");
    String maisonEditionAjout = scanner.nextLine();
    System.out.print("Entrez la catégorie du livre : ");
    String categorieAjout = scanner.nextLine();
    Livre livreAjout = new Livre(codeAjout, nomAjout, auteurAjout, maisonEditionAjout, categorieAjout);
    bibliotheque.ajouterLivre(livreAjout);
    System.out.println("Le livre '" + nomAjout + "' a été ajouté.");
    break;
case "9":
    System.out.println("Au revoir !");
    break;
default:
    System.out.println("Option non valide. Veuillez réessayer.");
}
} while (!choix.equals("9"));
    }
}