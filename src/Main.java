import java.io.*;
import java.util.*;

public class Main {

    static Scanner input = new Scanner(System.in);
    static File file = new File("cars.txt");

    public static void main(String[] args) {

        String TheUser = "ammar";
        String ThePass = "962005";

        while (true) {
            System.out.print("Username: ");
            String user = input.next();

            System.out.print("Password: ");
            String pass = input.next();

            if (user.equals(TheUser) && pass.equals(ThePass)) {
                break;
            } else {
                System.out.println("Wrong Data , try again\n");
            }
        }

        boolean running = true;

        while (running) {

            ArrayList<String> cars = loadCars();

            System.out.println("\nCars:");
            if (cars.isEmpty()) {
                System.out.println("No cars available");
            } else {
                for (String car : cars) {
                    System.out.println("- " + car.split(",")[0]);
                }
            }

            System.out.println("\n1- Select car");
            System.out.println("2- Add new car");
            System.out.println("3- Exit");
            System.out.print("Choose: ");
            int choice = input.nextInt();

            if (choice == 1) {

                if (cars.isEmpty()) {
                    System.out.println("No cars to select");
                    continue;
                }

                System.out.print("Enter car name: ");
                String name = input.next();

                int index = -1;
                for (int i = 0; i < cars.size(); i++) {
                    if (cars.get(i).startsWith(name + ",")) {
                        index = i;
                        break;
                    }
                }

                if (index == -1) {
                    System.out.println("Car not found");
                    continue;
                }

                String[] data = cars.get(index).split(",");
                System.out.println("Price: " + data[1]);
                System.out.println("Model: " + data[2]);

                System.out.println("\n1- Edit");
                System.out.println("2- Delete");
                int act = input.nextInt();

                if (act == 1) {
                    System.out.println("1- Edit price");
                    System.out.println("2- Edit model");
                    int edit = input.nextInt();

                    if (edit == 1) {
                        System.out.print("New price: ");
                        data[1] = input.next();
                    } else if (edit == 2) {
                        System.out.print("New model: ");
                        data[2] = input.next();
                    }

                    cars.set(index, data[0] + "," + data[1] + "," + data[2]);
                    saveCars(cars);
                } else if (act == 2) {
                    System.out.print("Are you sure? (yes/no): ");
                    String c = input.next();

                    if (c.equalsIgnoreCase("yes")) {
                        cars.remove(index);
                        saveCars(cars);
                        System.out.println("Car deleted");
                    }
                }

            } else if (choice == 2) {

                System.out.print("Car name: ");
                String name = input.next();

                System.out.print("Price: ");
                String price = input.next();

                System.out.print("Model: ");
                String model = input.next();

                cars.add(name + "," + price + "," + model);
                saveCars(cars);

            } else if (choice == 3) {
                running = false;
            }

            if (running) {
                System.out.print("\nDo you want to continue? (yes/no): ");
                String cont = input.next();
                if (!cont.equalsIgnoreCase("yes")) {
                    running = false;
                }
            }
        }

        System.out.println("مع الف سلامه");
    }

    static ArrayList<String> loadCars() {
        ArrayList<String> cars = new ArrayList<>();
        if (!file.exists()) return cars;

        try {
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                cars.add(fileReader.nextLine());
            }
            fileReader.close();
        } catch (Exception e) {
            System.out.println("Error reading cars file: " + e.getMessage());
        }

        return cars;
    }

    static void saveCars(ArrayList<String> cars) {
        try {
            PrintWriter pw = new PrintWriter(file);
            for (String car : cars) {
                pw.println(car);
            }
            pw.close();
        } catch (Exception e) {
            System.out.println("Error saving cars file: " + e.getMessage());
        }
    }
}
