package ModernJava;

import java.util.ArrayList;
import java.util.List;

public final class PersonDatabase {
    private static PersonDatabase m_personDatabase;

    private List<Person> database = new ArrayList<>();

    private PersonDatabase() {

        database.add(new Person("Eric", 32, List.of("Computers", "Logic")));
        database.add(new Person("Rini", 30, List.of("Business", "Fraud")));
        database.add(new Person("Alex", 30, List.of("Smoking", "Gaming")));
        database.add(new Person("Steven", 30, List.of("Music", "Logic", "Computers")));
    }

    public static PersonDatabase getInstance() {
        if (m_personDatabase == null)
            m_personDatabase = new PersonDatabase();

        return m_personDatabase;
    }

    public List<Person> getItems() {
        return database;
    }
}

