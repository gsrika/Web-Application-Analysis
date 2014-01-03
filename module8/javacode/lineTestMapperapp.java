package com.ica8;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;






















import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.bcel.classfile.Method;
import org.apache.bcel.generic.InstructionHandle;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;


public class lineTestMapperapp {
	
	
	
	private static Map< String, List<String> > testMap=new HashMap<String ,List<String >>();
	private static final String failed="FAILED";
	private static final String passed="PASSED";
	private static Map<String,String>testStatus=new HashMap<String,String>();
	
	private static List<String>testNames=new ArrayList<String>();
	
	public static void main (String args[])	{
		
		writetoFile();
		getTestStatus();
		
		
		
	for(String s:testStatus.keySet())
	{
//		System.out.println(s+ ";;;;;;;;;;;;;;"+ testStatus.get(s));
	}
	String [] jspNames={"Books_jsp","Login_jsp","OrdersRecord_jsp","MyInfo_jsp"};
	
	for(int i=0;i<jspNames.length;i++)
	{
	
	generateSuspicision(jspNames[i]);
	
	}	
	  
		
		
	}


	public static void generateSuspicision(String a) {
		Map<Integer,List<String >> linemap=new TreeMap<Integer,List<String>>();
		
		for(String s:testMap.keySet())
		{	
			
			if(s.contains(a))
			{
				int index=s.indexOf(":");
				
				int number=Integer.parseInt(s.substring(index+1));
			//	System.out.println(number);
			//	System.out.println(s);
				linemap.put(number, testMap.get(s));
			}
			
		}
		
		int totalFailed=0;
		int totalPassed=0;
		
		for(String s:testStatus.keySet())
		{
			
			if(failed.equals(testStatus.get(s)))
				 totalFailed++;
			else
				totalPassed++;
			
			
		}
		
		//System.out.println("answer is "+totalFailed + "value is "+ totalPassed);
		
		Map<Integer,Double>suspicionMap=new HashMap<Integer,Double>();
		double ans=(double)Integer.MIN_VALUE;
		int line=-1;
		for(int i:linemap.keySet())
		{
			
			//System.out.println(i +  "----------------------"+ map.get(i));
			List<String>test=linemap.get(i);
			int failedline=0;
			int passedline=0;
			for(String s:test)
			{
				String status=testStatus.get(s);
				if(failed.equals(status))
				{
					failedline++;
				}
				else
				{
					passedline++;
				}
			}
			
			double valfailed=(double )failedline /(double) totalFailed;	
			double valpassed=(double )passedline /(double) totalPassed;
			double val= valfailed/(valpassed+valfailed);
			suspicionMap.put(i, val);
			
			if(val>=ans)
			{
				line=i;
				ans=val;
				System.out.println("answer is "+line + "value is "+ ans);
			}
			
			
		}
		
		writeSuspicionOutput(suspicionMap,a);
			 
		   System.out.println("answer is "+line  + "   for "+ a);
	}
	
	private static Map sortByComparator(Map unsortMap) {
		 
		List list = new LinkedList(unsortMap.entrySet());
 
		// sort list based on comparator
		Collections.sort(list, new Comparator() {
			public int compare(Object o1, Object o2) {
				return ((Comparable) ((Map.Entry) (o2)).getValue())
                                       .compareTo(((Map.Entry) (o1)).getValue());
			}
		});
 
		// put sorted list into map again
                //LinkedHashMap make sure order in which keys were inserted
		Map sortedMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedMap.put(entry.getKey(), entry.getValue());
		}
		return sortedMap;
	}
	
	private static void writeSuspicionOutput(Map<Integer, Double> suspicionMap,String s) {
		
		
		File file=new File("/home/srikanth/gdrive/WorkSpace/ICA9/SuspicionReport.txt");
		try {
			FileWriter fw=new FileWriter(file,true);
			BufferedWriter bw=new BufferedWriter(fw);
			Map<Integer,Double>sortedMap=sortByComparator(suspicionMap);
			
			bw.write(".......................Suspicion Value Report for "+s+ "...................................................");
			bw.write("\n");
			bw.write("\n");
			bw.write("\n");
			
			
			for(Map.Entry<Integer,Double> m : sortedMap.entrySet())
			{
				System.out.println(m.getKey() + "   ...................."+m.getValue());
				
				bw.write("line number--> "+m.getKey() + "sucpicion value-->"+m.getValue());
				
				
				bw.write("\n");
				
				
			}
			
			bw.close();
			fw.close();
						
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}


	private static void getTestStatus() {
		
		File file=new File("/home/srikanth/gdrive/WorkSpace/ICA9/TestStatus.txt");
		try {
			FileReader fr=new FileReader(file);
			BufferedReader br=new BufferedReader(fr);
			String st;
			try {
				while ((st=br.readLine())!=null)
				{
					String line=st;
					String part[]=st.split(":");
					testStatus.put(part[0], part[1]);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}


	private static void writetoFile()
	{
		
		for(int i=1;i<=14;i++)
		{
			
			String testName="ICA9"+Integer.toString(i)+"Test";
			String reportdir="report"+Integer.toString(i);
			String filePath="/home/srikanth/gdrive/WorkSpace/bookstore/"+reportdir+"/coverage.xml";
			testMap(testName,filePath);
		}
		
		File file=new File("/home/srikanth/gdrive/WorkSpace/bookstore/report3/report.txt");
		try {
			FileWriter fw=new FileWriter(file);
			BufferedWriter bw=new BufferedWriter(fw);

			 for(String s: testMap.keySet())
			    {
			    	bw.write(s+"  "+testMap.get(s));
			    	bw.newLine();
			    }
			 
			 bw.close();
			 fw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	private static void testMap(String testName,String file)
	{
	
		 SAXBuilder builder = new SAXBuilder();
		  File xmlFile = new File(file);
	 
		  try {
	 
			Document document = (Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement();
			Element childNode=rootNode.getChild("packages");
			Element packNode=childNode.getChild("package");
			Element classList=packNode.getChild("classes");
			
			
			List<Element>list=classList.getChildren("class");
			
			for(int i=0;i<list.size();i++)
			{
				Element cl=list.get(i);
				
				String jspName=cl.getAttributeValue("name");
				
				
				Element lines=list.get(i).getChild("lines");
				
				List<Element>lineList=lines.getChildren("line");
				for(int j=0;j<lineList.size();j++)
				{
					String number=lineList.get(j).getAttributeValue("number");
					String hits=lineList.get(j).getAttributeValue("hits");
					if(!"0".equals(hits))
					{
						String key=jspName+":"+number;
						if(testMap.containsKey(key))
	    				{
	    					List a=testMap.get(key);
	    					a.add(testName);
	    				}
	    				else 
	    				{
	    					List a=new ArrayList<String>();
	    					a.add(testName);
	    					testMap.put(key, a);
	    					
	    					
	    				}
						
					}
					
				}
			}
			
	 
		  } catch (IOException io) {
			System.out.println(io.getMessage());
		  } catch (JDOMException jdomex) {
			System.out.println(jdomex.getMessage());
		  }
		
			
			
			
			
	}
	
private static void WriteScript() {
		
		
		
		FileWriter fw;
		try {

			File file = new File("/home/srikanth/gdrive/WorkSpace/ICA8/script1.sh");
			 
			
			fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			for(int i=0;i<testNames.size();i++)
			{
				String line="mvn test -Dtest="+testNames.get(i);
				bw.write(line);
				bw.newLine();
			}
			

			
			bw.flush();
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
}
		

	
	  
}
