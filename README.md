# PAOJ 2026

Programare Avansată pe Obiecte în Java — materiale și resurse pentru cursul din 2026.

---

## Întrebări frecvente (FAQ)

### Cum pot să obțin un job pe un proiect Java?

Pentru a lucra pe un proiect Java real, cel mai important lucru în prezent este să înveți **Spring Boot** pentru backend. Spring Boot este frameworkul dominant în industrie pentru aplicații enterprise Java și este cerut în marea majoritate a anunțurilor de angajare pentru dezvoltatori Java.

Pe lângă Spring Boot, **ingineria cloud** a devenit o competență esențială. Certificările cloud — în special certificările **AWS** (Amazon Web Services) — sunt foarte apreciate de angajatori și îți cresc semnificativ șansele de angajare. Este un domeniu în care eu însumi investesc în prezent, urmând o certificare AWS.

**Pe scurt:**
- Învață **Spring Boot** pentru a construi aplicații backend Java solide.
- Obține o **certificare AWS** (sau alt furnizor cloud) pentru a demonstra competențe în cloud engineering.

---

### Pot îmbina mai mulți comparatori în `Arrays.sort()` pentru a sorta după sub-criterii?

Da! Poți îmbina mai mulți comparatori în Java folosind metoda **`thenComparing()`**, introdusă în Java 8. Aceasta permite crearea unui lanț de criterii de sortare (lexicografice): dacă primul comparator consideră elementele egale, se trece la următorul.

**Metode principale pentru concatenare:**
- `Comparator.comparing()` — creează comparatorul de bază (primul criteriu).
- `.thenComparing()` — adaugă un sub-criteriu evaluat doar dacă rezultatul anterior a fost `0` (egalitate).
- `.reversed()` — inversează ordinea unui comparator specific sau a întregului lanț.

**Exemplu practic:**

Dacă ai o listă de obiecte `Angajat` și vrei să sortezi întâi după `Nume` (criteriu principal), apoi după `Vârstă` (sub-criteriu):

```java
import java.util.Comparator;
import java.util.List;

// Sortează după nume, apoi după vârstă
listaAngajati.sort(
    Comparator.comparing(Angajat::getNume)
              .thenComparing(Angajat::getVarsta)
);
```

**Variante avansate:**
- **Sortare inversă pe un singur criteriu:** Folosește `Comparator.comparing(Angajat::getNume, Comparator.reverseOrder())` pentru a inversa doar primul criteriu, menținând sub-criteriile în ordine naturală.
- **Gestionarea valorilor nule:** Folosește `nullsFirst()` sau `nullsLast()` în interiorul lanțului pentru a evita `NullPointerException`.
- **Metode primitive:** Pentru performanță mai bună, folosește variantele specializate precum `thenComparingInt()` sau `thenComparingLong()` pentru a evita procesul de autoboxing.
