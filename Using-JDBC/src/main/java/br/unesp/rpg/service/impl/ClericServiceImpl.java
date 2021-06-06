package br.unesp.rpg.service.impl;

//Imports:
import br.unesp.rpg.dao.ClericDAO;
import br.unesp.rpg.factory.DAOFactory;
import br.unesp.rpg.model.Cleric;
import br.unesp.rpg.service.ClericService;

public class ClericServiceImpl implements ClericService{
    //Attributes:
    private ClericDAO clericDAO;
    
    //Constructors:
    public ClericServiceImpl() {
        this.clericDAO = DAOFactory.getClericDAO();
    }
    
    //Methods:
    @Override
    public boolean save(Cleric cleric) {
        int ID = -1;

        if(cleric != null) {
            ID = this.clericDAO.save(cleric);
        }

        return ID != -1;
    }

    @Override
    public Cleric findByID(int ID) {
        return this.clericDAO.findByID(ID);
    }
}