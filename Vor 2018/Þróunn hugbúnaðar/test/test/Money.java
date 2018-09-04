public class Money {
  private int cash;
  private String currency;
  static Money ms = new Money(100,"$");
  public Money(int cash, String currency) {
	  this.cash = cash;
	  this.currency = currency;
  }
  public boolean isEqual(Money m) {
	  if(ms.currency.equals(m.currency)) {
		  return true;
	  }
	  if(ms.cash == m.cash){
		  return true;
	  }
	  
	  return false;
  }
}