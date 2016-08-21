
import java.awt.Canvas;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
	
	
public class Steg {
	


		/**
		 * @param args
		 * @throws IOException 
		 */
		public static void main(String[] args) throws IOException {
			Steg stegano = new Steg();
			//stegano.encrypt("taipei.jpg", "castle.jpg", "saved.bmp");
			stegano.decrypt("picsou.png","decode");
			System.out.println("OK");
		}
			public void decrypt(String source,String output) throws IOException{
				
				//File outputfile = new File(output);
				int kg,kb,kr;
				for(kg=0;kg<8;kg++){
					for(kb=0;kb<8;kb++){
						for(kr=0;kr<8;kr++){
							BufferedImage masource= ImageIO.read(new File(source));
							BufferedImage rendu = masource;
					String out=output+kg+kb+kr+".bmp";
					File outputfile= new File(out);
				int i,j;
				int a,r,g,b;
				int pixel,newpixel;
				for(j=0;j<masource.getHeight();j++){
					for(i=0;i<masource.getWidth();i++){
						pixel = masource.getRGB(i, j);
						b = pixel & 0xff;
						g = (pixel & 0xff00) >> 8;
						r = (pixel & 0xff0000) >> 16;					
						a = (pixel & 0xff000000) >>> 24;
						
						b = b << kb;
						g = g << kg;
						r = r << kr;
						
						newpixel = a*256*256*256 + r*256*256 + g*256 + b;
						rendu.setRGB(i,j, newpixel);						
						
					}
				}
				ImageIO.write(rendu, "bmp", outputfile);
				}
				}
				}
				
			}
		
			
			
			public void encrypt(String porteuse,String cachee,String output) throws IOException{
			BufferedImage monImage = ImageIO.read(new File(porteuse)); //303 * 257
			BufferedImage moncache = ImageIO.read(new File(cachee));//225 * 225
			BufferedImage finalImage = monImage;
			File outputfile = new File(output);

			
			int i,j;
			int r1,g1,b1,alpha1; //le pinguoin
			int r2,g2,b2; // la girafe
			int r3,g3,b3; // l'image finale
			int penguin,newpenguin;
			int girafe;
			
			//on cache l'image plus petite dans la plus grande
			for(j=0;j<monImage.getHeight();j++){ // H 225 W 225 girafe
				for(i=0;i<monImage.getWidth();i++){ //H 303 W 257 j hauteur i largeur penguin
					if(j<moncache.getHeight() && i <moncache.getWidth()){
						penguin = monImage.getRGB(i,j);
						girafe  = moncache.getRGB(i,j);
						
						//on recupere le pinguoin
						b1 = penguin & 0xff;
						g1 = (penguin & 0xff00) >> 8;
						r1 = (penguin & 0xff0000) >> 16;					
						alpha1 = (penguin & 0xff000000) >>> 24;
						//on recupere la girafe
						b2 = girafe & 0xff;
						g2 = (girafe & 0xff00) >> 8;
						r2 = (girafe & 0xff0000) >> 16;					
						//alpha2 = (girafe & 0xff000000) >>> 24;
						//System.out.println(alpha);
						b1 = b1 >> 4;
						b1 = b1 << 4;
						b2 = b2 >> 4;
						b3 = b2 + b1;
						r1 = r1 >> 4;
						r1 = r1 << 4;
						r2 = r2 >> 4;
						r3 = r2 + r1;
						g1 = g1 >> 4;
						g1 = g1 << 4;
						g2 = g2 >> 4;
						g3 = g2 + g1;
					
					} else {
						penguin = monImage.getRGB(i,j);
						girafe  = moncache.getRGB(moncache.getWidth()-1,moncache.getHeight()-1);
						
						//on recupere le pinguoin
						b1 = penguin & 0xff;
						g1 = (penguin & 0xff00) >> 8;
						r1 = (penguin & 0xff0000) >> 16;					
						alpha1 = (penguin & 0xff000000) >>> 24;
						//on recupere la girafe
						b2 = girafe & 0xff;
						g2 = (girafe & 0xff00) >> 8;
						r2 = (girafe & 0xff0000) >> 16;					
						//alpha2 = (girafe & 0xff000000) >>> 24;
						//System.out.println(alpha);
						b1 = b1 >> 4;
						b1 = b1 << 4;
						b2 = b2 >> 4;
						b3 = b1 +b2;
						r1 = r1 >> 4;
						r1 = r1 << 4;
						r2 = r2 >> 4;
						r3 = r1 + r2;
						g1 = g1 >> 4;
						g1 = g1 << 4;
						g2 = g2 >> 4;
						g3 = g1 + g2;
					}
					//r <<= 8;
					//r += g;
					newpenguin = alpha1*256*256*256 + r3*256*256 + g3*256 + b3;
					finalImage.setRGB(i,j, newpenguin);
					
				//	System.out.println(r);
					
					
				}
			}
			System.out.println("for ok");
		
			
			ImageIO.write(finalImage, "bmp", outputfile);
			
			
			}
		


}



   
 



