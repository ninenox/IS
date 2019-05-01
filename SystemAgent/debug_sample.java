import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class debug_sample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		System.out.println("Shops receive request from ");
		//----
		
		//-----
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(new File("myowl.owl"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		InputStreamReader isr = null;
		isr = new InputStreamReader(fis);
		BufferedReader buff=new BufferedReader(isr);
		String value1;
		String value2 = new String();
		try {
			while ((value1 = buff.readLine())!=null) {
				
				value2+=value1+"\n";
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("value2 ="+ value2);
		
		RequestAgent a = new RequestAgent();
		
		
	a.CreateFile("D:\\",value2,"owl");
		
	}

}
