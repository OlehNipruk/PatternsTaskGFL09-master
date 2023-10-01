package patterns.example;

import java.io.*;
import java.util.List;

public class FileHandler {
    public static void saveCustomersToFile(List<Customer> customers, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(customers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Customer> loadCustomersFromFile(String fileName) {
        List<Customer> customers = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            customers = (List<Customer>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public static void saveMoviesToFile(List<Movie> movies, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(movies);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Movie> loadMoviesFromFile(String fileName) {
        List<Movie> movies = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            movies = (List<Movie>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return movies;
    }
}
