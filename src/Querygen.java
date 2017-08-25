import org.apache.commons.math3.*;
import org.apache.commons.math3.random.MersenneTwister;
public class Querygen{
  public static void main(String[] args){
  MersenneTwister mt = new MersenneTwister();
  int num = mt.next(10);
  System.out.println(num);
  }
}
