package packObserver;

/**
 * L'interface Observable définit les méthodes pour gérer les observateurs et
 * les notifier de changements.
 * 
 * @author Yanis Mechta
 */
public interface Observable {

    /**
     * Ajoute un observateur à la liste des observateurs.
     *
     * @param observer L'observateur à ajouter.
     */
    void addObserver(Observer observer);

    /**
     * Supprime un observateur de la liste des observateurs.
     *
     * @param observer L'observateur à supprimer.
     */
    void removeObserver(Observer observer);

    /**
     * Notifie tous les observateurs que des changements ont eu lieu.
     */
    void notifyObservers();
}
