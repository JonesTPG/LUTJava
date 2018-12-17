package com.example.n2571.vk9;

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

public class Areas {

    public static ArrayList<Area> data = new ArrayList<Area>();

    public static void readAreasXML() {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            String urlString = "http://www.finnkino.fi/xml/TheatreAreas";
            Document doc = builder.parse(urlString);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getDocumentElement().getElementsByTagName("TheatreArea");

            for ( int i = 0; i < nList.getLength(); i++ ) {
                Node node = nList.item(i);

                if ( node.getNodeType() == Node.ELEMENT_NODE ) {
                    Element element = (Element) node;
                    String ID = element.getElementsByTagName("ID").item(0).getTextContent();
                    String name = element.getElementsByTagName("Name").item(0).getTextContent();
                    Area area = new Area(ID, name);
                    data.add(area);

                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("ONNISTUI");
        }
    }

    public static ArrayList<String> getTheaterNames() {
        ArrayList<String> names = new ArrayList<String>();
        for ( int i = 0; i<data.size(); i++ ) {
            names.add(data.get(i).name);
        }
        return names;
    }

    public static String getId(String name) {
        for (int i = 0; i < data.size(); i++) {
            if ( data.get(i).name.toString().equals(name) ) {
                return data.get(i).ID;
            }
        }
        return null;
    }

    public static String buildUrl(String theater, String date) {
        if ( theater == null && date == null ) {
            return "http://www.finnkino.fi/xml/Schedule";
        }

        else if ( theater == null && !(date == null) ) {
            return "http://www.finnkino.fi/xml/Schedule?dt=" + date;
        }

        else if ( !(theater == null) && date == null ) {
            String id = getId(theater);
            if (id == null) {
                return "http://www.finnkino.fi/xml/Schedule";
            } else {
                return "http://www.finnkino.fi/xml/Schedule?area=" + id;

            }
        }
        else {
            String id = getId(theater);
            if (id == null) {
                return "http://www.finnkino.fi/xml/Schedule?dt=" + date;
            }
            else {
                return "http://www.finnkino.fi/xml/Schedule?area=" + id + "&dt=" + date;
            }
        }
    }

    public static ArrayList<String> readScheduleXML(String url) {
        ArrayList<String> shows = new ArrayList<String>();
        shows.clear();

        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document doc = builder.parse(url);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getDocumentElement().getElementsByTagName("Show");


            for ( int i = 0; i < nList.getLength(); i++ ) {
                Node node = nList.item(i);
                if ( node.getNodeType() == Node.ELEMENT_NODE ) {
                    Element element = (Element) node;
                    String title = element.getElementsByTagName("Title").item(0).getTextContent();
                    String startTime = element.getElementsByTagName("dttmShowStart").item(0).getTextContent();
                    String cleanTime = startTime.substring(11, 16);
                    shows.add(title + "   " + cleanTime);

                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("ONNISTUI");
            return shows;
        }
    }

    public static ArrayList<String> filterTimes(ArrayList<String> movies, String url, String start, String end) {
        movies.clear();
        DocumentBuilder builder = null;
        try {
            builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document doc = null;
        try {
            doc = builder.parse(url);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        doc.getDocumentElement().normalize();

        NodeList nList = doc.getDocumentElement().getElementsByTagName("Show");


        for ( int i = 0; i < nList.getLength(); i++ ) {
            Node node = nList.item(i);
            if ( node.getNodeType() == Node.ELEMENT_NODE ) {
                Element element = (Element) node;
                String title = element.getElementsByTagName("Title").item(0).getTextContent();
                String startTime = element.getElementsByTagName("dttmShowStart").item(0).getTextContent();
                String cleanTime = startTime.substring(11, 16);
                String intTime = cleanTime.substring(0,2)+cleanTime.substring(3,5);
                int time = Integer.parseInt(intTime);
                String cleanStart = start + "00";
                String cleanEnd = end + "00";
                int iStart;
                int iEnd;
                if(cleanStart.matches("\\d+")) {
                    iStart = Integer.parseInt(cleanStart);
                }
                else { iStart = 0;}


                if(cleanEnd.matches("\\d+")) {
                    iEnd = Integer.parseInt(cleanEnd);
                }
                else {iEnd = 1000000;}


                if ( iStart < time && time < iEnd ) {
                    movies.add(title + "   " + cleanTime);
                }
                }
        }

        return movies;
    }
}
