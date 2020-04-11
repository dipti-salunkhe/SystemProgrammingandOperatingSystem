package pri;

import java.util.Scanner;
import java.util.TreeMap;


public class Pri {
	

	int np,burst[],wait[],ta[],burst_pre[],pri[];
	Integer burst1[];
	float avg_ta,avg_wt;
	TreeMap<Integer,Integer> tm=new TreeMap<Integer, Integer>();
	
	public Pri(){
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the no of processes: ");
		np=sc.nextInt();
		burst=new int[np];
		wait=new int[np];
		ta=new int[np];
		burst_pre=new int[np];
		pri=new int[np];
		burst();
	}
	public void burst(){
		Scanner sc1=new Scanner(System.in);
		Scanner sc2=new Scanner(System.in);
		System.out.println("Enter their burst times in orders: ");
		for(int i=0;i<np;i++){
			burst_pre[i]=sc1.nextInt();
		}
		System.out.println("Enter their priorities in orders: ");
		for(int i=0;i<np;i++){
			pri[i]=sc2.nextInt();
		}
		for(int i=0;i<np;i++){
			tm.put(pri[i],burst_pre[i]);
		}
		System.out.println(tm);
		burst1=tm.values().toArray(new Integer[tm.size()]);
		for(int i=0;i<np;i++){
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
		new Pri();
	}
}
