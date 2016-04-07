package compression;

public class Jacobi extends Operations {
	private double[][] matrice;
	private int taille_colonne;
	private int taille_ligne;
	private double teta;
	private double[][] B1 = new double[100][100];
	private double[][] B2 = new double[100][100];
	
	public double[][] getMatrice() {
		return matrice;
	}

	public void setMatrice(double[][] matrice) {
		this.matrice = matrice;
	}
	
	public Jacobi(double[][] B){
		afficher(B);
		//afficher(transposeMatrix(B));
		this.B1=produit(B,transposeMatrix(B));
		afficher(this.B1);
		
		this.B2=produit(transposeMatrix(B),B);
		afficher(this.B2);
		
		this.taille_colonne = B1.length;
		this.taille_ligne = B1[0].length;
		this.matrice = new double[taille_colonne][taille_ligne];
		this.matrice = B;
		
		int p=0,q=0;
		double tmp;
		Givens givens;
		double[][] temp = new double[taille_colonne][taille_ligne];
		double[][] jacobi =  new double[taille_colonne][taille_ligne];
		
		
		do{		
		//for(int x =0;x<17;x++){
			tmp = 0.0;
		
			for(int i=0;i<this.taille_colonne;i++){
				for(int j=0;j<this.taille_ligne;j++){
					if((Math.abs(this.B1[i][j]))>Math.abs(tmp) && i!=j){
						tmp=B1[i][j];
						p=i;
						q=j;
					}
				}
			}
			
			//System.out.println(p + " " + q + " = " + tmp);
			
			
			this.teta = polynome(this.B1,p,q);
			
			givens = new Givens(p,q,taille_colonne,taille_ligne,teta);
			//afficher(givens.getMatGivens());
			//afficher(givens.getMatGivensT());
			
			//temp = produitGivensToMatrix(givens.getMatGivensT(),this.B1,p,q);
			temp = produit(givens.getMatGivensT(),this.B1);

			//jacobi = produitMatrixToGivens(temp,givens.getMatGivens(),p,q);
			jacobi = produit(temp,givens.getMatGivens());
			
			this.B1=jacobi;
			//this.B1[p][q]=0;
			//afficher(this.B1);	
		//}
		}while(!this.condArret(this.B1,p,q));
		
		do{
			
			tmp = 0.0;
			
			for(int i=0;i<this.taille_colonne;i++){
				for(int j=0;j<this.taille_ligne;j++){
					if(Math.abs(this.B2[i][j])>Math.abs(tmp) && i!=j){
						tmp=B2[i][j];
						p=i;
						q=j;
					}
				}
			}
			
			//System.out.println(p + " " + q + " = " + tmp);
			
			this.teta = polynome(this.B2,p,q);
			
			givens = new Givens(p,q,taille_colonne,taille_ligne,teta);
			
			//temp = produitGivensToMatrix(givens.getMatGivensT(),this.B2,p,q);
			temp = produit(givens.getMatGivensT(),this.B2);
			
			//jacobi = produitMatrixToGivens(temp,givens.getMatGivens(),p,q);
			jacobi = produit(temp,givens.getMatGivens());
			
			this.B2=jacobi;

		}while(!this.condArret(this.B2,p,q));
		
		afficher(this.B1);
		afficher(this.B2);
	}

	public boolean condArret(double[][] matrice,int p, int q){
		for(int i = 0;i<this.taille_colonne;i++){
			for(int j=0;j<this.taille_ligne;j++){
				if(Math.abs(matrice[i][j])>1E-8 && i!=j)
					return false;
			}
		}
		return true;
	}
	
	public int getTaille() {
		return taille_ligne;
	}

	public void setTaille(int taille) {
		this.taille_ligne = taille;
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

	public double getTeta() {
		return teta;
	}

	public void setTéta(double teta) {
		this.teta = teta;
	}

	public int getTaille_colonne() {
		return taille_colonne;
	}

	public void setTaille_colonne(int taille_colonne) {
		this.taille_colonne = taille_colonne;
	}

	public int getTaille_ligne() {
		return taille_ligne;
	}

	public void setTaille_ligne(int taille_ligne) {
		this.taille_ligne = taille_ligne;
	}

	public void setTeta(double teta) {
		this.teta = teta;
	}
	
}
