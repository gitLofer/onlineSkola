import java.util.*;

public class Program {
	public static void main(String[] args) {
		System.out.print("Dobrodosli u nas Klasrum!\n\n\n");
		System.out.print("Ukucajte broj opcije koju zelite: \n");
		System.out.print("\t1) Ulogujte se\n");
		System.out.print("\t2) Napravite nalog\n");
		Scanner sken = new Scanner(System.in);
		boolean b = false;
		while(!b) {
			int izbor = sken.nextInt();
			if(izbor == 1) {
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
				Osoba o = new Osoba(ime, prezime, (pol.equals("m")) ? Pol.Musko : Pol.Zensko, email, id, kriptosifra);
				o.saveToJSON();
				b = true;
			}
			else {
				System.out.print("Nije ukucan validan broj\n");
			}
		}
		sken.close();
	}
}
