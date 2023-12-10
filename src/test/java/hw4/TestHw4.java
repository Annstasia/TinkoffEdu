package hw4;

import edu.hw4.Animal;
import edu.hw4.Task1;
import edu.hw4.Task10;
import edu.hw4.Task11;
import edu.hw4.Task12;
import edu.hw4.Task13;
import edu.hw4.Task14;
import edu.hw4.Task15;
import edu.hw4.Task16;
import edu.hw4.Task17;
import edu.hw4.Task18;
import edu.hw4.Task2;
import edu.hw4.Task3;
import edu.hw4.Task4;
import edu.hw4.Task5;
import edu.hw4.Task6;
import edu.hw4.Task7;
import edu.hw4.Task8;
import edu.hw4.Task9;
import edu.hw4.task19_20.Task19;
import edu.hw4.task19_20.Task20;
import edu.hw4.task19_20.errors.ValidationError;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestHw4 {
    List<Animal> animals = List.of(
        new Animal("dog dog", Animal.Type.DOG, Animal.Sex.M, 10, 10, 101, true),
        new Animal("dog1", Animal.Type.DOG, Animal.Sex.F, 4, 11, 11, true),
        new Animal("dog2", Animal.Type.DOG, Animal.Sex.F, 8, 130, 12, true),
        new Animal("fish3", Animal.Type.FISH, Animal.Sex.M, 10, 5, 13, false),
        new Animal("fish1", Animal.Type.FISH, Animal.Sex.M, 5, 7, 104, false),
        new Animal("fish2", Animal.Type.FISH, Animal.Sex.M, 7, 6, 15, false),
        new Animal("spider spider3a", Animal.Type.SPIDER, Animal.Sex.F, 11, 140, 16, true),
        new Animal("spider spider1aaa", Animal.Type.SPIDER, Animal.Sex.F, 5, 12, 17, false),
        new Animal("spider spider2aa", Animal.Type.SPIDER, Animal.Sex.F, 8, 120, 18, false)
    );
    List<Animal> animals2 = List.of(
        new Animal("dog dog", Animal.Type.DOG, Animal.Sex.F, 10, 10, 101, false),
        new Animal("dog", Animal.Type.DOG, Animal.Sex.M, 4, 11, 11, false),
        new Animal("dog", Animal.Type.DOG, Animal.Sex.M, 8, 14, 12, false),
        new Animal("fish", Animal.Type.FISH, Animal.Sex.F, 10, 5, 21, true),
        new Animal("fish", Animal.Type.FISH, Animal.Sex.F, 5, 6, 55, true),
        new Animal("fish", Animal.Type.FISH, Animal.Sex.F, 7, 7, 15, true),
        new Animal("spider spidera", Animal.Type.SPIDER, Animal.Sex.M, 11, 101, 16, false),
        new Animal("spider spideraaa", Animal.Type.SPIDER, Animal.Sex.M, 5, 12, 17, true),
        new Animal("spider spideraa", Animal.Type.SPIDER, Animal.Sex.M, 6, 13, 18, true)
    );

    @Test
    void task1Test() {
        Assertions.assertArrayEquals(Task1.sortHeightASC(animals).toArray(), List.of(
            animals.get(3),
            animals.get(5),
            animals.get(4),
            animals.get(0),
            animals.get(1),
            animals.get(7),
            animals.get(8),
            animals.get(2),
            animals.get(6)
        ).toArray());
    }

    List<Animal> invalidAnimals = List.of(
        new Animal(null, null, null, 0, 0, 0, false),
        new Animal("", null, null, -1, -3, -100, false),
        new Animal("dobby13", null, null, -1, -3, -100, false)
    );

    List<String> invalidAnimalsPrettyStringAnswer = List.of(
        "Name error. Cause: java.lang.NullPointerException: null name",
        "Age error. Cause: edu.hw4.task19_20.errors.NegativeException: -1 < 0\n" +
            "Name error: empty stirng\n" +
            "Weight error. Cause: edu.hw4.task19_20.errors.NegativeException: -100 < 0",
        "Age error. Cause: edu.hw4.task19_20.errors.NegativeException: -1 < 0\n" +
            "Weight error. Cause: edu.hw4.task19_20.errors.NegativeException: -100 < 0"
    );

    @Test
    void task2Test() {
        Assertions.assertArrayEquals(
            Task2.sortWeightFirstK(animals, 3).toArray(),
            List.of(
                animals.get(4),
                animals.get(0),
                animals.get(8)
            ).toArray()
        );
    }

    @Test
    void task3Test() {
        Map<Animal.Type, Integer> ans = Task3.groupCountersByType(animals);
        for (Animal.Type type : Animal.Type.values()) {
            if (type == Animal.Type.DOG
                || type == Animal.Type.FISH
                || type == Animal.Type.SPIDER
            ) {
                Assertions.assertEquals(ans.get(type), 3);
            } else {
                Assertions.assertNull(ans.get(type));
            }
        }
    }

    @Test
    void task4Test() {
        Assertions.assertEquals(Task4.longestName(animals), animals.get(7));
    }

    @Test
    void task5Test() {
        Assertions.assertEquals(Task5.maxSex(animals), Animal.Sex.F);
        Assertions.assertEquals(Task5.maxSex(animals2), Animal.Sex.M);
    }

    @Test
    void task6Test() {
        Map<Animal.Type, Animal> ans = Task6.heaviestByType(animals);
        for (Animal.Type type : Animal.Type.values()) {
            if (type == Animal.Type.DOG) {
                Assertions.assertEquals(ans.get(type), animals.get(0));
            } else if (type == Animal.Type.SPIDER) {
                Assertions.assertEquals(ans.get(type), animals.get(8));
            } else if (type == Animal.Type.FISH) {
                Assertions.assertEquals(ans.get(type), animals.get(4));
            } else {
                Assertions.assertNull(ans.get(type));
            }
        }
    }

    @Test
    void task7Test() {
        Assertions.assertEquals(Task7.kOldest(animals, 4), animals.get(2));
    }

    @Test
    void task8Test() {
        Assertions.assertEquals(Task8.heaviestFromSmallerK(animals, 7).get(), animals.get(5));
    }

    @Test
    void task9Test() {
        int sum = 0;
        for (Animal animal : animals) {
            sum += animal.paws();
        }
        Assertions.assertEquals(Task9.pawSum(animals), sum);
    }

    @Test
    void task10Test() {
        ArrayList<Animal> ans = new ArrayList<>(animals);
        ans.remove(8);
        ans.remove(1);
        Assertions.assertArrayEquals(Task10.animalsAgeDoesNotEqualPaws(animals).toArray(), ans.toArray());
    }

    @Test
    void task11Test() {
        Assertions.assertArrayEquals(Task11.bigBites(animals).toArray(), List.of(
            animals.get(2),
            animals.get(6)
        ).toArray());
    }

    @Test
    void task12Test() {
        Assertions.assertEquals(Task12.countFat(animals), 5);
    }

    @Test
    void task13Test() {
        Assertions.assertArrayEquals(Task13.complexNames(animals).toArray(), List.of(
            animals.get(0),
            animals.get(6),
            animals.get(7),
            animals.get(8)
        ).toArray());
    }

    @Test
    void task14Test() {
        Assertions.assertTrue(Task14.containsHigherKDog(animals, 100));
        Assertions.assertFalse(Task14.containsHigherKDog(animals, 150));
    }

    @Test
    void task15Test() {
        Map<Animal.Type, Integer> ans = Task15.sumWeightByTypeFilterAge(animals, 6, 20);
        for (Animal.Type type : Animal.Type.values()) {
            if (type == Animal.Type.DOG) {
                Assertions.assertEquals(ans.get(type), 113);
            } else if (type == Animal.Type.SPIDER) {
                Assertions.assertEquals(ans.get(type), 34);
            } else if (type == Animal.Type.FISH) {
                Assertions.assertEquals(ans.get(type), 28);
            } else {
                Assertions.assertNull(ans.get(type));
            }
        }
    }

    @Test
    void task16Test() {
        Assertions.assertArrayEquals(
            Task16.sortTypeSexName(animals).toArray(),
            List.of(
                animals.get(0),
                animals.get(1),
                animals.get(2),
                animals.get(4),
                animals.get(5),
                animals.get(3),
                animals.get(7),
                animals.get(8),
                animals.get(6)
            ).toArray()
        );
    }

    @Test
    void task17Test() {
        Assertions.assertFalse(Task17.spidersBitesMoreThanDogs(animals));
        Assertions.assertTrue(Task17.spidersBitesMoreThanDogs(animals2));
    }

    @Test
    void task18Test() {
        List<List<Animal>> input = List.of(animals, animals2);
        Assertions.assertEquals(Task18.heaviestFish(input), animals.get(4));
    }

    @Test
    void task19Test() {
        Map<String, Set<ValidationError>> errorMap = Task19.validate(invalidAnimals);
        Assertions.assertEquals(
            invalidAnimals.stream().map(Animal::name).collect(Collectors.toSet()),
            errorMap.keySet()
        );
        Assertions.assertTrue(Task19.validate(animals).isEmpty());

    }

    @Test
    void task20Test() {
        Map<String, String> validate = Task20.validate(invalidAnimals);
        for (int i = 0; i < invalidAnimals.size(); ++i) {
            Assertions.assertEquals(
                invalidAnimalsPrettyStringAnswer.get(i),
                validate.get(invalidAnimals.get(i).name())
            );
        }
    }

}
