import java.util.LinkedList;
import java.util.List;

public class pagel {
	  List<Integer> list;
	  int page_fault;
	public int lru(int ref_str[],int pagesize,int framesize) {
		list=new LinkedList<Integer>();
		int fsize=0;
		for (int i = 0; i < ref_str.length; i++) 
		{
			if(fsize < framesize)
			 {
				if(!list.contains(ref_str[i]))
				 {
					list.add(ref_str[i]);
					page_fault++;
					fsize++;
				}
				else if (list.contains(ref_str[i])) 
				{
					list.remove(list.indexOf(ref_str[i]));
					list.add(ref_str[i]);
				}
			}
			else if (list.contains(ref_str[i])) {
				list.remove(list.indexOf(ref_str[i]));
				list.add(ref_str[i]);
			}else {
				list.remove(0);
				list.add(ref_str[i]);
				page_fault++;
			}
			System.out.println(list);
		}
		return page_fault;
	}
public static void main(String[] args) {
	pagel p=new pagel();
	int page_ref[] = {1,2,3,4,2,1,5,6,2,1,2,3,7,6,3,2,1,2,3,6};
	  
    int capacity = 3;
    System.out.println("Number of page faults : "+p.lru(page_ref, page_ref.length, capacity));
}

}

