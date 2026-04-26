# Proiect PAO - Aplicație Bancară (Etapa I)

## 1.1 Lista acțiunilor / interogărilor posibile în sistem
1. Adăugarea unui client nou în sistem.
2. Deschiderea unui cont curent pentru un client.
3. Deschiderea unui cont de economii pentru un client.
4. Emiterea unui card atașat unui anumit cont bancar.
5. Efectuarea unei depuneri de numerar într-un cont.
6. Efectuarea unui transfer bancar între două conturi (pe baza IBAN-ului).
7. Căutarea unui cont specific folosind IBAN-ul.
8. Afișarea tuturor clienților băncii, sortați alfabetic după nume.
9. Listarea tuturor conturilor deținute de un anumit client.
10. Afișarea istoricului de tranzacții (extrasului de cont) pentru un anumit cont.

## 1.2 Tipurile de obiecte din domeniu (Minim 8)
1. **Utilizator** (Clasă abstractă)
2. **Client** (Extinde Utilizator)
3. **ContBancar** (Clasă abstractă / Interfață de bază)
4. **ContCurent** (Extinde ContBancar)
5. **ContEconomii** (Extinde ContBancar)
6. **Card**
7. **Tranzactie** (Clasă imutabilă - înregistrează detaliile unui transfer)
8. **Banca** (Entitatea care ține evidența sucursalelor/clienților)

