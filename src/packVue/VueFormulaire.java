package packVue;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import packControleur.ControllerAddFormulaire;
import packControleur.ControllerSupprFormulaire;
import packModele.Etudiant;
import packModele.Promotion;
import packObserver.Observer;

/**
 * Cette classe représente la vue du formulaire pour ajouter ou supprimer des
 * étudiants.
 * Elle implémente l'interface Observer pour être notifiée des mises à jour du
 * modèle.
 * 
 * @author L Buathier & A. Peytavie
 * @modified by Yanis Mechta
 */
public class VueFormulaire extends AbstractVue implements Observer {

    /**
     * Champ de texte pour saisir le numéro de l'étudiant à ajouter.
     */
    private final JTextField txtNumeroAjout = new JTextField(10);

    /**
     * Champ de texte pour saisir le numéro de l'étudiant à supprimer.
     */
    private final JTextField txtNumeroSuppr = new JTextField(10);

    /**
     * Champ de texte pour saisir le nom de l'étudiant.
     */
    private final JTextField txtNom = new JTextField(10);

    /**
     * Champ de texte pour saisir le prénom de l'étudiant.
     */
    private final JTextField txtPrenom = new JTextField(10);

    /**
     * Boîte de sélection pour choisir le type de baccalauréat.
     */
    private final JComboBox boxBac = new JComboBox();

    /**
     * Boîte de sélection pour choisir le département de l'étudiant.
     */
    private final JComboBox boxDpt = new JComboBox();

    /**
     * Label pour afficher "Numero:" dans la partie d'ajout.
     */
    private final JLabel lblNumeroAjout = new JLabel("Numero:");

    /**
     * Label pour afficher "Numero:" dans la partie de suppression.
     */
    private final JLabel lblNumeroSuppr = new JLabel("Numero:");

    /**
     * Label pour afficher "Nom:".
     */
    private final JLabel lblNom = new JLabel("Nom:");

    /**
     * Label pour afficher "Prénom:".
     */
    private final JLabel lblPrenom = new JLabel("Prénom:");

    /**
     * Label pour afficher "Bac:".
     */
    private final JLabel lblBac = new JLabel("Bac:");

    /**
     * Label pour afficher "Dpt:".
     */
    private final JLabel lblDpt = new JLabel("Dpt:");

    /**
     * Label pour afficher "Ajout d'un étudiant".
     */
    private final JLabel lblPartieAjout = new JLabel("Ajout d'un étudiant");

    /**
     * Label pour afficher "Suppression d'un étudiant".
     */
    private final JLabel lblPartieSuppr = new JLabel("Suppression d'un étudiant:");

    /**
     * Label d'espacement.
     */
    private final JLabel lblEspace = new JLabel("    ");

