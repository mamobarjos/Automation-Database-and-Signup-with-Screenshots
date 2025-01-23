package mypakage;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCase extends Parameters {
	String username;
@BeforeTest
public void MySetup () throws SQLException {
	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","258456");
	System.out.println(randomIndex);
	//driver.get(website);
	driver.get(website0);
	driver.manage().window().maximize();
		
	
} 
@Test(priority=1, description="add New Customer")
public void AddNewCustomer() throws SQLException {
	String QueryToAdd= "Insert into customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, addressLine2, city, state, postalCode, country, salesRepEmployeeNumber, creditLimit)\r\n"+"VALUES ("+randomIndex+", 'ABC Corporation', 'Smith', 'John', '+1 555 123 4567', '123 Main St', 'Suite 100', 'New York', 'NY', '10001', 'USA', 1370, 50000.00)";
	
	stmt =con.createStatement();
	int effectedRow = stmt.executeUpdate(QueryToAdd);
	System.out.println(effectedRow);
	Assert.assertEquals(effectedRow, 1);
}
@Test(priority=2, description="Update Customer")
public void UpdateCustomserInfo() throws SQLException {
	stmt =con.createStatement();
	int effectedRow = stmt.executeUpdate(QueryToUpdate);
	System.out.println(effectedRow);
	Assert.assertEquals(effectedRow, 1);
} 
//for coden box
/*@Test(priority=3, description="Read The Updated Data Result")
public void ReadTheUpdatedData() throws SQLException, InterruptedException, IOException {
	stmt =con.createStatement();
	rs = stmt.executeQuery(ReadQuery);
	//boolean ActualResult= rs.next();

	while(rs.next()) {
		String contactFirstName = rs.getString("contactFirstName");
        String CityOfTheCustomer = rs.getString("city");
    //int ContactId = Integer.parseInt(rs.getString("customerNumber"));
   
//driver.switchTo().alert().dismiss();
	
		System.out.println(contactFirstName);
		//read contactFirstName طول ما عدد الأحرف اكبر من  0  في داتا
		Assert.assertEquals(contactFirstName.length()>0, true);
		//read contactFirstName طول ما عدد الأحرف اكبر من  0  في داتا
		Assert.assertEquals(CityOfTheCustomer.length()>0, true);

		//System.out.println(ContactId);
		//System.out.println(CityOfTheCustomer);
		//Assert.assertEquals(ActualResult, true);
		 //test from codenbox
		System.out.println(contactFirstName);
		//int contactId = Integer.parseInt(rs.getString("customerNumber"));

		System.out.println(contactFirstName);
		 WebElement name_box = driver.findElement(By.id("name"));
			name_box.sendKeys(contactFirstName);
			//make screen shot after confirm
//			Thread.sleep(2000);
//			TakeScreenShot();
//			Thread.sleep(2000);
			WebElement Confirm = driver.findElement(By.id("confirmbtn"));
			Confirm.click();	
	}
	
}*/
// for smat buy
@Test(priority=3, description="input data to Random website")
public void ReadTheUpdatedData() throws SQLException, InterruptedException, IOException {
	stmt =con.createStatement();
	rs = stmt.executeQuery(ReadQuery);
	//boolean ActualResult= rs.next();

	while(rs.next()) {
		String contactFirstName = rs.getString("contactFirstName");
		String contactLastName = rs.getString("contactLastName");
	username =contactFirstName; 
		//مش احست اشي للإيميل بس بما انه ما في داتا
		//random number 0-999
		String randemailId = Integer.toString(rand.nextInt(999)) ;
		
        //String CityOfTheCustomer = rs.getString("city");
    //int ContactId = Integer.parseInt(rs.getString("customerNumber"));
   
//driver.switchTo().alert().dismiss();
	
		System.out.println(contactFirstName);
		//read contactFirstName طول ما عدد الأحرف اكبر من  0  في داتا
		Assert.assertEquals(contactFirstName.length()>0, true);
		//read contactFirstName طول ما عدد الأحرف اكبر من  0  في داتا
		//Assert.assertEquals(CityOfTheCustomer.length()>0, true);

		//System.out.println(ContactId);
		//System.out.println(CityOfTheCustomer);
		//Assert.assertEquals(ActualResult, true);
		 //test from codenbox
		System.out.println(contactFirstName);
		//int contactId = Integer.parseInt(rs.getString("customerNumber"));

		System.out.println(contactFirstName);
		driver.findElement(By.id("customer[first_name]")).sendKeys(contactFirstName);
		driver.findElement(By.id("customer[last_name]")).sendKeys(contactLastName);
        driver.findElement(By.id("customer[email]")).sendKeys(contactFirstName+contactLastName+randemailId+"@gmail.com");
        driver.findElement(By.id("customer[password]")).sendKeys(Password);
        driver.findElement(By.cssSelector(".form__submit.button.button--primary.button--full")).click();
//      //wait 2 second after login
//    	Thread.sleep(2000);
//    	//text after login
//    	String WelcomeMessage = driver.findElement(By.cssSelector(".header__action-item-title.hidden-pocket hidden-lap")).getText();
//    	Assert.assertEquals(WelcomeMessage.contains(contactFirstName),true);
	}	
	//wait 2 second after login
	Thread.sleep(2000);
	//text after login
	String WelcomeMessage = driver.findElement(By.cssSelector(".header__action-item-title.hidden-pocket.hidden-lap")).getText();
	Assert.assertEquals(WelcomeMessage.contains(username),true);
	System.out.println(username);
	System.out.println(WelcomeMessage);
	TakeScreenShot();
	
}
@Test(priority=4, description="Remove Customer", enabled=true)
public void DeleteCustomer() throws SQLException {
	stmt =con.createStatement();
	int effectedRow = stmt.executeUpdate(DeleteQuery);
	System.out.println(effectedRow);
	Assert.assertEquals(effectedRow, 1);
}
}
