
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JWindow;
import javax.swing.Timer;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.stream.StreamSource;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.DynamicTimeSeriesCollection;
import org.jfree.data.time.Second;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * @see http://stackoverflow.com/questions/5048852
 */
public class PieChart extends ApplicationFrame {

    private static final String TITLE = "Stock Market Visualisation";
    private static final String START = "Start";
    private static final String LOAD = "Load Stock";
    private static final String NEWS = "Display News";
    private static final float MINMAX = 20;
    private static final int COUNT = 2 * 60;
    private static final int FAST = 100;
    private static final int SLOW = FAST * 5;
    private static final Random random = new Random();
    private Timer timer;

    public PieChart(final String title) {
        super(title);
        JEditorPane newsArea = new JEditorPane();
        final DynamicTimeSeriesCollection dataset1 =
                new DynamicTimeSeriesCollection(1, COUNT, new Second());
        dataset1.setTimeBase(new Second(0, 0, 0, 1, 1, 2011));
        dataset1.addSeries(gaussianData(), 0, "Gaussian data");
        final XYSeriesCollection dataset = (XYSeriesCollection) createDataset();

        JFreeChart chart = createChart(dataset);
        JFreeChart chartPolar = createChartPolar(dataset);
        JFreeChart chartScatter = createChartScatter(dataset);

        final JComboBox combo = new JComboBox();
        combo.addItem("GOOG");
        combo.addItem("AAPL");
        combo.addItem("ASX.AX");
        combo.addItem("CBA.AX");
        combo.addItem("DAL");
        combo.addItem("ADBE");
        combo.addItem("AMZN");
        combo.addItem("CSCO");
        combo.addItem("EBAY");
        combo.addItem("SYMC");
        combo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ("AMZN".equals(combo.getSelectedItem())) {
                } else {
                    timer.setDelay(SLOW);
                }
            }
        });
        JProgressBar loadingBar = new JProgressBar(0, 100);
        loadingBar.setString("Loading stocks...");
