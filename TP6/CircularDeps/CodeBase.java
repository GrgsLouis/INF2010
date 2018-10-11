package CircularDeps;

import java.util.HashSet;
import java.util.Iterator;

public class CodeBase {
    private SourceFile[] sourceFiles;

    public CodeBase(SourceFile[] sourceFiles)
    {
        this.sourceFiles = sourceFiles;
    }

    public boolean hasCircularDependencies()
    {
        for (SourceFile file : this.sourceFiles) {
            HashSet<SourceFile> dependentFiles = new HashSet<>();
            if (existsCircularDependencies(file, dependentFiles)) {
                return true;
            }
        }
        return false;
    }

    // DFS partant de <<file>> détectant s'il existe
    // des dépendances circulaires dans les fichiers source.
    private boolean existsCircularDependencies(SourceFile file, HashSet<SourceFile> dependentFiles)
    {
    	
    	if(dependentFiles.contains(file)){//si "dependetFiles" contient le "file", return true
    		return true;
    	}
    	
    	else{dependentFiles.add(file);//si "dependetFiles" ne contient pas le "file", return false
    	}
    	
    	for(Iterator<SourceFile> iter = file.getDependencies().iterator(); iter.hasNext() ;) {//on applique la m�thode pour tout les enfants.
    		if (this.existsCircularDependencies(iter.next(), dependentFiles)) {
    			return true;
    		}
    	}
    	return false; //rendu ici, alors la boucle for na jamais retourner vrai, donc pas de circular dependencies.
    }
}
