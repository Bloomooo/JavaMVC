package packVue;

import java.awt.Dimension;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

import packControleur.ControllerCamember;
import packModele.Etudiant;
import packModele.Promotion;
import packObserver.Observer;

/**
 * Cette classe représente la vue d'un graphique camembert 3D affichant les
 * données des départements d'origine des étudiants.
 * Elle implémente l'interface Observer pour être notifiée des mises à jour du
 * modèle.
 * 
 * @author L Buathier & A. Peytavie
 * @modified by Yanis Mechta
 */
public class VueCamembertChart extends AbstractVue implements Observer {

    /**
     * Référence à l'objet Camembert, qui est une classe interne représentant le
     * graphique camembert.
     */
    private Camembert camemb;

    /**
     * Référence au contrôleur responsable de la gestion des données du graphique
     * camembert.
     */
    private ControllerCamember controllerCamember;

    /**
     * Constructeur de la classe VueCamembertChart.
     *
     * @param promotion La promotion à partir de laquelle les données sont
     *                  extraites.
     */
    public VueCamembertChart(Promotion promotion) {
        this.controllerCamember = new ControllerCamember(promotion);
        camemb = new Camembert();
        this.setContentPane(camemb);
        this.pack();

    }

    // Internal class
    public class Camembert extends ChartPanel {

        /**
         * Constructeur de la classe Camembert.
         */
        public Camembert() {
            super(null);
            this.setPreferredSize(new Dimension(450, 350));
            PieDataset dataset = createDataset();
            JFreeChart chart = createChart(dataset);
            this.setChart(chart);
        }
    }

    /**
     * Crée le graphique camembert 3D avec les données du dataset.
     *
     * @param dataset Le jeu de données à afficher dans le graphique.
     * @return Le graphique camembert 3D créé.
     */
    private JFreeChart createChart(final PieDataset dataset) {
        final JFreeChart chart = ChartFactory.createPieChart3D(
                "Departement d'origine", // Titre du graphique
                dataset, // Données
                true, // Inclure la légende
                true,
                false);
        final PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(100);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        plot.setNoDataMessage("No data to display");
        return chart;
    }

    /**
     * Crée le dataset avec les données des départements d'origine des étudiants.
     *
     * @return Le dataset créé.
     */
    private PieDataset createDataset() {
        final DefaultPieDataset result = new DefaultPieDataset();

        for (int i = 1; i <= 99; i++) {
            String deptCode = (i < 10 && i >= 2) ? "0" + i : String.valueOf(i);
            double count = controllerCamember.controllerDeptCount(deptCode);

            if (count != 0) {
                result.setValue(deptCode, count);
            }
        }

        return result;
    }

    /**
     * Met à jour la vue en recréant le graphique camembert avec les données mises à
     * jour.
     */
    @Override
    public void updateView() {
        PieDataset dataset = createDataset();
        this.camemb.setChart(createChart(dataset));
    }

    /**
     * Méthode appelée lorsqu'une mise à jour du modèle est détectée.
     * Cette méthode appelle la méthode `updateView` pour mettre à jour la vue.
     */
    @Override
    public void update() {
        updateView();
    }
}
