package Chapter2.item5;

public class Produce {
    public static Production create(String name) {
        Production production = new Production();
        production.setName(name);
        production.setHp(100);

        return production;
    }
}
