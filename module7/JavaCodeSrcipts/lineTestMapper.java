package com.ica8;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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


public class lineTestMapper {
	
	
	
	private static Map< String, List<String> > testMap=new HashMap<String ,List<String >>();
	
	private static List<String>testNames=new ArrayList<String>();
	
	public static void main (String args[])	{
		
		writetoFile();
		 
	   
		
		for(int i=1;i<=3;i++)
		{
			
		String name="case"+Integer.toString(i);
		
		CFG cfg=new CFG();
		
				
		Map<Method ,Map<InstructionHandle, List<InstructionHandle> > >cfg1=cfg.generateMethodCFG("/home/srikanth/gdrive/WorkSpace/"
				+ "bookstore/cfgclasses/"+name+"/Login_jsp.class");
		
		
		CFG cfgobj2=new CFG();
		
		Map<Method ,Map<InstructionHandle, List<InstructionHandle> > >cfg2=cfgobj2.generateMethodCFG("/home/srikanth/gdrive/WorkSpace/"
				+ "bookstore/cfgclasses/"+name+"/Login_modified_jsp.class");
		String className1="org.apache.jsp.Login_jsp";
		 compareMethodUtil(cfg1,cfg2,className1);
		
		 
		   
		}
		
		for(int i=7;i<=9;i++)
		{
		
		String name="case"+Integer.toString(i);
		CFG cfgobj3=new CFG();
		Map<Method ,Map<InstructionHandle, List<InstructionHandle> > >cfg3=cfgobj3.generateMethodCFG("/home/srikanth/gdrive/WorkSpace/"
				+ "bookstore/cfgclasses/"+name+"/MyInfo_jsp.class");
		
		
		CFG cfgobj4=new CFG();
		
		Map<Method ,Map<InstructionHandle, List<InstructionHandle> > >cfg4=cfgobj4.generateMethodCFG("/home/srikanth/gdrive/WorkSpace/"
				+ "bookstore/cfgclasses/"+name+"/MyInfo_modified_jsp.class");
		
		 String className2="org.apache.jsp.MyInfo_jsp";
		   compareMethodUtil(cfg3,cfg4,className2);
		   
		   
		
		}
		
		for(int i=4;i<=6;i++)
		{

		String name="case"+Integer.toString(i);
		CFG cfgobj5=new CFG();
		Map<Method ,Map<InstructionHandle, List<InstructionHandle> > >cfg5=cfgobj5.generateMethodCFG("/home/srikanth/gdrive/WorkSpace/"
				+ "bookstore/cfgclasses/"+name+"/ShoppingCart_jsp.class");
		
		
		CFG cfgobj6=new CFG();
		
		Map<Method ,Map<InstructionHandle, List<InstructionHandle> > >cfg6=cfgobj6.generateMethodCFG("/home/srikanth/gdrive/WorkSpace/"
				+ "bookstore/cfgclasses/"+name+"/ShoppingCart_modified_jsp.class");
		
		String className3="org.apache.jsp.ShoppingCart_jsp";
		  compareMethodUtil(cfg5,cfg6,className3);
		  
		  for(String test: testNames)
			{
				System.out.println(test);
			}  

		
		 
		}
	    
	    	    
	    
		
	    WriteScript();
		 
		
		
	}
	
	public static void compareMethodUtil (Map<Method ,Map<InstructionHandle, List<InstructionHandle> > >cfg1,Map<Method ,Map<InstructionHandle,
			List<InstructionHandle> > >cfg2,String className)
	{
		
		
		for (Method key:cfg1.keySet())
		{
			
		   Map<InstructionHandle,List<InstructionHandle>>adj1=cfg1.get(key);
		   
		 
		   
		   for(Method s:cfg1.keySet())
		   {
			   
			  // System.out.println(s + "   "+cfg1.get(s));
		   }
		   if(cfg2.containsKey(key))
		   {
			   Map<InstructionHandle,List<InstructionHandle >>adj2=cfg2.get(key);
			   
			   Dejavu(adj1,adj2,key,className);
			   
		   }
		  
		}
		
		
		
	}
	
	private static void Dejavu(Map<InstructionHandle, List<InstructionHandle>> adj1,
			Map<InstructionHandle, List<InstructionHandle>> adj2,Method m,String className) {
		
		
		InstructionHandle start1=getStartInstrucitonHandle(adj1);
		InstructionHandle start2=getStartInstrucitonHandle(adj2);
	//	List<String>testNames=new ArrayList<String>();
		List<Integer>visited=new ArrayList<Integer>();
		
		compare(start1,start2,adj1,adj2,visited,testNames,m,className);
		
		
		
		
	}

	private static InstructionHandle getStartInstrucitonHandle(
			Map<InstructionHandle, List<InstructionHandle>> adj1) {
		
		int min=Integer.MAX_VALUE;
		InstructionHandle firstih = null;
		for(InstructionHandle ihandle: adj1.keySet())
		{
			int val=ihandle.getPosition();
			if(val<min)
			{
				min=val;
				firstih=ihandle;
			}
			
		}
		
		
		return firstih;
	}

	private static void compare(InstructionHandle start1, InstructionHandle start2,
			Map<InstructionHandle, List<InstructionHandle>> adj1, Map<InstructionHandle, List<InstructionHandle>> adj2,
			List<Integer >visited,List<String> testNames,Method m,String className) {
	
		if(null!=start1)
		{
			
		
		 int  startpos=start1.getPosition();
		visited.add(startpos);
		List<InstructionHandle>original=adj1.get(start1);
		List<InstructionHandle>modified=adj2.get(start2);
		
		for(int i=0;null!=original && i<original.size();i++)
		{
			
			int val1=original.get(i).getPosition();
			
			if(modified.size()-1 >= i )
			{
				int val2=modified.get(i).getPosition();
				if(!visited.contains(val1))
				{
					
					if(!checkLexical(original.get(i),modified.get(i)))
					{
						//System.out.print("show hello ");
						
						int sourceline=m.getCode().getLineNumberTable().getSourceLine(val1);
						String key=className+"_"+Integer.toString(sourceline);
						List<String>tempList=testMap.get(key);
						for(int k=0;null!=tempList && k<tempList.size();k++)
						{
							if(!testNames.contains(tempList.get(k)))
							testNames.add(tempList.get(k));
							//System.out.println(sourceline + className );
						}
					}
					else 
						compare(original.get(i),modified.get(i),adj1,adj2,visited,testNames,m,className);
					
					
				}
				
			}
			
			
			
		}
		
		}
		
		}
	

	private static boolean checkLexical(InstructionHandle ih1, InstructionHandle ih2) {
		
		if(ih1.getInstruction().toString().equals(ih2.getInstruction().toString()))
			return true;
		
			
		return false;
	}

	private static void writetoFile()
	{
		
		for(int i=1;i<=9;i++)
		{
			
			String testName="ICA8"+Integer.toString(i)+"Test";
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
						String key=jspName+"_"+number;
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
