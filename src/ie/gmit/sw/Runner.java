package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Runner {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		String line;
		Menu menu = new Menu();
		System.out.println("Please enter File path: ");
		String fileName = sc.nextLine();
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    while((line = br.readLine()) != null){
			   System.out.println(line);
		   }
	    System.out.println("Please enter File path: ");
		String fileName2 = sc.nextLine();
	    BufferedReader br2 = new BufferedReader(new FileReader(fileName2));
	    while((line = br2.readLine()) != null){
			   System.out.println(line);
		   }
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