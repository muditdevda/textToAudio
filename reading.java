import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class reading {
	private static String str ;
	private static String teststring ;
	private static String file_data ;
	static StringBuffer sb = new StringBuffer();
	public static void main (String args[]) throws IOException, InterruptedException {
	    FileReader fr = new FileReader ("C:\\Users\\mudit.devda\\Desktop\\file_reader.txt"); //File Path
	    BufferedReader br = new BufferedReader(fr);
	    while ((str = br.readLine())!=null)
	    {
	    	file_data = str;
	    	System.out.println(file_data);
	    	sb.append(file_data);
	    }
	    br.close();
	    System.out.println(sb.length());
	    teststring = sb.toString();
	    generateAudio(teststring);
	}
	
	public static void generateAudio(String stringforaudio) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\mudit.devda\\Downloads\\chromedriver_win32 (3)\\chromedriver.exe");//ChromeDriver Path
		WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://micmonster.com/text-to-speech/hindi-india/");
        int loopBreaker = stringforaudio.length()/300;
        int startingIndex = 0; 
        int endIndex = 300; 
        int tempendIndex;
        for (int i=0;i<=loopBreaker;i++){
        	if (stringforaudio.length()>=endIndex) {
        		navigateAndGenerateAudio(driver,stringforaudio,startingIndex,endIndex);
        		driver.navigate().refresh();
            	startingIndex = startingIndex +300;
            	endIndex = endIndex + 300;
        	}
        	else if (endIndex > stringforaudio.length()) {
        		tempendIndex = stringforaudio.length() - startingIndex;
        		endIndex = startingIndex + tempendIndex;
        		navigateAndGenerateAudio(driver,stringforaudio,startingIndex,endIndex);
        		driver.quit();
			}
        }
	}
	
	public static void navigateAndGenerateAudio(WebDriver driver,String textforaudio,int startIndex, int endIndex) throws InterruptedException {
		driver.findElement(By.xpath("//textarea[@class='text-area mt-2']")).sendKeys(textforaudio.substring(startIndex, endIndex));
		driver.findElement(By.xpath("//button[@class='btn primary-btn bg-white mr-2']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@class='btn btn-xl primary-btn text-center mx-auto mt-5']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@class='mt-3 text-muted']")).click();
	}
}
