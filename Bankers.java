import java.util.Scanner;


public class Bankers {

	public int[][] alloc={
			{0,0,1,2},{1,0,0,0},{1,3,5,6},{0,6,3,2},{0,0,1,4}
			//{3,0,1},{1,2,0},{2,1,3},{0,3,0},{1,1,2}
			//{2,1,0},{3,2,3},{3,0,2},{3,2,0},{1,0,1}	
	};
	public int[][] max={
			{0,0,1,2},{1,7,5,0},{2,3,5,6},{0,6,5,2},{0,6,5,6}
			//{10,7,4},{8,5,3},{6,3,2},{9,6,3},{7,4,5}	
			//{5,6,3},{8,5,6},{4,8,2},{7,4,3},{4,3,3}	
	};
	
	
	public int[][] need=new int[10][10];
	public int[] avil=new int[10];
	public int[] work;
	public int[] finish=new int[10];
	public int[] seq=new int[10];
	public int np,nr;
	public static int iseq=0;
	public static boolean ls=true;
	 public static boolean rflag=true;
	
	
	public static void main(String[] args)
	{
		Bankers s=new Bankers();
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter np and nr\n");
		s.np=sc.nextInt();
		s.nr=sc.nextInt();
		s.work=new int[s.nr];

		System.out.println("Enter avil\n");
		for (int i = 0; i < s.nr; i++) 
		{
			s.avil[i]=sc.nextInt();
			s.work[i]=s.avil[i];
		}
		
		
		System.out.print("work: ");
		for (int j = 0; j < s.work.length; j++) 
		{
			System.out.print(s.work[j]+" ");
		}
		
		
		for (int i = 0; i < s.np; i++) 
		{
			s.finish[i]=0;
		}
		
		System.out.println();
		System.out.println("Allocation Matrix:");
		for (int i = 0; i < s.alloc.length; i++) 
		{
			for(int j=0;j<s.nr;j++) 
			{
				System.out.print(s.alloc[i][j]+" ");
			}
			System.out.println();
			
		}
		
		for (int i = 0; i < s.np; i++) 
		{
			for (int j = 0; j < s.nr; j++) 
			{
				s.need[i][j]=s.max[i][j]-s.alloc[i][j];
			}
		}
		
		
		System.out.println("Need Matrix:");
		for (int i = 0; i < s.np; i++) 
		{
			for(int j=0;j<s.nr;j++) 
			{
				System.out.print(s.need[i][j]+" ");
			}
			System.out.println();
		}
		
		for (int i = 0; i < s.np; i++) 
		{
			for (int j = 0; j < s.nr; j++) 
			{
				
				//System.out.println("need: "+s.need[i][j] + "work"+s.work[j]);
				if(s.need[i][j]>s.work[j])
				{
					ls=false;
				}
				
			}
			if(ls==true)
			{
				for (int j = 0; j < s.nr; j++) 
				{
					s.work[j]=s.work[j]+s.alloc[i][j];
				}
				s.finish[i]=1;
				s.seq[iseq++]=i;
				
				
			//Displaying Work Array
			System.out.print("work:");
			for (int j = 0; j < s.nr; j++) {
				System.out.print(s.work[j]+" ");
			}
			System.out.print("\t\t");
			
			//Displaying Finish Array
			System.out.print("Finish: ");
			for (int k = 0; k < s.np; k++) {
				System.out.print(+s.finish[k]+" ");
			}
			System.out.println();
			
			}
			ls=true;
		}

		boolean flag1=true;
		do
		{
			flag1=true;
		for (int i = 0; i < s.np; i++)
		{
			
			if (s.finish[i]==0) 
			{
				for (int j = 0; j < s.nr; j++) 
				{
					if(s.need[i][j]>s.work[j])
					{
						ls=false;
					}
				}
				
				if(ls==true)
				{
					for (int j = 0; j < s.nr; j++) 
					{
						s.work[j]=s.work[j]+s.alloc[i][j];
					}
					s.finish[i]=1;
					s.seq[iseq++]=i;
					
					//Displaying Work Array
					System.out.print("work:");
					for (int j = 0; j < s.nr; j++) 
					{
						System.out.print(s.work[j]+" ");
					}
					System.out.print("\t\t");
					
					//Displaying Finish Array
					System.out.print("Finish: ");
					for (int k = 0; k < s.np; k++) 
					{
						System.out.print(+s.finish[k]+" ");
					}
					System.out.println();
				
				}
				ls=true;
			}
		 }
		for(int k=0;k<s.np;k++)
		{
			if(s.finish[k]==0)
				flag1=false;
		}
		//System.out.println(flag1);
	}while(flag1!=true);
		
		
		System.out.print("Safe Sequence: ");
		for (int i = 0; i <s.np; i++) {
			System.out.print("P"+s.seq[i]+"  ");
		}
		
		//Resource request
		int reqmat[]=new int[s.nr];
        System.out.println("Please enter the number of the Process that is requesting more resources: ");
        int reqn=sc.nextInt();
        System.out.println("Enter the request matrix \n");
        for (int i = 0; i < reqmat.length; i++) {
		 reqmat[i]=sc.nextInt();
		}
       
        	for (int j = 0; j < s.nr; j++)
        	{
        		//System.out.println("reqmat[reqn] "+reqmat[j]+"s.need[reqn][j] "+s.need[reqn][j]);
				if(reqmat[j] > s.need[reqn][j])
				{
					rflag=false;
				}
			}
        	
        	if(rflag) 
        	{
        		for (int i = 0; i < s.nr; i++) 
        		{
        			if(reqmat[i] > s.avil[i])
        			{
    					rflag=false;
    				}
    			}
        	}
        	else
        	{
        		System.err.println("the process has exceeded its maximum claim.");
        		System.exit(0);
        	}
        	
        	if(rflag)
        	{
        		for (int i = 0; i < s.nr; i++) 
        		{
        			s.avil[i]=s.avil[i]-reqmat[i];
        			s.alloc[reqn][i]=s.alloc[reqn][i]+reqmat[i];
        			s.need[reqn][i]=s.need[reqn][i]-reqmat[i];
				}
				System.out.println("The system will be in a Safe State if the request is granted.");

        	}
        	else
        	{
        		System.err.println("the process must wait, since the resources are not available.");
        	}
	}

}
