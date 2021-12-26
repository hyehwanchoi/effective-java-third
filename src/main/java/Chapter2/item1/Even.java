package Chapter2.item1;

public class Even extends Calculator {

    private boolean even;

    public boolean isEven() {
        return even;
    }

    @Override
    public boolean calc(int number) {
        return this.even = number%2 == 0 ? true : false;
    }
}
