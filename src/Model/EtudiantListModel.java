package Model;

import java.util.List;

import javax.swing.AbstractListModel;

import packModele.Etudiant;

/**
 * Le modèle de liste pour les étudiants.
 * 
 * @author Yanis Mechta
 */
public class EtudiantListModel extends AbstractListModel<Etudiant> {
    private List<Etudiant> etudiants;

    /**
     * Constructeur du modèle de liste des étudiants.
     *
     * @param etudiants La liste des étudiants à afficher dans le modèle.
     */
    public EtudiantListModel(List<Etudiant> etudiants) {
        this.etudiants = etudiants;
    }

    /**
     * Renvoie le nombre d'éléments dans le modèle.
     *
     * @return Le nombre d'éléments dans le modèle.
     */
    @Override
    public int getSize() {
        return etudiants.size();
    }

    /**
     * Renvoie l'élément à l'indice spécifié.
     *
     * @param index L'indice de l'élément à renvoyer.
     * @return L'élément à l'indice spécifié.
     */
    @Override
    public Etudiant getElementAt(int index) {
        return etudiants.get(index);
    }
}
