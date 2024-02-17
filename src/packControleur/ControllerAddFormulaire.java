package packControleur;

import java.util.List;
import javax.swing.JOptionPane;

import packModele.Etudiant;
import packModele.Promotion;

/**
 * Le contrôleur ControllerAddFormulaire gère l'ajout d'étudiants à une
 * promotion.
 * Il effectue des opérations de contrôle sur les données entrées dans le
 * formulaire d'ajout d'étudiant.
 * 
 * @author Yanis Mechta
 */
public class ControllerAddFormulaire implements AbstractController {
    /**
     * La promotion à laquelle les étudiants seront ajoutés
     */
    private Promotion promotion;

    /**
     * L'étudiant en cours de modification
     */
    private Etudiant etudiant;

    /**
     * Constructeur de la classe ControllerAddFormulaire.
     * 
     * @param promotion La promotion à laquelle les étudiants seront ajoutés.
     */
    public ControllerAddFormulaire(Promotion promotion) {
        this.promotion = promotion;
    }

    /**
     * Contrôle les données entrées dans le formulaire d'ajout d'étudiant.
     * 
     * @param list La liste des données du formulaire (numéro, nom, prénom,
     *             baccalauréat, département).
     */
    @Override
    public void control(List<String> list) {
        boolean isNumber = list.get(0).matches("\\d+");
        try {
            for (int i = 0; i < 5; i++) {
                if (list.get(i).equals("")) {
                    throw new NumberFormatException("Un ou plusieurs champs sont vides.");
                }
            }
            int num = Integer.parseInt(list.get(0));
            if (num <= 0 || !isNumber) {
                throw new NumberFormatException("Le premier champ doit être un nombre valide.");
            } else {
                Etudiant etudiant = new Etudiant(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4));
                promotion.add(etudiant);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    null,
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Modifie les informations d'un étudiant existant.
     * 
     * @param num    Le nouveau numéro d'étudiant.
     * @param nom    Le nouveau nom de l'étudiant.
     * @param prenom Le nouveau prénom de l'étudiant.
     * @param bac    Le nouveau type de baccalauréat de l'étudiant.
     * @param dept   Le nouveau département de l'étudiant.
     */
    public void modify(String num, String nom, String prenom, String bac, String dept) {
        try {
            if (num.equals("") || nom.equals("") || prenom.equals("") || bac.equals("")
                    || dept.equals("")) {
                throw new NullPointerException("Un ou plusieurs champs sont vides.");
            } else {
                promotion.modify(etudiant, num, nom, prenom, bac, dept);
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Invalid Input",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Permet de spécifier l'étudiant en cours de modification.
     * 
     * @param etudiant L'étudiant à modifier.
     */
    public void getEtudiantModify(Etudiant etudiant) {
        this.etudiant = etudiant;
    }
}
