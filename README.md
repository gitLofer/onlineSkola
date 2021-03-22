# Online škola - Java projekat za OOP

## Projekat
Cilj projekta je rekreacija [Google Classroom-a](https://classroom.google.com/) na unikatana i poboljsan način.
Ispod se nalazi lista klasa koje su planirane za sada, menjaće se kako projekat napreduje.

## Klase
Osoba (ime + prezime, godište, pol, mail, slika, id, šifra)
→ Profesor (List\<Odeljenje\>, Predmet)
→ Ucenik (List\<Attachment\> MojRad)

Post (Osoba, Datum, List\<Post\> Komentari, Text)
→ Assignment (Rok, Attachment ZaUraditi, List\<Attachment\> DobijeniRad)
→ Material (Attachment Materijal)

Classroom (Profesor, List\<Ucenik\>, Naziv, Odeljenje, List\<Post\> Stvari, id)

Attachment (Osoba, ime, List\<File\>)

## TODO
- [X] *Napraviti ReadMe koji liči na nesto*
- [X] *Prebaciti sve na Maven radi lakšeg zajedničkog rada (i biblioteka)*
- [ ] *Završiti čuvanje podataka i njhov pristup (JSON)*
- [ ] *Stvoriti osnovnu funkcionalnost (u vidu terminala)*
- [ ] *Pretvoriti prethodnu funkcionalnost u GUI (JavaFX, JFrame, etc.)*
