package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;

public class DocumnetParser implements Runnable  {
	private BlockingQueue<Shingle> queue;
	private Deque<String> buffer = new LinkedList<>();
	private String file;
	private int shingleSize;
	@SuppressWarnings("unused")
	private int k;
	private int docID;
	
	public DocumnetParser(String file,BlockingQueue<Shingle> q,int shingleSize,int k) throws Exception{
		this.queue = q;
		this.file = file;
		this.shingleSize = shingleSize;
		this.k = k;
	}
	
	@Override
	public void run() {
		String line = null;
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			while((line = br.readLine()) != null)
			{
				String uLine = line.toUpperCase();
				String[] words = uLine.split(" "); 
				addWordToBuffer(words);
				Shingle s = getNextShingle();
				queue.put(s);  //put:阻塞
			}
			flushBuffer();
			br.close();
			} catch (Exception  e) {
				e.printStackTrace();
			} 
	}

	private void addWordToBuffer(String[] words) {
		for(String s: words)
		{
			buffer.add(s); // )add(s):把s加到BlockingQueue里,即如果BlockingQueue可以容纳,则返回true,否则招聘异常
		}
	}

	private Shingle getNextShingle() {
		StringBuffer sb = new StringBuffer();
		int counter = 0;
		while(counter<shingleSize)
		{
			if(buffer.peek()!=null)   //peek:检查
			{
				sb.append(buffer.poll());
				counter++;
			}
		}
		if (sb.length() > 0)
		{
			return(new Shingle(docID, sb.toString().hashCode()));
		}
		else
		{
			return(null);
		}
	}
	
	private void flushBuffer() throws InterruptedException {
		while(buffer.size()>0)
		{
			Shingle s = getNextShingle();
			if(s != null)
			{
				queue.put(s); //put(s):把s加到BlockingQueue里,如果BlockQueue没有空间,则调用此方法的线程被阻断直到BlockingQueue里面有空间再继续.
			}
			else
			{
				queue.put(new Poison(docID,0));
			}
		}
	}

}
