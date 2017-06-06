/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package autotest;

/**
 *
 * @author silo
 */


   
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
import static autotest.Autotest.facebook;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static org.apache.http.client.methods.RequestBuilder.options;
import static org.apache.http.client.methods.RequestBuilder.options;
import static org.apache.http.client.methods.RequestBuilder.options;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Autotest extends JFrame {
    
        JButton btnLogin = new JButton("Login");
        JPanel panel = new JPanel();
        JLabel username = new JLabel("Username:");
        JLabel password = new JLabel("Password:");
        JTextField txtUsername = new JTextField(15);
        JPasswordField pass = new JPasswordField(15);
        Autotest(){
                super("Login to facebook");
                setSize(400,300);
                setLocation(500,280);
                setResizable(false);
                
                panel.setLayout(null);
                panel.setBackground(Color.LIGHT_GRAY);
                username.setBounds(80, 65, 100, 20);
                password.setBounds(80, 110, 100, 20);
                txtUsername.setBounds(155, 65, 150, 20);
                pass.setBounds(155, 110, 150, 20);
                btnLogin.setBounds(160, 180, 80, 20);
                panel.add(btnLogin);
                panel.add(username);
                panel.add(password);
                panel.add(txtUsername);
                panel.add(pass);
                getContentPane().add(panel);
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        public static void facebook(String value1,String value2){
                //ChromeOptions options = new ChromeOptions();
                
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\silas\\Documents\\NetBeansProjects\\Autotest\\chromedriver.exe");
                ChromeOptions options = new ChromeOptions();
                options.addExtensions(new File("C:\\ultra_surf.crx"));
                options.addArguments("--disable-notifications");
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                //ChromeDriver driver = new ChromeDriver(capabilities);
                WebDriver Wdname = new ChromeDriver(options);
                Wdname.get("https://www.facebook.com");
                try{
                Thread.sleep(2000);
                }catch(Exception e){
                }
                WebElement phone = Wdname.findElement(By.id("email"));
                WebElement password = Wdname.findElement(By.id("pass"));
                WebElement button = Wdname.findElement(By.id("loginbutton"));
                phone.sendKeys(value1);
                password.sendKeys(value2);
                button.click();
                try{
                Thread.sleep(2000);
                }catch(Exception e){
                }
                WebElement profile =Wdname.findElement(By.xpath("//a[@title='Profile']"));
                profile.click();
                try
                {
                Thread.sleep(2000);
                }
                catch(Exception e) {}
                WebElement friends =Wdname.findElement(By.xpath("//a[@data-tab-key='friends']"));
                friends.click();
                try
                {
                Thread.sleep(1000);
                }
                catch(Exception e){}
                List<WebElement> friends1 = Wdname.findElements(By.xpath("//div[@class='fsl fwb fcb']/a"));
                //This is path for the friends in the chat box, but its saying not able to find element
                System.out.println("Total friends is = " +friends1.size());
                int newFriends = friends1.size();
                Actions act = new Actions(Wdname);
                int oldFriends = 0;
                while(newFriends != 80){
                WebElement lastFriend = friends1.get(friends1.size()-1);
                act.moveToElement(lastFriend).build().perform();
                try{
                Thread.sleep(1000);
                }catch(Exception e){
                }
                friends1 = Wdname.findElements(By.xpath("//div[@class='fsl fwb fcb']/a"));
                newFriends = friends1.size();
                System.out.println("Total friends is = " +friends1.size());
                }
                //This is path for the friends in the chat box, but its saying not able to find element
                System.out.println("Total friends is = " +friends1.size());
                PrintWriter pw = null;
                StringBuilder builder = new StringBuilder();
                String ColumnNamesList = "Facebook friends name";
                builder.append(ColumnNamesList +"\n");
                builder.append("1"+",");
                for(int i=0;i<friends1.size();i++){
                try
                {
                pw = new PrintWriter(new File("Friends.csv"));
                }
                catch (FileNotFoundException e)
                {
                e.printStackTrace();
                }
                builder.append(friends1.get(i).getText());
                builder.append('\n');
                pw.write(builder.toString());
                }
                pw.close();
                //Wdname.close();
        }
        public static void main(String[] args) {

            try
            {
                Autotest sel = new Autotest();
                sel.setVisible(true);
                sel.buttonAction();
                }
                catch(Exception e){}
            }
        public void buttonAction(){
            btnLogin.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
            String value1=txtUsername.getText();
            String value2=pass.getText();;
            facebook(value1,value2);
            //setVisible(false);
            }
            });
        }
}
