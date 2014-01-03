package wamparser.wamparser;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Hello world!
 * 
 */
public class App {
	
	private static List<String>urlList;
	public static void main(String[] args) {
      
		urlList=new ArrayList<String>();
		DocumentBuilder dBuilder;
		try {
			System.out.println("Hello World!");

			File file = new File(
					"/home/srikanth/gdrive/WorkSpace/empldir/analysis/interfaces"
							+ "/empldir-wamai-pda-interfaces.xml");

			dBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			try {
				Document doc = dBuilder.parse(file);
				if (doc.hasChildNodes()) {

					NodeList nodeList = doc.getChildNodes();
					for (int i = 0; i < nodeList.getLength(); i++) {
						Node tempNode = nodeList.item(i);
						for (int j = 0; j < tempNode.getChildNodes()
								.getLength(); j++) {
							Node tempNode2 = tempNode.getChildNodes().item(j);
							for (int k = 0; k < tempNode2.getChildNodes()
									.getLength(); k++) {

								Node tempNode3 = tempNode2.getChildNodes().item(k);
								List<String> nodeName = new ArrayList<String>();
								List<String> nodeType = new ArrayList<String>();
								if (tempNode3.getNodeType() == Node.ELEMENT_NODE ) {
									String baseJsp=tempNode3.getAttributes().item(1).getNodeValue();
									getUrl(tempNode3,baseJsp,"1.2","abc","1");
									getUrl(tempNode3,baseJsp,"-1.2","","1");
									getUrl(tempNode3,baseJsp,"1.2","abc","");
									
								}

							}

						}
					}

				}
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		WriteUrltoFile();

	}

	private static void WriteUrltoFile() {
		
		
		
			FileWriter fw;
			try {

				File file = new File("/home/srikanth/gdrive/WorkSpace/empldir/wget/url.sh");
				 
				
				fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("wget   --save-cookies "
						+ " cookies.txt  --keep-session-cookies  --post-data 'Login=admin&Password=admin&FormName=Login&FormAction=login&querystring=&ret_page='"
						+ "  http://localhost:8080/empldir/Login.jsp");
				
				bw.newLine();
				for(int i=0;i<urlList.size();i++)
					
				{
			//	if(!urlList.get(i).contains("Login"))	
				//{
				bw.write("wget --load-cookies  cookies.txt  --keep-session-cookies   "+urlList.get(i));
				bw.newLine();
			//	}
				}
				bw.flush();
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		
	}

	private static void getUrl(Node tempNode3,String baseJsp,String stringdefault,String intdefault,String doubledefault) {
		String[] formActionValues = null;
		String[] formNameValues=null;
		String[] doubleValues={"1.2","2.3"};//,"3.4","4.5"};
		String[] stringValues={"abc","cda"};//,"afc","rgd"};
		
		String url="http://localhost:8080/empldir/"+baseJsp+"?";
		urlList.add("http://localhost:8080/empldir/"+baseJsp);
		System.out.println(url);
		Map<String,String>pair=new HashMap<String,String>();
		for (int l=0;l<tempNode3.getChildNodes().getLength();l++)
		{			
			
			Node tempNode4=tempNode3.getChildNodes().item(l);
			 if(tempNode4.getNodeType()==Node.ELEMENT_NODE)
			 //   System.out.println(tempNode4.getNodeName())	;
			    
			 		if(tempNode4.hasAttributes()){	
			 			
			 			  String tempNodeAttributeName=tempNode4.getAttributes().getNamedItem("name").getNodeValue();
	 
			 				if("FormAction".equals(tempNodeAttributeName))
			 					{
			 					formActionValues=formatInterface(tempNode4);
			 					}
			 					else if ("FormName".equals(tempNodeAttributeName))
			 					{
			 						formNameValues=formatInterface(tempNode4);
			 						}
			 					else {
  
			 						if("DOUBLE".equals(tempNode4.getAttributes().getNamedItem("type").getNodeValue()))
			 						{
			 							url=url+tempNodeAttributeName+"="+doubledefault+"&";
			 							//pair.put("DOUBLE",tempNodeAttributeName);
			 							
			 						}
			 						else if("STRING".equals(tempNode4.getAttributes().getNamedItem("type").getNodeValue()))
			 						{
			 							url=url+tempNodeAttributeName+"="+stringdefault+"&";
			 							//pair.put("STRING",tempNodeAttributeName);
			 						}
			 						else if("INTEGER".equals(tempNode4.getAttributes().getNamedItem("type").getNodeValue()))
			 						{
			 							url=url+tempNodeAttributeName+"="+intdefault+"&";
			 							//pair.put("STRING",tempNodeAttributeName);
			 						}
			 							
			 						
			 					}
 
			 				}
			    }
		
		
		for(int i=0;null!=formActionValues && i<formActionValues.length;i++)
		{    String newUrl=url+"FormAction="+formActionValues[i];
			for(int j=0;null!=formNameValues && j<formNameValues.length;j++)
			{
				newUrl=newUrl+"&"+"FormName="+formNameValues[j];
				/*
				if(pair.containsKey("DOUBLE"))
				{
					for(int k=0;  k<doubleValues.length;k++)
					{
						newUrl=newUrl+"&"+pair.get("DOUBLE")+"="+doubleValues[k];
						if(pair.containsKey("STRING"))	
						{
							for(int l=0;pair.containsKey("STRING") && l<stringValues.length;l++)
							{
				
								newUrl=newUrl+"&"+pair.get("STRING")+"="+stringValues[l];
								urlList.add(newUrl);
							}
						}
						else 
						{
					urlList.add(newUrl);
						}
				}
				
				}
				else {
					
					if(pair.containsKey("STRING"))	
					{
						for(int l=0;pair.containsKey("STRING") && l<stringValues.length;l++)
						{
			
							newUrl=newUrl+"&"+pair.get("STRING")+"="+stringValues[l];
							urlList.add(newUrl);
						}
					}
					else 
					{
				urlList.add(newUrl);
					}
					
					
				}
				*/
				urlList.add(newUrl);
			}
		}
		
		for(int i=0;i<urlList.size();i++)
		{
			System.out.println(urlList.get(i));
		}
		System.out.println(urlList.size());
	}

	private static String [] formatInterface(Node tempNode4) {
		   String value=tempNode4.getAttributes().getNamedItem("values").getNodeValue().replace("[", "");
		   value=value.replace("]", ",");
		   String[] Values=value.split(",");
		   for(int i=0;i<Values.length;i++)
			   Values[i]=Values[i].trim();
		   for(String s:Values)
			   System.out.print(s +" words ");
		   return Values;
	}
}
