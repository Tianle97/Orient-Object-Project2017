package ie.gmit.sw;

/**
* The Shingle Class
*   
* @author Tianle Shu
* 
* @version 1.0b
*/
public class Shingle {
	private int docID;
	private int hashcode;
	
	public Shingle() {
		super();
	}
	
	public Shingle(int docID, int hashcode) {
		super();
		this.docID = docID;
		this.hashcode = hashcode;
	}
	
	public int getDocID() {
		return docID;
	}
	
	public void setDocID(int docID) {
		this.docID = docID;
	}
	
	public int getHashcode() {
		return hashcode;
	}
	
	public void setHashcode(int hashcode) {
		this.hashcode = hashcode;
	}
}