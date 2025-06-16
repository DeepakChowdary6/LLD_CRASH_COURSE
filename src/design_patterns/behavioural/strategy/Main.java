package design_patterns.behavioural.strategy;

interface PaymentStrategy {
    void pay(int amount);
}

class CashPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paid $" + amount + " using cash.");
    }
}

class CardPayment implements PaymentStrategy {
    private String creditCardNumber;;

    public CardPayment(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;}

    @Override
    public void pay(int amount) {
        System.out.println("Paid $" + amount + " using card " + creditCardNumber);
    }
}
class UpiPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Paid $" + amount + " using UPI.");
    }
}
class BitCoinPayment implements PaymentStrategy {
  private String bitCoinAddress;

  public BitCoinPayment(String bitCoinAddress) {
      this.bitCoinAddress = bitCoinAddress;
  }
     @Override
    public void pay(int amount) {
        System.out.println("Paid $" + amount + " using BitCoin.");
    }
}
class ShoppingCart {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void checkout(int amount) {
        paymentStrategy.pay(amount);
    }
}

public class Main {

    public static void main(String[] args) {

        ShoppingCart shoppingCart = new ShoppingCart();

        shoppingCart.setPaymentStrategy(new CashPayment());
        shoppingCart.checkout(100);

        shoppingCart.setPaymentStrategy(new CardPayment("1234-5678-9012-3456"));
        shoppingCart.checkout(200);

        shoppingCart.setPaymentStrategy(new UpiPayment());
        shoppingCart.checkout(300);

        shoppingCart.setPaymentStrategy(new BitCoinPayment("1BitCoinAddress"));
        shoppingCart.checkout(400);

    }

}
