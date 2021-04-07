# Online škola - Java projekat za OOP

## Projekat
Cilj projekta je rekreacija [Google Classroom-a](https://classroom.google.com/) na unikantan i poboljšan način.  
Ispod se nalazi lista klasa koje su planirane za sada, menjaće se kako projekat napreduje.

## Klase
Osoba (ime + prezime, pol, mail, slika, id, šifra)

Post (Osoba, Datum, List\<Post\> Komentari, Text)  
→ Assignment (Rok, Attachment ZaUraditi, List\<Attachment\> DobijeniRad)  
→ Material (Attachment Materijal)

Classroom (Osoba profesor, List\<Osoba\> učenici, Naziv, Odeljenje, List\<Post\> Stvari, id)

Attachment (Osoba, ime, List\<File\>)

## TODO
- [x] *Napraviti ReadMe koji liči na nesto*
- [x] *Prebaciti sve na Maven radi lakšeg zajedničkog rada (i biblioteka)*
- [ ] *Zavrsiti čuvanje podataka i njihov pristup (JSON)*
  - [X] *Napraviti Interface za JSON interakciju*
  - [X] *Napraviti sve potrebne .json fajlove i popuniti sa test primerom*
  - [X] *Namestiti da radi ijedna klasa (Osoba) sa funkcija loadFromJSON i saveToJSON*
  - [X] *Napraviti public static funkciju interface-a za brisanje*
  - [ ] *Povezati sve potrebne klase na interface*
  - [ ] *Napraviti exception za InvalidIDLookup*
- [ ] *Stvoriti osnovnu funkcionalnost (u vidu terminala)*
- [ ] *Pretvoriti prethodnu funkcionalnost u GUI (JavaFX, JFrame, etc.)*
