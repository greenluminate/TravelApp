package travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import travel.domain.User;
import travel.model.HomeModel;
import travel.service.Statistics;
import travel.service.TravelService;
import travel.service.UserService;

@Controller
public class HomeController {
    @Autowired
    private TravelService travelService;
    @Autowired
    private UserService userService;

    @ModelAttribute("model")
    public HomeModel createHomeModel() {
        User user = userService.getLoggedInUser();
        userService.authenticateUser(user.getCredentials());

        Statistics stat = travelService.getStatistics();

        HomeModel homeModel = new HomeModel();
        homeModel.setFullName(user.getFullName());
        homeModel.setRole(user.getRole().toString());
        homeModel.setNumOfDest(stat.getNumberOfDestinations());
        homeModel.setNumOfAttr(stat.getNumberOfAttractions());
        homeModel.setNumOfUsers(stat.getNumberOfUsers());
        homeModel.setNumOfAllReviews(stat.getNumberOfAllReviews());
        homeModel.setNumOfMyVisits(stat.getNumberOfUserVisits());
        homeModel.setNumOfMyReviews(stat.getNumberOfUserWrittenReviews());

        return homeModel;
    }

    @GetMapping("/home")
    public String home() {
        return "home-page";
    }
}
