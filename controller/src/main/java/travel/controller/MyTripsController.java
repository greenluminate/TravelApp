package travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import travel.domain.User;
import travel.model.TripListModel;
import travel.service.UserService;
import travel.transformer.ModelTransformer;

@Controller
public class MyTripsController {
    @Autowired
    private UserService userService;
    @Autowired
    private ModelTransformer modelTransformer;

    @ModelAttribute("model")
    public TripListModel createTripListModel() {
        User user = userService.getLoggedInUser();
        userService.authenticateUser(user.getCredentials());

        return new TripListModel(user.getRole().toString(), modelTransformer.TransformTripList(user.getTrips()));
    }

    @Secured({" ROLE_USER "})
    @GetMapping("/my-trips")
    public String destinations(Model model) {
        return "my-trips";
    }
}
