package sheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

public class sjf {
	
	int wait[],ta[],burst[];
	Integer[] burst1;
	float avg_ta,avg_wt,np;
	ArrayList<Integer> t=new ArrayList<>();
	
	public sjf(){
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the no of processes: ");
		np=sc.nextInt();
		burst=new int[(int) np];
		wait=new int[(int) np];
		ta=new int[(int) np];
		burst();
	}
	public void burst(){
		Scanner sc1=new Scanner(System.in);
		System.out.println("Enter their burst times respectively");
		int j;
		for(int i=0;i<np;i++){
			j=sc1.nextInt();
			t.add(j);
		}
		Collections.sort(t);
		
		burst1=t.toArray(new Integer[t.size()]);
		for(int i=0;i<burst1.length;i++){
			System.out.println(burst1[i]);
		}
		for(int i=0;i<burst.length;i++){
			burst[i]=burst1[i].intValue();
		}
		wait[0]=0;
		for(int i=1;i<np;i++){
			wait[i]=burst[i-1]+wait[i-1];
		}
		for(int i=0;i<np;i++){
			ta[i]=wait[i]+burst[i];
		}

		cal_avg();
	}
	public void cal_avg(){
		int total_ta=0,total_wait=0;
		for(int i=0;i<ta.length;i++){
			total_ta=total_ta+ta[i];
		}
		for(int i=0;i<wait.length;i++){
			total_wait=total_wait+wait[i];
		}
		avg_ta=total_ta/np;
		avg_wt=total_wait/np;
		System.out.println("Average Turnaround Time Is: "+avg_ta);
		System.out.println("Average Wait Time Is: "+avg_wt);
	}
	
	public static void main(String[] args) {
		new sjf();
	}
}
