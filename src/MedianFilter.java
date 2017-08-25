import java.util.ArrayList;
import java.util.*;
public class MedianFilter{
  int fSize;
  double[] arr;
  int border;

  public MedianFilter(double[] arr, int fSize){
    this.fSize=fSize;
    this.arr=arr;
    this.border=(fSize-1)/2;
  }

  public double findMedian(ArrayList<Double> checker){
// Sort list and return the middle element
    Collections.sort(checker);
    return checker.get((fSize-1)/2);
  }

  public double[] filter(){
    ArrayList<Double> checker = new ArrayList<Double>();
// for each element (barring the border elements)
    for(int i=border;i<(arr.length-border);i++){
// construct an array of the numbers fSize with the i^th element as the center
      for(int j=(i-border);j<(i+border+1);j++){
        checker.add(arr[j]);
      }
// Replace the i^th element with the median of its filter
      arr[i]=findMedian(checker);
      checker.clear();
    }
    return arr;
  }

  // public static void main(String[] args){
  //   double[] arr1 = {2.0,80.0,6.0,3.0,1.0};
  //   MedianFilter mf = new MedianFilter(arr1, 3);
  //   arr1 = mf.filter();
  //   for(int i=0;i<arr1.length;i++){
  //     System.out.println(arr1[i]);
  //   }
  // }

}
