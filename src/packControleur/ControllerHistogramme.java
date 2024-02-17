package packControleur;

import java.util.List;

import packModele.Promotion;

/**
 * Le contrôleur ControllerHistogramme gère les calculs relatifs aux
 * baccalauréats des étudiants.
 * Il permet d'obtenir des statistiques sur le nombre d'étudiants par type de
 * baccalauréat.
 * 
 * @author Yanis Mechta
 * @version 1.0
 */
public class ControllerHistogramme implements AbstractController {
    // La promotion à partir de laquelle les données sont extraites
    private Promotion promotion;

    /**
     * Constructeur de la classe ControllerHistogramme.
     * 
     * @param promotion La promotion à partir de laquelle les données sont
     *                  extraites.
     */
    public ControllerHistogramme(Promotion promotion) {
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
     * Récupère le nombre d'étudiants ayant un baccalauréat spécifique dans la
     * promotion associée.
     * 
     * @param bac Le type de baccalauréat à compter.
     * @return Le nombre d'étudiants ayant le baccalauréat spécifié.
     */
    public Double controllerBacCount(String bac) {
        return this.promotion.getBacCount(bac);
    }
}
