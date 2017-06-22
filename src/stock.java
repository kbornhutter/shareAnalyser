/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 *
 * @author kurt
 */
public class stock {

    String id;
    String url;
    String industry;
    double[] priceHistory;
    String[] news;

    public stock(String id) {
        this.id = id;
        //URL Builder
        url = "http://ichart.yahoo.com/table.csv?s=" + id;

    } 
//    public static void main (String [] args) {
//        stock shareStock = new stock("CBA.AX");
//        ArrayList stockList = httpParser(shareStock.url);
//        for (Object str : stockList) {
//            System.out.println(str);
//        }
//    }

    public ArrayList httpParser(String url) {
        ArrayList stockList = new ArrayList();
        HttpURLConnection connection = null;
        try {
            URL urlLink = new URL(url);
            URLConnection yc = urlLink.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                stockList.add(inputLine);
                //System.out.println(inputLine);
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
        return stockList;
    }
    public ArrayList date(ArrayList array) {
        int max = 0;
        ArrayList date = new ArrayList();
        for (Object o : array) {
            date.add(o.toString().split(",")[0]);
            if (max >= 100) {
                break;
            } else max++;
        }
        date.remove(0);
        return date;
    }
    public ArrayList price(ArrayList array) {
        int max = 0;
        ArrayList price = new ArrayList();
        for (Object o : array) {
            price.add(o.toString().split(",")[4]);
            if (max >= 10000) {
                break;
            } else max++;
        }
        price.remove(0);
        return price;
    }
    public ArrayList percentageChange(ArrayList array) {
        int max = 0;
        ArrayList percent = new ArrayList();
        int prev = 1;
        for (Object o : array) {
            percent.add(Integer.parseInt(o.toString())/prev);
            prev = Integer.parseInt(o.toString());
            if (max >= 100) {
                break;
            } else max++;
        }
        return percent;
    }
    public ArrayList volume(ArrayList array) {
        int max = 0;
        ArrayList volume = new ArrayList();
        for (Object o : array) {
            volume.add(o.toString().split(",")[5]);
            if (max >= 10000) {
                break;
            } else max++;
        }
        volume.remove(0);
        return volume;
    }
}
