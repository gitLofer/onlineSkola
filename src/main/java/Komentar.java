public class Komentar{
    private int autor;
    private int datumObjave; // Posle cemo ovo https://docs.oracle.com/javase/8/docs/api/java/sql/Date.html
    // Za sada, UNIX EOCH tj. broj sek. od 1. Jan. 1970
    private String objavaTekst;

    Komentar(int autor, int datumObjave, String objavaTekst) {
        this.autor = autor;
        this.datumObjave = datumObjave;
        this.objavaTekst = objavaTekst;
    }

    // Za debug, izmeniti kasnije
    @Override
    public String toString() {
        return "Komentar{" +
                "autor=" + autor +
                ", datumObjave=" + datumObjave +
                ", objavaTekst='" + objavaTekst + '\'' +
                '}' + '\n';
    }

    // Getteri i Setteri
    public int getAutor() {
        return autor;
    }
    public int getDatumObjave() {
        return datumObjave;
    }
    public String getObjavaTekst() {
        return objavaTekst;
    }

    public void setAutor(int autor) {
        this.autor = autor;
    }
    public void setDatumObjave(int datumObjave) {
        this.datumObjave = datumObjave;
    }
    public void setObjavaTekst(String objavaTekst) {
        this.objavaTekst = objavaTekst;
    }
}
