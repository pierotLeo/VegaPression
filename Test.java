package compression;

import java.io.IOException;


public class Test {
		 
	public static void main(String[] args) throws IOException, Exception {
		ImageManipulator l = new ImageManipulator("plus_petite_image.jpg");
		double pixel[][] = new double[100][100];	   
		pixel= l.getPixels();
		
        /*l.setTypeCompression(new CompressionSupressionLigneColonne(l.getPixels()));
        l.executeCompression(2);
        l.createImage("../hiuecsqkui","jpg");*/
		
		//double pixel[][] = {{0,2,-1},{3,-2,0},{-2,2,1}};
		
		Jacobi test = new Jacobi(pixel);

		/*l.setPixels(test.getMatrice());
		l.createImage("plus_petite_image_comp", "jpg");*/

		
	}
}