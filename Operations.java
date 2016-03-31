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
		double[][] c = new double[matG.length][matG.length];
		
		for (int i=0; i<matG.length; i++){
		    for (int j=0; j<matG.length; j++){
		    	c[i][j]=0;
		    }
		}    
		
		for (int i=0; i<matG.length; i++){
		    for (int j=0; j<matG.length; j++){
		      for (int k=0; k<matG.length && k<matB.length; k++){
		        c[i][j] += matG[i][k] * matB[k][j];
		      }

		    }
		}
		
		return c;
	}
	
	public double[][] produitGivens(double[][] mat,Givens matGT){
		double[][] retour = new double[mat.length][mat.length];
			
		int p = matGT.getP();
		int q = matGT.getQ();		
		int n = mat.length;
		
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if(j!=p && j!=q)
					retour[i][j]=mat[i][j];
			}
		}

		for(int i=0;i<n;i++){
			for(int k=0;k<n;k++){
				retour[i][p]+= mat[i][k] * matGT.getMatGivensT()[k][p];
			}
	
		}
		
		for(int i=0;i<n;i++){
			for(int k=0;k<n;k++){
				retour[i][q]+= mat[i][k] * matGT.getMatGivensT()[k][q]; 				
			}
		}
		
		return retour;
	}
	
	
	public double[][] produitGivensT(Givens matGT,double[][] mat){
		double[][] retour = new double[mat.length][mat.length];
			
		int p = matGT.getP();
		int q = matGT.getQ();		
		int n = mat.length;
		
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++){
				if(i!=p && i!=q)
					retour[i][j]=mat[i][j];
			}
		}
		
		for(int i=0;i<n;i++){
			for(int k=0;k<n;k++){
				retour[p][i]+= matGT.getMatGivensT()[p][k] * mat[k][i];
			}
	
		}
		
		for(int i=0;i<n;i++){
			for(int k=0;k<n;k++){
				retour[q][i]+= matGT.getMatGivensT()[q][k] * mat[k][i]; 				
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
		double discriminant = (4*(Math.pow(((matrice[p][p]-matrice[q][q])/(2*matrice[p][q])), 2)))+4;
		System.out.println(( 2 / (( -2 *((matrice[p][p] - matrice[q][q]) / (2 * matrice[p][q]))) + Math.sqrt(discriminant))));
		return Math.atan(2/((-2*(matrice[p][p] - matrice[q][q])/(2*matrice[p][q]) + Math.sqrt(discriminant))));
		
	}
}
