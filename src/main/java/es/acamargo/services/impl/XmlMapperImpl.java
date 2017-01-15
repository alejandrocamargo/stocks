package es.acamargo.services.impl;

import es.acamargo.entities.AbstractMarket;
import es.acamargo.services.XmlMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;


public class XmlMapperImpl implements XmlMapper {

    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<AbstractMarket> mapToMarketFromXml(String xml, Class<? extends AbstractMarket> clazz) {

        List<AbstractMarket> marketList = new ArrayList<>();

        Document document = getDocument(xml);

        Element root = document.getDocumentElement();

        Node results = root.getElementsByTagName("results").item(0);

        NodeList nodeList = results.getChildNodes();

        for (int i = 0; i <= nodeList.getLength(); i++) {

            Node node = nodeList.item(i);

            if (node != null) {

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    marketList.add(elementToAbstractMarket(element, clazz));

                }
            }

        }

        return marketList;

    }

    private Document getDocument(String xml) {

        Document doc = null;

        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = factory.newDocumentBuilder();

            StringBuilder xmlStringBuilder = new StringBuilder();

            xmlStringBuilder.append(xml);

            ByteArrayInputStream input = new ByteArrayInputStream(xmlStringBuilder.toString().getBytes("UTF-8"));

            doc = builder.parse(input);

        } catch (Exception e) {

            logger.error(e.getMessage());

        }

        return doc;

    }

    private AbstractMarket elementToAbstractMarket(Element e, Class<? extends AbstractMarket> clazz) {

        try {

            AbstractMarket market = clazz.newInstance();

            market.setSymbol(e.getAttribute("Symbol"));
            market.setDate(e.getElementsByTagName("Date").item(0).getTextContent());
            market.setOpen(Double.parseDouble(e.getElementsByTagName("Open").item(0).getTextContent()));
            market.setHigh(Double.parseDouble(e.getElementsByTagName("High").item(0).getTextContent()));
            market.setLow(Double.parseDouble(e.getElementsByTagName("Low").item(0).getTextContent()));
            market.setClose(Double.parseDouble(e.getElementsByTagName("Close").item(0).getTextContent()));
            market.setVolume(Double.parseDouble(e.getElementsByTagName("Volume").item(0).getTextContent()));
            market.setAdjClose(Double.parseDouble(e.getElementsByTagName("Adj_Close").item(0).getTextContent()));

            return market;

        } catch (Exception ex) {

            logger.error(ex.getMessage());
        }

        return null;


    }


}
