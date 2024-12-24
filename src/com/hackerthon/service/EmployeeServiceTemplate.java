package com.hackerthon.service;

import com.hackerthon.common.ConfigUtil;
import com.hackerthon.model.EmployeeModel;

import java.util.ArrayList;


/**
 * Abstract base class for employee service operations.
 * This class provides abstract methods for various employee-related operations
 * that need to be implemented by subclasses.
 */
abstract public class EmployeeServiceTemplate extends ConfigUtil {

    /**
     * Abstract method to load employee data from XML.
     * Subclasses must provide an implementation for this method.
     */
    public abstract void loadEmployeesFromXML();

    /**
     * Abstract method to create the employee table in the database.
     * Subclasses must provide an implementation for this method.
     */
    public abstract void createEmployeeTable();

    /**
     * Abstract method to add employees to the database.
     * Subclasses must provide an implementation for this method.
     */
    public abstract void addEmployees();

    /**
     * Abstract method to retrieve an employee from the database by their ID.
     * 
     * @param eid The ID of the employee to be retrieved.
     * Subclasses must provide an implementation for this method.
     */
    public abstract void getEmployeeById(String eid);

    /**
     * Abstract method to display all employees from the database.
     * Subclasses must provide an implementation for this method.
     */
    public abstract void displayEmployee();

    /**
     * Abstract method to delete an employee from the database by their ID.
     * 
     * @param eid The ID of the employee to be deleted.
     * Subclasses must provide an implementation for this method.
     */
    public abstract void deleteEmployee(String eid);

    /**
     * Abstract method to output the details of a list of employees.
     * 
     * @param l The list of EmployeeModel objects to be output.
     * Subclasses must provide an implementation for this method.
     */
    public abstract void outputEmployees(ArrayList<EmployeeModel> l);
}
