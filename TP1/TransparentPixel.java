/**
 * Classe de pixel transparent
 * @author :
 * @date : 
 */

public class TransparentPixel extends AbstractPixel
{
	public int[] rgba; // donnees de l'image
	
	/**
	 * Constructeur par defaut (pixel blanc)
	 */
	TransparentPixel()
	{
		rgba = new int[4];
		rgba[0] = 255;
		rgba[1] = 255;
		rgba[2] = 255;
		rgba[3] = 255;
	}
	
	/**
	 * Assigne une valeur au pixel
	 * @param rgb: valeurs a assigner 
	 */
	TransparentPixel(int[] rgba)
	{
		// compléter
		this.rgba = new int[4];
		for (int i =0; i < rgba.length; i++){
			this.rgba[i] = rgba[i];	
		}
	}
	
	/**
	 * Renvoie un pixel copie de type noir et blanc
	 */
	public BWPixel toBWPixel()
	{
		// compléter
		boolean bool;
		int moyenne = ((this.rgba[0] + this.rgba[1] + this.rgba[2]) / 3);
		if (moyenne>127)
			bool = true;
		else 
			bool = false;
		BWPixel bw = new BWPixel(bool);
		
	//	BWPixel bw = new BWPixel((moyenne <= 127) ? 0 : 1 );
		return bw;		

	}
	
	/**
	 * Renvoie un pixel copie de type tons de gris
	 */
	public GrayPixel toGrayPixel()
	{
		// compléter
		int moyenne = ((this.rgba[0] + this.rgba[1] + this.rgba[2]) / 3);
		return new GrayPixel(moyenne);
	}
	
	/**
	 * Renvoie un pixel copie de type couleurs
	 */
	public ColorPixel toColorPixel()
	{
		// compléter
		int [] tableau= new int[3];
		tableau[0]= rgba[0];
		tableau[1]= rgba[1];
		tableau[2]= rgba[2];
		ColorPixel cp= new ColorPixel(tableau);
		return cp;
	}
	
	/**
	 * Renvoie le negatif du pixel (255-pixel)
	 */
	public TransparentPixel Negative()
	{
		// compléter
		int[] tableau = new int[4];
		tableau[0]= 255-rgba[0];
		tableau[1]= 255-rgba[1];
		tableau[2]= 255-rgba[2];
		tableau[3]= rgba[3];
		
		TransparentPixel tr= new TransparentPixel(tableau);
		return tr;
		
	}
	
	public TransparentPixel toTransparentPixel()
	{
		// compléter
		return new TransparentPixel(this.rgba);
		
	}
	
	public void setAlpha(int alpha)
	{
		rgba[3] = alpha;
	}
	
	/**
	 * Convertit le pixel en String (sert a ecrire dans un fichier 
	 * (avec un espace supplémentaire en fin)s
	 */
	public String toString()
	{
		return  ((Integer)rgba[0]).toString() + " " + 
				((Integer)rgba[1]).toString() + " " +
				((Integer)rgba[2]).toString() + " " +
				((Integer)rgba[3]).toString() + " ";
	}
}
