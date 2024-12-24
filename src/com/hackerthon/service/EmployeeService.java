package com.hackerthon.service;

import com.hackerthon.common.CommonConstants;
import com.hackerthon.common.QueryUtil;
import com.hackerthon.common.TransformUtil;
import com.hackerthon.model.EmployeeModel;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;


/**
 * Service class for handling employee-related operations, including
 * database interactions and XML processing.
 */
public class EmployeeService extends EmployeeServiceTemplate {

    // Singleton instance of EmployeeService
    private static EmployeeService instance;

    // List to store EmployeeModel objects
    private final ArrayList<EmployeeModel> employeeList = new ArrayList<>();

    // Database connection
    private static Connection dbConnection;

    // SQL statement object
    private static Statement sqlStatement;

    // Prepared statement object for executing parameterized queries
    private PreparedStatement preparedStatement;

    // Logger for logging errors and information
    public static final Logger logger = Logger.getLogger(EmployeeService.class.getName());

    /**
     * Private constructor to initialize database connection.
     */
    private EmployeeService() {
        try {
            // Load database driver
            Class.forName(properties.getProperty(CommonConstants.DRIVERNAME));
            
            // Establish database connection
            dbConnection = DriverManager.getConnection(properties.getProperty(CommonConstants.URL),
                properties.getProperty(CommonConstants.USERNAME),
                properties.getProperty(CommonConstants.PASSWORD));
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    /**
     * Provides access to the singleton instance of EmployeeService.
     * 
     * @return The singleton instance of EmployeeService.
     */
    public static EmployeeService getInstance() {
        if (instance == null) {
            instance = new EmployeeService();
        }
        return instance;
    }

    /**
     * Loads employee data from XML using TransformUtil and stores it in employeeList.
     */
    public void loadEmployeesFromXML() {
        try {
            // Get employee data from XML and populate employeeList
            int numberOfRecords = TransformUtil.extractEmployeeData().size();
            
            for (int index = 0; index < numberOfRecords; index++) {
                Map<String, String> employeeData = TransformUtil.extractEmployeeData().get(index);
                
                EmployeeModel employee = new EmployeeModel();
                employee.setEmployeeID(employeeData.get(CommonConstants.XPATH_EMPLOYEE_ID_KEY));
                employee.setFullName(employeeData.get(CommonConstants.XPATH_EMPLOYEE_NAME_KEY));
                employee.setAddress(employeeData.get(CommonConstants.XPATH_EMPLOYEE_ADDRESS_KEY));
                employee.setFacultyName(employeeData.get(CommonConstants.XPATH_EMPLOYEE_FACULTY_NAME_KEY));
                employee.setDepartment(employeeData.get(CommonConstants.XPATH_EMPLOYEE_DEPARTMENT_KEY));
                employee.setDesignation(employeeData.get(CommonConstants.XPATH_EMPLOYEE_DESIGNATION_KEY));
                
                employeeList.add(employee);
                
                System.out.println(employee.toString() + "\n");
            }
            
        } catch (XPathExpressionException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (SAXException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (ParserConfigurationException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

    /**
     * Creates the employee table in the database.
     */
    public void createEmployeeTable() {
        try {
            sqlStatement = dbConnection.createStatement();
            
            // Execute SQL queries to delete and create employee table
            sqlStatement.executeUpdate(QueryUtil.getQueryById(CommonConstants.DELETE_EMPLOYEE_TABLE_QUERY));
            sqlStatement.executeUpdate(QueryUtil.getQueryById(CommonConstants.CREATE_EMPLOYEE_TABLE_QUERY));
        
        } catch (TransformerConfigurationException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (TransformerException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } finally {
            // Close resources
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (dbConnection != null) {
                    dbConnection.close();
                }
                
            } catch (SQLException e) {
                logger.log(Level.SEVERE, e.getMessage());
            }
        }
    }

    /**
     * Adds employees to the database in a batch operation.
     */
    public void addEmployees() {
        try {
            preparedStatement = dbConnection.prepareStatement(QueryUtil.getQueryById(CommonConstants.INSERT_EMPLOYEE_QUERY));
            
            dbConnection.setAutoCommit(false);
            for (EmployeeModel employee : employeeList) {
                preparedStatement.setString(1, employee.getEmployeeID());
                preparedStatement.setString(2, employee.getFullName());
                preparedStatement.setString(3, employee.getAddress());
                preparedStatement.setString(4, employee.getFacultyName());
                preparedStatement.setString(5, employee.getDepartment());
                preparedStatement.setString(6, employee.getDesignation());
                
                preparedStatement.addBatch();
            }
            
            preparedStatement.executeBatch();
            dbConnection.commit();
            
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL Exception: " + e.getMessage());
        } catch (TransformerConfigurationException e) {
            logger.log(Level.SEVERE, "Transformer Configuration Exception: " + e.getMessage());
        } catch (TransformerException e) {
            logger.log(Level.SEVERE, "Transformer Exception: " + e.getMessage());
        } finally {
            // Close resources
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (dbConnection != null) {
                    dbConnection.close();
                }
                
            } catch (SQLException e) {
                logger.log(Level.SEVERE, e.getMessage());
            }
        }
    }

    /**
     * Retrieves and outputs employee details by employee ID.
     * 
     * @param employeeID The employee ID.
     */
    @Override
    public void getEmployeeById(String employeeID) {
        EmployeeModel employee = new EmployeeModel();
        try {
            preparedStatement = dbConnection.prepareStatement(QueryUtil.getQueryById(CommonConstants.GET_EMPLOYEE_BY_ID_QUERY));
            preparedStatement.setString(1, employeeID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employee.setEmployeeID(resultSet.getString(1));
                employee.setFullName(resultSet.getString(2));
                employee.setAddress(resultSet.getString(3));
                employee.setFacultyName(resultSet.getString(4));
                employee.setDepartment(resultSet.getString(5));
                employee.setDesignation(resultSet.getString(6));
            }
            ArrayList<EmployeeModel> employeeList = new ArrayList<>();
            employeeList.add(employee);
            outputEmployees(employeeList);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage());
        } finally {
            // Close resources
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (dbConnection != null) {
                    dbConnection.close();
                }
                
            } catch (SQLException ex) {
                logger.log(Level.SEVERE, ex.getMessage());
            }
        }
    }

    /**
     * Deletes an employee from the database by employee ID.
     * 
     * @param employeeID The employee ID.
     */
    public void deleteEmployee(String employeeID) {
        try {
            preparedStatement = dbConnection.prepareStatement(QueryUtil.getQueryById(CommonConstants.DELETE_EMPLOYEE_BY_ID_QUERY));
            preparedStatement.setString(1, employeeID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (TransformerConfigurationException e) {
        	 logger.log(Level.SEVERE, e.getMessage());
		} catch (TransformerException e) {
			 logger.log(Level.SEVERE, e.getMessage());
		}
    }

    /**
     * Retrieves and displays all employees from the database.
     */
    public void displayEmployee() {
        ArrayList<EmployeeModel> employees = new ArrayList<>();
        try {
            preparedStatement = dbConnection.prepareStatement(QueryUtil.getQueryById(CommonConstants.GET_ALL_EMPLOYEES_QUERY));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                EmployeeModel employee = new EmployeeModel();
                employee.setEmployeeID(resultSet.getString(1));
                employee.setFullName(resultSet.getString(2));
                employee.setAddress(resultSet.getString(3));
                employee.setFacultyName(resultSet.getString(4));
                employee.setDepartment(resultSet.getString(5));
                employee.setDesignation(resultSet.getString(6));
                employees.add(employee);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, e.getMessage());
        } catch (TransformerConfigurationException e) {
        	logger.log(Level.SEVERE, e.getMessage());
		} catch (TransformerException e) {
			logger.log(Level.SEVERE, e.getMessage());
		} finally {
            // Close resources
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (dbConnection != null) {
                    dbConnection.close();
                }
                
            } catch (SQLException e) {
                logger.log(Level.SEVERE, e.getMessage());
            }
        }
        
        outputEmployees(employees);
    }

    /**
     * Outputs the details of the given list of employees.
     * 
     * @param employees The list of EmployeeModel objects to output.
     */
    public void outputEmployees(ArrayList<EmployeeModel> employees) {
        System.out.println("Employee ID" + "\t\t" + "Full Name" + "\t\t" + "Address" + "\t\t" + "Faculty Name" + "\t\t" + "Department" + "\t\t" + "Designation" + "\n");
        System.out.println("================================================================================================================");
        
        for (EmployeeModel employee : employees) {
            System.out.println(employee.getEmployeeID() + "\t" + employee.getFullName() + "\t\t" + employee.getAddress() + "\t" + employee.getFacultyName() + "\t" + employee.getDepartment() + "\t" + employee.getDesignation() + "\n");
            System.out.println("----------------------------------------------------------------------------------------------------------------");
        }
    }
}
