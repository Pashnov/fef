package university.util.access;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import university.util.access.entity.Constraint;
import university.util.access.entity.Security;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Andrii_Pashnov on 15.12.2014 13:07.
 */
public class SaxHandler extends DefaultHandler{

    private static final Logger LOG = Logger.getLogger(SaxHandler.class);

    private String xmlFileName;

    private InputStream xmlStream;

    private String currentElement;

    private Security security;

    private Constraint constraint;

    public SaxHandler(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    public SaxHandler(InputStream xmlStream) {
        this.xmlStream = xmlStream;
    }

    public Security getSecurity() {
        return security;
    }

    public void parse(boolean validate) throws SAXException, ParserConfigurationException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        factory.setNamespaceAware(true);

        SAXParser parser = factory.newSAXParser();
        if(xmlFileName != null){
            parser.parse(xmlFileName, this);
        } else {
            parser.parse(xmlStream, this);
        }
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        throw e;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
//        LOG.debug("startElement, "  + ", localName = " + localName + ", qName = " + qName);
        currentElement = localName;

        if("security".equals(currentElement)){
            security = new Security();
            return;
        }

        if("constraint".equals(currentElement)){
            constraint = new Constraint();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String elementText = new String(ch, start, length).trim();
//        LOG.debug("characters, elementText = " + elementText);
//        LOG.debug("currentElement = " + currentElement);
        if(elementText.length() == 0) return;
        if("url-pattern".equals(currentElement)){
//            LOG.debug("if url-pattern");
            constraint.setUrlPattern(elementText);
            return;
        }

        if("role".equals(currentElement)){
            constraint.getRoles().add(elementText);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
//        LOG.debug("endElement, uri = " + uri + ", localName = " + localName + ", qName = " + qName);

        if("constraint".equals(localName)){
//            LOG.debug("if constraint, constraint = " + constraint);
            security.getConstraints().add(constraint);
        }
//        currentElement = "";
    }
}
