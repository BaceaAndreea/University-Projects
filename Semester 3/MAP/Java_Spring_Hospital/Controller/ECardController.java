package map.project.demo.Controller;

import map.project.demo.Decorator.Decorator;
import map.project.demo.Domain.ECard;
import map.project.demo.Service.ECardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ECardController implements Decorator{
    private final ECardService service;

    @Autowired
    public ECardController(ECardService service) {
        this.service = service;
    }

    @PostMapping("/addECard")
    public String add(@RequestBody ECard eCard) {
        service.save(eCard);
        displayDetails(eCard.getElectronicID());
        return "Added successfully.";
    }

    @GetMapping("/findByIdentifierECard/{eCardID}")
    public ECard findECardByIdentifier(@PathVariable int eCardID) {
        return service.get(eCardID);
    }

    @GetMapping("/getAllECard")
    public List<ECard> getAll() {
        return service.listAll();
    }

    @GetMapping("/printAllECard")
    public String printAll() {
        List<ECard> eCards = service.listAll();
        eCards.forEach(eCard -> System.out.println(eCard.toString()));
        return "Printed.";
    }

    @GetMapping("/deleteECard/{eCardID}")
    public String delete(@PathVariable int eCardID) {
        ECard eCard = service.get(eCardID);
        if (eCard != null) {
            service.delete(eCardID);
            return "Deleted successfully.";
        } else {
            throw new IllegalArgumentException("Nothing was found for the provided identifier.");
        }
    }

    @PostMapping("/updateECard/{eCardID}")
    public String update(@PathVariable int eCardID, @RequestBody ECard newObject) {
        ECard existingECard = service.get(eCardID);
        if (existingECard != null) {
            delete(eCardID);
            add(newObject);
            return "Updated successfully.";
        } else {
            throw new IllegalArgumentException("Nothing was found for the provided identifier.");
        }
    }

    @GetMapping("/displayECard/{id}")
    public void displayDetails(@PathVariable int id) {
        ECard healthcard = findECardByIdentifier(id);
        String cardType = "Electronic Card";
        System.out.println("+----------------------------+");
        System.out.println("|      " + cardType + "       |");
        System.out.println("+----------------------------+");
        System.out.println("| Expiration Date: " + healthcard.getExpirationDate() + addEmptySpace(10, 9) + "|");
        System.out.println("| PIN: ****                  |");
        System.out.println("| ID: " + String.format("%04d", id) + addEmptySpace(4, 13) + "          |");
        System.out.println("+----------------------------+");
    }
}
