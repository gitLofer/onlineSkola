import java.util.*;

public class Ucenik extends Osoba {
    List<Attachment> materijal = new ArrayList<Attachment>();

    Ucenik(String ime, String prezime, int godiste, Pol pol, List<Attachment> materijal) {
        super(ime, prezime, godiste, pol);
        this.materijal = materijal;
    }

    public void setMaterijal(List<Attachment> materijal) {
        this.materijal = materijal;
    }
    public List<Attachment> getMaterijal() {
        return materijal;
    }
}
