package patterns.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer implements Serializable {
    private final String name;
    private final List<Rental> rentals;

    public Customer(String name) {
        this.name = name;
        this.rentals = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public List<Rental> getRentals() {
        return rentals;
    }
    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");

        for (Rental each : rentals) {
            double thisAmount = 0;

            switch (each.getMovie().getPriceCode()) {
                case REGULAR:
                    thisAmount += 2;
                    if (each.getDaysRented() > 2)
                        thisAmount += (each.getDaysRented() - 2) * 1.5;
                    break;
                case NEW_RELEASE:
                    thisAmount += each.getDaysRented() * 3;
                    break;
                case CHILDRENS:
                    thisAmount += 1.5;
                    if (each.getDaysRented() > 3)
                        thisAmount += (each.getDaysRented() - 3) * 1.5;
                    break;
                case DRAMA:

                    thisAmount += 4;
                    break;
                case COMEDY:

                    thisAmount += 2.5;
                    break;
                case THRILLER:

                    thisAmount += 3.5;
                    break;
            }

            frequentRenterPoints++;

            if ((each.getMovie().getPriceCode() == Movie.MovieType.NEW_RELEASE) && each.getDaysRented() > 1)
                frequentRenterPoints++;

            result.append("\t").append(each.getMovie().getTitle()).append("\t").append(thisAmount).append("\n");
            totalAmount += thisAmount;
        }

        result.append("Amount owed is ").append(totalAmount).append("\n");
        result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
        return result.toString();
    }

    public String htmlStatement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;
        StringBuilder result = new StringBuilder("<h1>Rental Record for <em>").append(getName()).append("</em></h1>\n");

        for (Rental each : rentals) {
            double thisAmount = 0;

            switch (each.getMovie().getPriceCode()) {
                case REGULAR:
                    thisAmount += 2;
                    if (each.getDaysRented() > 2)
                        thisAmount += (each.getDaysRented() - 2) * 1.5;
                    break;
                case NEW_RELEASE:
                    thisAmount += each.getDaysRented() * 3;
                    break;
                case CHILDRENS:
                    thisAmount += 1.5;
                    if (each.getDaysRented() > 3)
                        thisAmount += (each.getDaysRented() - 3) * 1.5;
                    break;
                case DRAMA:

                    thisAmount += 4;
                    break;
                case COMEDY:

                    thisAmount += 2.5;
                    break;
                case THRILLER:

                    thisAmount += 3.5;
                    break;
            }

            frequentRenterPoints++;

            if ((each.getMovie().getPriceCode() == Movie.MovieType.NEW_RELEASE) && each.getDaysRented() > 1)
                frequentRenterPoints++;

            result.append("<p>").append(each.getMovie().getTitle()).append(": ").append(thisAmount).append("</p>\n");
            totalAmount += thisAmount;
        }

        result.append("<p>Amount owed is <em>").append(totalAmount).append("</em></p>\n");
        result.append("<p>You earned <em>").append(frequentRenterPoints).append("</em> frequent renter points</p>");
        return result.toString();
    }

}
