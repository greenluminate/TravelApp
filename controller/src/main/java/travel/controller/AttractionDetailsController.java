package travel.controller;

import travel.domain.User;
import travel.model.ReviewListModel;
import travel.service.AttractionService;
import travel.service.TravelService;
import travel.transformer.ModelTransformer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class AttractionDetailsController {
    @Autowired
    private TravelService travelService;

    @Autowired
    private AttractionService attractionService;
    @Autowired
    private ModelTransformer modelTransformer;

    @ModelAttribute("model")
    public ReviewListModel createReviewListModel(String attractionId) {
        User user = travelService.getLoggedInUser();
        travelService.authenticateUser(user.getCredentials());

        var attraction = attractionService.findAttractionById(Long.parseLong(attractionId));

        return new ReviewListModel(
                user.getRole().toString(),
                attraction.getName(),
                attraction.getCategory().toString(),
                attraction.getDescription(),
                modelTransformer.TransformReviewList(attraction.getReviews()));
    }

    @GetMapping(value = "/destinations/attraction-details", params = "attractionId")
    public String destinations() {
        return "attraction-details";
    }
}
