    package br.unesp.rpg.service.impl;

//Imports:
import br.unesp.rpg.dao.BarbarianDAO;
import br.unesp.rpg.factory.DAOFactory;
import br.unesp.rpg.model.Barbarian;
import br.unesp.rpg.service.BarbarianService;

public class BarbarianServiceImpl implements BarbarianService{
    //Attributes:
    private BarbarianDAO barbarianDAO;
    
    //Constructors:
    public BarbarianServiceImpl() {
        this.barbarianDAO = DAOFactory.getBarbarianDAO();
    }
    
    //Methods:
    @Override
    public boolean save(Barbarian barbarian) {
        int ID = -1;

        if(barbarian != null) {
            ID = this.barbarianDAO.save(barbarian);
        }

        return ID != -1;
    }

    @Override
    public Barbarian findByID(int ID) {
        return this.barbarianDAO.findByID(ID);
    }
}