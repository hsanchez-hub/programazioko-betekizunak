import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Funtzioak {

    public static void erreserbaAldatu(Scanner sc,
                                       Erreserba[] erreserbak,
                                       ErosketaSistema sistema,
                                       DateTimeFormatter formatter) {

        if (erreserbak == null || erreserbak.length == 0) {
            System.out.println("Ez dago oraindik erreserbarik sortuta.");
            pausatu(sc);
            return;
        }

        System.out.print("\nAldatu nahi duzun erreserbaren kodea (adib. FXXXXXX): ");
        String kodeBilatu = sc.nextLine().toUpperCase();

        Erreserba erreserbaAurkitua = null;
        int erreserbaIndex = -1;
        for (int k = 0; k < erreserbak.length; k++) {
            if (erreserbak[k].kodea.equalsIgnoreCase(kodeBilatu)) {
                erreserbaAurkitua = erreserbak[k];
                erreserbaIndex = k;
                break;
            }
        }

        if (erreserbaAurkitua == null) {
            System.out.println("Ez da aurkitu emandako kodearekin erreserbarik.");
            pausatu(sc);
            return;
        }

        System.out.print("\nErabiltzailea: ");
        String erabiltzaileSartutakoa = sc.nextLine();
        System.out.print("Kredentziala: ");
        String kredentzialSartutakoa = sc.nextLine();

        boolean datuZuzena =
                erabiltzaileSartutakoa.equals(erreserbaAurkitua.bidaiaria.erabiltzailea) &&
                        kredentzialSartutakoa.equals(erreserbaAurkitua.bidaiaria.kredentziala);

        if (!datuZuzena) {
            System.out.println("Erabiltzailea edo kredentziala okerra da. Ezin da erreserba aldatu.");
            pausatu(sc);
            return;
        }

        LocalDate data = erreserbaAurkitua.bidaiDataOrdua.toLocalDate();

        System.out.println("\nERRESERBA AURKITUA");
        System.out.println("  Kodea:    " + erreserbaAurkitua.kodea);
        System.out.println("  Data:     " + data.format(formatter));
        System.out.println("  Bidaiaria:" + erreserbaAurkitua.bidaiaria.izena + " " + erreserbaAurkitua.bidaiaria.abizena);
        System.out.println("  Trena:    " + erreserbaAurkitua.tren.kodea);
        System.out.println("  Eserlekuak:" + String.join(", ", erreserbaAurkitua.eserlekuErreserbatuak));
        System.out.println("  Ibilbidea:" + erreserbaAurkitua.irteeraGeltokia + " -> " + erreserbaAurkitua.helmugaGeltokia);
        System.out.println("  Irteera:  " + erreserbaAurkitua.tren.lehenGeltokitikIrteeraOrdua.toLocalTime());

        System.out.print("\n1) Data aldatu nahi duzu? (B/E): ");
        String aldatuData = sc.nextLine().toUpperCase();
        LocalDate dataBerria = data;
        if (aldatuData.equals("B")) {
            System.out.print("Data berria (YYYY-MM-DD): ");
            dataBerria = LocalDate.parse(sc.nextLine(), formatter);
        }

        System.out.print("\n2) Eserlekua aldatu nahi duzu? (B/E): ");
        String aldatuEserlekuak = sc.nextLine().toUpperCase();
        if (aldatuEserlekuak.equals("B")) {
            erreserbaAurkitua.tren.liberaEserlekua(data, erreserbaAurkitua.eserlekuErreserbatuak[0]);

            System.out.println("Eserlekuen egoera trenean (0 = libre, X = okupatuta):");
            erreserbaAurkitua.tren.inprimatuEserlekuak(dataBerria);

            System.out.print("Eserleku berria (adib. A6): ");
            String eserlekuaBerria = sc.nextLine().toUpperCase();
            while (!erreserbaAurkitua.tren.erreserbatu(dataBerria, eserlekuaBerria)) {
                System.out.println("Eserlekua okupatuta dago edo ez da existitzen.");
                System.out.print("Sartu beste eserleku bat: ");
                eserlekuaBerria = sc.nextLine().toUpperCase();
            }
            erreserbaAurkitua.eserlekuErreserbatuak[0] = eserlekuaBerria;
        }

        System.out.print("\n3) Trenaren ordua aldatu nahi duzu? (B/E): ");
        String aldatuOrdua = sc.nextLine().toUpperCase();
        Tren trenBerria = erreserbaAurkitua.tren;
        if (aldatuOrdua.equals("B")) {
            Tren[] trenGuztiak = sistema.getTrenak();
            Ibilbide[] ibilbideak = sistema.getIbilbideak();

            System.out.println("\nData honetarako eskuragarri dauden trenak (ibilbidea " +
                    erreserbaAurkitua.tren.ibilbidea + "):");

            int kont = 1;
            for (Tren t : trenGuztiak) {
                if (t.ibilbidea == erreserbaAurkitua.tren.ibilbidea) {
                    Ibilbide ib = ibilbideak[t.ibilbidea - 1];
                    String hasiera = ib.geltokiak[0];
                    String amaiera = ib.geltokiak[ib.geltokiak.length - 1];

                    System.out.printf("  %d) Trena %s  [%s → %s]  (irteera %s)%n",
                            kont++,
                            t.kodea,
                            hasiera,
                            amaiera,
                            t.lehenGeltokitikIrteeraOrdua.toLocalTime());
                }
            }

            System.out.print("➤ Zein tren hartu nahi duzu? (1-" + (kont - 1) + "): ");
            int trenAukera = Integer.parseInt(sc.nextLine());

            kont = 1;
            for (Tren t : trenGuztiak) {
                if (t.ibilbidea == erreserbaAurkitua.tren.ibilbidea) {
                    if (kont == trenAukera) {
                        trenBerria = t;
                        break;
                    }
                    kont++;
                }
            }

            erreserbaAurkitua.tren.liberaEserlekua(dataBerria, erreserbaAurkitua.eserlekuErreserbatuak[0]);

            while (!trenBerria.erreserbatu(dataBerria, erreserbaAurkitua.eserlekuErreserbatuak[0])) {
                System.out.println("✖ Eserlekua okupatuta dago tren berrian.");
                System.out.print("➤ Eserleku berria aukeratu (adib. A6): ");
                String eserlekuaBerria = sc.nextLine().toUpperCase();
                erreserbaAurkitua.eserlekuErreserbatuak[0] = eserlekuaBerria;
            }

            System.out.println("✔ Trena aldatu da: " + trenBerria.kodea + " (" +
                    trenBerria.lehenGeltokitikIrteeraOrdua.toLocalTime() + ")");
        }

        erreserbaAurkitua.tren = trenBerria;
        erreserbaAurkitua.bidaiDataOrdua = dataBerria.atTime(trenBerria.lehenGeltokitikIrteeraOrdua.toLocalTime());

        boolean egonDiraAldaketak = !aldatuData.equals("E") || !aldatuEserlekuak.equals("E") || !aldatuOrdua.equals("E");

        if (egonDiraAldaketak) {
            double prezioBerria = sistema.getPrezioa(
                    erreserbaAurkitua.tren.ibilbidea,
                    erreserbaAurkitua.irteeraGeltokia,
                    erreserbaAurkitua.helmugaGeltokia
            );
            System.out.printf("  Prezio berria: %.2f €%n", prezioBerria);
        }

        System.out.println("\n✔ Erreserba ondo aldatu da!");
        System.out.printf("  Data berria: %s%n", dataBerria.format(formatter));
        System.out.printf("  Trena: %s (%s)%n", erreserbaAurkitua.tren.kodea, erreserbaAurkitua.tren.lehenGeltokitikIrteeraOrdua.toLocalTime());
        System.out.printf("  Eserlekuak: %s%n", String.join(", ", erreserbaAurkitua.eserlekuErreserbatuak));
        pausatu(sc);
    }

    private static void pausatu(Scanner sc) {
        System.out.println("Sakatu ENTER jarraitzeko...");
        sc.nextLine();
    }
}

