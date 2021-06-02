import java.util.*;

public class Program {
	public static void main(String[] args) {
		// TODO: Dodati provere
		// 1 - Profesor ne moze biti i ucenik istog klasruma
		// 2 - Dva ista ucenika ne smeju da budu deo istog klasruma
		// 3 - Dva naloga ne smeju da imaju isti email

		Scanner sken = new Scanner(System.in);
		Osoba o = mainMenu(sken);
		if (o == null) {
			sken.close();
			return;
		}

		Classroom selectedClass = null;

		while (true) {
			System.out.print("Dobrodosao " + o.getIme() + " " + o.getPrezime() + " nazad!\n\n");
			System.out.print("Imate sledece opcije: \n");
			System.out.print("\t1) Napravi novi klasrum\n");
			System.out.print("\t2) Otvori moje klasrume\n");
			System.out.print("\t3) Pridruzi se vec postojecem klasrumu\n");
			System.out.print("\t4) Prikazi sve opcije\n");
			System.out.print("\t5) Log out\n");
			System.out.print("\t6) Prekinete program\n");
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
					selectedClass = c;
					System.out.print("Cestitamo! Napravili ste " + naziv + " klasrum!\n");
				}
				case "2" -> {
					ArrayList<String> klasrumi = o.getKlasrume();
					for (String c : klasrumi) {
						Classroom k = new Classroom(c);
						System.out.println("Output from main");
						System.out.println(k);
					}
					System.out.println("Ukucajte broj klasruma koji hocete da pristupite (1 - " + klasrumi.size() + " ). Ukucajte 0 da se vratite nazad.");
					int selected = Integer.parseInt( sken.nextLine() );
					if (selected == 0) {
						break;
					}else{
						selectedClass = new Classroom(klasrumi.get(selected-1));
						classroomMenu(sken, selectedClass, o);
					}
				}
				case "3" -> {
					System.out.print("Ukucajte id klasruma: \n");
					String idKlasruma = sken.nextLine();
					Classroom c = new Classroom(idKlasruma);
					o.getKlasrume().add(c.getId());
					c.getOsobe().add(o.getId());
					o.saveToJSON();
					c.saveToJSON();
					selectedClass = c;
					System.out.print("Uspesno ste dodani u " + c.getNaziv() + " klasrum!\n");
					// Dodati opcionalno otvaranje ovog klasruma? Tj. pozivanje funkcije classroomMenu?
				}
				case "4" -> {
					continue;
				}

				case "5" -> {
					o = mainMenu(sken);
					if (o == null) {
						sken.close();
						return;
					}
				}

				case "6" -> {
					sken.close();
					return;
				}

				default -> {
					System.out.println("Nije ukucan validan broj!");
				}
			}
		}
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

	public static void classroomMenu(Scanner sken, Classroom clasroom, Osoba user) {
		boolean isProfessor = user.getId().equals(clasroom.getProf());
		while (true) {
			System.out.println(clasroom);
			System.out.print("\nImate sledece opcije: \n");
			System.out.print("\t1) Dodaj objavu\n");
			System.out.print("\t2) Dodaj komentar na objavu\n");
			System.out.print("\t3) \n");
			System.out.print("\t4) Nazad na glavni meni\n");
			String opcija = sken.nextLine();
			switch (opcija){
				case "1" -> {
					System.out.print("\n\tTekst objave: ");
					String tekstObjave = sken.nextLine();
//					Napraviti objekat Post, sacuvati u posts.json, dodati u classroom, sacuvati u clasroom.json
					// Post zaDodati = new Post(user.getId() , 1, tekstObjave, new ArrayList<Komentar>(), new ArrayList<Integer>(), UUID.randomUUID().toString());
					System.out.print("Objave je uspesno dodata!\n");
				}
				case "2" ->{

				}
				case "3" ->{

				}
				case "4" -> {
					System.out.println("Vracanje na glavni meni...\n");
					return;
				}
				default -> {
					System.out.println("Nije ukucan validan broj!");
				}
			}
		}
	}
}
