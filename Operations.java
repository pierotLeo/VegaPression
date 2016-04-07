package compression;

public class Operations {			
			
	public static void afficher(double[][] aff){
		for(int i = 0;i<aff.length;i++){
			System.out.println();
			for(int j = 0;j<aff[0].length;j++){
				
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
	
	public double[][] produit(double[][] matG, double[][] matB){
		double[][] c = new double[matG.length][matB[0].length]; 
		
		for (int i=0; i<matG.length; i++){
		    for (int j=0; j<matB[0].length; j++){
		    	System.out.println(i + " | " +j);
		    	c[i][j]=0;
		    	
		    	for (int k=0; k<matG[0].length && k<matB.length; k++){
		    		c[i][j] += matG[i][k] * matB[k][j];
		    	}

		    }
		}
		
		return c;
	}
	
	
	public double[][] produitMatrixToGivens(double[][] matrix, double[][] givens, int par_p, int par_q){
		  double[][] retour = new double[matrix.length][matrix.length];
		  
		  int p = par_p;
		  int q = par_q;  
		  int n = matrix.length;
		  
		  for(int i=0; i<n; i++){
		   for(int j=0; j<n; j++){
		    retour[i][j] = matrix[i][j];
		   }
		  }
		  
		  for(int i=0;i<n;i++){
		   for(int k = p; k <= q; k += (q-p)){
		     retour[i][k]=0;
		   }
		  }
		  
		  
		  for(int i=0; i<n; i++){
		   for(int j = p; j <= q; j += (q-p)){
		    for(int k = p; k <= q; k += (q-p)){
		     retour[i][j] += matrix[i][k] * givens[k][j];
		    }
		   }
		  }
		  
		  return retour;
		  
		 }
	
	public double[][] produitGivensToMatrix(double[][] givens, double[][] matrix, int par_p, int par_q){
		  double[][] retour = new double[matrix.length][matrix.length];
		  
		  int p = par_p;
		  int q = par_q;  
		  int n = matrix.length;
		  
		  for(int i=0; i<n; i++){
		   for(int j=0; j<n; j++){
		    retour[i][j] = matrix[i][j];
		   }
		  }
		  
		  for(int j=0;j<n;j++){
		   for(int k = p; k <= q; k += (q-p)){
		     retour[k][j]=0;
		   }
		  }
		  
		  
		  for(int i = p; i <= q; i += (q-p)){
		   for(int j=0; j<n; j++){
		    for(int k = p; k <= q; k += (q-p)){
		     retour[i][j] += givens[i][k] * matrix[k][j];
		    }
		   }
		  }
		  
		  return retour;
		  
	}
	
	
	
	 public double[][] transposeMatrix(double [][] m){
	        double[][] temp = new double[m[0].length][m.length];
	        for (int i = 0; i < m.length; i++)
	            for (int j = 0; j < m[0].length; j++)
	                temp[j][i] = m[i][j];
	        return temp;
	}
	 
	 
	public double polynome(double[][] matrice,int p, int q){
		double a = 1;
		double b = 2*((matrice[p][p] - matrice[q][q]) / (2 * matrice[p][q]));
		double c = -1;
		
		double discriminant = Math.pow(b, 2) - (4 * a * c);
		
		
		/*System.out.println("\ndiscriminant = " + discriminant);
		System.out.println("b = " + b);*/
		
		
		double s1 = (-b + ((Math.sqrt(discriminant))))/(2.0*a);
		double s2 = (-b - ((Math.sqrt(discriminant))))/(2.0*a);

		/*System.out.println("s1 = " + s1);
		System.out.println("s2 = " + s2);*/
	
		double teta = Math.atan(1/s1);

		/*System.out.println("téta1 = " + teta );
		System.out.println("téta2 = " + Math.atan(1/s2));
		
		System.out.println((Math.pow((1/Math.tan(teta)),2)));
		System.out.println((b*(1/Math.tan(teta))));*/
		
		
		/*System.out.println("équation s1 = " + (Math.pow((1/Math.tan(teta)),2) + (b*(1/Math.tan(teta)) -1)));
		System.out.println("équation s2 = " + (Math.pow((1/Math.tan(Math.atan(1/s2))),2) + (b*(1/Math.tan(Math.atan(1/s2))) -1))  + "\n");*/

		return teta; //téta en radians
		
	}
}
