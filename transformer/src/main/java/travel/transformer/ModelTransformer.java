package travel.transformer;

import org.decimal4j.util.DoubleRounder;
import org.springframework.stereotype.Component;
import travel.domain.*;
import travel.model.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class ModelTransformer {
    public List<DestinationModel> TransformDestinationList(List<Destination> destinations) {
        List<DestinationModel> destinationModels = new ArrayList<>();
        for (Destination destination : destinations) {
            destinationModels.add(
                    new DestinationModel(
                            destination.getId(),
                            destination.getCountry(),
                            destination.getName(),
                            destination.getAttractions().size()
                    ));
        }
        return destinationModels;
    }

    public List<AttractionModel> TransformAttractionList(List<Attraction> attractions) {
        List<AttractionModel> attractionModels = new ArrayList<>();
        for (Attraction attraction : attractions) {
            double sumRating = 0;
            for (Review review : attraction.getReviews()) {
                sumRating += review.getRating();
            }
            double avgRating = sumRating != 0
                    ? sumRating / attraction.getReviews().size()
                    : 0;

            attractionModels.add(
                    new AttractionModel(
                            attraction.getId(),
                            attraction.getDestination().getId(),
                            attraction.getName(),
                            attraction.getDescription(),
                            attraction.getCategory().toString(),
                            attraction.getReviews().size(),
                            DoubleRounder.round(avgRating, 1)
                    ));
        }
        return attractionModels;
    }

    public List<ReviewModel> TransformReviewList(List<Review> reviews) {
        List<ReviewModel> reviewModels = new ArrayList<>();
        for (Review review : reviews) {
            reviewModels.add(
                    new ReviewModel(
                            review.getId(),
                            review.getRating(),
                            review.getComment(),
                            review.getUser().getFullName()
                    ));
        }

        return reviewModels;
    }

    public List<TripModel> TransformTripList(List<Trip> trips) {
        List<TripModel> tripModels = new ArrayList<>();
        for (Trip trip : trips) {
            tripModels.add(
                    new TripModel(
                            trip.getId(),
                            trip.getDestination().getId(),
                            trip.getDestination().getName(),
                            trip.getDestination().getCountry(),
                            trip.getStartDate().toString(),
                            trip.getEndDate().toString()
                    ));
        }

        return tripModels;
    }

    public List<VisitModel> TransformVisitList(List<Visit> visits) {
        List<VisitModel> visitModels = new ArrayList<>();
        for (Visit visit : visits) {
            visitModels.add(
                    new VisitModel(
                            visit.getAttraction().getId(),
                            visit.getTrip().getId(),
                            visit.getAttraction().getName(),
                            visit.getVisitDate(),
                            visit.getTrip().getStartDate(),
                            visit.getTrip().getEndDate()
                    ));
        }

        return visitModels;
    }
}
