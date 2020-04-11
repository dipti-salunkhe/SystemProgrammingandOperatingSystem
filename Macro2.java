import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Macro2 {
	public String mdt[][]=new String[50][3];
	public String mnt[][]=new String[50][6];
	public String pntab[][]=new String[50][2];
	public String kpdtab[][]=new String[50][2];
	public static boolean ismacrostart=false;
	public static boolean isprototype=true;
	public static int mntp=0,pp=0,kp=0,pt=0,kt=0,mdtp=0,macroloc=0;

		public void putData() {
			System.out.println("============mdt tab===============");
			for (int i = 0; i < mdtp-1; i++) {
				for (int j = 0; j < 3; j++) {
					System.out.print(mdt[i][j]+ " ");
				}
				System.out.println();
			}
			System.out.println("============mnt tab===============");
			for (int i = 0; i < mntp; i++) {
				for (int j = 0; j < 6; j++) {
					System.out.print(mnt[i][j]+ " ");
				}
				System.out.println();
			}
			System.out.println("============pt tab===============");
			for (int i = 0; i < pt; i++) {
				for (int j = 0; j < 2; j++) {
					System.out.print(pntab[i][j]+ " ");
				}
				System.out.println();
			}
		}
		public int checkPntab(String s) {
			for (int i = 0; i < pt; i++) {
				if(s.equals(pntab[i][0])) {
					return i;
				}
			}
			return -1;
		}
		public boolean checkmntab(String s) {
			for (int i = 0; i < pt; i++) {
				if(s.equals(mnt[i][0])) {
					macroloc=i;
					return true;
				}
			}
			return false;
		}
public static void main(String[] args) throws IOException {

Macro2 m=new Macro2();
String[] words=new String[10];
FileReader fr = new FileReader("//home//student//workspace//TECOA105//src//input.txt");
BufferedReader br = new BufferedReader(fr);
String line,word;
int tmnti=0,tmdti=0,tpni=0,tkpi=0,temp;
while((line=br.readLine())!= null)
{
   
        words = line.split(" ");
        if (ismacrostart) {
			if (isprototype) {
				tmnti=mntp++;//storing position of mnt pointer
				m.mnt[tmnti][0]=words[0];
				m.mnt[tmnti][3]=Integer.toString(pt);//pttab pointer
				m.mnt[tmnti][5]=Integer.toString(mdtp);//pttab pointer
				for (int i = 1; i < words.length; i++) {
					if (words[i].contains("&")) {
						pp++;//count of &parameters
						pt++;
						m.pntab[tpni++][0]=words[i];//incr index of pttab
					}
				}
				m.mnt[tmnti][1]=Integer.toString(pp);//pttab pointer
				pp=0;
				isprototype=false;
			}else{
				tmdti=mdtp++;
				for (int i = 0; i < words.length; i++) {
					if(!words[i].equals("MEND")) {
						temp=m.checkPntab(words[i]);
						if(!(temp==-1)) {
							m.mdt[tmdti][i]=new String("P"+temp);
						}else {
							m.mdt[tmdti][i]=words[i];
						}
						
					}else {
						ismacrostart=false;
					}
						
				}
			}
			
			
        	
		} else {
			for(int i=0;i<words.length;i++)
	        {
	        	word=words[i];
	        	if(word.equals("MACRO")){
	        		ismacrostart=true;
	        		isprototype=true;
	        	}else if (m.checkmntab(word)) {
					
	        		for (int j = 0; j < Integer.parseInt(m.mnt[macroloc][1]); j++) {
						m.pntab[(j)+(Integer.parseInt(m.mnt[macroloc][3]))][1]=words[j+1];
					}
	        		
				}
	        }
		}
        
        
        }




m.putData();
int tempvar=0;
System.out.println("=======Second Pass=====");
for(int i=0;i<Integer.parseInt(m.mnt[tempvar][1]);i++)
{
	String t=m.mdt[i][2];
	int tc =t.charAt(1);
	int numericValue = Character.getNumericValue(tc);
	m.mdt[i][2]=m.pntab[numericValue][1];
	System.out.println((numericValue));
	System.out.println(t.charAt(1));
	
}
System.out.println("============mdt tab===============");
for (int i = 0; i < mdtp-1; i++) {
	for (int j = 0; j < 3; j++) {
		System.out.print(m.mdt[i][j]+ " ");
}System.out.println();}
}}