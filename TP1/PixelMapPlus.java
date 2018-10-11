import java.awt.PageAttributes.ColorType;

/**
 * Classe PixelMapPlus
 * Image de type noir et blanc, tons de gris ou couleurs
 * Peut lire et ecrire des fichiers PNM
 * Implemente les methodes de ImageOperations
 * @author : bassam ajam, george louis
 * @date   : 

 */

public class PixelMapPlus extends PixelMap implements ImageOperations 
{
	/**
	 * Constructeur creant l'image a partir d'un fichier
	 * @param fileName : Nom du fichier image
	 */
	PixelMapPlus(String fileName)
	{
		super( fileName );
	}
	
	/**
	 * Constructeur copie
	 * @param type : type de l'image a creer (BW/Gray/Color)
	 * @param image : source
	 */
	PixelMapPlus(PixelMap image)
	{
		super(image); 
	}
	
	/**
	 * Constructeur copie (sert a changer de format)
	 * @param type : type de l'image a creer (BW/Gray/Color)
	 * @param image : source
	 */
	PixelMapPlus(ImageType type, PixelMap image)
	{
		super(type, image); 
	}
	
	/**
	 * Constructeur servant a allouer la memoire de l'image
	 * @param type : type d'image (BW/Gray/Color)
	 * @param h : hauteur (height) de l'image 
	 * @param w : largeur (width) de l'image
	 */
	PixelMapPlus(ImageType type, int h, int w)
	{
		super(type, h, w);
	}
	
	/**
	 * Genere le negatif d'une image
	 */
	public void negate()
	{	
		for(int i=0 ; i < height; i++) {	//lignes
			for(int j=0; j < width; j++) {	//colonnes	
				imageData[i][j] = this.imageData[i][j].Negative();
			}
		}
	}
	
	/**
	 * Convertit l'image vers une image en noir et blanc
	 */
	public void convertToBWImage()
	{
		// compléter	
	
		AbstractPixel[][] NImage =  new BWPixel[height][width];
		for(int i=0 ; i < height; i++) {	//lignes
			for(int j=0; j < width; j++) {	//colonnes	
				 NImage[i][j]= this.imageData[i][j].toBWPixel();				
			}
		}
		imageData=NImage;
		imageType=ImageType.BW;	
	}
	
	/**
	 * Convertit l'image vers un format de tons de gris
	 */
	public void convertToGrayImage()
	{
		AbstractPixel[][] NImage =  new GrayPixel[height][width];
		for(int i=0 ; i < height; i++) {	//lignes
			for(int j=0; j < width; j++) {	//colonnes	
				 NImage[i][j]= this.imageData[i][j].toGrayPixel();	// nzid l this
			}
		}
		imageData=NImage;
		imageType=ImageType.Gray;
	}
	
	/**
	 * Convertit l'image vers une image en couleurs
	 */
	public void convertToColorImage()
	{
		
		AbstractPixel NImage[][]=new ColorPixel[height][width];
		for(int i=0;i<height;i++) {
			for(int j=0;j<width;j++) {
				NImage[i][j]= this.imageData[i][j].toColorPixel();
				
			}
		}
	imageData=NImage;
	imageType=imageType.Color;	
	}
	
	public void convertToTransparentImage()
	{
		for(int i=0 ; i < height; i++) {	//lignes
			for(int j=0; j < width; j++) {	//colonnes	
				imageData[i][j] = this.imageData[i][j].toTransparentPixel();			
			}
		}
		imageType=imageType.Transparent;
	}
	
