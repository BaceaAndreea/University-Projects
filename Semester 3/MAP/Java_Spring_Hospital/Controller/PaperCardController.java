package map.project.demo.Controller;

import map.project.demo.Decorator.Decorator;
import map.project.demo.Domain.ECard;
import map.project.demo.Domain.PaperCard;
import map.project.demo.Service.PaperCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import map.project.demo.Decorator.Decorator;

import java.util.List;

@RestController
public class PaperCardController implements Decorator {
    private final PaperCardService service;

    @Autowired
    public PaperCardController(PaperCardService service) {
        this.service = service;
    }

    @PostMapping("/addPaperCard")
    public String add(@RequestBody PaperCard paperCard) {
        service.save(paperCard);
        displayDetails(paperCard.getWrittenID());
        return "Added successfully.";
    }

    @GetMapping("/findByIdentifierPaperCard/{paperCardID}")
    public PaperCard findPaperCardByIdentifier(@PathVariable int paperCardID) {
        return service.get(paperCardID);
    }

    @GetMapping("/getAllPaperCard")
    public List<PaperCard> getAll() {
        return service.listAll();
    }

    @GetMapping("/printAllPaperCard")
    public String printAll() {
        List<PaperCard> paperCards = service.listAll();
        paperCards.forEach(paperCard -> System.out.println(paperCard.toString()));
        return "Printed.";
    }

    @GetMapping("/deletePaperCard/{paperCardID}")
    public String delete(@PathVariable int paperCardID) {
        PaperCard paperCard = service.get(paperCardID);
        if (paperCard != null) {
            service.delete(paperCardID);
            return "Deleted successfully.";
        } else {
            throw new IllegalArgumentException("Nothing was found for the provided identifier.");
        }
    }

    @PostMapping("/updatePaperCard/{paperCardID}")
    public String update(@PathVariable int paperCardID, @RequestBody PaperCard newObject) {
        PaperCard existingPaperCard = service.get(paperCardID);
        if (existingPaperCard != null) {
            delete(paperCardID);
            add(newObject);
            return "Updated successfully.";
        } else {
            throw new IllegalArgumentException("Nothing was found for the provided identifier.");
        }
    }

    @GetMapping("/displayPaperCard/{id}")
    public void displayDetails(@PathVariable int id) {
        PaperCard healthcard = findPaperCardByIdentifier(id);
        String cardType = "Paper Card";
        System.out.println("+----------------------------+");
        System.out.println("|      " + cardType + "       |");
        System.out.println("+----------------------------+");
        System.out.println("| Expiration Date: " + healthcard.getExpirationDate() + addEmptySpace(10, 9) + "|");
        System.out.println("| PIN: ****                  |");
        System.out.println("| ID: " + String.format("%04d", id) + addEmptySpace(4, 13) + "          |");
        System.out.println("+----------------------------+");
    }
}

