package packVue;

import java.awt.Dimension;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;

import packControleur.ControllerHistogramme;
import packModele.Promotion;
import packObserver.Observer;

/**
 * La classe VueHistogrammeChart représente la vue qui affiche un histogramme
 * des bacs d'origine des étudiants.
 * Elle observe les changements dans la promotion et se met à jour en
 * conséquence.
 * 
 * @author L Buathier & A. Peytavie
 * @modified by Yanis Mechta
 */
public class VueHistogrammeChart extends AbstractVue implements Observer {

    /**
     * Le panneau d'histogramme utilisé pour afficher le graphique d'histogramme.
     */
    private final Histogramme histo;

    /**
     * Le contrôleur responsable de la gestion des données de l'histogramme.
     */
    private ControllerHistogramme controllerHistogramme;

    /**
     * Constructeur de la classe VueHistogrammeChart.
     * 
     * @param promotion La promotion de laquelle les données seront extraites pour
     *                  l'histogramme.
     */
    public VueHistogrammeChart(Promotion promotion) {
        this.controllerHistogramme = new ControllerHistogramme(promotion);
        histo = new Histogramme();
        this.setContentPane(histo);
        this.pack();
    }

    /**
     * Crée un nouveau graphique d'histogramme à partir d'un ensemble de données
     * (dataset).
     * 
     * @param dataset L'ensemble de données pour l'histogramme.
     * @return Le graphique d'histogramme créé.
     */
    private JFreeChart createChart(final CategoryDataset dataset) {
        final JFreeChart chart = ChartFactory.createBarChart3D(
                "Bacs d'origine", // Titre du graphique
                "Category", // Étiquette de l'axe des domaines
                "Value", // Étiquette de l'axe des valeurs
                dataset, // Données
                PlotOrientation.VERTICAL, // Orientation du graphique
                true, // Inclure la légende
                true, // Afficher les tooltips
                false // URLs
        );

        final CategoryPlot plot = chart.getCategoryPlot();
        final CategoryAxis axis = plot.getDomainAxis();
        axis.setCategoryLabelPositions(
                CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 8.0));
        final BarRenderer3D renderer = (BarRenderer3D) plot.getRenderer();
        renderer.setDrawBarOutline(false);

        return chart;
    }

    /**
     * Met à jour la vue de l'histogramme avec de nouvelles données.
     */
    @Override
    public void updateView() {
        String[] bac = { "G", "T", "Pro", "A" };
        final double[][] newData = new double[bac.length][1];
        Double count = 0.0;
        for (int i = 0; i < 4; i++) {
            count = controllerHistogramme.controllerBacCount(bac[i]);
            newData[i][0] = count;
        }
        CategoryDataset newDataset = DatasetUtilities.createCategoryDataset(
                new String[] { "G", "T", "A", "Pro" },
                new String[] { "Bacs" },
                newData);
        this.histo.setChart(createChart(newDataset));
        this.histo.revalidate();
        this.histo.repaint();
    }

    /**
     * Obtient les données pour l'histogramme.
     * 
     * @return Les données pour l'histogramme sous forme d'un tableau 2D.
     */
    public double[][] data() {
        String[] bac = { "G", "T", "Pro", "A" };
        final double[][] data = new double[bac.length][1];
        Double count = 0.0;
        for (int i = 0; i < 4; i++) {
            count = controllerHistogramme.controllerBacCount(bac[i]);
            data[i][0] = count;
        }
        return data;
    }

    /**
     * Classe interne représentant le panneau de l'histogramme.
     */
    public class Histogramme extends ChartPanel {
        public Histogramme() {
            super(null);
            this.setPreferredSize(new Dimension(285, 350));
            CategoryDataset dataset = createDataset();
            final JFreeChart chart = createChart(dataset);
            this.setChart(chart);
        }

        private CategoryDataset createDataset() {
            String[] series = { "G", "T", "A", "Pro" }; // Séries
            String[] categories = { "Bacs" }; // Catégories
            return DatasetUtilities.createCategoryDataset(series, categories, data());
        }
    }

    /**
     * Met à jour la vue en réponse à des changements observés.
     */
    @Override
    public void update() {
        updateView();
    }
}
