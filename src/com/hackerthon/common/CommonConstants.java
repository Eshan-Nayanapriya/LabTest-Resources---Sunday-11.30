package com.hackerthon.common;

public class CommonConstants {

    // Path to the properties file used for configuration
    public static final String PROPERTY_FILE = "../config/config.properties";

    // Key for fetching the query XML file path from properties
    public static final String QUERY_XML = "QueryFilePath";

    // Key for fetching the request file path from properties
    public static final String REQUEST_FILE_PATH = "RequestFilePath";

    // Key for fetching the XSL file path from properties
    public static final String XSL_FILE_PATH = "XSLFilePath";

    // Key for fetching the response file path from properties
    public static final String RESPONSE_FILE_PATH = "ResponseFilePath";

    // XML tag name for queries
    public static final String TAG_NAME = "query";

    // XML attribute name for query IDs
    public static final String ATTRIB_ID = "id";

    // Key for fetching the database username from properties
    public static final String USERNAME = "username";

    // Key for fetching the database password from properties
    public static final String PASSWORD = "password";

    // Key for fetching the database driver name from properties
    public static final String DRIVERNAME = "driverName";

    // Key for fetching the database URL from properties
    public static final String URL = "url";

    // XPath key for extracting employee ID from XML
    public static final String XPATH_EMPLOYEE_ID_KEY = "XpathEmployeeIDKey";

    // XPath key for extracting employee name from XML
    public static final String XPATH_EMPLOYEE_NAME_KEY = "XpathEmployeeNameKey";

    // XPath key for extracting employee address from XML
    public static final String XPATH_EMPLOYEE_ADDRESS_KEY = "XpathEmployeeAddressKey";

    // XPath key for extracting faculty name from XML
    public static final String XPATH_EMPLOYEE_FACULTY_NAME_KEY = "XpathFacultyNameKey";

    // XPath key for extracting department from XML
    public static final String XPATH_EMPLOYEE_DEPARTMENT_KEY = "XpathDepartmentKey";

    // XPath key for extracting designation from XML
    public static final String XPATH_EMPLOYEE_DESIGNATION_KEY = "XpathDesignationKey";

    // SQL query key for creating the employee table
    public static final String CREATE_EMPLOYEE_TABLE_QUERY = "q1";

    // SQL query key for deleting the employee table
    public static final String DELETE_EMPLOYEE_TABLE_QUERY = "q2";

    // SQL query key for inserting an employee record
    public static final String INSERT_EMPLOYEE_QUERY = "q3";

    // SQL query key for retrieving an employee by ID
    public static final String GET_EMPLOYEE_BY_ID_QUERY = "q4";

    // SQL query key for retrieving all employees
    public static final String GET_ALL_EMPLOYEES_QUERY = "q5";

    // SQL query key for deleting an employee by ID
    public static final String DELETE_EMPLOYEE_BY_ID_QUERY = "q6";

    // XPath expression for counting the number of employees in an XML document
    public static final String EMPLOYEE_COUNT_XML_TAG = "count(//Employees/Employee)";
    
    // XPath tag name for the employee ID element in the XML
    public static final String EMPLOYEE_ID = "EmployeeID";

    // XPath tag name for the employee full name element in the XML
    public static final String EMPLOYEE_NAME = "EmployeeFullName";

    // XPath tag name for the employee address element in the XML
    public static final String EMPLOYEE_ADDRESS = "EmployeeFullAddress";

    // XPath tag name for the employee faculty name element in the XML
    public static final String EMPLOYEE_FACULTY_NAME = "FacultyName";

    // XPath tag name for the employee department element in the XML
    public static final String EMPLOYEE_DEPARTMENT = "Department";

    // XPath tag name for the employee designation element in the XML
    public static final String EMPLOYEE_DESIGNATION = "Designation";

}
