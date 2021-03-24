// klasa gde radim testiranje, radi sta hoces ovde
public class testing {
    public static void main(String[] args) {
        Osoba o = new Osoba();
        o.saveToJSON();
        System.out.println(o);
        // JSON_Interface.deleteFromJSON(123, "osobe.json");
    }
}
