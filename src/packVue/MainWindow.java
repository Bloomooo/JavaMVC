package packVue;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import packModele.Promotion;

/**
 * Fenêtre principale de l'application.
 * 
 * @author L Buathier & A. Peytavie
 * @modified by Yanis Mechta
 */
public class MainWindow extends JFrame {

    /**
     * Référence à la vue de formulaire.
     */
    private final VueFormulaire form;

    /**
     * Référence à la vue du graphique camembert.
     */
    private final VueCamembertChart camemb;

    /**
     * Référence à la vue de la liste des étudiants.
     */
    private final VueListe liste;

    /**
     * Référence à la vue du graphique histogramme.
     */
    private final VueHistogrammeChart histo;

    /**
     * Référence à l'objet Promotion pour la gestion des données de la promotion.
     */
    private Promotion promotion;

    /**
     * Constructeur de la classe MainWindow.
     * 
     * @param promotion L'objet Promotion associé à la fenêtre.
     */
    public MainWindow(Promotion promotion) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(new JDesktopPane());
        this.setTitle("Statistiques d'une promotion de BUT");
        this.setLocationRelativeTo(null);
        this.promotion = promotion;

        // vue formulaire
        form = new VueFormulaire(promotion);
        this.add(form);
        form.setTitle("Saisie d'étudiants");
        form.setVisible(true);
        form.setLocation(0, 0);

        // vue camembert
        camemb = new VueCamembertChart(promotion);
        this.add(camemb);
        camemb.setTitle("Départements d'origine");
        camemb.setVisible(true);
        camemb.setLocation(0, form.getHeight());

        // vue histogramme
        histo = new VueHistogrammeChart(promotion);
        this.add(histo);
        histo.setTitle("Bacs d'origine");
        histo.setVisible(true);
        histo.setLocation(camemb.getWidth(), form.getHeight());

        // vue liste
        liste = new VueListe(promotion, form);
        this.add(liste);
        liste.setTitle("Liste des étudiants");
        liste.setLocation(camemb.getWidth() + histo.getWidth(), 0);
        liste.setVisible(true);

        promotion.addObserver(liste);
        promotion.addObserver(camemb);
        promotion.addObserver(histo);
        promotion.addObserver(form);

        // taille de la fenetre
        this.pack();
        this.setSize(camemb.getWidth() + histo.getWidth() + liste.getWidth() + 18,
                form.getHeight() + camemb.getHeight() + 45);

    }
}