	/**
	 * Fait pivoter l'image de 10 degres autour du pixel (row,col)=(0, 0)
	 * dans le sens des aiguilles d'une montre (clockWise == true)
	 * ou dans le sens inverse des aiguilles d'une montre (clockWise == false).
	 * Les pixels vides sont blancs.
	 * @param clockWise : Direction de la rotation 
	 */
	public void rotate(int x, int y, double angleRadian)
	{
		// compléter
		int[][]tabPos= new int [3][height*width];	
	
		AbstractPixel[][] NImage= new ColorPixel[height][width];	// declaration d'une nouvelle image temporaire
		// Spécification de la type de la nouvelle image et la remplir par pixels blanc selon sa type.
		switch(imageType){
		case BW: 
			NImage = new BWPixel[height][width];
			for(int i=0;i<height;i++) {
				for(int j=0;j<width;j++) {
					NImage[i][j]=new BWPixel();
				}	
			}
			break;
		case Gray: 
			NImage=new GrayPixel[height][width];
			for(int i=0;i<height;i++) {
				for(int j=0;j<width;j++) {
					NImage[i][j]=new GrayPixel();
				}
			}
			break;
		case Color: 
			NImage=new ColorPixel[height][width];
			for(int i=0;i<height;i++) {
				for(int j=0;j<width;j++) {
					NImage[i][j]=new ColorPixel();
				}
				
			}
			break;
		case Transparent: 
			NImage=new TransparentPixel[height][width];
			for(int i=0;i<height;i++) {
				for(int j=0;j<width;j++) {
					NImage[i][j]=new TransparentPixel();
				}
				
			}
			break;
		
		}
		
		int nbPixel=0;
		for(int i=0;i<height;i++) {
			for(int j=0;j<width;j++) {
				tabPos[0][nbPixel]=(int)(((Math.cos(angleRadian)*i))+(Math.sin(angleRadian)*j)+((-(Math.cos(angleRadian)*x))-(+(Math.sin(angleRadian)*y))+x));
				tabPos[1][nbPixel]=(int)((-(Math.sin(angleRadian)*i))+((Math.cos(angleRadian)*j))+(((Math.sin(angleRadian)*x))-(Math.cos(angleRadian)*y)+y));
				tabPos[2][nbPixel]=1;
				nbPixel++;
			}
		}
		
		nbPixel=0;
		for(int i=0;i<height;i++) {
			for(int j=0;j<width;j++) {
				int tempo1=tabPos[0][nbPixel];
				int tempo2=tabPos[1][nbPixel];
				if(tempo1<height &&tempo1>=0 && tempo2>=0 && tempo2<width) {
					NImage[i][j]=imageData[tempo1][tempo2];
				}
				nbPixel++;
			}
		}
		imageData=NImage;
	}
	
	/**
	 * Modifie la longueur et la largeur de l'image 
	 * @param w : nouvelle largeur
	 * @param h : nouvelle hauteur
	 */
	public void resize(int w, int h) throws IllegalArgumentException
	{
		if(w < 0 || h < 0)
			throw new IllegalArgumentException();
			
		else {	
			AbstractPixel[][] NImage =null; // declaration d'une nouvelle image temporaire
			// Spécification de la type de la nouvelle image et la remplir par pixels blanc selon sa type.
			switch (imageType) {
			case Color:
				NImage = new ColorPixel[height][width];
				for(int i=0 ; i < height; i++) {	//lignes
					for(int j=0; j < width; j++) {	//colonnes	
						NImage[i][j] = imageData[i][j];
					}
				}
				break;
				case BW: 
				NImage = new BWPixel[height][width];
				for(int i=0 ; i < height; i++) {	//lignes
					for(int j=0; j < width; j++) {	//colonnes	
						NImage[i][j] = imageData[i][j];
					}
				}
				break;
				case Gray:
					NImage = new GrayPixel[height][width];
					for(int i=0 ; i < height; i++) {	//lignes
						for(int j=0; j < width; j++) {	//colonnes	
							NImage[i][j] = imageData[i][j];
						}
					}
					break;
				case Transparent:
					NImage = new TransparentPixel[height][width];
					for(int i=0 ; i < height; i++) {	//lignes
						for(int j=0; j < width; j++) {	//colonnes	
							NImage[i][j] = imageData[i][j];
						}
					}
					break;
			}
			
		// les pixels qu'on va skipper
		int Skiph = height /h;	 
		int Skipw = width /w;
		// Préciser la position de la nouvelle image
		int IdeNImage = 0; 
		int IdeimageData = 0;
		while (IdeimageData < height && IdeNImage < h  ) {
				int JdeNImage = 0;
				int JdeimageData = 0;
					while (JdeimageData < width && JdeNImage < w ) {
						NImage[IdeNImage][JdeNImage] = imageData[IdeimageData][JdeimageData];
						JdeNImage++;
						JdeimageData += Skipw;
					}
					IdeNImage++;
					IdeimageData+= Skiph;;
			}
			this.clearData();
			height = h;
			width = w;
			this.imageData = NImage;
		}
		
		
	}
	
