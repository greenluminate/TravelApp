package travel.controller;

import travel.domain.User;
import travel.model.HomeModel;
import travel.service.Statistics;
import travel.service.TravelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class HomeController {
    @Autowired
    private TravelService travelService;

    @ModelAttribute("model")
    public HomeModel createHomeModel() {

        User user = travelService.getLoggedInUser();
        travelService.authenticateUser(user.getCredentials());
        Statistics stat = travelService.getStatistics();

        HomeModel homeModel = new HomeModel();
        homeModel.setFullName(user.getFullName());
        homeModel.setRole(user.getRole().toString());
        homeModel.setNumOfDest(stat.getNumberOfDestinations());
        homeModel.setNumOfAttr(stat.getNumberOfAttractions());
        homeModel.setNumOfUsers(stat.getNumberOfUsers());
        homeModel.setNumOfAllReviews(stat.getNumberOfUserWrittenReviews());
        homeModel.setNumOfMyVisits(stat.getNumberOfUserVisits());
        homeModel.setNumOfMyReviews(stat.getNumberOfUserWrittenReviews());

        return homeModel;
    }

    @GetMapping("/home")
    public String home() {
        return "home-page";
    }
}
