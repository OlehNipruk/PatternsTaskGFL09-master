
package patterns.example;
import patterns.example.Customer;
import patterns.example.Movie;
import patterns.example.Rental;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<Movie> catalogOfMovies = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. View catalog of movies");
            System.out.println("2. Add a movie to catalog");
            System.out.println("3. Add a customer");
            System.out.println("4. Rent a movie");
            System.out.println("5. Search for a movie by title");
            System.out.println("6. View customer rentals");
            System.out.println("7. Generate HTML statement for customer");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:

                    System.out.println("\nCatalog of Movies:");
                    for (Movie movie : catalogOfMovies) {
                        System.out.println(movie.getTitle());
                    }
                    break;

                case 2:

                    System.out.print("Enter movie title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter movie type (REGULAR, NEW_RELEASE, CHILDRENS, DRAMA, COMEDY, THRILLER): ");
                    String movieTypeStr = scanner.nextLine();
                    Movie.MovieType movieType = Movie.MovieType.valueOf(movieTypeStr);
                    System.out.print("Enter country of origin: ");
                    String countryOfOrigin = scanner.nextLine();
                    System.out.print("Enter short description: ");
                    String shortDescription = scanner.nextLine();
                    System.out.print("Enter director: ");
                    String director = scanner.nextLine();
                    System.out.print("Enter actors (comma-separated): ");
                    String actorsStr = scanner.nextLine();
                    List<String> actors = Arrays.asList(actorsStr.split(","));


                    Movie newMovie = Movie.createMovie(title, movieType, countryOfOrigin, shortDescription, director, actors);
                    catalogOfMovies.add(newMovie);
                    System.out.println("Movie added to catalog successfully!");
                    break;




                case 3:

                    System.out.print("Enter customer name: ");
                    String customerName = scanner.nextLine();
                    Customer newCustomer = new Customer(customerName);
                    customers.add(newCustomer);
                    System.out.println("Customer added successfully!");
                    break;
                case 4:

                    System.out.print("Enter customer name: ");
                    String renterName = scanner.nextLine();
                    Customer rentingCustomer = findCustomerByName(customers, renterName);
                    if (rentingCustomer != null) {
                        System.out.print("Enter movie title to rent: ");
                        String rentMovieTitle = scanner.nextLine();
                        Movie movieToRent = findMovieByTitle(catalogOfMovies, rentMovieTitle);
                        if (movieToRent != null) {
                            System.out.print("Enter number of days to rent: ");
                            int rentDays = scanner.nextInt();
                            scanner.nextLine();
                            Rental rental = new Rental(movieToRent, rentDays);
                            rentingCustomer.addRental(rental);
                            System.out.println("Movie rented successfully!");
                        } else {
                            System.out.println("Movie not found in catalog.");
                        }
                    } else {
                        System.out.println("Customer not found.");
                    }
                    break;
                case 5:

                    System.out.print("Enter movie title to search: ");
                    String searchTitle = scanner.nextLine();
                    Movie foundMovie = findMovieByTitle(catalogOfMovies, searchTitle);
                    if (foundMovie != null) {
                        System.out.println("Movie found: " + foundMovie.getTitle());
                    } else {
                        System.out.println("Movie not found in catalog.");
                    }
                    break;
                case 6:
                    // 6. Перегляд оренд фільмів клієнта
                    System.out.print("Enter customer name: ");
                    String customerNameToView = scanner.nextLine();
                    Customer customerToView = findCustomerByName(customers, customerNameToView);
                    if (customerToView != null) {
                        System.out.println("\nRentals for " + customerToView.getName() + ":");
                        List<Rental> customerRentals = customerToView.getRentals();
                        for (Rental rental : customerRentals) {
                            System.out.println(rental.getMovie().getTitle() + " (Days Rented: " + rental.getDaysRented() + ")");
                        }
                    } else {
                        System.out.println("Customer not found.");
                    }
                    break;
                case 7:

                    System.out.print("Enter customer name: ");
                    String customerNameForHtmlStatement = scanner.nextLine();
                    Customer customerForHtmlStatement = findCustomerByName(customers, customerNameForHtmlStatement);
                    if (customerForHtmlStatement != null) {
                        String htmlStatement = customerForHtmlStatement.htmlStatement();
                        System.out.println("\nHTML Statement:\n" + htmlStatement);
                    } else {
                        System.out.println("Customer not found.");
                    }
                    break;
                case 8:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
                case 9:
                    // Зберегти фільми у файл
                    System.out.print("Enter filename to save movies: ");
                    String moviesFileName = scanner.nextLine();
                    FileHandler.saveMoviesToFile(catalogOfMovies, moviesFileName);
                    System.out.println("Movies saved to " + moviesFileName + " successfully!");
                    break;
                case 10:
                    // Завантажити фільми з файлу
                    System.out.print("Enter filename to load movies from: ");
                    String loadMoviesFileName = scanner.nextLine();
                    List<Movie> loadedMovies = FileHandler.loadMoviesFromFile(loadMoviesFileName);
                    if (loadedMovies != null) {
                        catalogOfMovies.addAll(loadedMovies);
                        System.out.println("Movies loaded from " + loadMoviesFileName + " successfully!");
                    } else {
                        System.out.println("Error loading movies from " + loadMoviesFileName);
                    }
                    break;




            }
        } while (choice != 8);
    }

    private static Movie findMovieByTitle(List<Movie> catalog, String title) {
        for (Movie movie : catalog) {
            if (movie.getTitle().equalsIgnoreCase(title)) {
                return movie;
            }
        }
        return null;
    }

    private static Customer findCustomerByName(List<Customer> customers, String name) {
        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(name)) {
                return customer;
            }
        }
        return null;
    }
}
