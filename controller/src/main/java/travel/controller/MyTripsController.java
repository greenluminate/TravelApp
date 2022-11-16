package travel.controller;

import travel.domain.User;
import travel.model.TripListModel;
import travel.service.TravelService;
import travel.transformer.ModelTransformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class MyTripsController {
    @Autowired
    private TravelService travelService;

    @Autowired
    private ModelTransformer modelTransformer;

    @ModelAttribute("model")
    public TripListModel createTripListModel() {
        User user = travelService.getLoggedInUser();
        travelService.authenticateUser(user.getCredentials());

        return new TripListModel(user.getRole().toString(), modelTransformer.TransformTripList(user.getTrips()));
    }

    @GetMapping("/my-trips")
    public String destinations(Model model) {
        return "my-trips";
    }
}
