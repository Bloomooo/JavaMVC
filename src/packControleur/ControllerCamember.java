package packControleur;

import java.util.List;

import packModele.Etudiant;
import packModele.Promotion;

/**
 * Le contrôleur ControllerCamember gère les calculs relatifs aux étudiants par
 * département.
 * Il permet d'obtenir des statistiques sur le nombre d'étudiants par
 * département.
 * 
 * @author Yanis Mechta
 * @version 1.0
 */
public class ControllerCamember implements AbstractController {
    // La promotion à partir de laquelle les données sont extraites
    private Promotion promotion;

    /**
     * Constructeur de la classe ControllerCamember.
     * 
     * @param promotion La promotion à partir de laquelle les données sont
     *                  extraites.
     */
    public ControllerCamember(Promotion promotion) {
        this.promotion = promotion;
    }

    /**
     * Contrôle les données passées sous forme de liste (non utilisé dans cette
     * classe).
     * 
     * @param list La liste de données à contrôler (non utilisé dans cette classe).
     */
    @Override
    public void control(List<String> list) {
        // Ne fait rien dans cette classe
    }

    /**
     * Récupère le nombre d'étudiants appartenant à un département spécifique dans
     * la promotion associée.
     * 
     * @param dept Le département pour lequel on souhaite obtenir le nombre
     *             d'étudiants.
     * @return Le nombre d'étudiants appartenant au département spécifié.
     */
    public double controllerDeptCount(String dept) {
        return promotion.getDeptCount(dept);
    }

    /**
     * Récupère la liste des étudiants de la promotion associée.
     * 
     * @return La liste des étudiants de la promotion.
     */
    public List<Etudiant> getLEtudiants() {
        return promotion.getListEtudiant();
    }
}
