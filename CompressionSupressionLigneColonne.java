package compression;


public class CompressionSupressionLigneColonne implements AlgoCompression{
	
	private double[][] matrice;
	CompressionSupressionLigneColonne(double[][] ds){
		this.matrice = ds;
	}
	
	public double[][] execute(int tauxCompression) {
		int hauteur = this.matrice.length - (this.matrice.length/tauxCompression);
		int largeur = this.matrice[0].length - (this.matrice[0].length/tauxCompression);
		double[][] nouvelleMatrice = new double[hauteur][largeur];
		int in=0;
		for(int i=0;i<this.matrice.length;i++)
		{
			int jn=0;
			for(int j=0;j<this.matrice[0].length;j++)
			{
				if(!(j%tauxCompression==0))
				{
					nouvelleMatrice[in][jn] = this.matrice[i][j];
					jn++;
				}
			}
			if(!(i%tauxCompression==0))
				in++;
		}
		return nouvelleMatrice;
	}
}

