// This file gives access to the underlying datafile and stores the data in the Workout class.
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.Scanner;
//import Primes.IterablePrimes;

public class FileAccess {
  
  public static boolean loadPrimes(Primes primes, String filename) 
  {
	  File file = new File (Config.DATAPATH + filename);
	    try {

	        Scanner scan = new Scanner(file);
	        while (scan.hasNext()) {
	            primes.addPrime(scan.nextBigInteger());
	        }
	        scan.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    } 
	  
	  
	 return true;
  }
  
  

public static boolean loadCrosses(Primes primes, String filename) {
	
	File file = new File (Config.DATAPATH + filename);
    try {

        Scanner scan = new Scanner(file);
        while (scan.hasNextLine()) {
        	String s[] = scan.nextLine().split(",");
        	BigInteger left = new BigInteger(s[0].trim());
        	BigInteger right = new BigInteger(s[1].trim());
        	Pair<BigInteger> p = new Pair<BigInteger>(left, right);
            primes.addCross(p);
        }
        scan.close();
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    } 
  
  
 return true;
	}
  
  public static boolean savePrimes(Primes primes, String filename)
  {
	  try
	  {
		  FileWriter fw = new FileWriter(Config.DATAPATH + filename);
	  
	  for(BigInteger b :  primes.iteratePrimes())
	  { 
		  fw.write(b.toString());
		  fw.write('\n');
	  }
	  fw.close();
	  }
	  catch(Exception e) {
			e.printStackTrace();
			return false;
	  }
	    
	 
  	return true;
  }
  
  public static boolean saveCrosses(Primes primes, String filename)
  {
	  try
	  {
		  FileWriter fw = new FileWriter(Config.DATAPATH + filename);
	  
	  for(Pair<BigInteger> p :  primes.iterateCrosses())
	  { 
		  fw.write(p.left().toString());
		  fw.write(", ");
		  fw.write(p.right().toString());
		  fw.write('\n');
	  }
	  fw.close();
	  }
	  catch(Exception e) {
			e.printStackTrace();
			return false;
	  }
	    
	 
  	return true;
  }
} 
