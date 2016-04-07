package compression;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;


public class Test {
		 
	public static void main(String[] args) throws IOException, Exception {
		/*ImageManipulator l = new ImageManipulator("plus_petite_image.jpg");
		double pixel[][] = new double[100][100];	   
		pixel= l.getPixels();*/
		
        /*l.setTypeCompression(new CompressionSupressionLigneColonne(l.getPixels()));
        l.executeCompression(2);
        l.createImage("../hiuecsqkui","jpg");*/
		
		//double pixel[][] = {{2,1,0},{1,1,1},{0,1,2}};
		double pixel[][] = {{1,2,3,4,5},{4,5,6,7,8},{5,7,9,11,13}};
		
		Jacobi test = new Jacobi(pixel);
		test.valP();
		test.vectP();
		
		
		double[][] resultat = new double[3][3];
		resultat = Operations.produit(test.getVectP1(),test.getSigma());
		resultat = Operations.produit(resultat,Operations.transposeMatrix(test.getVectP2()));//Operations.transposeMatrix(test.getVectP1()));
		
		for(int i=0;i<resultat.length;i++){
			for(int j=0;j<resultat[0].length;j++){
				resultat[i][j] = Math.round(resultat[i][j]);
			}
		}
		
		
		Operations.afficher(resultat);	
		/*l.setPixels(resultat);
		l.createImage("test", "jpg");*/

		
	}
}