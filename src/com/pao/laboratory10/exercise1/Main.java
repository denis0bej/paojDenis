package src.com.pao.laboratory10.exercise1;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        LinkedList<Tranzactie> tranzactii = new LinkedList<Tranzactie>();
        Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

        while(scanner.hasNext()){
            String comanda = scanner.next();
            if (comanda.equals("ENQUEUE")){
                int id = scanner.nextInt();
                double suma = scanner.nextDouble();
                String data = scanner.next();
                String tip = scanner.next();

                tranzactii.addLast(new Tranzactie(id,suma,data,TipTranzactie.valueOf(tip)));
            }
            else if (comanda.equals("DEQUEUE")){
                if (tranzactii.isEmpty()) System.out.println("Coada goala.");
                else {
                    Tranzactie t = tranzactii.removeFirst();
                    System.out.println("Procesat: " + t.toString());
                }
            }
            else if (comanda.equals("PUSH")){
                int id = scanner.nextInt();
                double suma = scanner.nextDouble();
                String data = scanner.next();
                String tip = scanner.next();

                tranzactii.addFirst(new Tranzactie(id,suma,data,TipTranzactie.valueOf(tip)));
            }
            else if (comanda.equals("POP")){
                if (tranzactii.isEmpty()) System.out.println("Coada goala.");
                else {
                    Tranzactie t = tranzactii.removeFirst();
                    System.out.println("Extras: " + t.toString());
                }
            }
            else if (comanda.equals("REMOVE_DEBIT")){
                Iterator<Tranzactie> it = tranzactii.iterator();
                int nrEliminat = 0;

                while (it.hasNext()) {
                    Tranzactie t = it.next();
                    if (t.getTip() == TipTranzactie.DEBIT) {
                        it.remove();
                        nrEliminat++;
                    }
                }
                System.out.println("Eliminat " + nrEliminat + " tranzactii DEBIT.");
            }
            else if (comanda.equals("REMOVE_BELOW")){
                double threshold = scanner.nextDouble();
                Iterator<Tranzactie> it = tranzactii.iterator();
                int nrEliminat = 0;

                while (it.hasNext()) {
                    Tranzactie t = it.next();
                    if (t.getSuma() < threshold) {
                        it.remove();
                        nrEliminat++;
                    }
                }
                // Formatting threshold to match potential precision requirements (optional but recommended)
                System.out.println("Eliminat " + nrEliminat + " tranzactii sub " + String.format("%.2f", threshold) + " RON.");
            }
            else if (comanda.equals("PRINT")){
                for (Tranzactie t : tranzactii) System.out.println(t.toString());
            }
            else if (comanda.equals("SIZE")){
                System.out.println("Dimensiune coada: " + tranzactii.size());
            }
        }
    }
}
