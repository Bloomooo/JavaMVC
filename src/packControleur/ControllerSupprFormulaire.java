package packControleur;

import java.util.List;
import javax.swing.JOptionPane;

import packModele.Etudiant;
import packModele.Promotion;

/**
 * Le contrôleur ControllerSupprFormulaire gère la suppression d'étudiants d'une
 * promotion.
 * Il effectue des opérations de contrôle sur les données entrées dans le
 * formulaire de suppression d'étudiant.
 *
 * @author Yanis Mechta
 */
public class ControllerSupprFormulaire implements AbstractController {
    // La promotion à partir de laquelle les données sont extraites
    private Promotion promotion;

    /**
     * Constructeur de la classe ControllerSupprFormulaire.
     * 
     * @param promotion La promotion à partir de laquelle les données sont
     *                  extraites.
     */
    public ControllerSupprFormulaire(Promotion promotion) {
        this.promotion = promotion;
    }

    /**
     * Contrôle les données entrées dans le formulaire de suppression d'étudiant.
     * 
     * @param list La liste des données du formulaire (numéro d'étudiant).
     */
    @Override
    public void control(List<String> list) {
        try {
            int num = Integer.parseInt(list.get(0));
            if (num <= 0) {
                throw new IllegalArgumentException("Le nombre doit être positif");
            } else {
                Etudiant etudiant = promotion.search(list.get(0));
                promotion.remove(etudiant);
            }

        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Invalid Input",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
}
