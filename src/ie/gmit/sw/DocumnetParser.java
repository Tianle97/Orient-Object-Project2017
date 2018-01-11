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
				q.put(s);  //put(s):��s�ӵ�BlockingQueue��,���BlockQueueû�пռ�,����ô˷������̱߳����ֱ��BlockingQueue�����пռ��ټ���.
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
			buffer.add(s); // add(s):��s�ӵ�BlockingQueue��,�����BlockingQueue��������,�򷵻�true,������Ƹ�쳣
		}
	}

	private Shingle getNextShingle() {
		StringBuffer sb = new StringBuffer();
		int counter = 0;
		while(counter<shingleSize)
		{
			if(buffer.peek()!=null)   //peek:���
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
				q.put(s); //put(s):��s�ӵ�BlockingQueue��,���BlockQueueû�пռ�,����ô˷������̱߳����ֱ��BlockingQueue�����пռ��ټ���.
			}
			else
			{
				q.put(new Poison(docID,0));
			}
		}
	}
	
	
	

}
