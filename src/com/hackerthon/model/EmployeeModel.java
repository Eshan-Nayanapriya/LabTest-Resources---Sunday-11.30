package com.hackerthon.model;

/**
 * Model class representing an employee with various attributes.
 */
public class EmployeeModel {

    // Employee attributes
    private String employeeID;
    private String fullName;
    private String address;
    private String facultyName;
    private String department;
    private String designation;

    /**
     * Gets the employee ID.
     *
     * @return The employee ID.
     */
    public String getEmployeeID() {
        return employeeID;
    }

    /**
     * Sets the employee ID.
     *
     * @param employeeID The employee ID to set.
     */
    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    /**
     * Gets the full name of the employee.
     *
     * @return The full name of the employee.
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the full name of the employee.
     *
     * @param fullName The full name to set.
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Gets the address of the employee.
     *
     * @return The address of the employee.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the employee.
     *
     * @param address The address to set.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the faculty name associated with the employee.
     *
     * @return The faculty name.
     */
    public String getFacultyName() {
        return facultyName;
    }

    /**
     * Sets the faculty name associated with the employee.
     *
     * @param facultyName The faculty name to set.
     */
    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    /**
     * Gets the department of the employee.
     *
     * @return The department of the employee.
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Sets the department of the employee.
     *
     * @param department The department to set.
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * Gets the designation of the employee.
     *
     * @return The designation of the employee.
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * Sets the designation of the employee.
     *
     * @param designation The designation to set.
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * Returns a string representation of the employee.
     *
     * @return A string containing the employee's details.
     */
    @Override
    public String toString() {
        return "Employee ID = " + employeeID + "\n" +
               "Full Name = " + fullName + "\n" +
               "Address = " + address + "\n" +
               "Faculty Name = " + facultyName + "\n" +
               "Department = " + department + "\n" +
               "Designation = " + designation;
    }
}
