package packControleur;

import java.util.List;
import javax.swing.JOptionPane;

import packModele.Etudiant;
import packModele.Promotion;

/**
 * Le contrôleur ControllerSupprList gère la suppression de plusieurs étudiants
 * d'une promotion.
 * Il effectue des opérations de contrôle sur les données entrées pour supprimer
 * les étudiants.
 * 
 * @author Yanis Mechta
 */
public class ControllerSupprList implements AbstractController {
    // La promotion à partir de laquelle les données sont extraites
    private Promotion promotion;

    /**
     * Constructeur de la classe ControllerSupprList.
     * 
     * @param promotion La promotion à partir de laquelle les données sont
     *                  extraites.
     */
    public ControllerSupprList(Promotion promotion) {
        this.promotion = promotion;
    }

    /**
     * Contrôle les données entrées pour supprimer les étudiants de la promotion.
     * 
     * @param list La liste des numéros d'étudiants à supprimer.
     */
    @Override
    public void control(List<String> list) {
        try {
            for (String num : list) {
                Etudiant etudiant = promotion.search(num);
                if (etudiant != null) {
                    promotion.remove(etudiant);
                } else {
                    throw new NullPointerException("Le champ est vide !");
                }
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    "Invalid Input",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
}
