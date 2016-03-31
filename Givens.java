package compression;

public class Givens {
	
	private double[][] matGivens;
	private int p,q;
	private double angle;
	
	
	public Givens(int a,int b,int n,double c) {
		this.matGivens = new double[n][n];
		this.p=a;
		this.q=b;
		this.angle=c;
		
		double radians = Math.toRadians(angle);
		
		if(Math.cos(radians) > 1.0E-15){
			this.matGivens[p][p]= Math.cos(radians);
			this.matGivens[q][q]= Math.cos(radians);
		}
		
		else{
			this.matGivens[p][p]= 0;
			this.matGivens[q][q]= 0;
		}
		
		
		if(Math.sin(radians) > 1.0E-15){
			this.matGivens[p][q]= Math.sin(radians);		
			this.matGivens[q][p]= - Math.sin(radians);
		}
		
		else{
			this.matGivens[p][q]= 0;		
			this.matGivens[q][p]= 0;
		}
			
				
								
		for(int i = 0;i<n;i++){
			for(int j = 0;j<n;j++){
				
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
		System.out.println("\n\n");
	}
	        		
	        		
	public double[][] getMatGivens() {
		return matGivens;
	}

	public void setMatGivens(double[][] matGivens) {
		this.matGivens = matGivens;
	}
	
}
