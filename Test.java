package compression;

import java.io.IOException;


public class Test {
		 
	public static void main(String[] args) throws IOException, Exception {
		/*ImageManipulator l = new ImageManipulator("plus_petite_image.jpg");
		double pixel[][] = new double[100][100];	   
		pixel= l.getPixels();*/
		
        /*l.setTypeCompression(new CompressionSupressionLigneColonne(l.getPixels()));
        l.executeCompression(2);
        l.createImage("../hiuecsqkui","jpg");*/
		
		double pixel[][] = {{2,1,0},{1,1,1},{0,1,2}};
		/*double vect[][] = {{1,1,1},{-1,0,1},{1,-2,1}};
		double val[][] = {{3,0,0},{0,2,0},{0,0,0}};
		double transp[][] = Operations.produit(Operations.transposeMatrix(pixel), pixel);
		
		Operations.afficher(pixel);
		Operations.afficher(transp);
		Operations.afficher(vect);
		Operations.afficher(val);*/
		
		Jacobi test = new Jacobi(pixel);
		test.valP();
		test.vectP();
		
		double[][] resultat = new double[3][3];
		resultat = Operations.produit(test.getVectP1(),test.getValP1());
		resultat = Operations.produit(resultat,Operations.transposeMatrix(test.getVectP1()));//Operations.transposeMatrix(test.getVectP1()));
		Operations.afficher(resultat);
		/*l.setPixels(test.getMatrice());
		l.createImage("plus_petite_image_comp", "jpg");*/

		
	}
}