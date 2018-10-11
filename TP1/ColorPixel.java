/**
 * Classe de pixel en couleurs 
 * @author :
 * @date : 
 */

public class ColorPixel extends AbstractPixel
{
	public int[] rgb; // donnees de l'image
	
	/**
	 * Constructeur par defaut (pixel blanc)
	 */
	ColorPixel()
	{
		rgb = new int[3];
		rgb[0] = 255;
		rgb[1] = 255;
		rgb[2] = 255;
	}
	
	/**
	 * Assigne une valeur au pixel
	 * @param rgb: valeurs a assigner 
	 */
	ColorPixel(int[] rgb)
	{
		// compléter
		this.rgb = new int[3];
		for (int i = 0; i < rgb.length; i++){ 	
			this.rgb[i] = rgb[i];
		}
	}
	
	/**
	 * Renvoie un pixel copie de type noir et blanc
	 */
	public BWPixel toBWPixel()
	{
		// compléter
		int moyenne = ((this.rgb[0] + this.rgb[1] + this.rgb[2]) / 3);
		boolean bool;
		if (moyenne>127) {
			bool = true;
		}
		else {
			bool = false;
		}
		BWPixel bw = new BWPixel(bool );
		return bw;	
	}
	
	/**
	 * Renvoie un pixel copie de type tons de gris
	 */
	public GrayPixel toGrayPixel()
	{
		// compléter
		int moyenne = ((this.rgb[0] + this.rgb[1] + this.rgb[2]) / 3);
		//int moyenne=2;
		return new GrayPixel(moyenne);
	}
	
	/**
	 * Renvoie un pixel copie de type couleurs
	 */
	public ColorPixel toColorPixel()
	{
		// compléter
		return new ColorPixel(this.rgb);	
	}
	
	public TransparentPixel toTransparentPixel()
	{
		// compléter
		int []tableau = new int[4];
		tableau[0]= rgb[0];
		tableau[1]= rgb[1];
		tableau[2]= rgb[2];
		
		tableau[3]= 255;
		TransparentPixel cp = new TransparentPixel(tableau);	
		return cp;
		
		
	}
	
	/**
	 * Renvoie le negatif du pixel (255-pixel)
	 */
	public AbstractPixel Negative()
	{
		// compléter
		int [] tableau = new int[3];
		for (int i=0;i < tableau.length;i++){
			tableau[i] = (255 - this.rgb[i]);
		}
		return new ColorPixel(tableau);
	}
	
	public void setAlpha(int alpha)
	{
		//ne fait rien
	}
	
	/**
	 * Convertit le pixel en String (sert a ecrire dans un fichier 
	 * (avec un espace supplémentaire en fin)s
	 */
	public String toString()
	{
		return  ((Integer)rgb[0]).toString() + " " + 
				((Integer)rgb[1]).toString() + " " +
				((Integer)rgb[2]).toString() + " ";
	}
}
