package sheduling;

import java.util.Scanner;

public class fcfs {

		int burst[],wait[],ta[];
		float avg_ta,avg_wt,np;
		
		public fcfs(){
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
			for(int i=0;i<np;i++){
				burst[i]=sc1.nextInt();
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
			for(int i=0;i<wait.length;i++){
				total_ta=total_ta+ta[i];
			}
			for(int i=0;i<wait.length;i++){
				total_wait=total_wait+wait[i];
			}
			avg_ta=total_ta/np;
			avg_wt=total_wait/np;
			System.out.println("Average Turnaround Time Is: "+avg_ta);
			System.out.println("Average Wait Time Is: "+avg_wt);
			
			//System.out.println(total_ta);
			//System.out.println(total_wait);
		}
		public static void main(String[] args) {
			new fcfs();
		}
}
