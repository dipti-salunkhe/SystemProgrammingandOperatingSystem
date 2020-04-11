import java.util.Scanner;

public class Pageopt {
 String ref="";
 int size=0;
 int pf=0;
 char frame[];
 int fc=0;
 Scanner input=new Scanner(System.in);
 public void input(){
  System.out.println("Enter Ref. String");
  ref=input.next();
  System.out.println("Enter Frame size");
  size=input.nextInt();
  frame=new char[size];
 }
 
 public boolean search(char ch){
  for(int i=0;i<fc;i++)
{
   char temp=frame[i];
   if(temp==ch){
    return true;
   }
  }
  return false;
 }
 public int future(char ch, int pos){
  int result=ref.length()+1;
  for(int i=pos+1;i<ref.length();i++){
   char temp=ref.charAt(i);
   if(temp==ch){
    if(i<result){
     result=i;
    }
   }
  }
  return result;
  
 }
 
 public void optimal(){
  System.out.println("Lenght="+ref.length());
  for(int i=0;i <ref.length();i++)
{
   char ch=ref.charAt(i);
   if(search(ch)){
    
   }else{
    // page fault
    System.out.print("Page fault At index "+i);
    pf++;
    
    if(fc<size){
     frame[fc]=ch;
     fc++;
    }else{
     
     int optimal[]=new int[size];
     
     for(int j=0;j<size;j++){
      optimal[j]=future(frame[j],i);
     }
     // to find the page to be replaced
     int index=0;
     int max=optimal[0];
     for(int j=1;j<size;j++){
      if(optimal[j]>max){
       max=optimal[j];
       index=j;
      }
     }
     // replace page
     System.out.print(" and page "+ frame[index]+" is repaced by "+ch);
     frame[index]=ch;
     
    }
    System.out.println();
    
   }
   
   
  }
  System.out.println("No. of Page fault="+pf);
 }
 public static void main(String args[]){
  Pageopt obj=new Pageopt();
  obj.input();
  obj.optimal();
 }

}
//70120304230321201701
