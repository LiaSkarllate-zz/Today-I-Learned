package br.unesp.rpg.factory;

//Imports:
import br.unesp.rpg.dao.AnimalDAO;
import br.unesp.rpg.dao.BackpackDAO;
import br.unesp.rpg.dao.BarbarianDAO;
import br.unesp.rpg.dao.CharacterDAO;
import br.unesp.rpg.dao.ClericDAO;
import br.unesp.rpg.dao.ItemDAO;
import br.unesp.rpg.dao.RangerDAO;
import br.unesp.rpg.dao.WizardDAO;
import br.unesp.rpg.dao.impl.AnimalDAOImpl;
import br.unesp.rpg.dao.impl.BackpackDAOImpl;
import br.unesp.rpg.dao.impl.BarbarianDAOImpl;
import br.unesp.rpg.dao.impl.CharacterDAOImpl;
import br.unesp.rpg.dao.impl.ClericDAOImpl;
import br.unesp.rpg.dao.impl.ItemDAOImpl;
import br.unesp.rpg.dao.impl.RangerDAOImpl;
import br.unesp.rpg.dao.impl.WizardDAOImpl;

public class DAOFactory {
    //Constructors:
    public DAOFactory() {
        
    }
    
    //Methods:
    public static BackpackDAO getBackpackDAO(){
        return new BackpackDAOImpl();
    }
    
    public static CharacterDAO getCharacterDAO(){
        return new CharacterDAOImpl();
    }
    
    public static ItemDAO getItemDAO(){
        return new ItemDAOImpl();
    }
    
    public static WizardDAO getWizardDAO(){
        return new WizardDAOImpl();
    }
    
    public static ClericDAO getClericDAO(){
        return new ClericDAOImpl();
    }
    
    public static BarbarianDAO getBarbarianDAO(){
        return new BarbarianDAOImpl();
    }   
    
    public static RangerDAO getRangerDAO(){
        return new RangerDAOImpl();
    } 
    
    public static AnimalDAO getAnimalDAO(){
        return new AnimalDAOImpl();
    }
}