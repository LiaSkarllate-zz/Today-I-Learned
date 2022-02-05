package br.unesp.rpg.service.impl;

//Imports:
import br.unesp.rpg.dao.RangerDAO;
import br.unesp.rpg.factory.DAOFactory;
import br.unesp.rpg.model.Ranger;
import br.unesp.rpg.service.RangerService;

public class RangerServiceImpl implements RangerService{
    //Attributes:
    private RangerDAO rangerDAO;
    
    //Constructors:
    public RangerServiceImpl() {
        this.rangerDAO = DAOFactory.getRangerDAO();
    }
    
    //Methods:
    @Override
    public boolean save(Ranger ranger) {
        int ID = -1;

        if(ranger != null) {
            ID = this.rangerDAO.save(ranger);
        }

        return ID != -1;
    }

    @Override
    public Ranger findByID(int ID) {
        return this.rangerDAO.findByID(ID);
    }
}