    /**
     * Label de séparation horizontal.
     */
    private final JLabel lblSeparation = new JLabel(
            "-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

    /**
     * Bouton pour effectuer l'ajout ou la modification de l'étudiant.
     */
    private final JButton btAjout = new JButton("Ajout");

    /**
     * Bouton pour supprimer l'étudiant.
     */
    private final JButton btSuppr = new JButton("Supprimer");

    /**
     * Contrôleur responsable de la gestion de l'ajout d'étudiant.
     */
    private ControllerAddFormulaire controllerAdd;

    /**
     * Contrôleur responsable de la gestion de la suppression d'étudiant.
     */
    private ControllerSupprFormulaire controllerRemove;

    /**
     * Liste des informations de l'étudiant.
     */
    private List<String> listInfoEtudiant;

    /**
     * Référence à l'objet Promotion pour la gestion des étudiants.
     */
    private Promotion promotion;

    /**
     * Indique si le formulaire est en mode d'édition.
     */
    private boolean isEditMode = false;

    /**
     * Constructeur de la classe VueFormulaire.
     *
     * @param promotion La promotion à laquelle appartient l'étudiant.
     */
    public VueFormulaire(Promotion promotion) {
        this.promotion = promotion;
        this.controllerAdd = new ControllerAddFormulaire(this.promotion);
        this.controllerRemove = new ControllerSupprFormulaire(this.promotion);
        this.listInfoEtudiant = new ArrayList<String>();

        initFrame();

        this.pack();
    }

    /**
     * Initialise la disposition des composants de l'interface utilisateur.
     */
    private void initFrame() {
        // remplissage des box
        boxDpt.addItem("- - -");
        for (int i = 1; i < 96; i++) {
            if (i != 20) {
                if (i < 10) {
                    boxDpt.addItem("0" + i);
                } else {
                    boxDpt.addItem("" + i);
                }
            } else {
                boxDpt.addItem("2A");
                boxDpt.addItem("2B");
            }
        }
        for (int i = 971; i < 977; i++) {
            boxDpt.addItem("" + i);
        }
        // seconde box
        boxBac.addItem("- - -");
        boxBac.addItem("General");
        boxBac.addItem("Techno");
        boxBac.addItem("Pro");
        boxBac.addItem("Autre");

        // placement des objets
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 2;
        this.add(lblPartieAjout, gc);
        gc.gridx = 0;
        gc.gridy = 1;
        gc.gridwidth = 1;
        this.add(lblNumeroAjout, gc);
        gc.gridx = 1;
        this.add(txtNumeroAjout, gc);
        gc.gridx = 2;
        this.add(lblPrenom, gc);
        gc.gridx = 3;
        this.add(txtPrenom, gc);
        gc.gridx = 4;
        this.add(lblNom, gc);
        gc.gridx = 5;
        this.add(txtNom, gc);
        gc.gridx = 6;
        this.add(lblBac, gc);
        gc.gridx = 7;
        this.add(boxBac, gc);
        gc.gridx = 8;
        this.add(lblDpt, gc);
        gc.gridx = 9;
        this.add(boxDpt, gc);
        gc.gridx = 10;
        this.add(lblEspace, gc);
        gc.gridx = 11;
        this.add(btAjout, gc);
        gc.gridx = 0;
        gc.gridy = 2;
        gc.gridwidth = 11;
        // this.add(lblSeparation, gc);
        this.add(new JSeparator(SwingConstants.HORIZONTAL), gc);

        gc.gridx = 0;
        gc.gridy = 3;
        gc.gridwidth = 2;
        this.add(lblPartieSuppr, gc);
        gc.gridx = 0;
        gc.gridy = 4;
        gc.gridwidth = 1;
        this.add(lblNumeroSuppr, gc);
        gc.gridx = 1;
        this.add(txtNumeroSuppr, gc);
        gc.gridx = 11;
        this.add(btSuppr, gc);
        initListeners();
    }

    /**
     * Initialise les écouteurs d'événements pour les boutons.
     */
    private void initListeners() {
        btAjout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isEditMode) {
                    listInfoEtudiant.clear();
                    listInfoEtudiant.add(txtNumeroAjout.getText());
                    listInfoEtudiant.add(txtNom.getText().toUpperCase());
                    listInfoEtudiant.add(txtPrenom.getText().toUpperCase());
                    listInfoEtudiant.add((String) boxBac.getSelectedItem());
                    listInfoEtudiant.add((String) boxDpt.getSelectedItem());
                    controllerAdd.control(listInfoEtudiant);
                    resetForm();
                } else {
                    controllerAdd.modify(txtNumeroAjout.getText(), txtNom.getText(), txtPrenom.getText(),
                            boxBac.getSelectedItem().toString(), boxDpt.getSelectedItem().toString());
                    resetForm();
                }

            }
        });

        btSuppr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listInfoEtudiant.clear();
                listInfoEtudiant.add(txtNumeroSuppr.getText());
                controllerRemove.control(listInfoEtudiant);
                txtNumeroSuppr.setText("");

            }
        });
    }

    /**
     * Permet de modifier les champs du formulaire avec les informations de
     * l'étudiant.
     *
     * @param etudiant L'étudiant dont les informations seront affichées dans le
     *                 formulaire.
     */
    public void modifyEtudiant(Etudiant etudiant) {
        txtNumeroAjout.setText(etudiant.getNumero());

        txtNom.setText(etudiant.getNom());
        txtPrenom.setText(etudiant.getPrenom());
        if (etudiant.getBac().equals("G")) {
            boxBac.setSelectedIndex(1);
        } else if (etudiant.getBac().equals("T")) {
            boxBac.setSelectedIndex(2);
        } else if (etudiant.getBac().equals("Pro")) {
            boxBac.setSelectedIndex(3);
        } else {
            boxBac.setSelectedIndex(4);
        }

        if (etudiant.getDept().equals("1")) {
            boxDpt.setSelectedIndex(1);

        } else {
            boxDpt.setSelectedItem(etudiant.getDept());
        }
        btAjout.setText("Modifier");
        isEditMode = true;
        controllerAdd.getEtudiantModify(etudiant);

    }

    /**
     * Réinitialise les champs du formulaire à leurs valeurs par défaut.
     */
    public void resetForm() {
        txtNumeroAjout.setText("");
        txtNom.setText("");
        txtPrenom.setText("");
        boxBac.setSelectedIndex(0);
        boxDpt.setSelectedIndex(0);
        btAjout.setText("Ajout");
        isEditMode = false;
    }

    /**
     * Méthode appelée lorsqu'une mise à jour de la vue est nécessaire.
     * Cette méthode ne fait rien dans cette classe.
     */
    @Override
    public void updateView() {
        // Cette méthode peut être étendue pour gérer les mises à jour de la vue.
    }

    /**
     * Méthode appelée lorsqu'une mise à jour du modèle est détectée.
     * Cette méthode ne fait rien dans cette classe.
     */
    @Override
    public void update() {
        // Cette méthode peut être étendue pour réagir aux mises à jour du modèle.
    }
}
