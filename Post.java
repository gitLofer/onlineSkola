import java.util.*;

public class Post {
    Osoba autor;
    Date datumObjave; // Videti https://docs.oracle.com/javase/8/docs/api/java/sql/Date.html
    List<Post> komentari;
    String objavaTekst;

    Post(Osoba autor, Date datumObjave, List<Post> komentari, String objavaTekst) {
        this.autor = autor;
        this.datumObjave = datumObjave;
        this.komentari = komentari;
        this.objavaTekst = objavaTekst;
    }

    // Konstruktor za komentar tj. post koji nije original
    Post(Osoba autor, Date datumObjave, List<Post> komentari, String objavaTekst, Post glava)
    {
        List<Post> temp = glava.getKomentari();
        temp.add(new Post(autor, datumObjave, komentari, objavaTekst))        
    }

    // Nesto trebamo da uradimo za razlikovanje Material i Assignment. Neku formu predaje da ima As dok Mat ima neki "preview"?
    // Ili da napravimo da Assignment nasledjuje Material; tu bi Material zapravo bila ova klasa tj. Post?

    // Getteri i Setteri
    public Osoba getAutor() {
        return autor;
    }
    public Date getDatumObjave() {
        return datumObjave;
    }
    public List<Post> getKomentari() {
        return komentari;
    }
    public String getObjavaTekst() {
        return objavaTekst;
    }

    public void setAutor(Osoba autor) {
        this.autor = autor;
    }
    public void setDatumObjave(Date datumObjave) {
        this.datumObjave = datumObjave;
    }
    public void setKomentari(List<Post> komentari) {
        this.komentari = komentari;
    }
    public void setObjavaTekst(String objavaTekst) {
        this.objavaTekst = objavaTekst;
    }
}
