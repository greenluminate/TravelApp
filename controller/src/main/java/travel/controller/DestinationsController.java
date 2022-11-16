package travel.controller;

import travel.domain.Destination;
import travel.domain.User;
import travel.model.DestinationListModel;
import travel.model.DestinationModel;
import travel.service.TravelService;
import travel.transformer.ModelTransformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class DestinationsController {
    @Autowired
    private TravelService travelService;

    @Autowired
    private ModelTransformer modelTransformer;

    @ModelAttribute("destinationModel")
    public DestinationModel createDestinationModel() {
        return new DestinationModel();
    }

    @ModelAttribute("model")
    public DestinationListModel createDestinationsModel() {
        User user = travelService.getLoggedInUser();
        travelService.authenticateUser(user.getCredentials());

        return new DestinationListModel(
                user.getRole().toString(),
                modelTransformer.TransformDestinationList(
                        travelService.getDestinations()));
    }

    @PostMapping("/destinations")
    public String createCard(@Valid DestinationModel destinationModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        String result;
        if (bindingResult.hasErrors()) {
            result = "destinations";
        } else {
            Destination destination = new Destination();
            destination.setName(destinationModel.getName());
            destination.setCountry(destinationModel.getCountry());
            travelService.createDestination(destination);

            redirectAttributes.addFlashAttribute("successMessage", "Destination added successfully");
            result = "redirect:destinations";
        }
        return result;
    }


    @GetMapping("/destinations")
    public String destinations(Model model) {
        return "destinations";
    }
}
