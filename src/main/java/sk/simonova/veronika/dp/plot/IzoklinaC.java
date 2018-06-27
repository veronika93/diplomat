package sk.simonova.veronika.dp.plot;

import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.function.Power;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.DefaultIntervalXYDataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYBarDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class IzoklinaC extends ApplicationFrame {
    /**
     * Constructs a new application frame.
     *
     * @param title the frame title.
     */
    public IzoklinaC(String title) {
        super(title);
        JFreeChart lineChart = ChartFactory.createXYLineChart(
                title,
                "kapital","spotreba",
                createDataset(0.5, 0.1,0.5,0, 5, 0.1),
                PlotOrientation.VERTICAL,
                true,true,false);

        ChartPanel chartPanel = new ChartPanel( lineChart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 1000 , 400 ) );
        setContentPane( chartPanel );
    }

    private XYDataset createDataset(double alfa, double r, double delta, double from, double to, double step) {
        Power power = new Power(1/(alfa-1));

        final XYSeries series1 = new XYSeries("First");

        for (double i = from; i < to; i+=step) {
            series1.add(power.value((r+delta)/alfa) , i );
        }
        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
     return dataset;
    }


    public static void main(String[] args) {
        IzoklinaC chart = new IzoklinaC("produckna");
        chart.pack( );
        chart.setVisible( true );
    }

}
