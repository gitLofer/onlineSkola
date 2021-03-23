import java.util.*;

public class Classroom {
	private Osoba prof;
	private List<Osoba> ucenici;
	private String naziv, odeljenje;
	private List<Post> postovi;
	private int id;
	
	Classroom(Osoba prof, List<Osoba> ucenici, String naziv, String odeljenje, List<Post> postovi, int id){
		this.prof = prof;
		this.ucenici = ucenici;
		this.naziv = naziv;
		this.odeljenje = odeljenje;
		this.postovi = postovi;
		this.id = id;
	}
	
	// Getteri i Setteri
	public Osoba getProf() {
        return prof;
    }
	public List<Osoba> getUcenici() {
        return ucenici;
    }
	public String getNaziv() {
        return naziv;
    }
	public String getOdeljenje() {
        return odeljenje;
    }
	public List<Post> getPostovi() {
        return postovi;
    }
	public int getId() {
        return id;
    }
    
    public void setProf(Osoba prof) {
    	this.prof = prof;
    }
    public void setUcenici(List<Osoba> ucenici) {
    	this.ucenici = ucenici;
    }
    public void setNaziv(String naziv) {
    	this.naziv = naziv;
    }
    public void setOdeljenje(String odeljenje) {
    	this.odeljenje = odeljenje;
    }
    public void setPostovi(List<Post> postovi) {
    	this.postovi = postovi;
    }
    public void setId(int id) {
    	this.id = id;
    }
}
