package ie.gmit.sw;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Runner {
	BlockingQueue<Shingle> queue = new LinkedBlockingQueue<>(100);
	
	public static void main(String[] args) throws Exception {
		new Menu().show();
	}
}
