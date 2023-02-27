import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class Praiser {

    private static final Random RANDOM = ThreadLocalRandom.current();

    private static final List<String> ATTRIBUTES = List.of("умный", "весёлый", "красивый", "добрый", "заботливый",
            "начитанный", "няшный", "котя");


    public static void main(String[] args) {
        String attribute = ATTRIBUTES.get(RANDOM.nextInt(ATTRIBUTES.size()));
        System.out.println(String.format("Ты такой %s!", attribute));
    }

}
