package ie.gmit.sw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

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
	
	@SuppressWarnings({ "unchecked", "rawtypes", "unused" })
	public void calculator() {
		List<Integer> intersection = map.get(1);
		Set<Integer> inter = new HashSet<Integer>(intersection);
		
		inter.retainAll(map.get(2));
		float jaccard = (float) (((float)inter.size())/(menu.getK()*1.0));
		System.out.println(jaccard);
	}
	
}
