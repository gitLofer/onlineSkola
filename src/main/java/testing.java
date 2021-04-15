// klasa gde radim testiranje, radi sta hoces ovde
public class testing {
    public static void main(String[] args) {
        // JSON_Interface.deleteFromJSON(482917684, "osobe.json");
        Osoba o = new Osoba(482917685);
        o.setIme("Milkica");
        o.saveToJSON();
    }
}
