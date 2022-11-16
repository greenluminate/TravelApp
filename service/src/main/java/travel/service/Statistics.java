package travel.service;

import java.util.Objects;

public class Statistics {
    private int numberOfDestinations;
    private int numberOfAttractions;
    private int numberOfUsers;
    private int numberOfAllReviews;
    private int numberOfUserVisits;
    private int numberOfUserWrittenReviews;
    public Statistics(int numberOfDestinations, int numberOfAttractions, int numberOfUsers, int numberOfAllReviews, int numberOfUserVisits, int numberOfUserWrittenReviews) {
        this.numberOfDestinations = numberOfDestinations;
        this.numberOfAttractions = numberOfAttractions;
        this.numberOfUsers = numberOfUsers;
        this.numberOfAllReviews = numberOfAllReviews;
        this.numberOfUserVisits = numberOfUserVisits;
        this.numberOfUserWrittenReviews = numberOfUserWrittenReviews;
    }
    public Statistics() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statistics that = (Statistics) o;
        return numberOfDestinations == that.numberOfDestinations && numberOfAttractions == that.numberOfAttractions && numberOfUsers == that.numberOfUsers && numberOfAllReviews == that.numberOfAllReviews && numberOfUserVisits == that.numberOfUserVisits && numberOfUserWrittenReviews == that.numberOfUserWrittenReviews;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfDestinations, numberOfAttractions, numberOfUsers, numberOfAllReviews, numberOfUserVisits, numberOfUserWrittenReviews);
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "numberOfDestinations=" + numberOfDestinations +
                ", numberOfAttractions=" + numberOfAttractions +
                ", numberOfUsers=" + numberOfUsers +
                ", numberOfAllReviews=" + numberOfAllReviews +
                ", numberOfUserVisits=" + numberOfUserVisits +
                ", numberOfUserWrittenReviews=" + numberOfUserWrittenReviews +
                '}';
    }

    public int getNumberOfDestinations() {
        return numberOfDestinations;
    }

    public int getNumberOfAttractions() {
        return numberOfAttractions;
    }

    public int getNumberOfUsers() {
        return numberOfUsers;
    }

    public int getNumberOfAllReviews() {
        return numberOfAllReviews;
    }

    public int getNumberOfUserVisits() {
        return numberOfUserVisits;
    }

    public int getNumberOfUserWrittenReviews() {
        return numberOfUserWrittenReviews;
    }
}
