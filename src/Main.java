import persoon.passagier.Passagier;
import persoon.personeel.*;
import trein.Trein;
import trein.locomotief.Locomotief;
import trein.locomotief.LocomotiefType;
import trein.wagon.Wagon;
import trein.wagon.WagonKlasse;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Set<Passagier> passagiers = new HashSet<>();
    private static final Set<Personeelslid> personeelsleden = new HashSet<>();
    private static final List<Trein> treinen = new ArrayList<>();

    public static void main(String[] args) {
        boolean status = true;
        while (status) {
            int keuze = leesKeuze();
            switch (keuze) {
                case 1 -> registreerPassagier();
                case 2 -> registreerPersoneelslid();
                case 3 -> maakTrein();
                case 10 -> status = false;
            }
        }
    }

    private static void maakTrein() {
        Locomotief locomotief = kiesLocomotief();
        int maximumAantalWagons = locomotief.getLocomotiefType().getMaximumAantalWagons();
        List<Wagon> wagons = maakWagons(maximumAantalWagons);
        Trein trein = new Trein(locomotief, wagons);
        treinen.add(trein);
        System.out.println("Aanmaken trein was succesvol");
    }

    private static List<Wagon> maakWagons(int maximumAantalWagons) {
        while (true) {
            System.out.println("Maximum aantal wagons: " + maximumAantalWagons);
            int eersteKlasseWagons = vraagGetal("Kies het aantal eerste klasse wagons");
            int tweedeKlasseWagons = vraagGetal("Kies het aantal tweede klasse wagons");
            if (eersteKlasseWagons + tweedeKlasseWagons <= maximumAantalWagons) {
                List<Wagon> wagons = new ArrayList<>();
                for (int i = 0; i < eersteKlasseWagons; i++) {
                    wagons.add(new Wagon(WagonKlasse.EERSTE_KLASSE));
                }
                for (int i = 0; i < tweedeKlasseWagons; i++) {
                    wagons.add(new Wagon(WagonKlasse.TWEEDE_KLASSE));
                }
                return wagons;
            }
            System.out.println("Je hebt te veel wagons gekozen, probeer opnieuw (maximum: " + maximumAantalWagons + ")");
        }
    }

    private static int vraagGetal(String boodschap) {
        while (true) {
            System.out.println(boodschap);
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine();
                if (value >= 0) {
                    return value;
                }
                System.out.println("Geef een positief getal");
            } else {
                System.out.println("Ongeldige, geef een getal");
                scanner.nextLine();
            }
        }
    }

    private static Locomotief kiesLocomotief() {
        while (true) {
            System.out.print("Kies een locomotief type (373 of 374): ");
            String type = scanner.nextLine();

            if (type.equals("373")) {
                return new Locomotief(LocomotiefType.CLASS_373);
            } else if (type.equals("374")) {
                return new Locomotief(LocomotiefType.CLASS_374);
            } else {
                System.out.println("Ongeldige keuze");
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
                System.out.println("Successvol geregistreerd");
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
        System.out.println("Successvol geregistreerd");
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
        System.out.println("2. Registreer een personeelslid");
        System.out.println("3. Maak een trein");
    }
}