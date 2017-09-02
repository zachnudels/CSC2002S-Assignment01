import java.util.*;
public class Querygen{
  double[] arr;

  public Querygen(int size){
    Random rand=new Random();
    this.arr = new double[size];
    for(int i=0;i<size;i++){
      arr[i]=rand.nextDouble();
    }
  }

}
