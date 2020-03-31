/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import beans.Product;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author crether
 */
public class ProductIO {

    private static String filebase
            = System.getProperty("user.dir") + File.separator + "src"
            + File.separator + "res" + File.separator;
    private static List<Product> products = new ArrayList<>();
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.YYYY");
    private static int productsRead = 0;

    public static void readProducts() throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(filebase + "products.csv");
        BufferedReader br = new BufferedReader(fr);

        String line = br.readLine();
        while ((line = br.readLine()) != null) {
            ArrayList<String> nameParts = new ArrayList<>();
            String[] parts;

            if (line.contains("\"")) {
                int first = line.indexOf("\"");
                int second = line.indexOf("\"", 2);
                int hyphen = line.indexOf("-");
                if (hyphen == -1) {
                    nameParts.add(line.substring(first+1, second));
                } else {
                    nameParts.add(line.substring(first, hyphen-1));
                    nameParts.add(line.substring(hyphen + 1, second));
                }
                line = line.substring(second + 1);
            }
            parts = line.split(",");

            if (nameParts.size() == 0) {
                int hyphen = line.indexOf("-");
                int comma = line.indexOf(",");
                if (hyphen == -1) {
                    nameParts.add(line.substring(0, comma));
                } else {
                    nameParts.add(line.substring(1, hyphen));
                    nameParts.add(line.substring(hyphen + 2, comma));
                }
            }

            int quantity = Integer.parseInt(parts[1]);
            double price = Double.parseDouble(parts[2]);
            Product product;
            if (parts.length < 4 && nameParts.size() == 2) {
                product = new Product(nameParts.get(0), nameParts.get(1), quantity, price);
            } else if (nameParts.size() == 2 && parts.length == 4) {
                String[] dateParts = parts[3].split("/");
                LocalDate date = LocalDate.of(
                        Integer.parseInt(dateParts[2]),
                        Integer.parseInt(dateParts[1]),
                        Integer.parseInt(dateParts[0]));
                product = new Product(nameParts.get(0), nameParts.get(1), quantity, price);
            } else if (parts.length < 4 && nameParts.size() == 1) {
                product = new Product(nameParts.get(0), "", quantity, price);
            } else {
                String[] dateParts = parts[3].split("/");
                LocalDate date = LocalDate.of(
                        Integer.parseInt(dateParts[2]),
                        Integer.parseInt(dateParts[1]),
                        Integer.parseInt(dateParts[0]));
                product = new Product(nameParts.get(0), "", quantity, price, date);
            }
            if (products.contains(product)) {
                int index = products.indexOf(product);
                Product contains = products.get(index);
                contains.setQuantity(contains.getQuantity() + product.getQuantity());
                products.set(index, contains);
            } else {
                products.add(product);
            }
            productsRead++;
        }
    }

    public static void evaluateProducts() {
        System.out.println("Products read: " + productsRead);
        double price = 0;
        int quantity = 0;
        LocalDate minDate = LocalDate.MAX;
        LocalDate maxDate = LocalDate.MIN;
        for (Product product : products) {
            price += product.getPrice() * product.getQuantity();
            quantity += product.getQuantity();
            LocalDate productDate = product.getExpiration();
            if (productDate != null) {
                if (productDate.isAfter(maxDate)) {
                    maxDate = productDate;
                } else if (productDate.isBefore(minDate)) {
                    minDate = productDate;
                }
            }

        }
        System.out.println("Different products: " + products.size());

        System.out.println(String.format("Total price of products: " + NumberFormat.getNumberInstance(Locale.GERMAN).format(price) + " €"));

        System.out.println(String.format("Total numbers of items: %d", quantity));
        System.out.println("Minimum expiration date: " + dtf.format(minDate));
        System.out.println("Maximum expiration date: " + dtf.format(maxDate));
    }

}
