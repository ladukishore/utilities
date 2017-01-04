


package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.*;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class UrlRead {

    public static void main(String[] args) throws IOException {
        //	WebDriver driver=new FirefoxDriver();
        

        WebDriver driver;
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\cp268372\\Downloads\\geckodriver-v0.11.1-win64\\geckodriver.exe");
        driver = new FirefoxDriver();
         WebElement element;


        driver.manage().window().maximize();
        String csvFile = "C:/ecommerce_code/csv/urls.csv";

        String line = "";
        String cvsSplitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine();
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] urls = line.split(cvsSplitBy);

                driver.get(urls[1]);
            //    List<WebElement> links = driver.findElements(By.tagName("a"));
                element = driver.findElement(By.xpath("//h1[contains(.,'Page Not Found!')]"));
                //  links.addAll(driver.findElements(By.tagName("?SKU")));
                
                if (element.getText().equals("Page Not Found!"))

                System.out.println("PGID=" +urls[0] +"URL="+urls[0]+"  "+ element.getText());

            /*    for (int i = 0; i < links.size(); i++) {

                    WebElement ele = links.get(i);

                    String url = ele.getAttribute("href");

                    verifyLinkActive(url);

                }*/

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void verifyLinkActive(String linkUrl) {
        try {
       Logger logger = Logger.getLogger(VerifyLinks.class.getName());
               PropertyConfigurator.configure("C:\\Users\\cp268372\\Documents\\NetBeansProjects\\test\\src\\test\\log4j.properties");
                //   logger.setLevel(Level.WARN);

            URL url = new URL(linkUrl);

            HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();

            httpURLConnect.setConnectTimeout(3000);

            httpURLConnect.connect();

            if (httpURLConnect.getResponseCode() == 200) {
                             //    logger.info(linkUrl+"---"+httpURLConnect.getResponseMessage());

                System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage());
            } else {
                System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage() + " - " + HttpURLConnection.HTTP_NOT_FOUND);
		if(logger.isInfoEnabled()){
                 logger.info(linkUrl+"---"+httpURLConnect.getResponseMessage());
        }
            }
            /*     if(httpURLConnect.getResponseCode()==HttpURLConnection.HTTP_NOT_FOUND)  
           {
               System.out.println(linkUrl+" - "+httpURLConnect.getResponseMessage() + " - "+ HttpURLConnection.HTTP_NOT_FOUND);
            } */
        } catch (Exception e) {

        }
    }

}
