package compression;

public class Jacobi {
	private double[][] matrice;
	private int taille;
	
	
	public double[][] getMatrice() {
		return matrice;
	}

	public void setMatrice(double[][] matrice) {
		this.matrice = matrice;
	}
	
	public Jacobi(double[][] B){
		this.taille = B.length;
		this.matrice = new double[taille][taille];				
		double tmp = B[0][0];
		int p=0,q=0;

		
		for(int i=0;i<this.taille;i++){
			for(int j=0;j<this.taille;j++){
				if(Math.abs(B[i][j])>tmp && i!=j && i<=this.taille && j<=this.taille && i>0 && j>0){
					tmp=B[i][j];
					p=i;
					q=j;
				}
			}
		}
			
		System.out.println("\n" + p + " " + q + "\n\n");
		
		Givens givens = new Givens(p,q,taille,90);
		givens.afficher();	
		
		double[][] givensTranspose = Test.transposeMatrix(givens.getMatGivens());
		this.afficher(givensTranspose);
		
		
		double[][] temp = new double[taille][taille];
		temp = Test.produit(givensTranspose,B);
				
		double[][] jacobi = new double[taille][taille];
		jacobi = Test.produit(temp, givens.getMatGivens());
		
		
		this.matrice=jacobi;
		this.afficher(jacobi);			
		
		
		for(int x=0;x<10;x++){
						
			tmp = jacobi[0][0];
			p=0;
			q=0;
		
			for(int i=0;i<this.taille;i++){
				for(int j=0;j<this.taille;j++){
					if(Math.abs(jacobi[i][j])>tmp && i!=j && i<=this.taille && j<=this.taille && i>0 && j>0){
						tmp=jacobi[i][j];
						p=i;
						q=j;
					
					}
				}
			}

			System.out.println("\n" + p + " " + q);
			givens = new Givens(p,q,taille,90);
			givens.afficher();
	
		
			givensTranspose = Test.transposeMatrix(givens.getMatGivens());
			this.afficher(givensTranspose);		
		
			temp = new double[taille][taille];
			temp = Test.produit(givensTranspose,this.matrice);
				
			jacobi = new double[taille][taille];
			jacobi = Test.produit(temp, givens.getMatGivens());
		
		
			this.matrice=jacobi;
			this.afficher(jacobi);
		
		}
	}
	
	
	public void afficher(double[][] aff){
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
	
}
