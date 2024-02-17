package packVue;

import javax.swing.JInternalFrame;

/**
 * La classe abstraite AbstractVue étend JInternalFrame et sert de base pour les
 * vues de l'application.
 * 
 * @author L Buathier & A. Peytavie
 * @modified by Yanis Mechta
 */
public abstract class AbstractVue extends JInternalFrame {

    /**
     * Met à jour la vue en fonction des données ou de l'état actuel.
     */
    public abstract void updateView();
}
