package br.unesp.rpg.service.impl;

//Imports:
import br.unesp.rpg.dao.WizardDAO;
import br.unesp.rpg.factory.DAOFactory;
import br.unesp.rpg.model.Wizard;
import br.unesp.rpg.service.WizardService;

public class WizardServiceImpl implements WizardService{
    //Attributes:
    private WizardDAO wizardDAO;
    
    //Constructors:
    public WizardServiceImpl() {
        this.wizardDAO = DAOFactory.getWizardDAO();
    }
    
    //Methods:
    @Override
    public boolean save(Wizard wizard) {
        int ID = -1;

        if(wizard != null) {
            ID = this.wizardDAO.save(wizard);
        }
        
        return ID != -1;
    }

    @Override
    public Wizard findByID(int ID) {
        return this.wizardDAO.findByID(ID);
    }
}