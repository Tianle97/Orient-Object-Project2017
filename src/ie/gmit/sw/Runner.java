package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
/**
* The Runner Class
*   
* @author Tianle Shu
* 
* @version 1.0b
*/
public class Runner {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		/**
		 * The main function is ask user to enter url about file-1 and file-2
		 * and output the file content respectively
		 * Next ask user to enter the k , poolsize ,BlockingQueuesize and shingleSize
		 * finally will output the result of jaccard 
		 * 
		 */
		Scanner sc = new Scanner(System.in);
		String line;
		Menu menu = new Menu();
		System.out.println("--------Java-api-for-document-similarity--------");
		System.out.println("");
		System.out.println("Please enter File path: ");
		String fileName = sc.nextLine();
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    while((line = br.readLine()) != null){
			   System.out.println(line);
		   }
	    System.out.println("");
	    System.out.println("Please enter File path  : ");
		String fileName2 = sc.nextLine();
	    BufferedReader br2 = new BufferedReader(new FileReader(fileName2));
	    while((line = br2.readLine()) != null){
			   System.out.println(line);
		   }
	    System.out.println("");
	    System.out.println("Please enter k:");
	    menu.setK(sc.nextInt());
	    System.out.println("Please enter the poolsize: ");
	    menu.setPoolsize(sc.nextInt());
	    System.out.println("Please enter the  BlockingQueuesize: ");
	    menu.setBlockingQueuesize(sc.nextInt());
	    System.out.println("Please enter the  shingleSize: ");
	    menu.setShingleSize(sc.nextInt());
    	Launcher l = new Launcher(menu);
    	l.launch(fileName, fileName2);
	}
}