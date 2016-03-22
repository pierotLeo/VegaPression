package compression;

import java.io.IOException;


public class Test {

	public static void main(String[] args) throws IOException, Exception {
		   ImageManipulator l = new ImageManipulator("test.jpg");
		   double pixel[][] = new double[100][100];
		   
		   /*for(int i=0;i<100;i++)
			   for(int j=0;j<100;j++)
				   pixel[i][j] = 255;*/
		   
		   pixel= l.getPixels();
		   
		   for(int i = 0;i<pixel.length;i++){
				System.out.println();
				for(int j = 0;j<pixel.length;j++){
					System.out.print(pixel[i][j] + " | ");
				}
		   }
		   
		   System.out.println("\n\n\n\n");
           /*l.setTypeCompression(new CompressionSupressionLigneColonne(l.getPixels()));
           l.executeCompression(2);
           l.createImage("../hiuecsqkui","jpg");*/
		
			Givens mat = new Givens(10,20,pixel.length,0);
			double[][] test = new double[pixel.length][pixel.length];
			test = Givens.produit(mat.getMatGivens(),pixel);
			//mat.afficher();
			System.out.println("\n\n\n\n");
			
			/*for(int i = 0;i<test.length;i++){
				System.out.println();
				for(int j = 0;j<test.length;j++){
					System.out.print(test[i][j] + " | ");
				}
			}*/
			
			l.setPixels(test);
			l.createImage("nouveauTest", "jpg");
			
	}
}