import java.util.LinkedList;
import java.util.List;

public class Pagefifo
{
	List<Integer> list;
	public int fsize=0;
	int pagefault=0;
	public int plru(int ref_str[],int pagesize,int framesize)
	{
		list=new LinkedList<Integer>();
		for (int i = 0; i < ref_str.length; i++)
		{
				
			if(fsize<framesize)
			{
				if(!list.contains(ref_str[i]))
				{
				list.add(ref_str[i]);
				pagefault++;
				fsize++;
				}

			}
			else if(!list.contains(ref_str[i]))
			{
				list.remove(0);
				list.add(ref_str[i]);
				pagefault++;
			}
			System.out.println(list);
		}
		return pagefault;
	}
	public static void main(String[] args) 
	{
		Pagefifo obj=new Pagefifo();
		int[] ref_str= {1,2,3,4,2,1,5,6,2,1,2,3,7,6,3,2,1,2,3,6};
			//{1,2,3,4,2,1,5,6,1,2,3,7,6,3,2,1,2,3,6};
		int capacity=3;
		System.out.println("Total no of Page faults are: "+obj.plru(ref_str,ref_str.length,capacity));
	}
}
