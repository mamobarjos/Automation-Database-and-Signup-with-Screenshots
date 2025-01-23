package mypakage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCaseRandom {
	//WebDriver driver = new ChromeDriver();
	Connection con;
	Statement stmt;
	ResultSet rs;  
	String randomIndex;


@BeforeTest
public void MySetup () throws SQLException {
	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","258456");
	System.out.println(randomIndex);
} 
@Test(priority=1, description="add New Customer",invocationCount =5)
public void AddNewCustomer() throws SQLException {
	//for random data
		//must be integer
		//int randomIndex = rand.nextInt(500,900);
		//or convert to string
	Random rand = new Random();
	String randomIndex = Integer.toString(rand.nextInt(500,900));

	String QueryToAdd= "Insert into customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, addressLine2, city, state, postalCode, country, salesRepEmployeeNumber, creditLimit)\r\n"+"VALUES ("+randomIndex+", 'ABC Corporation', 'Smith', 'John', '+1 555 123 4567', '123 Main St', 'Suite 100', 'New York', 'NY', '10001', 'USA', 1370, 50000.00)";

	stmt =con.createStatement();
	int effectedRow = stmt.executeUpdate(QueryToAdd);
	System.out.println(effectedRow);
	Assert.assertEquals(effectedRow, 1);
}
@Test(priority=2, description="Update Customer")
public void UpdateCustomerInfo() throws SQLException {
	String MyQuery = "update customers set contactFirstName = 'mhmood' where customerNumber =" + randomIndex;	
	stmt =con.createStatement();
	int effectedRow = stmt.executeUpdate(MyQuery);
	System.out.println(effectedRow);
	Assert.assertEquals(effectedRow, 1);
} 
@Test(priority=3, description="Read The Updated Data Result")
public void ReadTheUpdatedData() throws SQLException {
	String MyQuery = "select * from customers where customerNumber =" + randomIndex;
	stmt =con.createStatement();
	rs = stmt.executeQuery(MyQuery);
	boolean ActualResult= rs.next();
	while(rs.next()) {
		String contactFirstName = rs.getString("contactFirstName");
		int ContactId = Integer.parseInt(rs.getString("customerNumber"));
		String CityOfTheCustomer = rs.getString("city");
		
		System.out.println(contactFirstName);
		//read contactFirstName طول ما عدد الأحرف اكبر من  0  في داتا
		Assert.assertEquals(contactFirstName.length()>0, true);
		//read contactFirstName طول ما عدد الأحرف اكبر من  0  في داتا
		Assert.assertEquals(CityOfTheCustomer.length()>0, true);

		System.out.println(ContactId);
		System.out.println(CityOfTheCustomer);
	}
	Assert.assertEquals(ActualResult, true);

} 
@Test(priority=4, description="Remove Customer", enabled=true)
public void DeleteCustomer() throws SQLException {
	String MyQuery= "delete from customers where customerNumber =" + randomIndex;
	
	stmt =con.createStatement();
	int effectedRow = stmt.executeUpdate(MyQuery);
	System.out.println(effectedRow);
	Assert.assertEquals(effectedRow, 1);
}
}
