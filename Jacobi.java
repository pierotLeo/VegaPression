package compression;

public class Jacobi extends Operations {
	private double[][] matrice;
	private int taille_colonne;
	private int taille_ligne;
	private double teta;
	private double[][] B1 = new double[100][100];
	private double[][] B2 = new double[100][100];
	
	private double[][] valP1;
	private double[][] valP2;
	
	private double[][] vectP1;
	private double[][] vectP2;
	
	public double[][] getMatrice() {
		return matrice;
	}

	public void setMatrice(double[][] matrice) {
		this.matrice = matrice;
	}
	
	public Jacobi(double[][] B){
		afficher(B);
		this.B1=B;//produit(B,transposeMatrix(B));	
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
		
		
		this.teta = polynome(this.B1,p,q);
		
		givens = new Givens(p,q,taille_colonne,taille_ligne,teta);
		
		temp = produitGivensToMatrix(givens.getMatGivensT(),this.B1,p,q);
		//temp = produit(givens.getMatGivensT(),this.B1);

		jacobi = produitMatrixToGivens(temp,givens.getMatGivens(),p,q);
		//jacobi = produit(temp,givens.getMatGivens());
		
		vectP1=givens.getMatGivens();
		
		this.B1=jacobi;
				
		while(!this.condArret(this.B1,p,q)){		
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
			
			this.teta = polynome(this.B1,p,q);
			
			givens = new Givens(p,q,taille_colonne,taille_ligne,teta);
			
			//temp = produitGivensToMatrix(givens.getMatGivensT(),this.B1,p,q);
			temp = produit(givens.getMatGivensT(),this.B1);

			//jacobi = produitMatrixToGivens(temp,givens.getMatGivens(),p,q);
			jacobi = produit(temp,givens.getMatGivens());
			
			vectP1=produit(vectP1,givens.getMatGivens());
			
			this.B1=jacobi;
		}
		
		tmp = 0.0;
		
		for(int i=0;i<this.taille_colonne;i++){
			for(int j=0;j<this.taille_ligne;j++){
				if((Math.abs(this.B2[i][j]))>Math.abs(tmp) && i!=j){
					tmp=B2[i][j];
					p=i;
					q=j;
				}
			}
		}
		
		
		this.teta = polynome(this.B2,p,q);
		
		givens = new Givens(p,q,taille_colonne,taille_ligne,teta);
		
		temp = produitGivensToMatrix(givens.getMatGivensT(),this.B2,p,q);
		//temp = produit(givens.getMatGivensT(),this.B2);

		jacobi = produitMatrixToGivens(temp,givens.getMatGivens(),p,q);
		//jacobi = produit(temp,givens.getMatGivens());
		
		vectP1=givens.getMatGivens();
		
		this.B2=jacobi;
				
		while(!this.condArret(this.B2,p,q)){		
			tmp = 0.0;
		
			for(int i=0;i<this.taille_colonne;i++){
				for(int j=0;j<this.taille_ligne;j++){
					if((Math.abs(this.B2[i][j]))>Math.abs(tmp) && i!=j){
						tmp=B2[i][j];
						p=i;
						q=j;
					}
				}
			}			
			
			this.teta = polynome(this.B2,p,q);
			
			givens = new Givens(p,q,taille_colonne,taille_ligne,teta);
			
			temp = produitGivensToMatrix(givens.getMatGivensT(),this.B2,p,q);
			//temp = produit(givens.getMatGivensT(),this.B2);

			jacobi = produitMatrixToGivens(temp,givens.getMatGivens(),p,q);
			//jacobi = produit(temp,givens.getMatGivens());
			
			vectP1=produit(vectP1,givens.getMatGivens());
			
			this.B2=jacobi;
			
		}
		
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
	
	public void valP(){
		double tmp;
		this.valP1 = new double[taille_colonne][taille_ligne];
		this.valP1[0][0]=Math.sqrt(Math.abs(this.B1[0][0]));
		for(int i=1,j=1;i<taille_colonne && j<taille_ligne;i++,j++){
			
			this.valP1[i][j]=Math.sqrt(Math.abs((this.B1[i][j])));
			
			for(int x=i,y=j;x>0 && y>0;x--,y--){
				
				if(this.valP1[x][y]>this.valP1[x-1][y-1]){
					tmp = this.valP1[x-1][x-1];
					this.valP1[x-1][y-1]=this.valP1[x][y];
					this.valP1[x][y]=tmp;
				}	
			}
			
		}	
		
		afficher(this.valP1);
		
		this.valP2 = new double[taille_colonne][taille_ligne];
		this.valP2[0][0]=Math.sqrt(Math.abs(this.B2[0][0]));
		for(int i=1,j=1;i<taille_colonne && j<taille_ligne;i++,j++){
			
			this.valP2[i][j]=Math.sqrt(Math.abs((this.B2[i][j])));
			
			for(int x=i,y=j;x>0 && y>0;x--,y--){
				
				if(this.valP2[x][y]>this.valP2[x-1][y-1]){
					tmp = Math.sqrt(Math.abs((this.valP2[x-1][x-1])));
					this.valP2[x-1][y-1]=this.valP2[x][y];
					this.valP2[x][y]=tmp;
				}						
			}
			this.valP2[i][j]=Math.sqrt(Math.abs((this.valP2[i][j])));
		}	
		
		afficher(this.valP2);
	}
	
	public void vectP(){
		afficher(this.vectP1);
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

	public double[][] getValP1() {
		return valP1;
	}

	public void setValP1(double[][] valP1) {
		this.valP1 = valP1;
	}

	public double[][] getValP2() {
		return valP2;
	}

	public void setValP2(double[][] valP2) {
		this.valP2 = valP2;
	}

	public double[][] getVectP1() {
		return vectP1;
	}

	public void setVectP1(double[][] vectP1) {
		this.vectP1 = vectP1;
	}

	public double[][] getVectP2() {
		return vectP2;
	}

	public void setVectP2(double[][] vectP2) {
		this.vectP2 = vectP2;
	}
	
}
