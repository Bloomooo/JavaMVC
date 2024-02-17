package packControleur;

import java.util.List;

import packModele.Etudiant;
import packModele.Promotion;

/**
 * Le contrôleur ControllerList gère l'affichage de la liste des étudiants et
 * les recherches d'étudiants.
 * Il permet d'accéder aux données de la promotion et de récupérer la liste des
 * étudiants ou de rechercher un étudiant par numéro.
 * 
 * @author Yanis Mechta
 */
public class ControllerList implements AbstractController {
    // La promotion à partir de laquelle les données sont extraites
    private Promotion promotion;

    /**
     * Constructeur de la classe ControllerList.
     * 
     * @param promotion La promotion à partir de laquelle les données sont
     *                  extraites.
     */
    public ControllerList(Promotion promotion) {
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
     * Obtient la liste complète des étudiants de la promotion.
     * 
     * @return Une liste d'objets de type Etudiant représentant les étudiants de la
     *         promotion.
     */
    public List<Etudiant> getListEtudiants() {
        return promotion.getListEtudiant();
    }

    /**
     * Recherche un étudiant dans la promotion par son numéro d'étudiant.
     * 
     * @param num Le numéro d'étudiant à rechercher.
     * @return L'objet Etudiant correspondant au numéro donné, ou null s'il n'existe
     *         pas.
     */
    public Etudiant researchEtudiant(String num) {
        return this.promotion.search(num);
    }
}
