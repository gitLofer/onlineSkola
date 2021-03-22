import java.util.*;

public class Ucenik extends Osoba {
	private List<Attachment> materijal = new ArrayList<Attachment>();

    Ucenik(String ime, String prezime, int godiste, Pol pol, String email, int id, String sifra, List<Attachment> materijal) {
        super(ime, prezime, godiste, pol, email, id, sifra);
        this.materijal = materijal;
    }

    // Getteri i Setteri
    public void setMaterijal(List<Attachment> materijal) {
        this.materijal = materijal;
    }
    public List<Attachment> getMaterijal() {
        return materijal;
    }
}
