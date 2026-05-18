package src.com.pao.laboratory10.exercise2;

import src.com.pao.laboratory10.exercise1.Tranzactie;
import src.com.pao.laboratory10.exercise1.TipTranzactie;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        if (!scanner.hasNextInt()) return;

        int n = scanner.nextInt();
        List<Tranzactie> lista = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int id = scanner.nextInt();
            double suma = scanner.nextDouble();
            String data = scanner.next();
            TipTranzactie tip = TipTranzactie.valueOf(scanner.next().toUpperCase());

            lista.add(new Tranzactie(id, suma, data, tip));
        }

        while (scanner.hasNext()) {
            String comanda = scanner.next();

            switch (comanda) {
                case "UNIQUE_IDS":
                    LinkedHashSet<Integer> uniqueIds = new LinkedHashSet<>();
                    for (Tranzactie t : lista) {
                        uniqueIds.add(t.getId());
                    }
                    System.out.println("IDs unice (" + uniqueIds.size() + "): " + uniqueIds);
                    break;

                case "MONTHLY_REPORT":
                    TreeMap<String, double[]> report = new TreeMap<>();
                    for (Tranzactie t : lista) {
                        String luna = t.getData().substring(0, 7);
                        report.putIfAbsent(luna, new double[]{0.0, 0.0});

                        if (t.getTip() == TipTranzactie.CREDIT) {
                            report.get(luna)[0] += t.getSuma();
                        } else {
                            report.get(luna)[1] += t.getSuma();
                        }
                    }

                    for (Map.Entry<String, double[]> entry : report.entrySet()) {
                        System.out.printf(Locale.US, "%s: CREDIT %.2f RON, DEBIT %.2f RON\n",
                                entry.getKey(), entry.getValue()[0], entry.getValue()[1]);
                    }
                    break;

                case "TOP":
                    int nrTop = scanner.nextInt();
                    System.out.println("Top " + nrTop + ":");

                    List<Tranzactie> copieSortata = new ArrayList<>(lista);
                    copieSortata.sort(Comparator.comparingDouble(Tranzactie::getSuma).reversed());

                    int limita = Math.min(nrTop, copieSortata.size());
                    for (int i = 0; i < limita; i++) {
                        System.out.println(copieSortata.get(i));
                    }
                    break;

                case "SORT_ASC":
                    lista.sort(Comparator.comparingDouble(Tranzactie::getSuma));
                    for (Tranzactie t : lista) System.out.println(t);
                    break;

                case "SORT_DESC":
                    lista.sort(Comparator.comparingDouble(Tranzactie::getSuma).reversed());
                    for (Tranzactie t : lista) System.out.println(t);
                    break;

                case "REVERSE":
                    Collections.reverse(lista);
                    for (Tranzactie t : lista) System.out.println(t);
                    break;

                case "MIN_MAX":
                    if (!lista.isEmpty()) {
                        Tranzactie min = Collections.min(lista, Comparator.comparingDouble(Tranzactie::getSuma));
                        Tranzactie max = Collections.max(lista, Comparator.comparingDouble(Tranzactie::getSuma));
                        System.out.println("MIN: " + min);
                        System.out.println("MAX: " + max);
                    }
                    break;

                case "CME_DEMO":
                    try {
                        for (Tranzactie t : lista) {
                            lista.remove(t);
                        }
                    } catch (ConcurrentModificationException e) {
                        System.out.println("ConcurrentModificationException prins: modificare in iteratie detectata.");
                    }
                    break;
            }
        }

        scanner.close();
    }
}