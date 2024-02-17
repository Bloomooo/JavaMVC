package packModele;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

import packObserver.Observable;
import packObserver.Observer;

/**
 * La classe Promotion représente une promotion d'étudiants.
 * Elle permet de gérer la liste des étudiants et d'observer les changements.
 * 
 * @author Yanis Mechta
 */
public class Promotion implements Observable {
    /**
     * Liste des étudiants dans la promotion.
     */
    private List<Etudiant> listEtudiant;

    /**
     * Liste des observateurs de la promotion.
     */
    private List<Observer> observers;

    /**
     * Indique si un étudiant avec les mêmes informations existe déjà.
     */
    private boolean alreadyExists = false;

    /**
     * Constructeur de la classe Promotion.
     * Initialise les listes d'étudiants et d'observateurs, et charge la liste des
     * étudiants depuis un fichier.
     */
    public Promotion() {
        listEtudiant = new ArrayList<>();
        observers = new ArrayList<>();
        loadListEtudiant();
    }

    /**
     * Ajoute un étudiant à la liste de la promotion.
     * 
     * @param etudiant L'étudiant à ajouter.
     */
    public void add(Etudiant etudiant) {
        try {
            for (Etudiant e : listEtudiant) {
                if (e.getNumero().equals(etudiant.getNumero())) {
                    alreadyExists = true;
                    throw new Exception("Le numéro " + etudiant.getNumero() + " est déjà attribué !");
                } else if (e.getNom().equals(etudiant.getNom()) && e.getPrenom().equals(etudiant.getPrenom())) {
                    JOptionPane.showMessageDialog(null,
                            "L'étudiant " + etudiant.getNom() + " " + etudiant.getPrenom() + " existe déjà !",
                            null,
                            JOptionPane.ERROR_MESSAGE);
                    alreadyExists = true;
                }
            }
            if (!alreadyExists) {
                listEtudiant.add(etudiant);
                notifyObservers();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Supprime un étudiant de la liste de la promotion.
     * 
     * @param etudiant L'étudiant à supprimer.
     */
    public void remove(Etudiant etudiant) {
        try {
            if (listEtudiant.contains(etudiant)) {
                listEtudiant.remove(etudiant);
                notifyObservers();
            } else {
                throw new Exception("L'étudiant n'existe pas !");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Recherche un étudiant par son numéro d'étudiant.
     * 
     * @param num Le numéro d'étudiant à rechercher.
     * @return L'étudiant correspondant ou null s'il n'est pas trouvé.
     */
    public Etudiant search(String num) {
        for (Etudiant etudiant : listEtudiant) {
            if (etudiant.getNumero().equals(num)) {
                return etudiant;
            }
        }
        return null;
    }

    /**
     * Modifie les informations d'un étudiant existant.
     * 
     * @param etudiant L'étudiant à modifier.
     * @param num      Le nouveau numéro d'étudiant.
     * @param nom      Le nouveau nom de l'étudiant.
     * @param prenom   Le nouveau prénom de l'étudiant.
     * @param bac      Le nouveau type de baccalauréat de l'étudiant.
     * @param dept     Le nouveau département de l'étudiant.
     */
    public void modify(Etudiant etudiant, String num, String nom, String prenom, String bac, String dept) {
        for (Etudiant e : listEtudiant) {
            if (e.equals(etudiant)) {
                e.setNumero(num);
                e.setNom(nom);
                e.setPrenom(prenom);
                e.setBac(bac);
                e.setDept(dept);
            }
        }
        notifyObservers();
    }

    /**
     * Charge la liste des étudiants à partir d'un fichier CSV.
     */
    public void loadListEtudiant() {
        File file = new File("././data/promoBUT.csv");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(";");
                if (data.length == 5) {
                    Etudiant etudiant = new Etudiant(data[0], data[1], data[2], data[4], data[3]);
                    listEtudiant.add(etudiant);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Le fichier n'a pas été trouvé : " + e.getMessage());
        }
    }

    /**
     * Ajoute un observateur à la liste des observateurs de la promotion.
     * 
     * @param observer L'observateur à ajouter.
     */
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Supprime un observateur de la liste des observateurs de la promotion.
     * 
     * @param observer L'observateur à supprimer.
     */
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Méthode privée pour notifier les observateurs et mettre à jour la liste des
     * départements.
     */
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    /**
     * @return La liste des étudiants de la promotion.
     */
    public List<Etudiant> getListEtudiant() {
        return listEtudiant;
    }

    /**
     * Récupère le nombre d'étudiants pour un département donné.
     * 
     * @param deptCode Le code du département à rechercher.
     * @return Le nombre d'étudiants dans le département spécifié.
     */
    public double getDeptCount(String deptCode) {
        double nb = 0.0;
        for (Etudiant e : listEtudiant) {
            if (e.getDept().equals(deptCode)) {
                nb++;
            }
        }
        return nb;
    }

    /**
     * Récupère le nombre d'étudiants ayant un baccalauréat spécifique dans la
     * promotion associée.
     * 
     * @param bac Le type de baccalauréat pour lequel on souhaite obtenir le nombre
     *            d'étudiants.
     * @return Le nombre d'étudiants ayant le baccalauréat spécifié.
     */
    public double getBacCount(String bac) {
        double nb = 0.0;
        for (Etudiant e : listEtudiant) {
            if (e.getBac().equals(bac)) {
                nb++;
            }
        }
        return nb;
    }

}
