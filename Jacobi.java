package compression;

public class Jacobi extends Operations {
	private double[][] matrice;
	private int taille;
	private double t�ta;
	private double[][] B1 = new double[100][100];
	private double[][] B2 = new double[100][100];
	
	public double[][] getMatrice() {
		return matrice;
	}

	public void setMatrice(double[][] matrice) {
		this.matrice = matrice;
	}
	
	public Jacobi(double[][] B){
		this.B1=produit(B,transposeMatrix(B));
		afficher(this.B1);
		this.B2=produit(transposeMatrix(B),B);
		this.taille = B1.length;
		this.matrice = new double[taille][taille];
		this.matrice = B;
		
		//for(int x=0;x<100;x++){
				
			double tmp = this.B1[0][0];
			int p=0,q=0;

		
			for(int i=1;i<this.taille;i++){
				for(int j=1;j<this.taille;j++){
					if(Math.abs(this.B1[i][j])>tmp && i!=j){
						tmp=B1[i][j];
						p=i;
						q=j;
					}
				}
			}
			
			System.out.println(p + " " + q);
			
			t�ta = polynome(this.B1,p,q);
			
			Givens givens = new Givens(p,q,taille,t�ta);
			
			double[][] temp = new double[taille][taille];
			temp = produitGivensT(givens,this.matrice);
			
			double[][] jacobi = new double[taille][taille];
			jacobi = produitGivens(temp, givens);
					
			this.matrice=jacobi;
			afficher(jacobi);			
		//}
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

	public double[][] getB1() {
		return B1;
	}

	public void setB1(double[][] b1) {
		B1 = b1;
	}

	public double[][] getB2() {
		return B2;
	}

	public void setB2(double[][] b2) {
		B2 = b2;
	}

	public double getT�ta() {
		return t�ta;
	}

	public void setT�ta(double t�ta) {
		this.t�ta = t�ta;
	}
	
}
