import java.util.*;

public class Program {
	public static void main(String[] args) {
		boolean b = false;
		while(!b) {
			System.out.print("Dobrodosli u nas Klasrum!\n\n\n");
			System.out.print("Ukucajte broj opcije koju zelite: \n");
			System.out.print("\t1) Ulogujte se\n");
			System.out.print("\t2) Napravite nalog\n");
			System.out.print("\t3) Prekinete program\n");
			Scanner sken = new Scanner(System.in);
			int izbor = sken.nextInt();
			if(izbor == 1) {
				String sifra, email, kriptosifra;
				Osoba o;
				while(true) {
					System.out.print("Ukucajte vase podatke ispod da biste se ulogovali: \n");
					System.out.print("\tEmail: ");
					email = sken.next();
					System.out.print("\tSifra: ");
					sifra = sken.next();
					kriptosifra = org.apache.commons.codec.digest.DigestUtils.sha256Hex(sifra);
					o = new Osoba(email, kriptosifra);
					//Ulogovano
					if(o.getIme() != null) {
						System.out.print("Dobrodosao " + o.getIme() + " " + o.getPrezime() + " nazad!\n\n");
						System.out.print("Imate sledece opcije: \n");
						System.out.print("\t1) Napravi novi klasrum\n");
						System.out.print("\t2) Otvori moje klasrume\n");
						System.out.print("\t3) Pridruzi se vec postojecem klasrumu\n");
						int izbor0 = sken.nextInt();
						if(izbor0 == 1) {
							System.out.print("Da biste napravili klasrum, popunite sledeca polja: \n");
							System.out.print("\tNaziv: ");
							String naziv = sken.next();
							System.out.print("\tOdeljenje: ");
							String odeljenje = sken.next();
							String id = UUID.randomUUID().toString();
							Classroom c = new Classroom(o.getId(), new ArrayList<>(), naziv, odeljenje, new ArrayList<>(), id);
							c.saveToJSON();
							o.getKlasrume().add(c.getId());
							o.saveToJSON();
							System.out.print("Cestitamo! Napravili ste " + naziv + " klasrum!\n");
						}
						break;
					}
					System.out.print("\tPogresno ste ukucali email ili sifru\n");
				}
				b = true;
				
			}
			else if(izbor == 2) {
				System.out.print("Ukucajte vase podatke ispod da biste napravili nalog: \n");
				
				System.out.print("\tIme: ");
				String ime = sken.next();
				System.out.print("\tPrezime: ");
				String prezime = sken.next();
				String pol;
				while(true) {
					System.out.print("\tPol (ukucajte M ili Z): ");
					pol = sken.next();
					pol = pol.toLowerCase();
					if(pol.equals("m") || pol.equals("z")) {
						break;
					}
					System.out.print("\tPostoje samo dva pola\n");
				}
				System.out.print("\tEmail: ");
				String email = sken.next();
				String id = UUID.randomUUID().toString();
				String sifra, sifrap, kriptosifra;
				while(true) {
					System.out.print("\tSifra: ");
					sifra = sken.next();
					System.out.print("\tPotvrdite vasu sifru: ");
					sifrap = sken.next();
					if(!sifra.equals(sifrap)) {
						System.out.print("\tNe poklapaju se sifre\n");
						continue;
					}
					kriptosifra = org.apache.commons.codec.digest.DigestUtils.sha256Hex(sifra);
					break;
				}
				System.out.println(ime);
				System.out.println(prezime);
				System.out.println(pol);
				System.out.println(email);
				System.out.println(id);
				System.out.println(kriptosifra);
				Osoba o = new Osoba(ime, prezime, (pol.equals("m")) ? Pol.Musko : Pol.Zensko, email, id, kriptosifra, new ArrayList<>());
				o.saveToJSON();
				b = true;
			}
			else if (izbor == 3){
				sken.close();
				return;
			}
			else {
				System.out.print("Nije ukucan validan broj\n");
			}
			sken.close();
		}
	}
}
