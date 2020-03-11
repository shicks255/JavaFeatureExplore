package ModernJava;

import java.util.ArrayList;
import java.util.List;

public final class PersonDatabase {
    private static PersonDatabase m_personDatabase;

    private List<Person> database = new ArrayList<>();

    private PersonDatabase() {

        database.add(new Person("Eric", 32));
        database.add(new Person("Rini", 30));
        database.add(new Person("Alex", 30));
        database.add(new Person("Steve", 30));
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
class Person {
    String name = "";
    int age;
    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person - " + name + " is " + age + " years old";
    }
}
