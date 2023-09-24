
public class Person {
    private String name;

    private Integer age;

    public Person(String name , int age)  {
        if(age < 0) throw new InvalidAgeException("Wiek nie moze byc mniejjszy niz zero");
        else if(age > 100) throw new InvalidAgeException("wiek nie moze byc wiekszy nic 100");
        this.age = age;
        this.name = name;
    }

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) throws InvalidAgeException {
        if(age < 0) throw new InvalidAgeException("Wiek nie moze byc mniejjszy niz zero");
        else if(age > 100) throw new InvalidAgeException("wiek nie moze byc wiekszy nic 100");
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
