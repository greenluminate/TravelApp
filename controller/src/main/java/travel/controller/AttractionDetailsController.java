package travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import travel.domain.Attraction;
import travel.domain.User;
import travel.model.ReviewListModel;
import travel.service.AttractionService;
import travel.service.UserService;
import travel.transformer.ModelTransformer;

@Controller
public class AttractionDetailsController {
    @Autowired
    private UserService userService;
    @Autowired
    private AttractionService attractionService;
    @Autowired
    private ModelTransformer modelTransformer;

    @ModelAttribute("model")
    public ReviewListModel createReviewListModel(String attractionId) {
        User user = userService.getLoggedInUser();
        userService.authenticateUser(user.getCredentials());

        Attraction attraction = attractionService.findAttractionById(Long.parseLong(attractionId));

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
