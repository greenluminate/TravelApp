package travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import travel.domain.Attraction;
import travel.domain.Category;
import travel.domain.Destination;
import travel.domain.User;
import travel.model.AttractionListModel;
import travel.model.AttractionModel;
import travel.service.AttractionService;
import travel.service.DestinationService;
import travel.service.UserService;
import travel.transformer.ModelTransformer;

import javax.validation.Valid;

@Controller
public class DestinationDetailsController {
    @Autowired
    private UserService userService;
    @Autowired
    private AttractionService attractionService;
    @Autowired
    private DestinationService destinationService;
    @Autowired
    private ModelTransformer modelTransformer;

    @ModelAttribute("attractionModel")
    public AttractionModel createAttractionModel() {
        return new AttractionModel();
    }

    @ModelAttribute("model")
    public AttractionListModel createAttractionListModel(String destinationId) {
        long destinationIdLong = Long.parseLong(destinationId);

        User user = userService.getLoggedInUser();
        userService.authenticateUser(user.getCredentials());

        Destination destination = destinationService.getDestinationById(destinationIdLong);

        AttractionListModel attractionListModel = new AttractionListModel(
                destinationIdLong,
                destination.getName(),
                destination.getCountry(),
                user.getRole().toString(),
                modelTransformer.TransformAttractionList(destination.getAttractions())
        );

        for (AttractionModel attrModel : attractionListModel.getAttractionModels()) {
            attrModel.setDestinationId(destinationIdLong);
        }

        return attractionListModel;
    }

    @PostMapping(value = "/destinations/create-attraction", params = "destinationId")
    public String createCard(@Valid AttractionModel attractionModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        String result;
        if (bindingResult.hasErrors()) {
            result = "destination-details";
        } else {

            Attraction attraction = new Attraction();
            attraction.setName(attractionModel.getName());
            attraction.setDescription(attractionModel.getDescription());
            attraction.setCategory(Category.valueOf(attractionModel.getCategory()));

            attractionService.createAttraction(attractionModel.getDestinationId(), attraction);

            redirectAttributes.addFlashAttribute("successMessage", "Attraction added successfully");
            result = "redirect:/destinations/destination-details?destinationId=" + attractionModel.getDestinationId();
        }
        return result;
    }


    @GetMapping(value = "/destinations/destination-details", params = "destinationId")
    public String destinations() {
        return "destination-details";
    }
}
