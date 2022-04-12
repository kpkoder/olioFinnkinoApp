package com.example.oliofinnkinoapp;

// Class for reading XML data

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class WebReader {

    //Arraylist for movies
    ArrayList<MovieClass> Movies = new ArrayList();

    //singleton principle
    private static WebReader webReader = new WebReader();
    public static WebReader getInstance() { return webReader; }


    // Method for reading Finnkino movie data and creating MovieClass objects
    // !TODO
    public void readXML() {

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            String urlString = "https://www.finnkino.fi/xml/Schedule/"; //schedule url
            Document doc = builder.parse(urlString);

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getDocumentElement().getElementsByTagName("Show");
            //loop
            for (int i = 0; i < nList.getLength(); i++) {
                /*For skipping unnecessary nodes at start (if needed)
                if (i == 0 || i == 1) {
                    continue;
                }*/

                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    //Parsing movie data
                    Element element = (Element) node;

                    String tempName = element.getElementsByTagName("Title").item(0).getTextContent();
                    String aika = element.getElementsByTagName("dttmShowStart").item(0).getTextContent().substring(11,16);
                    aika = aika + " - " + element.getElementsByTagName("dttmShowEnd").item(0).getTextContent().substring(11,16);
                    String TempLengthInMinutes = element.getElementsByTagName("LengthInMinutes").item(0).getTextContent();
                    int LengthInMinutes = Integer.valueOf(TempLengthInMinutes);

                    //Might need these
                    //String tempName = element.getElementsByTagName("Theatre").item(0).getTextContent();

                    //PRINT FOR CHECK
                    //System.out.print(tempName);
                    //System.out.println(" "+tempFilm);

                    //Making new Movies
                    //MovieClass(String name, Float rating, String airTime, int LengthInMinutes) {
                    MovieClass kino = new MovieClass(tempName, 0F, aika, LengthInMinutes);
                    Movies.add(kino);

                }

                //FILESAVER TEST
                FileSaver fileSaver = FileSaver.getInstance();
                fileSaver.Write(Movies);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } finally {
            //PRINT FOR CHECK
            //System.out.println("");
        }

    }

}
