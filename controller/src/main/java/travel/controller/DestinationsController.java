package travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import travel.domain.Destination;
import travel.domain.User;
import travel.model.DestinationListModel;
import travel.model.DestinationModel;
import travel.service.DestinationService;
import travel.service.UserService;
import travel.transformer.ModelTransformer;

import javax.validation.Valid;

@Controller
public class DestinationsController {
    @Autowired
    private UserService userService;
    @Autowired
    private DestinationService destinationService;
    @Autowired
    private ModelTransformer modelTransformer;

    @ModelAttribute("destinationModel")
    public DestinationModel createDestinationModel() {
        return new DestinationModel();
    }

    @ModelAttribute("model")
    public DestinationListModel createDestinationsModel() {
        User user = userService.getLoggedInUser();
        userService.authenticateUser(user.getCredentials());

        return new DestinationListModel(
                user.getRole().toString(),
                modelTransformer.TransformDestinationList(
                        destinationService.getDestinations()));
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

            destinationService.createDestination(destination);

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
