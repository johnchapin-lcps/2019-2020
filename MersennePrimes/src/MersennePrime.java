import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;



public class MersennePrime {
	int width = 150;    //width of the image
	int height = 150;   //height of the image
	BufferedImage image = null;
	File f = null;
	
	public static void main(String[] args) {
		int width = 150;    //width of the image
		int height = 150;   //height of the image
		BufferedImage img = null;
		File f = null;
		
		//read image
	    try{
	      f = new File("mersennesquare.jpeg"); //image file path
	      img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	      img = ImageIO.read(f);
	      System.out.println("Reading complete.");
	    }catch(IOException e){
	      System.out.println("Error: "+e);
	    }
	    
	    //get image width and height
	    width = img.getWidth();
	    height = img.getHeight();
	    int[][] primeImg = new int[width][height];
	    
	    for(int y = 0; y < height; y++){
	        for(int x = 0; x < width; x++){
	          int p = img.getRGB(x,y);
	          System.out.print("p" + p);

	          int a = (p>>24)&0xff;
	          System.out.print("a" + a);

	          int r = (p>>16)&0xff;
	          System.out.print("r" + r);

	          int g = (p>>8)&0xff;
	          System.out.print("g" + g);
	          
	          int b = p&0xff;
	          System.out.println("b" + b);

	          //calculate average
	          int avg = (r+g+b)/3;
	          primeImg[x][y] = avg/25;
	          avg = primeImg[x][y]*25;

	          //replace RGB value with avg
	          p = (a<<24) | (avg<<16) | (avg<<8) | avg;

	          img.setRGB(x, y, p);
	        }
	    }
	    
	    //write image
	    try{
	      f = new File("Output.jpg");  //output file path
	      ImageIO.write(img, "jpg", f);
	      System.out.println("Writing complete.");
	    }catch(IOException e){
	      System.out.println("Error: "+e);
	    }

		
		

	}
}
