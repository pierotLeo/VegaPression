package compression;

import java.io.IOException;


public class Test {

	public static void afficher(double[][] aff){
		for(int i = 0;i<aff.length;i++){
			System.out.println();
			for(int j = 0;j<aff.length;j++){
				
				if(aff[i][j] < 10)	
					System.out.print(aff[i][j] + "    | ");
				else if(aff[i][j] < 100)
					System.out.print(aff[i][j] + "   | ");
				else if(aff[i][j] < 1000)
					System.out.print(aff[i][j] + "  | ");
				else
					System.out.print(aff[i][j] + " | ");
			}
		}
		System.out.println("\n\n");
	}
	
	public static double[][] produit(double[][] matG, double[][] matB){
		double[][] c = new double[matG.length][matG.length];
		
		for (int i=0; i<matG.length; i++){
		    for (int j=0; j<matG.length; j++){
		    	c[i][j]=0;
		    }
		}    
		    	
		
		for (int i=0; i<matG.length; i++){
		    for (int j=0; j<matG.length; j++){
		      for (int k=0; k<matG.length; k++){
		        c[i][j] += matG[i][k] * matB[k][j]; 
		      }
		    }
		}
				
		
		return c;
	} 
	
	
	 public static double[][] transposeMatrix(double [][] m){
	        double[][] temp = new double[m[0].length][m.length];
	        for (int i = 0; i < m.length; i++)
	            for (int j = 0; j < m[0].length; j++)
	                temp[j][i] = m[i][j];
	        return temp;
	    }
	
	 
	public static void main(String[] args) throws IOException, Exception {
		   ImageManipulator l = new ImageManipulator("test2.jpg");
		   double pixel[][] = new double[100][100];
		   
		   /*for(int i=0;i<100;i++)
			   for(int j=0;j<100;j++)
				   pixel[i][j] = 255;*/
		   
		   pixel= l.getPixels();
		   
		   afficher(pixel);
		   
		   //System.out.println("\n\n\n\n");
           /*l.setTypeCompression(new CompressionSupressionLigneColonne(l.getPixels()));
           l.executeCompression(2);
           l.createImage("../hiuecsqkui","jpg");*/
		
			/*Givens mat = new Givens(10,20,pixel.length,0);
			double[][] test = new double[pixel.length][pixel.length];
			test = produit(pixel,mat.getMatGivens());
			mat.afficher();
			System.out.println("\n\n\n\n");*/
			
			/*for(int i = 0;i<test.length;i++){
				System.out.println();
				for(int j = 0;j<test.length;j++){
					System.out.print(test[i][j] + " | ");
				}
			}*/
		   Jacobi test = new Jacobi(pixel);
			l.setPixels(test.getMatrice());
			l.createImage("4", "jpg");
				
		
		/*double[][] temp = new double[2][2];
		double[][] temp2 = new double[2][2];
		
		temp[0][0]=1;
		temp[0][1]=2;
		temp[1][0]=3;
		temp[1][1]=4;
		 
		temp2[0][0]=4;
		temp2[0][1]=3;
		temp2[1][0]=1;
		temp2[1][1]=2;
		 
		double[][] test = new double[2][2];
		test=produit(temp,temp2);
		
		for (int i=0; i<test.length; i++){
			System.out.println();
		    for (int j=0; j<test.length; j++){
		    	System.out.print(temp[i][j] + " ");
		    }
		}
		
		for (int i=0; i<test.length; i++){
			System.out.println();
		    for (int j=0; j<test.length; j++){
		    	System.out.print(temp2[i][j] + " ");
		    }
		}
		
		for (int i=0; i<test.length; i++){
			System.out.println();
		    for (int j=0; j<test.length; j++){
		    	System.out.print(test[i][j] + " ");
		    }
		}*/
	}
}