	/**
	 * Insert pm dans l'image a la position row0 col0
	 */
	public void inset(PixelMap pm, int row0, int col0)
	{			
		// compléter
		for(int i=row0; (i-row0<pm.height&& i<height) ; i++) {
			for(int j=col0; (j-col0<pm.width && j<width) ; j++) {
				AbstractPixel tab= pm.imageData[i-row0][j-col0].toColorPixel();
				this.imageData[i][j]=tab;
			}
		}
	}
	
	/**
	 * Decoupe l'image 
	 */
	public void crop(int h, int w)
	{
		// compléter
		AbstractPixel[][] NImage =new ColorPixel[h][w]; // declaration d'une nouvelle image temporaire
		// Spécification de la type de la nouvelle image et la remplir par pixels blanc selon sa type.
		switch (imageType) {
			case Color:
				for(int i=0 ; i < h; i++) {	//lignes
					for(int j=0; j < w; j++) {	//colonnes	
						NImage[i][j] = new ColorPixel();
					}
				}
				break;
				case BW: 
				for(int i=0 ; i < h; i++) {	//lignes
					for(int j=0; j < w; j++) {	//colonnes	
						NImage[i][j] = new BWPixel();
					}
				}
				break;
				case Gray:
					for(int i=0 ; i < h; i++) {	//lignes
						for(int j=0; j < w; j++) {	//colonnes	
						NImage[i][j] = new GrayPixel();
						}
					}
					break;
				case Transparent:
					for(int i=0 ; i < h; i++) {	//lignes
						for(int j=0; j < w; j++) {	//colonnes	
						NImage[i][j] = new TransparentPixel();
						}
					}
					break;
		}
		
		int a = 0;
		int b = 0;
		// Si la hauteur qu'on a spécifié pour couper l'image est plus grand que la hauteur de l'image elle meme,
		// on crée un variable qui est égale à la hauteur de l'image originale pour éviter de couper l'image dans la vide, parce que sinon ca  va nous afficher un erreur nullPointer. 
		// La meme idée pour la largeur.
		if (height < h) {
			a = height;
		}
		else {
			a = h;
		}
		if (width < w) {
			b = width;
		}
		else {
			b = w;
		}
		for (int i =0; i < a; i++) {
			for (int j = 0; j < b; j++) {
				
					NImage[i][j] = imageData[i][j];	
			}
		}
			this.clearData();
			height= h;
			width = w;
			this.imageData = NImage;
		}
	
	/**
	 * Effectue une translation de l'image 
	 */
	public void translate(int rowOffset, int colOffset)
	{
		// compléter
		AbstractPixel[][] NImage =new ColorPixel[height][width]; // declaration d'une nouvelle image temporaire
		// Spécification de la type de la nouvelle image et la remplir par pixels blanc selon sa type.
		switch (imageType) {
			case Color:
				for(int i=0 ; i < height; i++) {	//lignes
					for(int j=0; j < width; j++) {	//colonnes	
						NImage[i][j] = new ColorPixel();
					}
				}
				break;
				case BW: 
				for(int i=0 ; i < height; i++) {	//lignes
					for(int j=0; j < width; j++) {	//colonnes	
						NImage[i][j] = new BWPixel();
					}
				}
				break;
				case Gray:
					for(int i=0 ; i < height; i++) {	//lignes
						for(int j=0; j < width; j++) {	//colonnes	
						NImage[i][j] = new GrayPixel();
						}
					}
					break;
				case Transparent:
					for(int i=0 ; i < height; i++) {	//lignes
						for(int j=0; j < width; j++) {	//colonnes	
						NImage[i][j] = new TransparentPixel();
						}
					}
					break;
		}
		
		for(int i=0 ; i < height; i++) {	//lignes
			int I=i+rowOffset;	// La position initiale I de la nouvelle partie qu'on veut decaler
			for(int j=0; j < width; j++) {	//colonnes	
				int J=j+colOffset; // La position initiale J de la nouvelle partie qu'on veut decaler
				//Pour savoir quelle partie on va decaler. (partie (+ , +) ou (- , -) ou (+ , -) ou (- , +)
				if(rowOffset > 0 && colOffset > 0) {
					// Pour ne pas copier les pixels dans la vide à l'exterieur de la partie précisée et pour ne pas avoir des erreurs comme nullPointeur 
					if( i <height-rowOffset && j < width-colOffset) {
						NImage[I][J] = imageData[i][j];
					}
				}	
				else if (rowOffset<0 && colOffset<0) {
					if(  i < height+rowOffset && j < width+colOffset) {
						NImage[I-rowOffset][J-colOffset] = imageData[i-rowOffset][j-colOffset];
					}	
				}
				else if (rowOffset<0  || colOffset<0 ) {
					if(rowOffset <0 && colOffset >0) {
						if(  i < height+rowOffset && j < width-colOffset) {
							NImage[I-rowOffset][J] = imageData[i-rowOffset][j];
						}
					}
					else if(rowOffset>0 && colOffset<0 ) {
						if(  i < height-rowOffset && j < width+colOffset) {
							NImage[I][J-colOffset] = imageData[i][j-colOffset];
						}
					}
				}
			}
		}
		
		this.clearData();
		this.imageData = NImage;
		
	}
	
