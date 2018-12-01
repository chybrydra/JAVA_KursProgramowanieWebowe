package pl.lukaszgrymulski.hello;

import io.vavr.collection.List;
import java.util.stream.IntStream;

public class Hello {
    public static void main(String[] args) {
        //Witaj świecie wykonane funkcyjnie,
        //ograniczamy użycie zmiennych dzięki temu
        //program staje się mniej błędogenny
        IntStream.range(0,5).forEach(i -> System.out.println("Witaj świecie!"));

        System.out.println("\nInne użycie:");

        IntStream.range(0,5)
            .mapToObj(i->"Witaj po raz: "+i)
            .forEach(i-> System.out.println(i));

        System.out.println("\nInne użycie:");
        //tu z użyciem biblioteki vavr, którą dodaliśmy od dependencies
        List.range(1,6).map(i->"Witaj po raz: " + i)
                .forEach(i -> System.out.println(i));
    }
}
