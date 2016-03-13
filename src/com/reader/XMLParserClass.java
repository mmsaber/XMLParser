package com.reader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLParserClass extends DefaultHandler {
	 
    //List to hold Employees object
    private List<Employee> empList = null;
    private Employee emp = null;
 
 
    //getter method for employee list
    public List<Employee> getEmpList() {
        return empList;
    }
 
    boolean bId = false;
    boolean bAge = false;
    boolean bName = false;
    boolean bGrade = false;
    boolean bSalary = false;
 
 
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
 
        if (qName.equalsIgnoreCase("id")) {
           bId = true;
        } else if (qName.equalsIgnoreCase("name")) {
            //set boolean values for fields, will be used in setting Employee variables
            bName = true;
        } else if (qName.equalsIgnoreCase("age")) {
            bAge = true;
        } else if (qName.equalsIgnoreCase("salary")) {
            bSalary = true;
        } else if (qName.equalsIgnoreCase("grade")) {
            bGrade = true;
        }
    }
 
    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
    	
    	if(bId)
    	{
            emp = new Employee();
            emp.setId(Integer.parseInt(new String(ch, start, length)));
            //initialize list
            if (empList == null)
                empList = new ArrayList<>();
    		bId = false;
    	}
    	else if (bAge) {
            //age element, set Employee age
            emp.setAge(Integer.parseInt(new String(ch, start, length)));
            bAge = false;
        } else if (bName) {
            emp.setName(new String(ch, start, length));
            bName = false;
        } else if (bSalary) {
            emp.setSalary(Double.parseDouble(new String(ch, start, length)));
            bSalary = false;
        } else if (bGrade) {
            emp.setGrade(Integer.parseInt(new String(ch, start, length)));
            bGrade = false;
        }
    }
	public Connection getConnection() {
		Connection connection = null;
		String connectionURL = "jdbc:mysql://localhost:3306/JavaSQL";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(connectionURL, "root", "admin");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

	// close the connection
	public void closeConnection(Connection c) {
		try {
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("Employee")) {
            //add Employee object to list
            empList.add(emp);
            String sqlQuery = "INSERT INTO employees (id,name,age,salary,grade) VALUES (?,?,?,?,?)";
    		Connection connection = getConnection();
    		try {
    			PreparedStatement statement = connection.prepareStatement(sqlQuery);
    			statement.setInt(1, emp.getId());
    			statement.setString(2, emp.getName());
    			statement.setInt(3, emp.getAge());
    			statement.setDouble(4, emp.getSalary());
    			statement.setInt(5, emp.getGrade());
    			statement.execute();

    		} catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} finally {
    			closeConnection(connection);
    		}
        }
    }
}