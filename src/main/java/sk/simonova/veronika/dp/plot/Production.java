package sk.simonova.veronika.dp.plot;

import org.apache.commons.math3.analysis.function.Power;
import org.apache.commons.math3.util.FastMath;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;

public class Production extends ApplicationFrame {
    public Production(final String title) {

        super(title);

        final XYDataset dataset = createDataset(0.5,0.1,0.5,0,5,0.001);
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(1000, 400));
        setContentPane(chartPanel);

    }
    private JFreeChart createChart(final XYDataset dataset) {

        // create the chart...
        final JFreeChart chart = ChartFactory.createXYLineChart(
                "Ramsey",      // chart title
                "kapital",                      // x axis label
                "spotreba",                      // y axis label
                dataset,                  // data
                PlotOrientation.VERTICAL,
                true,                     // include legend
                true,                     // tooltips
                false                     // urls
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.white);

//        final StandardLegend legend = (StandardLegend) chart.getLegend();
        //      legend.setDisplaySeriesShapes(true);

        // get a reference to the plot for further customisation...
        final XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(Color.lightGray);
        //    plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, false);
        renderer.setSeriesShapesVisible(1, false);
        plot.setRenderer(renderer);

        // change the auto tick unit selection to integer units only...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        // OPTIONAL CUSTOMISATION COMPLETED.

        return chart;

    }

    private XYDataset createDataset(double alfa, double r, double delta, double from, double to, double step) {

        final XYSeriesCollection dataset = new XYSeriesCollection();

        final XYSeries series1 = new XYSeries("First");
        Power power = new Power(alfa);
        for (double i = from; i < to; i+=step) {
            series1.add(i, power.value(i));
        }
        dataset.addSeries(series1);

        final XYSeries series2 = new XYSeries("Second");
        Power power2 = new Power(1/(alfa-1));
        for (double i = from; i < to; i+=step) {
            series1.add(power2.value((r+delta)/alfa) , i );
        }

        dataset.addSeries(series2);

        final XYSeries series3 = new XYSeries("Second");
        for (double i = from; i < to; i+=step) {
            series3.add(i , (power.value(i)-delta*i));
        }
        dataset.addSeries(series3);
        return dataset;
    }


    public static void main(String[] args) {
        Production chart = new Production("");
        chart.pack( );
        chart.setVisible( true );



    }

}
