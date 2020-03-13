package ModernJava;

import java.util.List;

public class Person {

    String name = "";
    int age;
    List<String> skills;

    public Person(String name, int age, List<String> skills) {
        this.name = name;
        this.age = age;
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Person - " + name + " is " + age + " years old";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<String> getSkills() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
}
