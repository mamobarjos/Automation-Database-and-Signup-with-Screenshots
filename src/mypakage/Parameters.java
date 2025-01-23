package mypakage;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Parameters {
	    WebDriver driver = new ChromeDriver();
		Connection con;
		Statement stmt;
		ResultSet rs;  
		//for random data
		Random rand = new Random();
		//must be integer
		//int randomIndex = rand.nextInt(500,900);
		//or convert to string
		String randomIndex = Integer.toString(rand.nextInt(500,900));
		String QueryToAdd= "Insert into customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, addressLine2, city, state, postalCode, country, salesRepEmployeeNumber, creditLimit)\r\n"+"VALUES ("+randomIndex+", 'ABC Corporation', 'Smith', 'John', '+1 555 123 4567', '123 Main St', 'Suite 100', 'New York', 'NY', '10001', 'USA', 1370, 50000.00)";
		String QueryToUpdate = "update customers set contactFirstName = 'mhmood' where customerNumber =" + randomIndex;	
		String ReadQuery = "select * from customers where customerNumber =" + randomIndex;
		String DeleteQuery= "delete from customers where customerNumber =" + randomIndex;
		String website = "https://codenboxautomationlab.com/practice/";	
		String website0 = "https://smartbuy-me.com/account/register";	
        String Password = "M@hmoud123456";
   	 public void TakeScreenShot() throws IOException {
		 Date mynewDate = new Date();
		 System.out.println(mynewDate.toString().replace(":", "-"));
		 String fileName = mynewDate.toString().replace(":", "-");
		 TakesScreenshot ts = (TakesScreenshot) driver; 
		 File myScreenShot = ts.getScreenshotAs(OutputType.FILE);
		 FileUtils.copyFile(myScreenShot,new File("./Screenshotfolder/",fileName+".jpg")); 
   	 }
				
}


