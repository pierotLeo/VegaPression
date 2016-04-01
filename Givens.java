package compression;

public class Givens extends Operations {
	
	private double[][] matGivens;
	private double[][] matGivensT;
	private int p,q;
	private double angle;
	
	
	public Givens(int a,int b,int n,double c) {
		this.matGivens = new double[n][n];
		this.p=a;
		this.q=b;
		this.angle=c;
		
		if(Math.cos(this.angle) > 1.0E-15){
			this.matGivens[p][p]= Math.cos(this.angle);
			this.matGivens[q][q]= Math.cos(this.angle);
		}
		
		else{
			this.matGivens[p][p]= 0;
			this.matGivens[q][q]= 0;
		}
		
		
		if(Math.sin(this.angle) > 1.0E-15){
			this.matGivens[p][q]= Math.sin(this.angle);		
			this.matGivens[q][p]= - Math.sin(this.angle);
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
		
		this.setMatGivensT(transposeMatrix(this.getMatGivens()));
	}
	
	
	public void afficherGivens(){
		for(int i = 0;i<matGivens.length;i++){
			System.out.println();
			for(int j = 0;j<matGivens.length;j++){
				System.out.print(matGivens[i][j] + " | ");
			}
		}
		System.out.println("\n\n");
	}
	        		
	public void afficherGivensT(){
		for(int i = 0;i<matGivensT.length;i++){
			System.out.println();
			for(int j = 0;j<matGivensT.length;j++){
				System.out.print(matGivensT[i][j] + " | ");
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


	public double[][] getMatGivensT() {
		return matGivensT;
	}


	public void setMatGivensT(double[][] matGivensT) {
		this.matGivensT = matGivensT;
	}


	public int getP() {
		return p;
	}


	public void setP(int p) {
		this.p = p;
	}


	public int getQ() {
		return q;
	}


	public void setQ(int q) {
		this.q = q;
	}


	public double getAngle() {
		return angle;
	}


	public void setAngle(double angle) {
		this.angle = angle;
	}
	
}
