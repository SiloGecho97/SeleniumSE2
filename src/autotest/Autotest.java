/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package autotest;

import java.io.File;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
/**
 *
 * @author silo
 */

public class Autotest {
    //System.setProperty("webdriver.chrome.driver", "path to chrome driver");
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Start autotest..............");
        //method1();
        //method3();
        facebook();
        //method2();
        
    
        
    
    }
    
    public static void method3()
    {
        System.out.println("Searching..........");
        
        WebDriver  driver = new ChromeDriver();
        
        driver.get("https://www.google.com");
        
        WebElement searchbox = driver.findElement(By.name("q"));
        
        searchbox.sendKeys("Addis Ababa Institute");
        
        searchbox.click();
         try
        {
            Thread.sleep(2000);
            
        }catch(Exception e){
        
        }
        
        searchbox.clear();
        driver.close();
    }
    public static void method2()
    {
        System.out.println("naviagating...........");
        System.setProperty("webdriver.chrome.driver","c:\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        
        driver.navigate().to("https:www.yahoo.com");
        
        try
        {
            Thread.sleep(2000);
            
        }catch(Exception e){
        
        }
        driver.navigate().to("https:www.google.com");
        
        driver.navigate().refresh();
        driver.navigate().back();
        driver.navigate().forward();
        
        driver.close();
        
        
    }
    
    public static void method1()
    {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\silas\\Documents\\NetBeansProjects\\Autotest\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        
       
        driver.get("https://www.google.com");
        
        System.out.println("Page Title: "+driver.getTitle());
        System.out.println("Current Url: "+driver.getCurrentUrl());
        System.out.println("Length of page: "+driver.getPageSource().toString().length());
        System.out.println("");
        
        try{
            Thread.sleep(2000);
        }
        catch(Exception e){
        }
        driver.close();
    }
    
    public static void facebook()
    {
        ChromeOptions options = new ChromeOptions();
        options.addExtensions(new File("C:\\ultra_surf.crx"));
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        ChromeDriver driver = new ChromeDriver(capabilities);
        
        
        driver.get("https://www.facebook.com");
        WebElement username = driver.findElement(By.id("email"));
        WebElement password = driver.findElement(By.id("pass"));
        WebElement login = driver.findElement(By.id("loginbutton"));
        //ArrayList<List> a= new ArrayList<List>();
        
        //WebElement loglin = driver.findElement(By.id("")).findElement(By.className(""));
        username.sendKeys("+251931644114");
        password.sendKeys("silogecho");
        login.click();
        
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Autotest.class.getName()).log(Level.SEVERE, null, ex);
        }
        driver.findElement(By.xpath("//*[@title='Profile']")).click();

        //find your frineds count
       // String frinedsCount = driver.findElement(By.xpath("//*[@data-tab-key='friends']")).getText().substring(7);
        int count = 1000;

        //click on frineds tab
        driver.findElement(By.xpath("//*[@data-tab-key='friends']")).click();

        //find your couurent loaded frineds count and get it in a list
        List<WebElement> frineds = driver.findElements(By.xpath("//*[@class='fsl fwb fcb']"));
        int found = frineds.size();


        while (found <= count){

            //scroll to the last friend found from the current loaded friend list
            //Coordinates coordinate = ( frineds.get(found-1)).getCoordinates();
            //coordinate..onPage();
            //coordinate.inViewPort();
            frineds = driver.findElements(By.xpath("//*[@class='fsl fwb fcb']"));
            found = frineds.size();

            // break and print frined list if the condition found frineds = count of frined list
            if (found == count){
                System.out.println(found);
                System.out.println("---Printing FriendList---");
                for (int i=0; i<found; i++){
                System.out.println(frineds.get(i).getText());
                }
                break;
            }

        }
    
      
         driver.close();
        
    }
    
    
    
}
