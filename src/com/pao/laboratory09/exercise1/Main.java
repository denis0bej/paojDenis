package src.com.pao.laboratory09.exercise1;

import java.io.*;
import java.util.*;

public class Main {
    private static final String OUTPUT_FILE = "output/lab09_ex1.ser";

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        // Enforce Locale.US for scanner so it properly reads decimal numbers with a dot
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        // Ensure the output directory exists before attempting to create the file
        File file = new File(OUTPUT_FILE);
        if (file.getParentFile() != null) {
            file.getParentFile().mkdirs();
        }

        if (!scanner.hasNextInt()) return;

        int n = scanner.nextInt();
        List<Tranzactie> tranzactii = new ArrayList<>();

        // 1. Citește N din stdin, apoi cele N tranzacții
        for (int i = 0; i < n; i++) {
            int id = scanner.nextInt();
            double suma = scanner.nextDouble();
            String data = scanner.next();
            String contSursa = scanner.next();
            String contDestinatie = scanner.next();
            String tipStr = scanner.next();
            TipTranzactie tip = TipTranzactie.valueOf(tipStr);

            Tranzactie t = new Tranzactie(id, suma, data, contSursa, contDestinatie, tip);
            // 2. Setează câmpul note = "procesat"
            t.setNote("procesat");
            tranzactii.add(t);
        }

        // 3. Serializează lista de tranzacții cu ObjectOutputStream
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(OUTPUT_FILE))) {
            oos.writeObject(tranzactii);
        }

        // 4. Deserializează lista din OUTPUT_FILE cu ObjectInputStream
        List<Tranzactie> deserializedTranzactii = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(OUTPUT_FILE))) {
            deserializedTranzactii = (List<Tranzactie>) ois.readObject();
        }

        // 5. Procesează comenzile din stdin până la EOF
        while (scanner.hasNext()) {
            String command = scanner.next();

            if (command.equals("LIST")) {
                for (Tranzactie t : deserializedTranzactii) {
                    System.out.println(t);
                }
            } else if (command.equals("FILTER")) {
                String prefix = scanner.next();
                boolean found = false;
                for (Tranzactie t : deserializedTranzactii) {
                    if (t.getData().startsWith(prefix)) {
                        System.out.println(t);
                        found = true;
                    }
                }
                if (!found) {
                    System.out.println("Niciun rezultat.");
                }
            } else if (command.equals("NOTE")) {
                int id = scanner.nextInt();
                boolean found = false;
                for (Tranzactie t : deserializedTranzactii) {
                    if (t.getId() == id) {
                        System.out.println("NOTE[" + id + "]: " + t.getNote());
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("NOTE[" + id + "]: not found");
                }
            }
        }

        scanner.close();
    }
}