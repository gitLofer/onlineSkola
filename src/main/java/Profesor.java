import java.util.*;

public class Profesor extends Osoba {
	private List<String> odeljenja;
	private String predmet;
    Profesor(String ime, String prezime, int godiste, Pol pol, String email, int id, String sifra, List<String> odeljenja, String predmet){
        super(ime, prezime, godiste, pol, email, id, sifra);
        this.odeljenja = odeljenja;
        this.predmet = predmet;
    }
    
    
    // Getteri i Setteri
    public void setOdeljenja(List<String> odeljenja) {
    	this.odeljenja = odeljenja;
    }
    public void setPredmet(String predmet) {
    	this.predmet = predmet;
    }
    public List<String> getOdeljenja(){
    	return odeljenja;
    }
    public String getPredmet(){
    	return predmet;
    }

}
