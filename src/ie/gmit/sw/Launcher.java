package ie.gmit.sw;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
* The Launcher Class
*   
* @author Tianle Shu
* 
* @version 1.0b
*/
public final class Launcher {
	BlockingQueue<Shingle> q1;
	BlockingQueue<Shingle> q2;
	Map<Integer,List<Integer>> map = new HashMap<Integer,List<Integer>>();
	Menu menu = new Menu();
	int docId=1;
	
	public Launcher(Menu menu) {
		this.menu = menu;
		init();
	}
	
	/**
	*  A init method 
	*  
	*/
	public void init() {
		q1 = new LinkedBlockingQueue<>(menu.getBlockingQueuesize());
		q2 = new LinkedBlockingQueue<>(menu.getBlockingQueuesize());
	}
	
	public void launch(String f1,String f2) throws Exception {
		DocumnetParser d1 = new DocumnetParser(f1, menu.getShingleSize(), menu.getK(), docId++);
		q1 = d1.run();
		Consumer c = new Consumer(menu.getK(),menu.getPoolsize());
		map.put(1,c.run(q1).get(0));
		DocumnetParser d2 = new DocumnetParser(f2, menu.getShingleSize(), menu.getK(),docId++);
		q2 = d2.run();
		map.put(2,c.run(q2).get(0));
		calculator();
	}
	
	/**
	 * Calculator method takes compare two Documents.
	 * Calculates the minHash on each Document passed in.
	 * Calculates the jaccard index of the two documents by first calculateing the 
	 * intersection of the two sets of minHashes, and dividing it by the size of the minHashes.
	 */
	public void calculator() {
		List<Integer> intersection = map.get(1);
		Set<Integer> inter = new HashSet<Integer>(intersection);
		 
		inter.retainAll(map.get(2));
		float jaccard = (float) (((float) (((float)inter.size())/(menu.getK()*1.0)))*100.0);
		System.out.println("The document similarity is: "+jaccard+"%");
	}
}