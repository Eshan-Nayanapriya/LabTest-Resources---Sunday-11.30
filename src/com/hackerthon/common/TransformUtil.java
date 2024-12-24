package com.hackerthon.common;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.*;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * Utility class for transforming XML documents using XSLT and extracting data using XPath.
 * This class extends ConfigUtil to utilize configuration properties.
 */
public class TransformUtil extends ConfigUtil {

    // A list to store employee data extracted from the XML.
    private static final ArrayList<Map<String, String>> employeeDataList = new ArrayList<>();

    // A map to temporarily store data for each employee during XML processing.
    private static Map<String, String> employeeDataMap = null;

    /**
     * Transforms an XML file using the XSLT specified in the configuration properties.
     * 
     * @throws TransformerException If an error occurs during the transformation.
     * @throws TransformerConfigurationException If there is an error in the transformer configuration.
     * @throws TransformerFactoryConfigurationError If a factory configuration error occurs.
     * @throws ParserConfigurationException If there is a parser configuration error.
     */
    public static void transformXML() throws TransformerException, TransformerConfigurationException, TransformerFactoryConfigurationError, ParserConfigurationException {
        
        // Source XML file to be transformed, specified in the configuration properties.
        Source xmlSource = new StreamSource(new File(properties.getProperty(CommonConstants.REQUEST_FILE_PATH)));
        
        // XSLT file to be applied to the XML, specified in the configuration properties.
        Source xslSource = new StreamSource(new File(properties.getProperty(CommonConstants.XSL_FILE_PATH)));
        
        // Output file path for the transformed XML, specified in the configuration properties.
        Result transformedXmlResult = new StreamResult(new File(properties.getProperty(CommonConstants.RESPONSE_FILE_PATH)));
        
        // Perform the transformation from XML to the desired format using the specified XSLT.
        TransformerFactory.newInstance().newTransformer(xslSource).transform(xmlSource, transformedXmlResult);
    }

    /**
     * Extracts data from the transformed XML using XPath and stores it in a list of maps.
     * 
     * @return An ArrayList of Maps where each Map represents an employee's data.
     * @throws XPathExpressionException If an error occurs during XPath evaluation.
     * @throws SAXException If a SAX parsing error occurs.
     * @throws IOException If an I/O error occurs while reading the XML.
     * @throws ParserConfigurationException If there is a parser configuration error.
     */
    public static ArrayList<Map<String, String>> extractEmployeeData() throws XPathExpressionException, SAXException, IOException, ParserConfigurationException {

        // Parse the transformed XML document specified in the configuration properties.
        Document xmlDocument = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(properties.getProperty(CommonConstants.RESPONSE_FILE_PATH));
        
        // Create an XPath object for evaluating XPath expressions.
        XPath xpathEvaluator = XPathFactory.newInstance().newXPath();
        
        // Compile an XPath expression to count the number of employee elements in the XML.
        XPathExpression countExpression = xpathEvaluator.compile(CommonConstants.EMPLOYEE_COUNT_XML_TAG);
        
        // Evaluate the expression to get the total number of employees.
        int employeeCount = Integer.parseInt((String) countExpression.evaluate(xmlDocument, XPathConstants.STRING));
        
        // Loop through each employee node and extract relevant data.
        for (int i = 1; i <= employeeCount; i++) {
            employeeDataMap = new HashMap<>();
            
            // Extract and store employee ID, name, address, faculty name, department, and designation.
            employeeDataMap.put(CommonConstants.XPATH_EMPLOYEE_ID_KEY, extractValueByXPath(xmlDocument, xpathEvaluator, CommonConstants.EMPLOYEE_ID, i));
            employeeDataMap.put(CommonConstants.XPATH_EMPLOYEE_NAME_KEY, extractValueByXPath(xmlDocument, xpathEvaluator, CommonConstants.EMPLOYEE_NAME, i));
            employeeDataMap.put(CommonConstants.XPATH_EMPLOYEE_ADDRESS_KEY, extractValueByXPath(xmlDocument, xpathEvaluator, CommonConstants.EMPLOYEE_ADDRESS, i));
            employeeDataMap.put(CommonConstants.XPATH_EMPLOYEE_FACULTY_NAME_KEY, extractValueByXPath(xmlDocument, xpathEvaluator, CommonConstants.EMPLOYEE_FACULTY_NAME, i));
            employeeDataMap.put(CommonConstants.XPATH_EMPLOYEE_DEPARTMENT_KEY, extractValueByXPath(xmlDocument, xpathEvaluator, CommonConstants.EMPLOYEE_DEPARTMENT, i));
            employeeDataMap.put(CommonConstants.XPATH_EMPLOYEE_DESIGNATION_KEY, extractValueByXPath(xmlDocument, xpathEvaluator, CommonConstants.EMPLOYEE_DESIGNATION, i));
            
            // Add the extracted employee data to the list.
            employeeDataList.add(employeeDataMap);
        }
        // Return the list of employee data.
        return employeeDataList;
    }
    
    /**
     * Helper method to extract the value of a specific XPath expression from the XML document.
     * 
     * @param document The XML Document object.
     * @param xpath The XPath object to evaluate expressions.
     * @param elementKey The XML tag key to be evaluated.
     * @param nodeIndex The node index for which the value is extracted.
     * @return The extracted value as a String.
     * @throws XPathExpressionException If an error occurs during XPath evaluation.
     */
    private static String extractValueByXPath(Document document, XPath xpath, String elementKey, int nodeIndex) throws XPathExpressionException {
        
        // Compile the XPath expression using the element key and node index, and evaluate it.
        XPathExpression expression = xpath.compile(MessageFormat.format(properties.getProperty("text"), nodeIndex, elementKey));
        
        // Return the evaluated value as a String.
        return (String) expression.evaluate(document, XPathConstants.STRING);
    }
}
