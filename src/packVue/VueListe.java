package packVue;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import Model.EtudiantListModel;
import packControleur.ControllerList;
import packControleur.ControllerSupprList;
import packModele.Promotion;
import packObserver.Observer;

/**
 * La classe VueListe représente la vue qui affiche la liste des étudiants et
 * permet leur suppression.
 * Elle observe les changements dans la promotion et se met à jour en
 * conséquence.
 * 
 * @author L Buathier & A. Peytavie
 * @modified by Yanis Mechta
 */
public class VueListe extends AbstractVue implements Observer {
        /**
         * Composant JList pour afficher la liste des étudiants.
         */
        private final JList liste;

        /**
         * Liste de chaînes de caractères représentant les étudiants.
         */
        private final List<String> listeEtudiant;

        /**
         * Bouton "Supprimer" pour supprimer un étudiant de la liste.
         */
        private final JButton btSuppr = new JButton("Supprimer");

        /**
         * Référence à l'objet Promotion pour la gestion des données de la promotion.
         */
        private Promotion promotion;

        /**
         * Contrôleur responsable de la suppression d'étudiants de la liste.
         */
        private ControllerSupprList supprList;

        /**
         * Modèle de liste personnalisé pour afficher les étudiants dans la JList.
         */
        private EtudiantListModel model;

        /**
         * Deuxième modèle de liste personnalisé (peut être utilisé pour une autre vue).
         */
        private EtudiantListModel model2;

        /**
         * Contrôleur responsable de la gestion de la liste des étudiants.
         */
        private ControllerList controllerList;

        /**
         * Référence à la vue de formulaire associée pour les interactions.
         */
        private VueFormulaire vueFormulaire;

        /**
         * Constructeur de la classe VueListe.
         * 
         * @param promotion     La promotion de laquelle les données seront extraites.
         * @param vueFormulaire La vue du formulaire associée pour les modifications
         *                      d'étudiants.
         */
        public VueListe(Promotion promotion, VueFormulaire vueFormulaire) {
                liste = new JList();
                listeEtudiant = new ArrayList<>();
                this.promotion = promotion;
                controllerList = new ControllerList(this.promotion);
                supprList = new ControllerSupprList(this.promotion);
                this.vueFormulaire = vueFormulaire;
                liste.setLayoutOrientation(JList.VERTICAL);
                JScrollPane scrollPane = new JScrollPane(liste);
                liste.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                this.setLayout(new GridBagLayout());
                GridBagConstraints gc = new GridBagConstraints();
                gc.fill = GridBagConstraints.BOTH;
                gc.gridx = 0;
                gc.gridy = 0;
                this.add(scrollPane, gc);
                gc.gridx = 0;
                gc.gridy = 1;
                this.add(btSuppr, gc);
                remplissageListe();
                listListeners();
                listenerJListe();
                this.pack();
                liste.setVisibleRowCount(this.getHeight() / 8);
                this.pack();
        }

        /**
         * Remplit la liste avec les étudiants de la promotion.
         */
        private void remplissageListe() {
                liste.removeAll();
                model = new EtudiantListModel(controllerList.getListEtudiants());
                liste.setModel(model);
        }

        /**
         * Configure le gestionnaire d'événements pour la JList.
         */
        private void listenerJListe() {
                this.liste.addMouseListener(new MouseListener() {
                        /**
                         * {@inheritDoc}
                         */
                        @Override
                        public void mouseClicked(MouseEvent e) {
                                if (e.getClickCount() == 2) {
                                        Object selectedValue = liste.getSelectedValue();
                                        if (selectedValue != null) {
                                                String etudiant = selectedValue.toString();
                                                String[] parts = etudiant.split(" - ");
                                                if (parts.length >= 2) {
                                                        String numero = parts[0].trim();
                                                        vueFormulaire.modifyEtudiant(
                                                                        controllerList.researchEtudiant(numero));
                                                } else {
                                                        System.out.println("Not enough data to modify etudiant");
                                                }
                                        } else {
                                                System.out.println("No selection made");
                                        }
                                }
                        }

                        /**
                         * {@inheritDoc}
                         */
                        @Override
                        public void mousePressed(MouseEvent e) {

                        }

                        /**
                         * {@inheritDoc}
                         */
                        @Override
                        public void mouseReleased(MouseEvent e) {

                        }

                        /**
                         * {@inheritDoc}
                         */
                        @Override
                        public void mouseEntered(MouseEvent e) {

                        }

                        /**
                         * {@inheritDoc}
                         */
                        @Override
                        public void mouseExited(MouseEvent e) {

                        }
                });
        }

        /**
         * Configure le gestionnaire d'événements pour le bouton "Supprimer".
         */
        private void listListeners() {
                btSuppr.addActionListener(new ActionListener() {
                        /**
                         * {@inheritDoc}
                         */
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                if (!liste.isSelectionEmpty()) {
                                        Object[] selectedValues = liste.getSelectedValues();
                                        for (Object value : selectedValues) {
                                                String num = value.toString().split(" - ")[0];
                                                listeEtudiant.add(num);
                                        }
                                        vueFormulaire.resetForm();
                                        supprList.control(listeEtudiant);
                                        listeEtudiant.clear();
                                }
                        }
                });
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void updateView() {
                model2 = new EtudiantListModel(controllerList.getListEtudiants());
                liste.setModel(model2);
                liste.revalidate();
                liste.repaint();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void update() {
                updateView();
        }
}
