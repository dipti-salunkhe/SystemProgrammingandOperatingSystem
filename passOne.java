import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;


class literalTable
{
	String litName;
	String litAddress;
	
	literalTable(String litName,String litAddress) 
	{
	 
		this.litName=litName;
		this.litAddress=litAddress;
		
	}
}

public class passOne 
{

	
	public static void main(String[] args) throws IOException 
	{
	
		Integer lc=500,poscount=0,litcount=0;
		String newLitAddress;
		
		Map<String,String> dirTab=new HashMap<String,String>();
		Map<String,Integer> regTab=new HashMap<String, Integer>();
		Map<String,Integer> ccTab=new HashMap<String, Integer>();
		Map<String,Integer> mnemonicTab=new HashMap<String, Integer>();
		Map<String,String> symTab=new LinkedHashMap<String,String>();
		
		ArrayList<literalTable> litTab=new ArrayList<literalTable>();
		
		//directive Table
		dirTab.put("START","R1");
		dirTab.put("END","R2");
		dirTab.put("LTORG","R3");
		dirTab.put("ORIGIN","R4");
		dirTab.put("EQU","R5");
		dirTab.put("DS","R6");
		dirTab.put("DC","R7");
		
		
		//mnemonic Table
		mnemonicTab.put("ADD",1);
		mnemonicTab.put("SUB",2);
		mnemonicTab.put("STOP",0);
		mnemonicTab.put("MULT",3);
		mnemonicTab.put("MOVER",4);
		mnemonicTab.put("MOVEM",5);
		mnemonicTab.put("COMP",6);
		mnemonicTab.put("BC",7);
		mnemonicTab.put("DIV",8);
		mnemonicTab.put("READ",9);
		mnemonicTab.put("PRINT",10);

		
		//regTable
		regTab.put("AREG",11);
		regTab.put("BREG",22);
		regTab.put("CREG",33);
		regTab.put("DREG",44);
		
		//ccTab
		ccTab.put("LT",01);
		ccTab.put("LE",02);
		ccTab.put("EQ",03);
		ccTab.put("GT",04);
		ccTab.put("GE",05);
		ccTab.put("ANY",06);
		
		BufferedReader br=new BufferedReader(new FileReader(new File("assemblyCode.txt")));
		PrintWriter fw=new PrintWriter("poneOutput.txt","UTF-8");
		
		String inputLine;
		
		String[] splittedLine=new String[4];
		
		while ((inputLine=br.readLine())!=null)
		{
			splittedLine=inputLine.split(" ");
			
			if(splittedLine.length>4)
			{
				System.out.println("Assembly statement should not have more than 4 words");
				continue;
			}
			
			for (int i = 0; i < splittedLine.length; i++) 
			{
				if(mnemonicTab.containsKey(splittedLine[i]))
				{
					fw.print(lc+" ");
					fw.print(mnemonicTab.get(splittedLine[i])+"  ");
					lc++;
				}
				else if(regTab.containsKey(splittedLine[i]))
				{
					fw.print(regTab.get(splittedLine[i])+" ");
				}
				else if(ccTab.containsKey(splittedLine[i]))
				{
					fw.print(ccTab.get(splittedLine[i])+" ");
				}
				else if(dirTab.containsKey(splittedLine[i]))
				{
					if(splittedLine[i].equals("DS") ||splittedLine[i].equals("DC"))
					{
						symTab.put(splittedLine[i-1],lc.toString());
						lc++;
						break;
					}
					else if(splittedLine[i].equals("EQU"))
					{
						if(symTab.containsKey(splittedLine[i+1]))
						{
							symTab.put(splittedLine[i-1],symTab.get(splittedLine[i+1]));
						}
						else
						{
							symTab.put(splittedLine[i-1],splittedLine[i+1]);
						}
						break;
					}
					else
					{
						if(splittedLine[i].equals("START"))
						{
							if(splittedLine.length>1)
							{
								lc=Integer.parseInt(splittedLine[1]);
							}
							else
							{
								lc=0;
								lc++;
							}
							break;
						}
						else if(splittedLine[i].equals("END") || splittedLine[i].equals("LTORG"))
						{
							poscount=0;
							Iterator it=litTab.iterator();
							while(it.hasNext())
							{
								literalTable st=(literalTable)it.next();
								if(st.litAddress==null)
								{
									newLitAddress=lc.toString();
									literalTable lt=new literalTable(st.litName,newLitAddress);
									litTab.set(poscount,lt);
									lc++;
								}
								poscount++;
							}
							
						}
						else if(splittedLine[i].equals("ORIGIN"))
						{
							lc=Integer.parseInt(symTab.get(splittedLine[i+1]));

					                                            i++;
						}
					}
				}
				else if(splittedLine.length==4 && i==0)
				{
					symTab.put(splittedLine[i],lc.toString());
					continue;
				}
				else if(ccTab.containsKey(splittedLine[i]))
				{
					fw.print(ccTab.get(splittedLine[i])+" ");
					lc++;
				}
				else
				{
					if(splittedLine[i].startsWith("="))
					{
						literalTable lt=new literalTable(splittedLine[i],null);
						litTab.add(lt);
						fw.print("L"+litcount);
						litcount++;
					}
					else
					{
						if(!symTab.containsKey(splittedLine[i]))
						{
						                      if(i!=0)
						                      {
							symTab.put(splittedLine[i],null);
							int pos=new ArrayList<String>(symTab.keySet()).indexOf(splittedLine[i]);
							fw.print("S"+pos);
							}
						}
						else if(symTab.containsKey(splittedLine[i]) && i==0)
						{
							continue;
						}
						else
						{
							if(symTab.get(splittedLine[i])!=null)
							{
								fw.print(symTab.get(splittedLine[i]));
							}
							else
							{
								int pos=new ArrayList<String>(symTab.keySet()).indexOf(splittedLine[i]);
								fw.print("S"+pos);
							}
						}
					}
				}
				
			}
			fw.print("\n");
			
		}
		
		System.out.println("\n\nMnemonic Table =>");
		System.out.println(mnemonicTab);
		
		System.out.println("\n\nAsembly Directive Table =>");
		System.out.println(dirTab);
		
		System.out.println("\n\nRegister Table =>");
		System.out.println(regTab);
		
		System.out.println("\n\nCOndition Code Table =>");
		System.out.println(ccTab);
		
		System.out.println("\n\nSymbol Table =>");
		System.out.println(symTab);
		
		System.out.println("\n\nLiteral Table");
		
		Iterator itr=litTab.iterator();
		while (itr.hasNext()) 
		{
			literalTable st = (literalTable) itr.next();
			
			System.out.println(st.litName+"  "+st.litAddress);
		}
		
		
		


		fw.close();
	}

	
}
