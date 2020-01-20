package functions;

import beans.Company;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class CompanyFunctions {
    private List<Company> companies;

    public void loadData() throws IOException {
        Path path = Paths.get(System.getProperty("user.dir"), "src", "res", "company_data.csv");
        companies = Files.lines(path)
                .skip(1)
                .map(Company::new)
                .collect(Collectors.toList());

//        companies.forEach(System.out::println);
    }

    public void performStreamingtest() {
        // sortieren
        List<Company> sorted = companies.stream()
                .sorted(Comparator.comparing(Company::getCountry)
                        .thenComparing(Company::getFounded))
                .collect(Collectors.toList());

        //filtern
        List<Company> filtered = companies.stream()
                .filter(c -> c.getFounded().isBefore(LocalDate.of(2000, 1, 1)))
                .sorted(Comparator.comparing(Company::getCountry)
                        .thenComparing(Company::getFounded))
                .collect(Collectors.toList());

        //Sum of all turnovers
        double sum = companies.stream().mapToDouble(Company::getTurnover).sum();

        // alle namen, nicht doppelt, aufsteigend in string array

        Set<String> setC = new HashSet<>(companies.stream().map(Company::getCountry).collect(Collectors.toList()));
        String[] array = (String[]) setC.stream().sorted().toArray();
        Arrays.asList(array).forEach(System.out::println);

        String[] array2 = (String[]) companies.stream()
                .map(Company::getCountry)
                .distinct()
                .sorted()
                .toArray();

//        sorted.forEach(System.out::println);
//        filtered.forEach(System.out::println);
        System.out.println("total sum: " + sum);
    }

    public static void main(String[] args) {
        CompanyFunctions cf = new CompanyFunctions();
        try {
            cf.loadData();
            cf.performStreamingtest();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
