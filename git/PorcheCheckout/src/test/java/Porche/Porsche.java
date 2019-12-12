package Porche;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

        public class Porsche {
            @Test
            public void go(){
                WebDriverManager.chromedriver().setup();
                WebDriver driver =new ChromeDriver();
                driver.get("https://www.porsche.com/usa/modelstart/");
                driver.findElement(By.xpath("//img[@alt='Porsche - 718']")).click();
                String originalPrice=driver.findElement(By.xpath("//div[.='From $ 56,900.00*']")).getText();
                driver.findElement(By.xpath("//div[.='From $ 56,900.00*']")).click();
                for(String s: driver.getWindowHandles()){
                    driver.switchTo().window(s);
                }
                originalPrice=originalPrice.replace("From","").trim().replace("*","")
                        .replace(" ","");
                originalPrice=originalPrice.substring(0,originalPrice.length()-3);
                //.substring(0,originalPrice.length()-3);
                //
                //originalPrice text = From $ 56,900.00
                //basePrice = $56,900
                WebElement downArrow=driver.findElement(By.xpath("(//span[@class='expandButton'])[3]"));
                downArrow.click();
                String basePrice = driver.findElement(By.xpath("(//div[@class='cca-price'])[2]")).getText();
                System.out.println(basePrice);
                Assert.assertEquals(originalPrice,basePrice);

                String equipmentPrice=driver.findElement(By.xpath("//div[@class='row']/div[2]")).getText();
                Assert.assertEquals(equipmentPrice,"$0");

                String fee=driver.findElement(By.xpath("(//div[@class='row additional'][2]/div[2]")).getText();
               double fee1=Double.parseDouble(fee.replace("$","").replace(",",""));
               double base1=Double.parseDouble(fee.replace("$","").replace(",",""));

               String totalPrice=driver.findElement(By.xpath("//div[@class='row highited priceTotal separator']/div[.='58.150']")).getText();
               double total=Double.parseDouble(totalPrice.replace("$","").replace(",",""));

               Assert.assertTrue(total==fee1+base1);
               //Thread.sleep(4000);

            }
        }


