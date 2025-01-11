package vending_machine;

public enum Coin {
    ONE(1),TWO(2),FIVE(5),TEN(10);

    public Integer value;
    Coin(Integer value){
        this.value=value;
    }

    public Integer getValue() {
        return value;
    }
}
