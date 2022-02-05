package br.unesp.rpg.service.impl;

//Imports:
import br.unesp.rpg.dao.ItemDAO;
import br.unesp.rpg.factory.DAOFactory;
import br.unesp.rpg.model.Backpack;
import br.unesp.rpg.model.Item;
import br.unesp.rpg.service.ItemService;

public class ItemServiceImpl implements ItemService{
    //Attributes:
    private ItemDAO itemDAO;
    
    //Constructors:
    public ItemServiceImpl() {
        this.itemDAO = DAOFactory.getItemDAO();
    }
    
    //Methods:
    @Override
    public boolean save(Item item, Backpack backpack) {
        int ID = -1;

        if(item != null && backpack != null) {
            ID = this.itemDAO.save(item, backpack);
        }

        return ID != -1;
    }

    @Override
    public Item findByID(int ID) {
        return this.itemDAO.findByID(ID);
    }
}