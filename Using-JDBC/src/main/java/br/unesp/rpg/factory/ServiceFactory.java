package br.unesp.rpg.factory;

//Imports:
import br.unesp.rpg.service.AnimalService;
import br.unesp.rpg.service.BackpackService;
import br.unesp.rpg.service.BarbarianService;
import br.unesp.rpg.service.CharacterService;
import br.unesp.rpg.service.ClericService;
import br.unesp.rpg.service.ItemService;
import br.unesp.rpg.service.RangerService;
import br.unesp.rpg.service.WizardService;
import br.unesp.rpg.service.impl.BarbarianServiceImpl;
import br.unesp.rpg.service.impl.ClericServiceImpl;
import br.unesp.rpg.service.impl.ItemServiceImpl;
import br.unesp.rpg.service.impl.RangerServiceImpl;
import br.unesp.rpg.service.impl.WizardServiceImpl;

public class ServiceFactory {
    //Constructors:
    public ServiceFactory() {
        
    }
    
    //Methods:
    public static ItemService getItemService(){
        return new ItemServiceImpl();
    }
    
    public static WizardService getWizardService(){
        return new WizardServiceImpl();
    }
    
    public static ClericService getClericService(){
        return new ClericServiceImpl();
    }
    
    public static RangerService getRangerService(){
        return new RangerServiceImpl();
    }
    
    public static BarbarianService getBarbarianService(){
        return new BarbarianServiceImpl();
    }
}