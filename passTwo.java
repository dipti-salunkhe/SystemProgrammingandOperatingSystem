import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;



class literalTable
{
	String litName,litAddress;
	literalTable(String litName,String litAddress)
	{
		this.litName=litName;
		this.litAddress=litAddress;
		
	}
	
}

public class passTwo {

	
	public static void main(String[] args) throws IOException 
	{

                                            Map<String,String> symTab=new LinkedHashMap<String, String>();
		ArrayList<literalTable> liTab=new ArrayList<literalTable>();
		
		literalTable l0=new literalTable("='10'","515");
		literalTable l1=new literalTable("='20'","519");
		literalTable l2=new literalTable("='10'","520");
		liTab.add(l0);
		liTab.add(l1);
		liTab.add(l2);
		
		symTab.put("sym1","200");
		symTab.put("sym2","513");
		symTab.put("sym3","509");
		symTab.put("sym4","508");
		symTab.put("FIRST","504");
		symTab.put("sym5","510");
		symTab.put("sym6","511");
		symTab.put("A","513");
		symTab.put("SECOND","514");
		
		String inputLine;
		String[] splittedLine=new String[5];
		String[] splittedWord=new String[2];
		
		int elementIndex,count;
		
		BufferedReader br=new BufferedReader(new FileReader(new File("poneOutput.txt")));
		PrintWriter fw=new PrintWriter("ptwoOutput11111.txt","UTF-8");
		
		
		while ((inputLine=br.readLine())!=null)
		{
		
			   splittedLine=inputLine.split(" ");
			   for (int i = 0; i < splittedLine.length; i++) 
			   {
				
				   if(splittedLine[i].startsWith("S"))
				   {
					   splittedWord=splittedLine[i].split("");
					   elementIndex=Integer.parseInt(splittedWord[1]);
					   
					   Set<Map.Entry<String, String>> mapset=symTab.entrySet();
					   Map.Entry<String, String> elementAddress=(Map.Entry<String, String>)mapset.toArray()[elementIndex];
					   fw.print(elementAddress.getValue()+" ");
				   }
				   else if(splittedLine[i].startsWith("L"))
				   {
					   splittedWord=splittedLine[i].split("");
					   elementIndex=Integer.parseInt(splittedWord[1]);
					   
					   Iterator it=liTab.iterator();
					   
					   count=0;
					   while(it.hasNext())
					   {
						   literalTable st=(literalTable)it.next();
						   
						   if(count==elementIndex)
						  {
							   fw.print(st.litAddress+" ");
						   }
						   count++;
						   
					   }
				   }
				   else
				   {
					   fw.print(splittedLine[i]+" ");
				   }
				   
			   }
			   fw.print("\n");
			
		}
		fw.close();
		
	}

}

