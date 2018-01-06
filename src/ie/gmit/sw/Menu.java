package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Menu {
	Scanner sc = new Scanner(System.in);
	String line;
	@SuppressWarnings("resource")
	public void show() throws Exception {
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
	    System.out.println("enter '1' can show similarity.");
	    int a = sc.nextInt();
	    if(a == 1)
	    {
	    	
	    	Launcher l = new Launcher();
	    	l.launch(fileName, fileName2);
	    }
	}
}
