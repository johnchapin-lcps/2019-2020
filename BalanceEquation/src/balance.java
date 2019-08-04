/*

*/

import java.math.*;
import java.util.*;
import java.io.*;

// This object represents a component of a compound as a String of the element
// name and a integer containing the subscript of that element in the compound
class Component
{
		public String element;
		public long subscript;
		
		public Component(String e, long s)
		{
			element = e.toString();
			subscript = s;
		}	
}

// This object represents a chemical compound (either a reactant or 
// a product). It contains a coefficient in front of the compound,
// and a list of components that comprise the compound
class Compound
{
	public ArrayList<Component> terms;
	public long coefficient;
	
	// Constructor created the list of components and initializes
	// the coefficient to one
	public Compound()
	{
		terms = new ArrayList<Component>();
		coefficient = 1;
	}
	
	// Add a new component to this compound
	// (used for the construction of the compound)
	public void add(Component c)
	{
		terms.add(c);
	}
}

class balance
{
	/****************************************************************************/
	// Calculates the greatest common divisor of two integers; that is,
	// the greatest integer that divides evenly into both A and B
	public static long gcd(long A, long B)
	{
		if (B == 0)
			return A;
		
		return gcd (B, A % B);
	}

	/****************************************************************************/
	// Calculates the least common multiple of two integers; that is, 
	// the smallest number that both A and B divide into evenly
	public static long lcm(long A, long B)
	{
		return A * (B / gcd(A,B));
	}

	/****************************************************************************/
	public static void main(String[] args) throws IOException
	{
		// Create a scanner to read from the input file
		Scanner inpt = new Scanner(new File("balance.in"));
		
		// Read the number of data cases, and then loop for that many cases
		int numCases = inpt.nextInt();		
		for (int curCase = 1; curCase <= numCases; curCase++)
		{
			// Read the number of reactants and the number of products from
			// the input file
			int numReactants = inpt.nextInt();
			int numProducts  = inpt.nextInt();
							
			// Create arrays to hold the compounds on the react and product
			// sides of the equation
			Compound[] reactants = new Compound[numReactants];
			Compound[] products  = new Compound[numProducts];
			
			// Read the reactants from the input file
			for (int i = 0; i < numReactants; i++)
			{
    			reactants[i] = new Compound();
				
				String element = inpt.next();
				
				while (!element.equals("+") && !element.equals("->"))
				{
					long subscript = inpt.nextLong();					
					reactants[i].add(new Component(element, subscript));
					
					element = inpt.next();
				}
			}
			
			// Read the rest of the line, and create a second scanner
			// to parse that line
			String secondHalf = inpt.nextLine();
			Scanner pscn = new Scanner(secondHalf);
			
			// Read the products from the input file
			for (int i = 0; i < numProducts; i++)
			{
				products[i] = new Compound();
				String element = pscn.next();
				while (!element.equals("+") && pscn.hasNext())
				{
					long subscript = pscn.nextLong();										
					products[i].add(new Component(element, subscript));
					
					if (pscn.hasNext())					
						element = pscn.next();
					
				}
			}
			
			boolean balanced = false;
			
			// Continue iterating until the equation is balanced
			while (!balanced)
			{
				balanced = true;
				
				// Check each compound in the reactants
				for (int i = 0; i < numReactants; i++)
				{
					Compound r = reactants[i];
					
					// For each element in the current reactant compound r
					for (Component reactElem : r.terms)
					{
						// For each compund int the products
						for (int j = 0; j < numProducts; j++)
						{
							Compound p = products[j];
							for (Component prodElem : p.terms)
							{
								// If this the the same element on the other side of the equation
								if (reactElem.element.equals(prodElem.element))
								{
									// If the number of atoms of the element aren't the same in the
									// products and in the reactants
									if (reactElem.subscript * r.coefficient != 
									    prodElem.subscript * p.coefficient)
									{
										// Get the least common multiple of the product and reactant
										// side of the equation, and set the coefficient for each side
										// to the lcm divided by the subscript of the element
									   long least = lcm(reactElem.subscript * r.coefficient,
										                prodElem.subscript * p.coefficient);
										r.coefficient = least / reactElem.subscript;
										p.coefficient = least / prodElem.subscript;
										
										// Since we had to make a change, we know that the equation
										// is not yet done being balanced
										balanced = false;
									}
								}
							}
						}
					}
				}
			}
			
			// Print out the resulting balanced equation
			System.out.printf("Equation %d: ",curCase);
			
			// Print the reactants
			for (int i = 0; i < reactants.length; i++)
			{
				Compound c = reactants[i];
				// Print this compounds coefficient
				System.out.printf(c.coefficient+" ");
				
				// Print each component of the compound
				for (int j = 0; j < c.terms.size(); j++)				
					System.out.printf("%s %d ", 
					                  c.terms.get(j).element, c.terms.get(j).subscript);
					
				// print a plus if there's a next compound, or an arrow if it's the end of
				// the reactants
				if (i != reactants.length - 1)
					System.out.print("+ ");
				else
					System.out.print("-> ");
			}
			
			// Print the products
			for (int i = 0; i < products.length; i++)
			{
				Compound c = products[i];
				// Print this compounds coefficient
				System.out.printf(c.coefficient+" ");
				
				// Print each component of the compound
				for (int j = 0; j < c.terms.size(); j++)				
					System.out.printf("%s %d ", c.terms.get(j).element, 
					                            c.terms.get(j).subscript);
					
				// print a plus if there's a next compound
				if (i != products.length - 1)
					System.out.print("+ ");
			}
			
			System.out.println();
		}
	}
}