//        //JDialog win = new JWindow();
//        JDialog loadingFrame = new JDialog();
//        loadingFrame.add(loadingBar);
//        loadingFrame.setSize(400, 300);
//        loadingFrame.pack();
//        loadingFrame.setVisible(true);;

        stock stockDetails = new stock("GOOG");
        final ArrayList GOOGstock = stockDetails.price(stockDetails.httpParser(stockDetails.url));
        final ArrayList GOOGstockVol = stockDetails.volume(stockDetails.httpParser(stockDetails.url));
        loadingBar.setValue(12);
        System.out.println("Loading stocks... 12% ");
        stock stockDetails2 = new stock("AAPL");
        final ArrayList AAPLstock = stockDetails2.price(stockDetails2.httpParser(stockDetails2.url));
        final ArrayList AAPLstockVol = stockDetails2.volume(stockDetails2.httpParser(stockDetails2.url));
        loadingBar.setValue(22);
        System.out.println("Loading stocks... 22%");
        stock stockDetails3 = new stock("ASX.AX");
        final ArrayList ASXstock = stockDetails3.price(stockDetails3.httpParser(stockDetails3.url));
        final ArrayList ASXstockVol = stockDetails3.volume(stockDetails3.httpParser(stockDetails3.url));
        loadingBar.setValue(35);
        System.out.println("Loading stocks... 35%");
        stock stockDetails4 = new stock("CBA.AX");
        final ArrayList CBAstock = stockDetails4.price(stockDetails3.httpParser(stockDetails3.url));
        final ArrayList CBAstockVol = stockDetails4.volume(stockDetails3.httpParser(stockDetails3.url));
        loadingBar.setValue(37);
        System.out.println("Loading stocks... 37%");
        stock stockDetails5 = new stock("DAL");
        final ArrayList DALstock = stockDetails5.price(stockDetails3.httpParser(stockDetails3.url));
        final ArrayList DALstockVol = stockDetails5.volume(stockDetails3.httpParser(stockDetails3.url));
        loadingBar.setValue(47);
        System.out.println("Loading stocks... 47%");
        stock stockDetails6 = new stock("ADBE");
        final ArrayList ADBEstock = stockDetails6.price(stockDetails6.httpParser(stockDetails6.url));
        final ArrayList ADBEstockVol = stockDetails6.volume(stockDetails6.httpParser(stockDetails6.url));
        loadingBar.setValue(56);
        stock stockDetails7 = new stock("AMZN");
        final ArrayList AMZNstock = stockDetails7.price(stockDetails7.httpParser(stockDetails7.url));
        final ArrayList AMZNstockVol = stockDetails7.volume(stockDetails7.httpParser(stockDetails7.url));
        loadingBar.setValue(69);
        System.out.println("Loading stocks... 69%");
        stock stockDetails8 = new stock("CSCO");
        final ArrayList CSCOstock = stockDetails8.price(stockDetails8.httpParser(stockDetails8.url));
        final ArrayList CSCOstockVol = stockDetails8.volume(stockDetails8.httpParser(stockDetails8.url));
        loadingBar.setValue(74);
        System.out.println("Loading stocks... 74%");
        stock stockDetails9 = new stock("EBAY");
        final ArrayList EBAYstock = stockDetails9.price(stockDetails9.httpParser(stockDetails9.url));
        final ArrayList EBAYstockVol = stockDetails9.volume(stockDetails9.httpParser(stockDetails9.url));
        loadingBar.setValue(88);
        System.out.println("Loading stocks... 88%");
        stock stockDetails10 = new stock("SYMC");
        final ArrayList SYMCstock = stockDetails10.price(stockDetails10.httpParser(stockDetails10.url));
        final ArrayList SYMCstockVol = stockDetails10.volume(stockDetails10.httpParser(stockDetails10.url));
        loadingBar.setValue(99);
        System.out.println("Loading stocks... 99%");

        JButton run = new JButton(LOAD);
        run.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (combo.getSelectedItem().equals("GOOG")) {
                    //JLabel loading = new JLabel("Please wait, Loading...");

                    final XYSeries series1 = new XYSeries("GOOG");
                    float x = 0;
                    for (Object o : GOOGstock) {
                        series1.add(x, Double.valueOf(o.toString()));
                        x++;
                    }
                    x = 0;
                    for (Object o : GOOGstockVol) {
                        series1.add(x, -Double.valueOf(o.toString()) / 200000);
                        x++;
                    }
                    dataset.addSeries(series1);
                }
                if (combo.getSelectedItem().equals("AAPL")) {
                    final XYSeries series2 = new XYSeries("AAPL");
                    float x2 = 0;
                    for (Object o : AAPLstock) {
                        series2.add(x2, Double.valueOf(o.toString()));
                        x2++;
                    }
                    x2 = 0;
                    for (Object o : AAPLstockVol) {
                        series2.add(x2, -Double.valueOf(o.toString()) / 200000);
                        x2++;
                    }
                    dataset.addSeries(series2);
                }
                if (combo.getSelectedItem().equals("ASX.AX")) {
                    final XYSeries series3 = new XYSeries("ASX.AX");
                    float x3 = 0;
                    for (Object o : ASXstock) {
                        series3.add(x3, Double.valueOf(o.toString()));
                        x3++;
                    }
                    x3 = 0;
                    for (Object o : ASXstockVol) {
                        series3.add(x3, -Double.valueOf(o.toString()) / 200000);
                        x3++;
                    }
                    dataset.addSeries(series3);
                }
                if (combo.getSelectedItem().equals("CBA.AX")) {
                    final XYSeries series4 = new XYSeries("CBA.AX");
                    float x4 = 0;
                    for (Object o : CBAstock) {
                        series4.add(x4, Double.valueOf(o.toString()));
                        x4++;
                    }
                    x4 = 0;
                    for (Object o : CBAstockVol) {
                        series4.add(x4, -Double.valueOf(o.toString()) / 200000);
                        x4++;
                    }
                    dataset.addSeries(series4);
                }
                if (combo.getSelectedItem().equals("DAL")) {
                    final XYSeries series5 = new XYSeries("DAL");
                    float x5 = 0;
                    for (Object o : DALstock) {
                        series5.add(x5, Double.valueOf(o.toString()));
                        x5++;
                    }
                    x5 = 0;
                    for (Object o : DALstockVol) {
                        series5.add(x5, -Double.valueOf(o.toString()) / 200000);
                        x5++;
                    }
                    dataset.addSeries(series5);

                }
                if (combo.getSelectedItem().equals("ADBE")) {
                    final XYSeries series6 = new XYSeries("ADBE");
                    float x6 = 0;
                    for (Object o : ADBEstock) {
                        series6.add(x6, Double.valueOf(o.toString()));
                        x6++;
                    }
                    x6 = 0;
                    for (Object o : ADBEstockVol) {
                        series6.add(x6, -Double.valueOf(o.toString()) / 200000);
                        x6++;
                    }
                    dataset.addSeries(series6);
                }
                if (combo.getSelectedItem().equals("EBAY")) {

                    final XYSeries series7 = new XYSeries("EBAY");
                    float x7 = 0;
                    for (Object o : EBAYstock) {
                        series7.add(x7, Double.valueOf(o.toString()));
                        x7++;
                    }
                    x7 = 0;
                    for (Object o : EBAYstockVol) {
                        series7.add(x7, -Double.valueOf(o.toString()) / 200000);
                        x7++;
                    }
                    dataset.addSeries(series7);
                }
                if (combo.getSelectedItem().equals("AMZN")) {

                    final XYSeries series8 = new XYSeries("AMZN");
                    float x8 = 0;
                    for (Object o : AMZNstock) {
                        series8.add(x8, Double.valueOf(o.toString()));
                        x8++;
                    }
                    x8 = 0;
                    for (Object o : AMZNstockVol) {
                        series8.add(x8, -Double.valueOf(o.toString()) / 200000);
                        x8++;
                    }
                    dataset.addSeries(series8);
                    if (combo.getSelectedItem().equals("SYMC")) {

                        final XYSeries series9 = new XYSeries("SYMC");
                        float x9 = 0;
                        for (Object o : SYMCstock) {
                            series9.add(x9, Double.valueOf(o.toString()));
                            x9++;
                        }
                        x9 = 0;
                        for (Object o : SYMCstockVol) {
                            series9.add(x9, -Double.valueOf(o.toString()) / 200000);
                            x9++;
                        }
                        dataset.addSeries(series9);
                    }
                    if (combo.getSelectedItem().equals("CSCO")) {

                        final XYSeries series10 = new XYSeries("CSCO");
                        float x10 = 0;
                        for (Object o : CSCOstock) {
                            series10.add(x10, Double.valueOf(o.toString()));
                            x8++;
                        }
                        x10 = 0;
                        for (Object o : CSCOstockVol) {
                            series10.add(x10, -Double.valueOf(o.toString()) / 200000);
                            x10++;
                        }
                        dataset.addSeries(series10);
                    }
                } else {
                    //do nothing
                }
            }
        });
        final JEditorPane newsRSS = new JEditorPane();

        //newsRSS.setSize(200, 50);
        newsRSS.setContentType(
                "text/html");
        final JScrollPane newsRSSinfo = new JScrollPane(newsRSS);
        newsRSSinfo.getViewport().setPreferredSize(new Dimension(300, 300));
        newsRSSinfo.setAutoscrolls(
                true);
        newsRSSinfo.setSize(
                20, 50);
        newsRSS.setEditable(
                false);
        JButton news = new JButton(NEWS);

        news.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                newsRSS.setText("<html>" + (String) getNews("GOOG").get(0));
                System.out.println(newsRSS.getText());
                System.out.println(getNews("GOOG").size());
            }
        });
        // loadingFrame.remove(loadingBar);
        this.add(
                new ChartPanel(chart), BorderLayout.NORTH);
        //JPanel moreCharts = new JPanel();
        ChartPanel scatter = new ChartPanel(chartScatter);
        scatter.setPreferredSize(new Dimension(400, 400));
        this.add(scatter, BorderLayout.EAST);
        ChartPanel polar = new ChartPanel(chartPolar);
        polar.setPreferredSize(new Dimension(400, 400));
        this.add(polar, BorderLayout.WEST);
        //moreCharts.setPreferredSize(new Dimension(100, 100));
        //this.add(moreCharts);
        JPanel btnPanel = new JPanel(new FlowLayout());

        btnPanel.add(run);

        btnPanel.add(combo);

        btnPanel.add(news);
        JPanel newsPanel = new JPanel(new FlowLayout());

        newsPanel.add(newsRSSinfo, BorderLayout.SOUTH);


        this.add(newsPanel, BorderLayout.CENTER);

        this.add(btnPanel, BorderLayout.SOUTH);
