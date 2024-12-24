package com.hackerthon.main;

import com.hackerthon.common.TransformUtil;
import com.hackerthon.service.EmployeeService;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;


/**
 * Main class to execute the sequence of operations related to employee data transformation and management.
 */
public class ExecuteMain {

    /**
     * Main method to run the application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {

        // Get the singleton instance of EmployeeService.
        EmployeeService employeeService = EmployeeService.getInstance();

        try {
            // Transform XML data using the TransformUtil class.
            TransformUtil.transformXML();

            // Load employee data from the transformed XML.
            employeeService.loadEmployeesFromXML();
            
            // Create the employee table in the database.
            employeeService.createEmployeeTable();
            
            // Add employees to the database.
            employeeService.addEmployees();
            
            // Retrieve and display an employee by ID.
            employeeService.getEmployeeById("EMP10004");
            
            // Delete an employee from the database by ID.
            employeeService.deleteEmployee("EMP10001");
            
            // Display all employees from the database.
            employeeService.displayEmployee();
            
        } catch (TransformerConfigurationException e) {
            // Handle exceptions related to transformer configuration errors.
            e.printStackTrace();
        } catch (TransformerException e) {
            // Handle exceptions related to transformer errors.
            e.printStackTrace();
        } catch (TransformerFactoryConfigurationError e) {
            // Handle errors related to transformer factory configuration.
            e.printStackTrace();
        } catch (Exception e) {
            // Handle any other exceptions that may occur.
            e.printStackTrace();
        }
    }
}
