package compression;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ImageManipulator {
   private BufferedImage image;
   private AlgoCompression typeCompression;
   
   ImageManipulator(String nomF)throws Exception { 
     File input = new File(nomF);
     image = ImageIO.read(input);
   }
   
   public BufferedImage getImage(){
	   return this.image;
   }
   
   public AlgoCompression getTypeCompression(){
	   return this.typeCompression;
   }
   
   public void setTypeCompression(AlgoCompression a) {
	   this.typeCompression = a;
   }
   
   public void executeCompression(int taux) {
	   double[][] pixels = this.typeCompression.execute(taux);
	   this.setPixels(pixels);
   }
   
   public Dimension getImageSize() {
     return new Dimension(image.getWidth(), image.getHeight());
   }

   public double[][] getPixels() {
     int wid, hgt;

     // compute size of the array
     wid = image.getWidth();
     hgt = image.getHeight();

     double[][] pixelMatrix = new double[hgt][wid];
     // start getting the pixels
     Raster pixelData;
     pixelData = image.getData();
     for(int i=0;i<hgt;i++)
    	 for(int j=0;j<wid;j++) {
	     pixelMatrix[i][j] = pixelData.getSample(j, i,(int) pixelMatrix[i][j]);
    	 }
     return pixelMatrix;  
   }

   public void setPixels(double pixels[][]) {
	   this.image = new BufferedImage(pixels[0].length, pixels.length,BufferedImage.TYPE_BYTE_GRAY);
	   double[] pixelsTab = new double[pixels[0].length*pixels.length];
	   int y=0;
	   for(int i=0;i<pixels.length;i++)
	   {
		   
		   for(int j=0;j<pixels[0].length;j++)
		   {
			   pixelsTab[y] = pixels[i][j];
			   y++;
		   }
	   }
	   image.getRaster().setPixels(0, 0,pixels[0].length, pixels.length, pixelsTab);
   }

   public void createImage(String nomF,String format) throws IOException{
      File f = new File(nomF);
      ImageIO.write(this.image, format, f);
   }

 }