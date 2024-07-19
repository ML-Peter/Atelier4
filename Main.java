package Bibliotheque;

import java.io.*;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Créer une instance de Bibliotheque
        Bibliotheque bibliotheque = new Bibliotheque();

        // Charger les livres depuis le fichier
        chargerLivresDepuisFichier(bibliotheque, "bibliotheque.txt");

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
                    if (livre != null) {
                        System.out.println("Le livre obtenu est : " + livre.getNom());
                    } else {
                        System.out.println("Livre non trouvé.");
                    }
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
                    Livre nouveauLivre = new Livre(codeModif, nouveauNom, nouvelAuteur, nouvelleMaisonEdition,
                            nouvelleCategorie);
                    bibliotheque.modifierLivre(codeModif, nouveauLivre);
                    sauvegarderLivresDansFichier(bibliotheque, "bibliotheque.txt");
                    break;
                case "3":
                    System.out.print("Entrez le nom du livre à rechercher : ");
                    String nomRecherche = scanner.nextLine();
                    Livre livreRecherche = bibliotheque.rechercherParNom(nomRecherche);
                    if (livreRecherche != null) {
                        System.out.println("Le livre recherché est : " + livreRecherche.getNom());
                    } else {
                        System.out.println("Livre non trouvé.");
                    }
                    break;
                case "4":
                    System.out.print("Entrez la lettre par laquelle les livres commencent : ");
                    char lettre = scanner.nextLine().charAt(0);
                    Map<Object, Object> livresParLettre = bibliotheque.listerLivresParLettre(lettre);
                    System.out.println("Les livres commençant par " + lettre + " sont : " + livresParLettre);
                    break;
                case "5":
                    System.out.println("Le nombre total de livres est : " + bibliotheque.getNombreTotalLivres());
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
                    sauvegarderLivresDansFichier(bibliotheque, "bibliotheque.txt");
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
                    appendLivreDansFichier(livreAjout, "bibliotheque.txt");
                    System.out.println("Le livre '" + nomAjout + " de " + auteurAjout + "' a été ajouté.");
                    break;
                case "9":
                    System.out.println("Au revoir !");
                    break;
                default:
                    System.out.println("Option non valide. Veuillez réessayer.");
            }
        } while (!choix.equals("9"));

        scanner.close();
    }

    private static void chargerLivresDepuisFichier(Bibliotheque bibliotheque, String filePath) {
        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            while (fileScanner.hasNextLine()) {
                String[] codeLine = fileScanner.nextLine().split(": ");
                String[] nomLine = fileScanner.nextLine().split(": ");
                String[] auteurLine = fileScanner.nextLine().split(": ");
                String[] maisonEditionLine = fileScanner.nextLine().split(": ");
                String[] categorieLine = fileScanner.nextLine().split(": ");
                fileScanner.nextLine(); // Skip empty line

                if (codeLine.length > 1 && nomLine.length > 1 && auteurLine.length > 1 && maisonEditionLine.length > 1
                        && categorieLine.length > 1) {
                    String code = codeLine[1];
                    String nom = nomLine[1];
                    String auteur = auteurLine[1];
                    String maisonEdition = maisonEditionLine[1];
                    String categorie = categorieLine[1];

                    Livre livre;
                    if (categorie.equals("Biographie")) {
                        livre = new Biographie(code, nom, auteur, maisonEdition, categorie);
                    } else if (categorie.equals("ScienceFiction")) {
                        livre = new ScienceFiction(code, nom, auteur, maisonEdition, categorie);
                    } else {
                        livre = new Roman(code, nom, auteur, maisonEdition, categorie);
                    }

                    bibliotheque.ajouterLivre(livre);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Fichier non trouvé. Aucune donnée chargée.");
        }
    }

    private static void sauvegarderLivresDansFichier(Bibliotheque bibliotheque, String filePath) {
        try (PrintWriter writer = new PrintWriter(new File(filePath))) {
            for (Livre livre : bibliotheque.getLivres()) {
                writer.println("Code: " + livre.getCode());
                writer.println("Nom: " + livre.getNom());
                writer.println("Auteur: " + livre.getAuteur());
                writer.println("Maison d'édition: " + livre.getMaisonEdition());
                writer.println("Catégorie: " + livre.getCategorie());
                writer.println();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Une erreur est survenue lors de l'écriture du fichier.");
            e.printStackTrace();
        }
    }

    private static void appendLivreDansFichier(Livre livre, String filePath) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, true))) {
            writer.println("Code: " + livre.getCode());
            writer.println("Nom: " + livre.getNom());
            writer.println("Auteur: " + livre.getAuteur());
            writer.println("Maison d'édition: " + livre.getMaisonEdition());
            writer.println("Catégorie: " + livre.getCategorie());
            writer.println();
        } catch (IOException e) {
            System.out.println("Une erreur est survenue lors de l'ajout du livre au fichier.");
            e.printStackTrace();
        }
    }
}
