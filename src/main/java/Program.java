import java.util.*;

public class Program {
	public static void main(String[] args) {
		// TODO: Dodati 3)

		Scanner sken = new Scanner(System.in);
		Osoba o = mainMenu(sken);
		if (o == null) {
			return;
		}

		System.out.print("Dobrodosao " + o.getIme() + " " + o.getPrezime() + " nazad!\n\n");
		System.out.print("Imate sledece opcije: \n");
		System.out.print("\t1) Napravi novi klasrum\n");
		System.out.print("\t2) Otvori moje klasrume\n");
		System.out.print("\t3) Pridruzi se vec postojecem klasrumu\n");
		System.out.print("\t4) Prikazi sve opcije\n");
		System.out.print("\t5) Log out\n");
		while (true) {
			String izbor0 = sken.nextLine();
			switch (izbor0) {
				case "1" -> {
					System.out.print("Da biste napravili klasrum, popunite sledeca polja: \n");
					System.out.print("\tNaziv: ");
					String naziv = sken.nextLine();
					System.out.print("\tOdeljenje: ");
					String odeljenje = sken.nextLine();
					String id = UUID.randomUUID().toString();
					Classroom c = new Classroom(o.getId(), new ArrayList<>(), naziv, odeljenje, new ArrayList<>(), id);
					c.saveToJSON();
					o.getKlasrume().add(c.getId());
					o.saveToJSON();
					System.out.print("Cestitamo! Napravili ste " + naziv + " klasrum!\n");
				}
				case "2" -> {
					ArrayList<String> klasrumi = o.getKlasrume();
					for (String c : klasrumi) {
						Classroom k = new Classroom(c);
						System.out.println("Output from main");
						System.out.println(k);
					}
				}
				case "4" -> {
					System.out.print("Imate sledece opcije: \n");
					System.out.print("\t1) Napravi novi klasrum\n");
					System.out.print("\t2) Otvori moje klasrume\n");
					System.out.print("\t3) Pridruzi se vec postojecem klasrumu\n");
					System.out.print("\t4) Prikazi sve opcije\n");
					System.out.print("\t5) Log out\n");
				}

				case "5" -> {
					sken.close();
					return;
				}
				default -> {
					System.out.println("Nije ukucan validan broj!");
				}
			}
		}
		// sken.close();
	}

	public static Osoba mainMenu(Scanner sken) {
			boolean b = false;
			while(!b) {
				System.out.print("Dobrodosli u nas Klasrum!\n\n\n");
				System.out.print("Ukucajte broj opcije koju zelite: \n");
				System.out.print("\t1) Ulogujte se\n");
				System.out.print("\t2) Napravite nalog\n");
				System.out.print("\t3) Prekinete program\n");
				String izbor = sken.nextLine();
				// DEBUG: System.out.println(izbor);
				switch (izbor) {
					case "1" -> { // Ulogujte se
						String sifra, email, kriptosifra;
						Osoba o;
						while (true) {
							System.out.flush();
							System.out.print("Ukucajte vase podatke ispod da biste se ulogovali: \n");
							System.out.print("\tEmail: ");
							email = sken.nextLine();
							System.out.print("\tSifra: ");
							sifra = sken.nextLine();
							kriptosifra = org.apache.commons.codec.digest.DigestUtils.sha256Hex(sifra);
							o = new Osoba(email, kriptosifra);
							//Ulogovano
							if (o.getIme() != null) {
								return o;
							}
							System.out.print("\tPogresno ste ukucali email ili sifru\n");
						}
					}
					case "2" -> { // Napravite nalog
						System.out.print("Ukucajte vase podatke ispod da biste napravili nalog: \n");

						System.out.print("\tIme: ");
						String ime = sken.nextLine();
						System.out.print("\tPrezime: ");
						String prezime = sken.nextLine();
						String pol;
						while (true) {
							System.out.print("\tPol (ukucajte M ili Z): ");
							pol = sken.nextLine();
							pol = pol.toLowerCase();
							if (pol.equals("m") || pol.equals("z")) {
								break;
							}
							System.out.print("\tPostoje samo dva pola\n");
						}
						System.out.print("\tEmail: ");
						String email = sken.nextLine();
						String id = UUID.randomUUID().toString();
						String sifra, sifrap, kriptosifra;
						while (true) {
							System.out.print("\tSifra: ");
							sifra = sken.nextLine();
							System.out.print("\tPotvrdite vasu sifru: ");
							sifrap = sken.nextLine();
							if (!sifra.equals(sifrap)) {
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
						return o;
					}
					case "3" -> {
						return null;
					}
					default -> {
						System.out.print("Nije ukucan validan broj\n");
					}
				}
			}
			return null;
	}
}
