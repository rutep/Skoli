public class interTest implements inter {
  public void hello(){
    System.out.println("Hæ heimur");
  }
  public void by(){
    System.out.println("Bæ heimur");
  }

  public static void main(String[] args){
    interTest i = new interTest();
    i.hello();
    i.by();
  }
}

interface inter {
  public void hello();
  public void by();
}