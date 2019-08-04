import java.io.File;
import java.io.FileWriter;
import java.io.FileOutputStream;

import java.io.IOException;
import java.math.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class MersennePrime {
	int width = 150; // width of the image
	int height = 150; // height of the image
	BufferedImage image = null;
	File f = null;

	public static void main(String[] args) {
		int width = 75; // width of the image
		int height = 75; // height of the image
		BufferedImage img = null;
		File f = null;
		
		// create BigInteger
		BigInteger bi2;

		// create Boolean object
		Boolean b2;
		b2 = false;

		// read image
		try {
			f = new File("mersenne75X75.jpg"); // image file path
			img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
			img = ImageIO.read(f);
			System.out.println("Reading complete.");
		} catch (IOException e) {
			System.out.println("Error: " + e);
		}

		// get image width and height
		//create integer array to store greyscale values
		width = img.getWidth();
		height = img.getHeight();
		int[][] primeImg = new int[width][height];
		int total = 0;
		String tempNum = "";

		// Initial setup of array based on image
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				int p = img.getRGB(x, y);
				int a = (p >> 24) & 0xff;
				int r = (p >> 16) & 0xff;
				int g = (p >> 8) & 0xff;
				int b = p & 0xff;

				// calculate average
				int avg = (r + g + b) / 3;
				primeImg[x][y] = avg / 25;
				total += primeImg[x][y];
				avg = primeImg[x][y] * 25;
				//if statement creates a smaller array for testing later
//				if (y % 2 == 0) {

				tempNum = tempNum + Integer.toString(primeImg[x][y]);
//				}

				// replace RGB value with avg
				p = (a << 24) | (avg << 16) | (avg << 8) | avg;
				img.setRGB(x, y, p);
			}
		}
		//print out the sum of the greyscale values
		System.out.println("total beginning: " + total);
		
		//print out the string of greyscale values
		System.out.println("string: " + tempNum);
		
		//set the initial value of the BigInteger to the string of greyscale values
		bi2 = new BigInteger(tempNum);
		String str2 = "";
		
		//find a prime number near the greyscale BigInteger starting number
		while (!b2) {
			b2 = bi2.isProbablePrime(1);
			str2 = bi2 + "\n is prime with certainity 1 is \n" + b2;
			System.out.println(str2);
			//

			if (!b2) {
				int rand = (int) (Math.random() * 3);
				BigInteger randBi = new BigInteger(Integer.toString(rand));
				bi2 = bi2.nextProbablePrime();
				b2 = bi2.isProbablePrime(1);

			}

			str2 = bi2 + "\n is prime with certainity 1 is.. \n" + b2;
			System.out.println(str2);

		} // while loop

		// write image
		try {
			File newTextFile = new File("PrimeNumber.txt");

			FileWriter fw = new FileWriter(newTextFile);
			fw.write(bi2.toString());
			fw.close();
			f = new File("Output.jpg"); // output file path
			ImageIO.write(img, "jpg", f);
			System.out.println("Writing complete.");

		} catch (IOException e) {
			System.out.println("Error: " + e);
		}

	}
}
