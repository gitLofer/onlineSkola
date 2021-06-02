public class Komentar{
    private String autor;
    private String datumObjave; // Posle cemo ovo https://docs.oracle.com/javase/8/docs/api/java/sql/Date.html
    // Za sada, UNIX EOCH tj. broj sek. od 1. Jan. 1970
    private String objavaTekst;

    Komentar(String autor, String datumObjave, String objavaTekst) {
        this.autor = autor;
        this.datumObjave = datumObjave;
        this.objavaTekst = objavaTekst;
    }

    // Za debug, izmeniti kasnije
    @Override
    public String toString() {
        String out = "\n" + autor + " // " + datumObjave
                + "\n" + objavaTekst;
        return out;
    }

    // Getteri i Setteri
    public String getAutor() {
        return autor;
    }
    public String getDatumObjave() {
        return datumObjave;
    }
    public String getObjavaTekst() {
        return objavaTekst;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    public void setDatumObjave(String datumObjave) {
        this.datumObjave = datumObjave;
    }
    public void setObjavaTekst(String objavaTekst) {
        this.objavaTekst = objavaTekst;
    }
}
