package es.acamargo.services;

import es.acamargo.entities.AbstractMarket;

import java.util.List;

public interface XmlMapper {

    List<AbstractMarket> mapToMarketFromXml(String xml, Class<? extends AbstractMarket> clazz);

}
