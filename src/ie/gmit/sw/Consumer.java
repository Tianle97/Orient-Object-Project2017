package ie.gmit.sw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
* The Consumer Class
*   
* @author Tianle Shu
* 
* @version 1.0b
*/
public class Consumer {
	/** The map. */
	Map<Integer,List<Integer>> map;
	BlockingQueue<Shingle> queue;
	int k;
	int[] minhashes;
	ExecutorService pool;
	
	/**
	* Creates a new <code>Consumer</code> object 
	*
	* @param int k
	* @param int poolsize
	*/
	public Consumer (int k,int poolsize) {
		this.k = k;
		pool = Executors.newFixedThreadPool(poolsize);
		init();
	}

	/**
	* 
	* Creates a new <code>init</code> method 
	*
	*/

	public void init() {
		Random random = new Random();
		minhashes = new int[k];
		for(int i=0;i<k;i++)
		{
			minhashes[i] = random.nextInt();
		} 
	}
	
	/**
	* 
	* Creates a new <code><Integer,List<Integer>>run</code> method 
	* take hashcode from <code>BlockingQueue<Shingle> queue</code> and then return the map
	* 
	*/
	@SuppressWarnings({ "unused" })
	public Map<Integer,List<Integer>> run(BlockingQueue<Shingle> q) {
		map = new HashMap<Integer,List<Integer>>();
		this.queue = q;
		int docNum = 2;
		while(queue.size()>0)
		{
			try {
				Shingle s = queue.take();
				if(s instanceof Poison)
				{
					docNum--;
				}
				else
				{

					for(int i = 0; i < minhashes.length; i++)
					{
						int value = s.getHashcode()^minhashes[i];   // ^ - xor(Random generated key)
						List<Integer> list = map.get(s.getDocID());
						if(list == null)
						{					        // Happens once for each document
							list = new ArrayList<Integer>(k);       // k - size  
							for (int j =0; j < k; j++) 
							{		
								list.add(Integer.MAX_VALUE);	
							}						
							map.put(s.getDocID(), list);	 		
						}
				 		else
						{
							if(list.get(i) > value)
							{
								list.set(i, value);
							}	
						}
					}  
				}
			}catch (InterruptedException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
			return map;
	}
}				