package packControleur;

import java.util.List;

/**
 * L'interface AbstractController définit les méthodes nécessaires pour le
 * contrôleur d'une application.
 * Les classes de contrôleur implémentent cette interface pour gérer les actions
 * et la logique de l'application.
 * 
 * @author Yanis Mechta
 */
public interface AbstractController {
    /**
     * Méthode permettant de contrôler une liste de chaînes de caractères.
     * Cette méthode est appelée pour effectuer des opérations de contrôle sur une
     * liste de données.
     * 
     * @param list La liste de chaînes de caractères à contrôler.
     */
    public void control(List<String> list);
}
