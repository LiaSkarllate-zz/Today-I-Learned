package br.unesp.rpg;

//Imports:
import br.unesp.rpg.factory.ServiceFactory;
import br.unesp.rpg.model.Animal;
import br.unesp.rpg.model.Backpack;
import br.unesp.rpg.model.Item;
import br.unesp.rpg.model.Ranger;
import br.unesp.rpg.service.ItemService;
import br.unesp.rpg.service.RangerService;

public class Demo {
    public static void main(String[] args) {
        Ranger ranger = new Ranger(new Animal("Bangaré", "Terrestre", 40));
        ranger.setName("William");
        ranger.setHealth(1000);
        ranger.setArmor(95);
        ranger.setMagicResistance(60);
        ranger.setDexterity(85);
        ranger.setStrength(90);
        ranger.setIntelligence(70);
        ranger.setWisdom(55);
        ranger.setCharisma(65);
        ranger.setBackpack(new Backpack(50));
        
        Item item1 = new Item("Ferro", "Ferramenta", 600, 100, 150);
        Item item2 = new Item("Algodão", "Vestimenta", 20, 500, 600);
        
        RangerService service1 = ServiceFactory.getRangerService();
        ItemService service2 = ServiceFactory.getItemService();
        
        System.out.println(service1.save(ranger));
        System.out.println(service2.save(item1, ranger.getBackpack()));
        System.out.println(service2.save(item2, ranger.getBackpack()));
    }
}
