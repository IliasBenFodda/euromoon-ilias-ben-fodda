import persoon.passagier.Passagier;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Set<Passagier> passagiers = new HashSet<>();

    public static void main(String[] args) {
        boolean status = true;
        while (status) {
            getWelkomMenu();
            int keuze = scanner.nextInt();
            scanner.nextLine();
            switch (keuze) {
                case 1 -> registreerPassagier();
                case 10 -> status = false;
            }
        }
    }

    private static void registreerPassagier() {
        System.out.println("Naam: ");
        String naam = scanner.nextLine();
        System.out.println("Achternaam: ");
        String achternaam = scanner.nextLine();
        System.out.println("Rijkregisternummer: ");
        String rijkregisternummer = scanner.nextLine();
        LocalDate geboortedatum = formatGeboortedatum(scanner);
        Passagier passagier = new Passagier(naam, achternaam, rijkregisternummer, geboortedatum);
        passagiers.add(passagier);
    }

    private static LocalDate formatGeboortedatum(Scanner scanner) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        while (true) {
            System.out.println("Geboortedatum (dd-MM-yyyy): ");
            String geboortedatum = scanner.nextLine();
            try {
                return LocalDate.parse(geboortedatum, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Ongeldig formaat, probeer opnieuw");
            }
        }
    }

    private static void getWelkomMenu() {
        System.out.println("Welkom bij de Euromoon applicatie");
        System.out.println("Via het onderstaande menu kan je kiezen wat je wil doen:");
        System.out.println("1. Registreer een passagier");
    }
}