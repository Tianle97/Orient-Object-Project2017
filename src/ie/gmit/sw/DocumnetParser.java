package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class DocumnetParser {
	BlockingQueue<Shingle> q= new LinkedBlockingQueue<>();
	private Deque<String> buffer = new LinkedList<>();
	private String file;
	private int shingleSize;
	private int k;
	private int docID;
	int docId;
	
	public DocumnetParser(String file,int shingleSize,int k, int docId) throws Exception{
		this.file = file;
		this.shingleSize = shingleSize;
		this.k = k;
		this.docId = docId;	
	}
	
	public BlockingQueue<Shingle> run() {
		String line = null;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			while((line = br.readLine()) != null)
			{
				String uLine = line.toUpperCase();
				String[] words = uLine.split(" "); 
				addWordToBuffer(words);
				Shingle s = getNextShingle();
				s.setDocID(docID);
				q.put(s);  //put(s):把s加到BlockingQueue里,如果BlockQueue没有空间,则调用此方法的线程被阻断直到BlockingQueue里面有空间再继续.
			}
			flushBuffer();
			br.close();
			} catch (Exception  e) {
				e.printStackTrace();
			} 
		return q;
	}



	public void addWordToBuffer(String[] words) {
		for(String s: words)
		{
			buffer.add(s); // add(s):把s加到BlockingQueue里,即如果BlockingQueue可以容纳,则返回true,否则招聘异常
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
			}
			counter++;
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
				q.put(s); //put(s):把s加到BlockingQueue里,如果BlockQueue没有空间,则调用此方法的线程被阻断直到BlockingQueue里面有空间再继续.
			}
			else
			{
				q.put(new Poison(docID,0));
			}
		}
	}
	
	
	

}
