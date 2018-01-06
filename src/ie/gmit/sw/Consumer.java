package ie.gmit.sw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.text.html.parser.DocumentParser;

public class Consumer implements Runnable {
	private BlockingQueue<Shingle> queue;
	private int k;
	private int[] minhashes;
	private Map<Integer,List<Integer>> map = new HashMap<>();
	private ExecutorService pool;
	
	public Consumer (BlockingQueue<Shingle> q,int k,int poolsize) {
		this.queue = q;
		this.k = k;
		pool = Executors.newFixedThreadPool(poolsize);
		init();
	}

	private void init() {
		Random random = new Random();
		minhashes = new int[k];
		for(int i=0;i<10;i++)
		{
			minhashes[i] = random.nextInt();
		} 
	}
	
	public void run() {
		int docNum = 2;
		while(docNum>0)
		{
			Shingle s = null;
			try {
				s = queue.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(s instanceof Poison)
			{
				docNum--;
			}
			else
			{
				pool.execute(new Runnable() {
					public void run() {
					for(int i = 0; i < minhashes.length; i++)
					{
						int value = s.getHashcode()^minhashes[i];   // ^ - xor(Random generated key)
						List<Integer> list = map.get(s.getDocID());
						if(list == null)
						{					        // Happens once for each document
							list = new ArrayList<Integer>(k);       // k - size  
							for (int j =0; j < list.size(); j++) 
							{		
								list.set(j, Integer.MAX_VALUE);	
							}						
							map.pool(s.getDocID(), list);			
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
			});
		}
		}
	}
}