	/**
	 * Effectue un zoom autour du pixel (x,y) d'un facteur zoomFactor 
	 * @param x : colonne autour de laquelle le zoom sera effectue
	 * @param y : rangee autour de laquelle le zoom sera effectue  
	 * @param zoomFactor : facteur du zoom a effectuer. Doit etre superieur a 1
	 */
	public void zoomIn(int x, int y, double zoomFactor) throws IllegalArgumentException
	{
		if(zoomFactor < 1.0)
			throw new IllegalArgumentException();
		
		// compléter
		else {	
			AbstractPixel[][] NImage =null; // declaration d'une image temporaire
			// Spécification de la type de la nouvelle image et la remplir par pixels blanc selon sa type.
			switch (imageType) {
				case Color:
					NImage = new ColorPixel[height][width];
					for(int i=0 ; i < height; i++) {	//lignes
						for(int j=0; j < width; j++) {	//colonnes	
							NImage[i][j] = imageData[i][j];
						}
					}
					break;
 				case BW: 
					NImage = new BWPixel[height][width];
					for(int i=0 ; i < height; i++) {	//lignes
						for(int j=0; j < width; j++) {	//colonnes	
							NImage[i][j] = imageData[i][j];
						}
					}
					break;
 				case Gray:
 					NImage = new GrayPixel[height][width];
 					for(int i=0 ; i < height; i++) {	//lignes
						for(int j=0; j < width; j++) {	//colonnes	
							NImage[i][j] = imageData[i][j];
						}
 					}
 					break;
 				case Transparent:
 					NImage = new TransparentPixel[height][width];
 					for(int i=0 ; i < height; i++) {	//lignes
						for(int j=0; j < width; j++) {	//colonnes	
							NImage[i][j] = imageData[i][j];
						}
 					}
 					break;
			}
			
			// La hauteur et la largeur de la partie de l'image qu'on veut zoommer
			int nh=(int) (height/zoomFactor);	
			int nw=(int) (width/zoomFactor);	
			// La position initiale (0,0) de la partie de l'image qu'on veut zoomer 
			int positionInitI = (int) (x-(nh/(2)));	
			int positionInitJ = (int) (y-(nw/(2)));
			// Ces deux variables nous aident à sauter une ligne et une colonne lorsqu'on copie la partie zoomer dans l'image originale 
			int colonne = 0;
			int ligne = 0;
			
			// Ces conditions precisent les frontières de l'ancien et de la nouvelle image pour qu'on ne copie pas les pixels 
			// dans une partie vide et avoir des erreurs comme nullPointer 
			if (positionInitI < 0 && positionInitJ > 0) {
				positionInitI = 0;
				positionInitJ = width - nw;
			} 
			else if (positionInitI < 0 && positionInitJ < 0) {
				positionInitJ =0;
				positionInitI = 0;
			}
			else if (positionInitI > 0 && positionInitJ < 0) {
				positionInitI = height - nh;
				positionInitJ = 0;
			}
			else {
				positionInitJ = width - nw;
				positionInitI = height -nh;
			}
			
			for (int i = positionInitI; i <positionInitI + nh && ligne < height; i++) {
				colonne = 0;
				for (int j = positionInitJ; j < positionInitJ+nw && colonne < width; j++) {
					for (int n =0; n < zoomFactor; n++) {
						for (int m = 0; m < zoomFactor; m++) {
							NImage[ligne + n][colonne + m] = imageData[i][j];
						}
					}
					colonne += zoomFactor;
				}
				ligne += zoomFactor;
			}
			imageData = NImage;
		
		}
	}
}












