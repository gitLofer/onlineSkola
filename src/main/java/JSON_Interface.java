// Sve osnovne/bazne klase trebaju implement ovo. Vidi Osoba kao primer
public interface JSON_Interface<selfObject> {
    // jsonLoc + "exact_file_name.json"
    String jsonLoc = "src/main/resources/";

    // Tutorijal: https://mkyong.com/java/json-simple-example-read-and-write-json/
    selfObject loadFromJSON (int id);
    void saveToJSON();
}
