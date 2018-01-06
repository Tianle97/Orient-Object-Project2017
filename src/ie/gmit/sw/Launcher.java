package ie.gmit.sw;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Launcher {
	BlockingQueue<Shingle> q = new LinkedBlockingQueue<>(100);
	int shingleSize;
	int k;
	
	

	public void launch(String f1,String f2) throws Exception {
		Thread t1 = new Thread(new DocumnetParser(f1, q, shingleSize, k),"t1");
		Thread t2 = new Thread(new DocumnetParser(f2, q, shingleSize, k),"t2");
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
	}
}
