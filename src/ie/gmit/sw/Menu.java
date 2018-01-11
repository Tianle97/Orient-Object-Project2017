package ie.gmit.sw;

/**
* The Menu Class
*   
* @author Tianle Shu
* 
* @version 1.0b
*/
public class Menu {
	int k;
	int BlockingQueuesize;
	int poolsize;
	int shingleSize;
	
	public int getShingleSize() {
		return shingleSize;
	}
	
	public void setShingleSize(int shingleSize) {
		this.shingleSize = shingleSize;
	}

	public int getK() {
		return k;
	}

	public void setK(int k) {
		this.k = k;
	}

	public int getBlockingQueuesize() {
		return BlockingQueuesize;
	}

	public void setBlockingQueuesize(int blockingQueuesize) {
		BlockingQueuesize = blockingQueuesize;
	}

	public int getPoolsize() {
		return poolsize;
	}

	public void setPoolsize(int poolsize) {
		this.poolsize = poolsize;
	}
}