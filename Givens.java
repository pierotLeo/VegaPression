package compression;

public class Givens {
	
	private double[][] matGivens;
	private int p,q;
	private double angle;
	
	
	public Givens(int a,int b,int n,double c) {
		this.matGivens = new double[n][n];
		this.p=a-1;
		this.q=b-1;
		this.angle=c;
		
		double radians = Math.toRadians(angle);
		
		this.matGivens[p][p]= Math.cos(radians);
		this.matGivens[q][q]= Math.cos(radians);
		this.matGivens[p][q]= Math.sin(radians);
		this.matGivens[q][p]= - Math.sin(radians);
				
								
		for(int i = 0;i<matGivens.length;i++){
			for(int j = 0;j<matGivens.length;j++){
				
				if(i!=p && j!=p && j!=q && i!=p){
					if(i==j)
						matGivens[i][j]=1;
					else 
						matGivens[i][j]=0;
				}
				
			}
		}
	}
	
	
	public void afficher(){
		for(int i = 0;i<matGivens.length;i++){
			System.out.println();
			for(int j = 0;j<matGivens.length;j++){
				
				if(i==p && j==p)
					System.out.print("cos(" + this.angle + ")  | ");
				else if(i==q && j==q)
					System.out.print("cos(" + this.angle + ") | ");
				else if(i==p && j==q)
					System.out.print("sin(" + this.angle + ") | ");
				else if(i==q && j==p)
					System.out.print("-sin(" + this.angle + ") | ");
				else if(j==p && i!=q)	
					System.out.print(matGivens[i][j] + "        | ");
				else if(j==q)
					System.out.print(matGivens[i][j] + "       | ");
				else
					System.out.print(matGivens[i][j] + " | ");
			}
		}
	}
	
	public static double[][] produit(double[][] matG, double[][] matB){
		double[][] c = new double[matG.length][matG.length];
		
		
		for (int i=0; i<matG.length; i++)
		    for (int j=0; j<matG.length; j++)
		      for (int k=0; k<matG.length; k++)
		        c[i][k] += matB[i][k] * matG[k][j]; 
		
		return c;
	} 
	        		
	        		
	public double[][] getMatGivens() {
		return matGivens;
	}

	public void setMatGivens(double[][] matGivens) {
		this.matGivens = matGivens;
	}
	
}
