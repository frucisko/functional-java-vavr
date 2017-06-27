package net.owocki.functional;

import io.vavr.Function1;
import io.vavr.Function2;

public class Compose {
    static Function1<Integer, Integer> triple = x -> x * 3;
    static Function1<Integer, Integer> square = arg -> arg * arg;

//    static Function1<Integer, Function1<Integer, Integer>> add = x -> y -> x + y;
    static Function2<Integer, Integer, Integer> add = (x,  y) -> x + y;

//    static Function1<
//            Function1<Integer, Integer>,
//            Function1<
//                    Function1<Integer, Integer>,
//                    Function1<Integer, Integer>
//                    >
//            > compose = x -> y -> z -> x.apply(y.apply(z));


    static Function2<
            Function1<Integer, Integer>,
            Function1<Integer, Integer>,
            Function1<Integer, Integer>> compose = (f, g) -> f.compose(g);

    static <T, U, V> Function2<
            Function1<U, V>,
            Function1<T, U>,
            Function1<T, V>> higherCompose() {
        return (f, g) -> f.compose(g);
    }



    public static void main(String[] args) {
        System.out.println(triple.andThen(square).apply(2));
        System.out.println(triple.compose(square).apply(2));
        System.out.println(add.apply(2).apply(3));
        System.out.println(add.apply(2, 3));

        System.out.println(compose.apply(triple).apply(square).apply(2));
        System.out.println(compose.apply(triple, square).apply(2));
        System.out.println(compose.apply(square).apply(triple).apply(2));
        System.out.println(compose.apply(square, triple).apply(2));

        Compose.<Integer, Integer, Integer>higherCompose().apply(square, triple).apply(2);
    }
}
