package com.hackerthon.common;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;


/**
 * Utility class for fetching queries from an XML file based on a given ID.
 * This class extends ConfigUtil to utilize the configuration properties.
 */
public class QueryUtil extends ConfigUtil {
	
    /**
     * Retrieves the query string associated with the given ID from the XML file.
     *
     * @param id The ID of the query to retrieve.
     * @return The query string corresponding to the provided ID.
     * @throws TransformerException If an exceptional condition occurs during the transformation process.
     * @throws TransformerConfigurationException If an error occurs in the configuration of the transformer.
     */
	public static String getQueryById(String id) throws TransformerException, TransformerConfigurationException {
		NodeList nodeList; // List of nodes with the tag name specified in the XML.
		Element element = null; // Element to hold the found query node.
		
        try {
            // Parse the XML file specified in the properties and get the list of nodes with the tag name.
			nodeList = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(new File(properties.getProperty(CommonConstants.QUERY_XML)))
					.getElementsByTagName(CommonConstants.TAG_NAME);
			
            // Iterate through the node list to find the element with the matching ID attribute.
			for (int x = 0; x < nodeList.getLength(); x++) {
				element = (Element) nodeList.item(x);
				
                // If the element's ID attribute matches the provided ID, break the loop.
				if (element.getAttribute(CommonConstants.ATTRIB_ID).equals(id))
					break;
			}
		} catch (TransformerFactoryConfigurationError e) {
			// Log an error if a TransformerFactoryConfigurationError occurs.
			Log.log(Level.SEVERE, e.getMessage());
		} catch (SAXException e) {
			// Log an error if a SAXException occurs while parsing the XML file.
			Log.log(Level.SEVERE, e.getMessage());
		} catch (IOException e) {
			// Log an error if an IOException occurs while reading the XML file.
			Log.log(Level.SEVERE, e.getMessage());
		} catch (ParserConfigurationException e) {
			// Log an error if a ParserConfigurationException occurs while setting up the XML parser.
			Log.log(Level.SEVERE, e.getMessage());
		}
		
        // Return the text content of the found element, trimmed of any leading or trailing whitespace.
		return element.getTextContent().trim();
	}
}
