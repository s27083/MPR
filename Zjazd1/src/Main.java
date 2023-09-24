import java.util.*;
import java.util.stream.Collectors;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Person person = new Person("Julka",5);
        System.out.println("Imie " + person.getName() + " Wiek" + person.getAge());
        System.out.println(person.toString());
        try {
            Person person2 = new Person("ELO", -5);
        } catch (InvalidAgeException e) {
            System.out.println("Cacth:" + e.getMessage());
        }

        try {
            person.setAge(-5);
        } catch (InvalidAgeException e) {
            System.out.println("Cacth:" + e.getMessage());
        }

        Person person2 = new Person("Kamil",20);
        Person person3 = new Person("Ala",26);
        List<Person> immutalbePersonList = List.of(person,person2,person3);
        System.out.println("List nie mutowalna "+ immutalbePersonList);
        List<Person> mutablePersonList = new ArrayList<>();
//        imutablePersonList.add(person) -- rzuca wyjatek set nie mutowalny

        mutablePersonList.add(person);
        mutablePersonList.add(person2);
        mutablePersonList.add(person3);
        System.out.println("List mutowalna "+ mutablePersonList);
        Set<Person> imutablePersonSet = Set.of(person,person2,person3);
//        imutablePersonSet.add(person) -- rzuca wyjatek set nie mutowalny
        System.out.println("Set nie mutowalny"+ imutablePersonSet);

        Set<Person> mutablePersonSet = new HashSet<>();
        mutablePersonSet.add(person);
        mutablePersonSet.add(person2);
        mutablePersonSet.add(person3);
        mutablePersonSet.add(person);
        System.out.println("Set mutowalny" + mutablePersonSet);

        Map<Integer, Person> immutablePersonMap = Map.of(1,person,2,person2,3,person3);
        System.out.println("Nie mutowalna mapa" + immutablePersonMap);

        Map<Integer, Person> muttablePersonMap = new HashMap<>();
        muttablePersonMap.put(1,person);
        muttablePersonMap.put(2,person2);
        muttablePersonMap.put(3,person);
        System.out.println("mutowalna mapa" + muttablePersonMap);

        // Stream
        List<Integer> ageList = immutalbePersonList.stream().map(Person::getAge)
                .collect(Collectors.toList());
        Integer ageSum =  ageList.stream()
                .reduce(0,(sum, value) -> sum + value);

        System.out.println("suma lat: " + ageSum);

        double avgAge = (double) ageSum / ageList.size();
        System.out.println("Sredni wiek " + avgAge);
        Integer ageSum1 = immutalbePersonList.stream().map(p -> p.getAge()).reduce(0,(sum, value)-> sum + value);
        System.out.println("Suma srednia chain" + ageSum1);

    // 4.2
        List<String> nameList = immutalbePersonList.stream().map(Person::getName).collect(Collectors.toList());
        System.out.println("List imion"+ nameList);
    // 4.3
        List<Person> oldAgeList = immutalbePersonList.stream().filter(p -> p.getAge() > 25).collect(Collectors.toList());
        System.out.println("List osob powyzej 25 roku zycia"+ oldAgeList);
    // 4.4
        List<Person> sortedPersonList = immutalbePersonList.stream().sorted(Comparator.comparing(Person::getName)).collect(Collectors.toList());
        System.out.println("Posortowana list osob"+ sortedPersonList);
    // 4.5
        immutalbePersonList.stream().forEach(System.out::println);
    // 4.6
        Person oldestPerson = immutalbePersonList.stream().max(Comparator.comparing(Person::getAge)).orElseThrow();
        System.out.println("Najstarsza osoba" + oldestPerson);
        Person youngestPerson = immutalbePersonList.stream().min(Comparator.comparing(Person::getAge)).orElseThrow();
        System.out.println("Najmlodsza osoba" + youngestPerson);
    }
}