# Online skola - Java projekat za OOP  
  
## Projekat  
Cilj projekta je rekreacija [Google Classroom-a](https://classroom.google.com/) na unikatana i poboljsan nacin.  
Ispod se nalazi lista klasa koje su planirane za sada, menjace se kako projekat napreduje.  
  
## Klase  
Osoba (ime + prezime, godiste, pol, mail, slika, id, sifra)  
→ Profesor (List\<Odeljenje\>, Predmet)  
→ Ucenik (List\<Attachment\> MojRad)  
  
Post (Osoba, Datum, List\<Post\> Komentari, Text)  
→ Assignment (Rok, Attachment ZaUraditi, List\<Attachment\> DobijeniRad)  
→ Material (Attachment Materijal)  
  
Classroom (Profesor, List\<Ucenik\>, Naziv, Odeljenje, List\<Post\> Stvari, id)  
  
Attachment (Osoba, ime, List\<File\>)  
  
## TODO  
- [x] *Napraviti ReadMe koji lici na nesto*  
- [x] *Prebaciti sve na Maven radi lakseg zajednickog rada (i biblioteka)*  
- [ ] *Zavrsiti cuvanje podataka i njhov pristup (JSON)*  
- [ ] *Stvoriti osnovnu funkcionalnost (u vidu terminala)*  
- [ ] *Pretvoriti prethodnu funkcionalnost u GUI (JavaFX, JFrame, etc.)*
