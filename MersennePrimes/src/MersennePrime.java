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
		BufferedImage image = null;
		File f = null;
		
		//read image
	    try{
	      f = new File("mersennesquare.jpeg"); //image file path
	      image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	      image = ImageIO.read(f);
	      System.out.println("Reading complete.");
	    }catch(IOException e){
	      System.out.println("Error: "+e);
	    }
	    
	    //write image
	    try{
	      f = new File("Output.jpg");  //output file path
	      ImageIO.write(image, "jpg", f);
	      System.out.println("Writing complete.");
	    }catch(IOException e){
	      System.out.println("Error: "+e);
	    }

		
		

	}
}
