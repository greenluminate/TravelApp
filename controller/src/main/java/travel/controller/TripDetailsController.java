package travel.controller;

import travel.domain.*;
import travel.model.*;
import travel.service.AttractionService;
import travel.service.TravelService;
import travel.service.TripService;
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
public class TripDetailsController {
    @Autowired
    private TravelService travelService;
    @Autowired
    private AttractionService attractionService;
    @Autowired
    private TripService tripService;
    @Autowired
    private ModelTransformer modelTransformer;

    @ModelAttribute("visitModel")
    public VisitModel createVisitModel() {
        return new VisitModel();
    }

    @ModelAttribute("model")
    public VisitListModel createAttractionListModel(String tripId) {
        User user = travelService.getLoggedInUser();
        travelService.authenticateUser(user.getCredentials());
        Trip trip = tripService.findTripById(Long.parseLong(tripId));

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
            User user = travelService.getLoggedInUser();
            travelService.authenticateUser(user.getCredentials());
            Trip trip = tripService.findTripById(visitModel.getTripId());
            Attraction attraction = attractionService.findAttractionById(visitModel.getAttractionId());

            Visit visit = new Visit();
            visit.setAttraction(attraction);
            visit.setTrip(trip);
            visit.setVisitDate(visitModel.getVisitDate());

            travelService.createVisit(visit);

            redirectAttributes.addFlashAttribute("successMessage", "Visit added successfully");
            result = "redirect:/my-trips/trip-details?tripId=" + visitModel.getTripId();
        }
        return result;
    }

    @GetMapping(value = "/my-trips/trip-details", params = "tripId")
    public String destinations() {
        return "trip-details";
    }
}