//        timer = new Timer(FAST, new ActionListener() {
//
//            float[] newData = new float[1];
//
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                newData[0] = randomValue();
//                dataset.advanceTime();
//                dataset.appendData(newData);
//            }
        //  );
    }

    private ArrayList getNews(String stock) {
        ArrayList newsList = new ArrayList();
        HttpURLConnection connection = null;
        String newsString = "";
        try {
            URL urlLink = new URL("http://finance.yahoo.com/rss/headline?s=" + stock);
            URLConnection yc = urlLink.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                newsString += XMLConverter(inputLine);
                //System.out.println(XMLConverter(inputLine) + "NEW LINE");

            }
            in.close();

            // do something with the input stream here

        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            if (null != connection) {
                connection.disconnect();
            }
        }
        newsString = newsString.replaceAll("xml version=&quot;1.0&quot; encoding=&quot;utf-8&quot;?&gt;<rss version=\"2.0\">", "");
        newsString = newsString.replaceAll("Yahoo! Finance: GOOG News", "");
        newsString = newsString.replaceAll("<copyright>", "");
        newsString = newsString.replaceAll("Copyright (c) 2013 Yahoo! Inc. All rights reserved.</copyright><a href=\"http://finance.yahoo.com/q/h?s=goog\">Latest", "");
        newsString = newsString.replaceAll("Financial News for Google Inc.</a><language>en-US</language><lastbuilddate>Sat,", "");
        newsString = newsString.replaceAll("25 May 2013 00:02:23 GMT</lastbuilddate><img><url>http://l.yimg.com/a/i/brand/purplelogo/uh/us/fin.gif</url>", "");

        newsList.add(newsString);
        return newsList;
    }

    /**
     * Creates a sample dataset.
     *
     * @return a sample dataset.
     */
    private XYDataset createDataset() {

        final XYSeriesCollection dataset = new XYSeriesCollection();
        return dataset;

    }

    private float randomValue() {
        return (float) (random.nextGaussian() * MINMAX / 3);
    }

    private float[] gaussianData() {
        float[] a = new float[COUNT];
        for (int i = 0; i < a.length; i++) {
            a[i] = randomValue();
        }
        return a;
    }

    private JFreeChart createChart(final XYDataset dataset) {
        final JFreeChart result = ChartFactory.createXYAreaChart(
                TITLE, "Days from 1/1/2003", "Volume / Stock Value", dataset, PlotOrientation.HORIZONTAL, true, true, false);
        final XYPlot plot = result.getXYPlot();
        ValueAxis domain = plot.getDomainAxis();
        //domain.setRange(-700, 700);
        domain.setAutoRange(true);

        ValueAxis range = plot.getRangeAxis();
        //range.setAutoRange(true);
        range.setRange(-200, 800);

        return result;
    }

    private JFreeChart createChartScatter(final XYDataset dataset) {
        final JFreeChart result = ChartFactory.createScatterPlot(
                TITLE, "Days from 1/1/2003", "Volume / Stock Value", dataset, PlotOrientation.HORIZONTAL, true, true, false);
        final XYPlot plot = result.getXYPlot();
        ValueAxis domain = plot.getDomainAxis();
        //domain.setRange(-700, 700);
        domain.setAutoRange(true);

        ValueAxis range = plot.getRangeAxis();
        //range.setAutoRange(true);
        range.setRange(-200, 800);
        return result;
    }

    private JFreeChart createChartPolar(final XYDataset dataset) {
        final JFreeChart result = ChartFactory.createPolarChart(
                TITLE, dataset, true, true, false);
        final Plot plot = result.getPlot();
        //ValueAxis domain = plot.getDomainAxis();
        //domain.setRange(-700, 700);
        //domain.setAutoRange(true);

        //ValueAxis range = plot.getRangeAxis();
        //range.setAutoRange(true);
        //range.setRange(-200, 800);
        return result;
    }

    private String XMLConverter(String xml) {
        ArrayList XMLconverted = new ArrayList();
        String newXML = xml.replaceAll("<channel>", "");
        newXML = newXML.replaceAll("</channel>", "");
        newXML = newXML.replaceAll("<title>", "<h1>");
        newXML = newXML.replaceAll("</title>", "</h1>");
        newXML = newXML.replaceAll("<link>", "<a href=");
        newXML = newXML.replaceAll("</link>", ">");
        newXML = newXML.replaceAll("<description>", "");
        newXML = newXML.replaceAll("</description>", "</a>");
        return newXML;
    }

    public void start() {
        timer.start();
    }

    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                PieChart demo = new PieChart(TITLE);
                demo.pack();
                RefineryUtilities.centerFrameOnScreen(demo);
                demo.setVisible(true);
                demo.start();
            }
        });
    }
}