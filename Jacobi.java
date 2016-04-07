package compression;

public class Jacobi extends Operations {
	private double[][] matrice;
	private int taille_B1;
	private int taille_B2;
	private double teta;
	private double[][] B1 = new double[100][100];
	private double[][] B2 = new double[100][100];
	
	private double[][] valP1;
	private double[][] valP2;
	
	private double[][] vectP1;
	private double[][] vectP2;
	
	private double[][] sigma;
	
	public double[][] getMatrice() {
		return matrice;
	}

	public void setMatrice(double[][] matrice) {
		this.matrice = matrice;
	}
	
	public Jacobi(double[][] B){
		afficher(B);
		this.B1=produit(B,transposeMatrix(B));	
		afficher(this.B1);
		this.B2=produit(transposeMatrix(B),B);
		afficher(this.B2);
		
		this.taille_B1 = B1.length;
		this.taille_B2 = B2.length;
		this.matrice = new double[taille_B1][taille_B2];
		this.matrice = B;
		
		int p=0,q=0;
		double tmp;
		Givens givens;
		double[][] tempB1 = new double[taille_B1][taille_B1];
		double[][] jacobiB1 =  new double[taille_B1][taille_B1];
		
		tmp = 0.0;
		
		for(int i=0;i<this.taille_B1;i++){
			for(int j=0;j<this.taille_B1;j++){
				if((Math.abs(this.B1[i][j]))>Math.abs(tmp) && i!=j){
					tmp=B1[i][j];
					p=i;
					q=j;
				}
			}
		}
		
		
		this.teta = polynome(this.B1,p,q);
		
		givens = new Givens(p,q,taille_B1,taille_B1,teta);
		
		tempB1 = produitGivensToMatrix(givens.getMatGivensT(),this.B1,p,q);
		//temp = produit(givens.getMatGivensT(),this.B1);

		jacobiB1 = produitMatrixToGivens(tempB1,givens.getMatGivens(),p,q);
		//jacobi = produit(temp,givens.getMatGivens());
		
		vectP1=givens.getMatGivens();
		
		this.B1=jacobiB1;
				
		while(!this.condArretB1(this.B1,p,q)){		
			tmp = 0.0;
		
			for(int i=0;i<this.taille_B1;i++){
				for(int j=0;j<this.taille_B1;j++){
					if((Math.abs(this.B1[i][j]))>Math.abs(tmp) && i!=j){
						tmp=B1[i][j];
						p=i;
						q=j;
					}
				}
			}			
			
			this.teta = polynome(this.B1,p,q);
			
			givens = new Givens(p,q,taille_B1,taille_B1,teta);
			
			//temp = produitGivensToMatrix(givens.getMatGivensT(),this.B1,p,q);
			tempB1 = produit(givens.getMatGivensT(),this.B1);

			//jacobi = produitMatrixToGivens(temp,givens.getMatGivens(),p,q);
			jacobiB1 = produit(tempB1,givens.getMatGivens());
			
			vectP1=produitMatrixToGivens(vectP1,givens.getMatGivens(),p,q);
			
			this.B1=jacobiB1;

		}
		
		tmp = 0.0;
		double[][] tempB2 = new double[taille_B2][taille_B2];
		double[][] jacobiB2 =  new double[taille_B2][taille_B2];
		
		for(int i=0;i<this.taille_B2;i++){
			for(int j=0;j<this.taille_B2;j++){
				if((Math.abs(this.B2[i][j]))>Math.abs(tmp) && i!=j){
					tmp=B2[i][j];
					p=i;
					q=j;
				}
			}
		}
		
		
		this.teta = polynome(this.B2,p,q);
		
		givens = new Givens(p,q,taille_B2,taille_B2,teta);
		
		tempB2 = produitGivensToMatrix(givens.getMatGivensT(),this.B2,p,q);
		//temp = produit(givens.getMatGivensT(),this.B2);

		jacobiB2 = produitMatrixToGivens(tempB2,givens.getMatGivens(),p,q);
		//jacobi = produit(temp,givens.getMatGivens());
		
		vectP2=givens.getMatGivens();
		
		this.B2=jacobiB2;
				
		while(!this.condArretB2(this.B2,p,q)){		
			tmp = 0.0;
		
			for(int i=0;i<this.taille_B2;i++){
				for(int j=0;j<this.taille_B2;j++){
					if((Math.abs(this.B2[i][j]))>Math.abs(tmp) && i!=j){
						tmp=B2[i][j];
						p=i;
						q=j;
					}
				}
			}			
			
			this.teta = polynome(this.B2,p,q);
			
			givens = new Givens(p,q,taille_B2,taille_B2,teta);
			
			tempB2 = produitGivensToMatrix(givens.getMatGivensT(),this.B2,p,q);
			//temp = produit(givens.getMatGivensT(),this.B2);

			jacobiB2 = produitMatrixToGivens(tempB2,givens.getMatGivens(),p,q);
			//jacobi = produit(temp,givens.getMatGivens());
			
			vectP2=produitMatrixToGivens(vectP2,givens.getMatGivens(),p,q);
			
			this.B2=jacobiB2;
			
		}
		
		System.out.println("B1 fini :");
		afficher(this.B1);
		System.out.println("B2 fini :");
		afficher(this.B2);
	}

