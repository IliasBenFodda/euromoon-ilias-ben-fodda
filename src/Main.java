import persoon.passagier.Passagier;
import persoon.personeel.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Set<Passagier> passagiers = new HashSet<>();
    private static final Set<Personeelslid> personeelsleden = new HashSet<>();

    public static void main(String[] args) {
        boolean status = true;
        while (status) {
            int keuze = leesKeuze();
            scanner.nextLine();
            switch (keuze) {
                case 1 -> registreerPassagier();
                case 2 -> registreerPersoneelslid();
                case 10 -> status = false;
            }
        }
    }

    private static int leesKeuze() {
        while (true) {
            getWelkomMenu();
            if (!scanner.hasNextInt()) {
                System.out.println("Ongeldige invoer, probeer opnieuw");
                scanner.nextLine();
                continue;
            }
            int keuze = scanner.nextInt();
            scanner.nextLine();
            return keuze;
        }
    }

    private static void registreerPersoneelslid() {
        System.out.println("Naam: ");
        String naam = scanner.nextLine();
        System.out.println("Achternaam: ");
        String achternaam = scanner.nextLine();
        System.out.println("Rijkregisternummer: ");
        String rijkregisternummer = scanner.nextLine();
        LocalDate geboortedatum = formatGeboortedatum(scanner);

        while (true) {
            System.out.println("Kies een functie: Bagage, Conducteur, Steward, Bestuurder");
            String functie = scanner.nextLine();
            try {
                Personeelslid personeelslid = maakPersoneelslid(functie, naam, achternaam, rijkregisternummer, geboortedatum);
                personeelsleden.add(personeelslid);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Onbekende functie, probeer opnieuw");
            }
        }
    }

    private static Personeelslid maakPersoneelslid(String functie, String naam, String achternaam, String rijkregisternummer, LocalDate geboortedatum) {
        return switch (functie.toLowerCase()) {
            case "bagage" -> new BagageMedewerker(naam, achternaam, rijkregisternummer, geboortedatum);
            case "conducteur" -> new Conducteur(naam, achternaam, rijkregisternummer, geboortedatum);
            case "steward" -> new Steward(naam, achternaam, rijkregisternummer, geboortedatum);
            case "bestuurder" -> new TreinBestuurder(naam, achternaam, rijkregisternummer, geboortedatum);
            default -> throw new IllegalArgumentException("Onbekende functie: " + functie.toLowerCase());
        };
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