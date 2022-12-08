package travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import travel.domain.Attraction;
import travel.domain.Trip;
import travel.domain.User;
import travel.domain.Visit;
import travel.model.VisitListModel;
import travel.model.VisitModel;
import travel.service.AttractionService;
import travel.service.UserService;
import travel.service.VisitService;
import travel.transformer.ModelTransformer;

import javax.validation.Valid;

@Controller
public class TripDetailsController {
    @Autowired
    private AttractionService attractionService;
    @Autowired
    private UserService userService;
    @Autowired
    private VisitService visitService;
    @Autowired
    private ModelTransformer modelTransformer;

    @ModelAttribute("visitModel")
    public VisitModel createVisitModel() {
        return new VisitModel();
    }

    @ModelAttribute("model")
    public VisitListModel createAttractionListModel(String tripId) {
        User user = userService.getLoggedInUser();
        userService.authenticateUser(user.getCredentials());

        Trip trip = user.getTrips().stream()
                .filter(t -> t.getId() == Long.parseLong(tripId))
                .findFirst().orElse(null);

        return new VisitListModel(
                trip.getId(),
                user.getRole().toString(),
                trip.getDestination().getName(),
                trip.getDestination().getCountry(),
                trip.getStartDate().toString(),
                trip.getEndDate().toString(),
                modelTransformer.TransformVisitList((trip.getVisits())),
                modelTransformer.TransformAttractionList(trip.getDestination().getAttractions())
        );
    }

    @PostMapping(value = "/my-trips/add-visit", params = "tripId")
    public String createCard(@Valid VisitModel visitModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        String result;
        if (bindingResult.hasErrors()) {
            result = "trip-details";
        } else {
            User user = userService.getLoggedInUser();
            userService.authenticateUser(user.getCredentials());

            Trip trip = user.getTrips().stream()
                    .filter(t -> t.getId() == visitModel.getTripId())
                    .findFirst().orElse(null);

            Attraction attraction = attractionService.findAttractionById(visitModel.getAttractionId());

            Visit visit = new Visit();
            visit.setAttraction(attraction);
            visit.setTrip(trip);
            visit.setVisitDate(visitModel.getVisitDate());

            visitService.createVisit(visit);

            if (trip != null) {
                trip.getVisits().add(visit);
            }

            redirectAttributes.addFlashAttribute("successMessage", "Visit added successfully");
            result = "redirect:/my-trips/trip-details?tripId=" + visitModel.getTripId();
        }
        return result;
    }

    @GetMapping(value = "/my-trips/trip-details", params = "tripId")
    public String trips() {
        return "trip-details";
    }
}
