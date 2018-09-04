abstract public class impAbs extends abs {
  public void absMethod(){
    System.out.println("Hey implemented absMethod");
  }
  public static void main(String[] args){
    // impAbs iabs = new impAbs();
  }
}

abstract class abs {
  abstract public void absMethod();
  abstract public void absTest();
}
