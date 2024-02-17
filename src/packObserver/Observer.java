package packObserver;

/**
 * L'interface Observer définit une méthode de mise à jour pour les objets
 * observateurs.
 * Les classes qui souhaitent être notifiées des changements d'un sujet observé
 * doivent implémenter cette interface et fournir une implémentation pour la
 * méthode update().
 * 
 * @author Yanis Mechta
 */
public interface Observer {

    /**
     * Méthode appelée lorsque l'objet observé subit un changement.
     * Les objets observateurs implémentent cette méthode pour réagir aux mises à
     * jour
     * émises par le sujet observé.
     */
    public void update();
}
