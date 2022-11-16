package travel.controller;

import travel.domain.Attraction;
import travel.domain.Category;
import travel.domain.Destination;
import travel.domain.User;
import travel.model.AttractionListModel;
import travel.model.AttractionModel;
import travel.service.DestinationService;
import travel.service.TravelService;
import travel.transformer.ModelTransformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class DestinationDetailsController {
    @Autowired
    private TravelService travelService;

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
        User user = travelService.getLoggedInUser();
        travelService.authenticateUser(user.getCredentials());

        Destination destination = destinationService.findDestinationById(Long.parseLong(destinationId));

        AttractionListModel attractionListModel = new AttractionListModel(
                Long.parseLong(destinationId),
                destination.getName(),
                destination.getCountry(),
                user.getRole().toString(),
                modelTransformer.TransformAttractionList(destination.getAttractions())
        );

        for (AttractionModel attrModel : attractionListModel.getAttractionModels()) {
            attrModel.setDestinationId(Long.parseLong(destinationId));
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

            travelService.createAttraction(attractionModel.getDestinationId(), attraction);

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
