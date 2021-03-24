import java.util.*;

public class Attachment {
	private Osoba autor;
	private String naziv;
	private String link;
	private int id;

    Attachment(Osoba autor, String naziv, String link, int id) {
        this.autor = autor;
        this.naziv = naziv;
        this.link = link;
        this.id = id;
    }

    // Getteri i Setteri
    public String getLink() {
        return link;
    }
    public String getNaziv() {
        return naziv;
    }
    public Osoba getAutor() {
        return autor;
    }
    public int getId() { return id; }

    public void setLink(String link) {
        this.link = link;
    }
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    public void setAutor(Osoba autor) {
        this.autor = autor;
    }
    public void setId(int id) { this.id = id; }
}
