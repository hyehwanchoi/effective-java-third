package chpater2.item1;

public class Odd extends Calculator {

    private boolean odd;

    public boolean isOdd() {
        return odd;
    }

    @Override
    public boolean calc(int number) {
        return this.odd = number%2 == 1 ? true : false;
    }
}
