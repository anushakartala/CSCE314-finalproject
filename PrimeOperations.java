import java.util.ArrayList; 
import java.math.BigInteger;


/*
 *  Desc: This class generates primes, twin primes, and hexagon crosses using BigInteger data types.
 */
public class PrimeOperations { 
	
	// Pair class implementation.
	private class Pair<T> {
		T first;
		T second;
		
		public Pair(T f, T s)
		{
			first = f;
			second = s;
		}
		
		public void setFirst(T f)
		{ 
			first = f;
		}
		
		public void setSecond(T s)
		{ 
			second = s;
		}
		
		public T getFirst()
		{
			return first;
		}
		
		public T getSecond()
		{
			return second;
		}
		
		
	}
	
	// Member variables for containing out lists of integers, twin primes, hexagon crosses, and the pairs of twin primes that make up the hex crosses.
	private ArrayList<BigInteger> Primes = new ArrayList<BigInteger>();
	private ArrayList<Pair> Twins = new ArrayList<Pair>();
	//private ArrayList<Pair> Hexes = new ArrayList<Pair>();
	private ArrayList<ArrayList<Pair>> Hexes = new ArrayList<ArrayList<Pair>>();

			
	
	// Add a prime to the prime list if and only iff it is not already in the list. (ignore duplicates)
	public void addPrime(BigInteger x)
	{
		Primes.add(x);
	}
	
	// Output the prime list. Each prime should be on a separate line and the total number of primes should be on the following line.
	public void printPrimes()
	{
		for (int index = 0; index < Primes.size(); index++)
			   System.out.println(Primes.get(index));
		System.out.print("Total Primes: ");
		System.out.println(Primes.size());
		
		 
	}
		
	// Output the twin prime list. Each twin prime should be on a separate line with a comma separating them, and the total number of twin primes should be on the following line.
	public void printTwins()
	{
		for (int index = 0; index < Twins.size(); index++)
		{   
			   System.out.print(Twins.get(index).getFirst());
			   System.out.print(", ");
			   System.out.println(Twins.get(index).getSecond());
		}
		System.out.print("Total Twins: ");
		System.out.println(Twins.size());
	}
		
	// Output the hexagon cross list. Each should be on a separate line listing the two twin primes and the corresponding hexagon cross, with the total number on the following line.
	public void printHexes()
	{
		for (int i=0; i<Hexes.size();i++)
		{
			
				System.out.print("Prime Pairs: ");
		 		System.out.print(Hexes.get(i).get(0).getFirst());
				System.out.print(", ");
				System.out.print(Hexes.get(i).get(0).getSecond());
				System.out.print(" and ");
				System.out.print(Hexes.get(i).get(2).getFirst());
				System.out.print(", ");
				System.out.print(Hexes.get(i).get(2).getSecond());
				System.out.print(" separated by ");
				System.out.print(Hexes.get(i).get(1).getFirst());
				System.out.print(", ");
				System.out.println(Hexes.get(i).get(1).getSecond());
				
		}
		System.out.print("Total Hexes: ");
		System.out.println(Hexes.size());
	}
		
	// Generate and store a list of primes.
	public void generatePrimes(int count)
	{
		
        BigInteger num = new BigInteger("1");
        BigInteger i;
        int counter = 0;
        BigInteger remainder;
        boolean isPrime = true;
        while (counter<count)
        {
        	isPrime = true;
        	for (i = new BigInteger("2"); i.compareTo(num.divide(new BigInteger("2"))) <= 0 ; i=i.add(new BigInteger("1")))
        	{
                remainder = num.mod(i);
                if (remainder.equals(new BigInteger("0"))) 
                {
                    isPrime = false;
                    break;
                }
            }
        	if (isPrime == true)
        	{
        		
        		addPrime(num);
        		counter ++;
        		
        	}
        	num = num.add(new BigInteger("1"));
        	
        }
        	
           
		
	}
	
	// Generate and store a list of twin primes.
	public void generateTwinPrimes()
	{
		for(int i=0; i<Primes.size()-1; i++)
		{
			
			if(Primes.get(i+1).subtract(Primes.get(i)).equals(new BigInteger("2")))
			{
				
				Pair<BigInteger> p =new Pair<BigInteger>(Primes.get(i), Primes.get(i+1));
				Twins.add(p);
			}
		}
		
	}
	
	// Generate and store the hexagon crosses, along with the two twin primes that generate the hexagon cross.
	public void generateHexPrimes()
	{
		int counter =0; 
		int j;
		for(int i=0; i<Twins.size()-1; i++)
		{
			BigInteger iValue = (BigInteger)Twins.get(i).getFirst();
			iValue = iValue.add(new BigInteger("1"));
			BigInteger jValue = (BigInteger)Twins.get(i+1).getFirst();
			jValue = jValue.add(new BigInteger("1"));
			j=i+1;
			
			while(iValue.compareTo(jValue.divide(new BigInteger("2"))) >= 0)
			{
				if(iValue.compareTo(jValue.divide(new BigInteger("2"))) == 0)
				{
					
					ArrayList<Pair> a = new ArrayList<Pair>();
					Pair<BigInteger> num1 = new Pair(iValue.subtract(new BigInteger("1")), iValue.add(new BigInteger("1")));						
					Pair<BigInteger> num2 = new Pair(iValue, jValue);
					Pair<BigInteger> num3 = new Pair(jValue.subtract(new BigInteger("1")), jValue.add(new BigInteger("1")));
					a.add(num1);
					a.add(num2);
					a.add(num3);
					Hexes.add(a);
					
				}
				/*System.out.println("i");
				System.out.println(i);
				System.out.println("j");
				System.out.println(j);
				System.out.println("Size");
				System.out.println(Twins.size());*/
				if(j<Twins.size()-1)
				{
				j=j+1;
				jValue = (BigInteger)Twins.get(j).getFirst();
				jValue = jValue.add(new BigInteger("1"));
				/*System.out.println("ivaluein");
				System.out.println(iValue);
				System.out.println("jvaluein");
				System.out.println(jValue);*/
				}
				else
					break;
				
				
			}
		}
	}
}
