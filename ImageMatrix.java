package fr.vegapression.torename;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ImageMatrix implements Constants{
	private byte[] matrix;
	private int height;
	private int width;
	
	public ImageMatrix(byte[] matrix, int height, int width){
		this.matrix = matrix;
		this.height = height;
		this.width = width;
	}
	
	public ImageMatrix(String imgUrl, int grayScaleType){
		this(imgUrl, grayScaleType, MAX_NUMBER_GRAY_SHADES);
	}
	
	public ImageMatrix(String imgUrl, int grayScaleType, int numberGrayShades){
		BufferedImage sourceImage = null;
		try {
			sourceImage = ImageIO.read(new File(imgUrl));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(sourceImage != null){
			height = sourceImage.getHeight();
			width = sourceImage.getWidth();
			matrix = new byte[height*width];
			int color, conversionFactor = 0;
			short  r, g, b, avg = 0, shade = 0;
			
			for(int i = 0; i < matrix.length; i++){
				color = sourceImage.getRGB(i%width, i/width);
				r = (short)((color & 0xFF0000) >> 16);
				g = (short)((color & 0xFF00) >> 8);
				b = (short)(color & 0xFF);
				
				if(grayScaleType == AVERAGE || grayScaleType == CUSTOM_NUMBER_GRAY_SHADES){
					avg = (short)((r + g + b) / 3);
					if(grayScaleType == CUSTOM_NUMBER_GRAY_SHADES){
						conversionFactor = (numberGrayShades <= MAX_NUMBER_GRAY_SHADES && numberGrayShades >= MIN_NUMBER_GRAY_SHADES)? (255 / (numberGrayShades-1)) : 1;	
						shade = (short)(avg/conversionFactor);
						shade = (avg > (shade + 0.5f)*conversionFactor)? (short)((shade+1)*conversionFactor) : (short)(shade*conversionFactor);
					}
				}
				
				switch(grayScaleType){
					case CUSTOM_NUMBER_GRAY_SHADES:
						matrix[i] = (byte)(shade);
						break;
					case LUMA_BT709:
						matrix[i] = (byte)( 0.2126f*r + 0.7152f*g + 0.0722f*b);
						break;
					case LUMA_BT601:
						matrix[i] = (byte)(( 0.3f*r + 0.59f*g + 0.11f*b));
						break;
					case AVERAGE:
						matrix[i] = (byte)(avg);
						break;
				}
				
			}
		}
	}
	
	public byte[] stupidCompression(int factor){
		int suppressedRows = height / factor * width;
		int suppressedCols = width / factor * (height - (height / factor));
		
		byte[] compressed = new byte[matrix.length - (suppressedRows + suppressedCols)];
		int j = 0;
		
		for(int i = 0; i < matrix.length; i++){
			if((i - ((i / width) * width) + 1) % factor != 0 && ((i / width) + 1) % factor != 0){
				compressed[j] = matrix[i];
				j++;
			}
		}
		
		return compressed;
	}
	
	public void setMatrix(byte[] matrix){
		this.matrix = matrix;
	}
	
	public BufferedImage getImage(){
		BufferedImage grayScale = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
		
		for(int i=0; i<matrix.length; i++){
			int grayLevel = matrix[i] & 0xFF;
			grayScale.setRGB(i%width, i/width, grayLevel << 16 | grayLevel << 8 | grayLevel);
		}
		
		return grayScale;
	}

	public byte[] givenMatrix(int p, int q, int angle){
		byte[] givenMatrix = new byte[p*q];
		
		givenMatrix[(p - 1) * (q + 1)] = (byte)Math.cos(angle);
		givenMatrix[q * q - 1] = (byte)Math.cos(angle);
		givenMatrix[q * p - 1] = (byte)Math.sin(angle);
		givenMatrix[(q - 1) * q + p] = (byte)(-Math.sin(angle));
		
		//for(int i=0; i<m, )
		return givenMatrix;
	}
	
	public static void main(String[] args){
		byte[] lel = new byte[32];
		for(byte i=0; i<lel.length; i++){
			lel[i] = i;
		}
		ImageMatrix piouPiou = new ImageMatrix(lel, 4, 8);
		byte[] compressedPiouPiou = piouPiou.stupidCompression(4);
		for(byte j : compressedPiouPiou){
			System.out.println(j);
		}
	}
}