	public boolean condArretB1(double[][] matrice,int p, int q){
		for(int i = 0;i<this.taille_B1;i++){
			for(int j=0;j<this.taille_B1;j++){
				if(Math.abs(matrice[i][j])>1E-8 && i!=j)
					return false;
			}
		}
		return true;
	}
	
	public boolean condArretB2(double[][] matrice,int p, int q){
		for(int i = 0;i<this.taille_B2;i++){
			for(int j=0;j<this.taille_B2;j++){
				if(Math.abs(matrice[i][j])>1E-8 && i!=j)
					return false;
			}
		}
		return true;
	}
	
	public void valP(){
		double tmp;
		double tmp2;
		this.valP1 = new double[taille_B1][taille_B1];
		this.valP1[0][0]=Math.sqrt(this.B1[0][0]);
		for(int i=1,j=1;i<taille_B1 && j<taille_B1;i++,j++){
			
			this.valP1[i][j]=Math.sqrt((this.B1[i][j]));
			
			for(int x=i,y=j;x>0 && y>0;x--,y--){
				
				if(this.valP1[x][y]>this.valP1[x-1][y-1]){
					tmp = this.valP1[x-1][x-1];
					this.valP1[x-1][y-1]=this.valP1[x][y];
					this.valP1[x][y]=tmp;
					
					for(int a=0;a<this.vectP1.length;a++){
						tmp2 = this.vectP1[a][x];
						this.vectP1[a][x] = this.vectP1[a][x-1];
						this.vectP1[a][x-1] = tmp2;
					}
				}
			}
		}
			
		
		this.valP2 = new double[taille_B2][taille_B2];
		this.valP2[0][0]=Math.sqrt(this.B2[0][0]);
		for(int i=1,j=1;i<taille_B2 && j<taille_B2;i++,j++){
			
			this.valP2[i][j]=Math.sqrt((this.B2[i][j]));
			
			for(int x=i,y=j;x>0 && y>0;x--,y--){
				
				if(this.valP2[x][y]>this.valP2[x-1][y-1]){
					tmp = this.valP2[x-1][x-1];
					this.valP2[x-1][y-1]=this.valP2[x][y];
					this.valP2[x][y]=tmp;
					
					for(int a=0;a<this.vectP2.length;a++){
						tmp2 = this.vectP2[a][x];
						this.vectP2[a][x] = this.vectP2[a][x-1];
						this.vectP2[a][x-1] = tmp2;
					}
				}				
			}
		}
		
		afficher(this.valP2);
		
		int m;
		if(this.taille_B1<this.taille_B2){
			m=this.taille_B1;
			this.sigma = new double[this.taille_B1][this.taille_B1];
			
			for(int i=0;i<m;i++){
				for(int j=0;j<m;j++){
					this.sigma[i][j]=this.valP1[i][j];
				}
			}
		}
		
		else{
			m=this.taille_B2;
			this.sigma = new double[this.taille_B2][this.taille_B2];
			
			for(int i=0;i<m;i++){
				for(int j=0;j<m;j++){
					this.sigma[i][j]=this.valP2[i][j];
				}
			}
		}
		
		
		afficher(sigma);
	}
	
	
	public void vectP(){
		afficher(this.vectP1);
		afficher(this.vectP2);
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

	public int getTaille_B1() {
		return taille_B1;
	}

	public void setTaille_B1(int taille_B1) {
		this.taille_B1 = taille_B1;
	}

	public int getTaille_B2() {
		return taille_B2;
	}

	public void setTaille_B2(int taille_B2) {
		this.taille_B2 = taille_B2;
	}

	public double[][] getSigma() {
		return sigma;
	}

	public void setSigma(double[][] sigma) {
		this.sigma = sigma;
	}
	
}
