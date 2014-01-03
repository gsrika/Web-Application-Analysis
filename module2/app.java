package CrawlJax.defaultartifact;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.Document;

import org.apache.commons.io.FileUtils;
import org.cyberneko.html.parsers.DOMParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.crawljax.browser.EmbeddedBrowser.BrowserType;
import com.crawljax.core.CrawlSession;
import com.crawljax.core.CrawlerContext;
import com.crawljax.core.CrawljaxRunner;
import com.crawljax.core.configuration.BrowserConfiguration;
import com.crawljax.core.configuration.CrawljaxConfiguration;
import com.crawljax.core.configuration.CrawljaxConfiguration.CrawljaxConfigurationBuilder;
import com.crawljax.core.configuration.Form;
import com.crawljax.core.configuration.InputSpecification;
import com.crawljax.core.plugin.OnNewStatePlugin;
import com.crawljax.core.state.StateVertex;
import com.crawljax.plugins.crawloverview.CrawlOverview;


public class app {

	private static final long WAIT_TIME_AFTER_EVENT = 200;
	private static final long WAIT_TIME_AFTER_RELOAD = 20;
	private static final String URL = "http://localhost:8080/bookstore/Default.jsp";
	
	private static List<String>visitedUrl;
	
	
	public static void main(String[] args) {
		
		
		File outFolder=new File("out");
		FileUtils.deleteQuietly(outFolder);
		/*System.setProperty("webdriver.chrome.driver", "/home/srikanth/Downloads/chromedriver");
		WebDriver driver = new ChromeDriver();*/
		CrawljaxConfigurationBuilder builder = CrawljaxConfiguration.builderFor(URL);
		builder.crawlRules().insertRandomDataInInputForms(false);
		
		
		CrawlOverview ov=new CrawlOverview();
				
		builder.crawlRules().setInputSpec(getInputSpecification());
		// click these elements
				
		builder.crawlRules().clickDefaultElements();
		builder.crawlRules().click("div");
             		
		builder.crawlRules().click("a");
	//	builder.setMaximumStates(2);
		builder.setMaximumDepth(20);
		
		//builder.setUnlimitedCrawlDepth();
		builder.setUnlimitedStates();
		builder.crawlRules().clickElementsInRandomOrder(false);
		
		builder.setMaximumRunTime(15, TimeUnit.MINUTES);
		
		

		// Set timeouts
		builder.crawlRules().waitAfterReloadUrl(WAIT_TIME_AFTER_RELOAD, TimeUnit.MILLISECONDS);
		builder.crawlRules().waitAfterEvent(WAIT_TIME_AFTER_EVENT, TimeUnit.MILLISECONDS);

		builder.addPlugin(ov);
		// We want to use two browsers simultaneously.
		builder.setBrowserConfig(new BrowserConfiguration(BrowserType.FIREFOX, 1));
		
		//set input specification s
		
		builder.addPlugin(new OnNewStatePlugin() {

						
			public void onNewState(CrawlerContext context, StateVertex newState) {
				WriteStatstoFile(context);
			}

			
		});


		CrawljaxRunner crawljax = new CrawljaxRunner(builder.build());
		crawljax.call();
		

	}
	
	private static void WriteStatstoFile(CrawlerContext context) {
		// This will print the DOM when a new state is detected. You should see it in your
		// console.
		
		String vURL=context.getBrowser().getCurrentUrl();
		if(null==visitedUrl || !visitedUrl.contains(vURL))
		{
			if(null==visitedUrl)
				visitedUrl=new ArrayList<String>();
		   visitedUrl.add(vURL);
		try {
			File file = new File("/home/srikanth/gdrive/WorkSpace/CrawlJax/filename.txt");
			 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
			BufferedWriter bw = new BufferedWriter(fw);
			DOMParser parser = new DOMParser();
			
			bw.write("Now Listing downs links for "+vURL);
			bw.newLine();
		    String dm=context.getBrowser().getUnStrippedDom().toString();
		    String regexe = "<a\\s+[^>]*>";
		    Pattern pattern = Pattern.compile(regexe);
		      //Pattern pattern = Pattern.compile(regexe, Pattern.CASE_INSENSITIVE);  // case-    insensitive matching

		      // Step 2: Allocate a Matcher object from the compiled regexe pattern,
		      //         and provide the input to the Matcher
		      Matcher matcher = pattern.matcher(dm);

		      // Step 3: Perform the matching and process the matching result
		      int count = 0;
		      // Use method find()
		      while (matcher.find()) {
		    	  // find the next match
		    	  
		         System.out.println("find() found the pattern \"" + matcher.group()
		               + "\" starting at index " + matcher.start()
		               + " and ending at index " + matcher.end());
		          count++;
		          bw.write(matcher.group());
		          bw.newLine();

		      }
		      
		           
		      
		      
		      regexe="<input\\s+[^>]*>";
		      
		      pattern = Pattern.compile(regexe);
		      matcher = pattern.matcher(dm);

		      int count1 = 0;
		      // Use method find()
		      while (matcher.find()) {
		    	  // find the next match
		    	  
		         System.out.println("find() found the pattern \"" + matcher.group()
		               + "\" starting at index " + matcher.start()
		               + " and ending at index " + matcher.end());
		          count1++;
		          bw.write(matcher.group());
		          bw.newLine();

		      }
		      
			   regexe="<form\\s+[^>]*>";
		      
		      pattern = Pattern.compile(regexe);
		      matcher = pattern.matcher(dm);

		      int count2 = 0;
		      // Use method find()
		      while (matcher.find()) {
		    	  // find the next match
		    	  
		         System.out.println("find() found the pattern \"" + matcher.group()
		               + "\" starting at index " + matcher.start()
		               + " and ending at index " + matcher.end());
		          count2++;
		          bw.write(matcher.group());
		          bw.newLine();

		      }
		      
		      
		      bw.write("Number of counts of  <a>   tags"+count);
		      bw.newLine();
		      bw.write("Number of counts of  <input> tags"+count1);
		      bw.newLine();
		      bw.write("Number of counts of  <form> tags"+count2);
		      bw.newLine();
		      
		      
		      
			bw.flush();
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
		
	}
	
	
	private static InputSpecification getInputSpecification() {
		InputSpecification input = new InputSpecification();
		Form contactForm = new Form();
		
		contactForm.field("Login").setValues("admin");
		contactForm.field("Password").setValues("admin");
		
		
		input.setValuesInForm(contactForm).beforeClickElement("button").withText("Login");
		return input;
	}
		
		

	

}
