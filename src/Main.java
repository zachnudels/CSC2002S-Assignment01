import java.io.*;
import java.util.*;
import java.util.concurrent.*;
public class Main{
  static long startTime = 0;

	private static void tick(){
		startTime = System.currentTimeMillis();
	}
	private static float tock(){
		return (System.currentTimeMillis() - startTime) / 1000.0f;
	}

  static final ForkJoinPool fjPool = new ForkJoinPool();

  static double[] parFilter(double[] arr, int fSize){
    double[] ans = new double[arr.length];
    int border = (fSize-1)/2;
    fjPool.invoke(new ParallelFilter(0,arr.length,arr,ans,fSize,border));
    return ans;
  }
  public static void main(String[] args) throws IOException{

//Accept arguments from terminal
    String fileName = args[0];
    int fSize = Integer.parseInt(args[1]);

// Read file and write into initialised arrayList
    BufferedReader br = new BufferedReader(new FileReader(fileName));
    ArrayList<String> lines = new ArrayList<String>(Integer.parseInt(br.readLine()));
    String line = br.readLine();
    for (int x=0;x<100001;x++){
      lines.add(line);
      line=br.readLine();
    }
    // for(int z=0;z<101;z++){
    //   System.out.println(lines.get(z));
    // }

// Seperate lines into lineNumbers and actual data save actual data into new array
    double[] arr = new double[lines.size()];
    String[] tmp = new String[2];
    for (int i=0;i<arr.length;i++){
      tmp=lines.get(i).split("\\s");
      arr[i] = Double.parseDouble(tmp[1]);
    }

    double[] seqArr = new double[arr.length];
    double[] parArr = new double[arr.length];
    ArrayList<Float> seqTimes = new ArrayList<Float>();
    ArrayList<Float> parTimes = new ArrayList<Float>();


// Create new filter object and invoke filter method on data array and time
  for (int m=0;m<11;m++){
    tick();
    MedianFilter mf = new MedianFilter(arr, fSize);
    seqArr = mf.filter();
    float time = tock();
    seqTimes.add(time);
  }
  float seqAve=0.0f;
  for (int p=1;p<11;p++){
    seqAve+=seqTimes.get(p);
  }
  seqAve/=11;
// Write new array to new file with line numbers
  String writeName = "Resources/SeqMainResult"+fSize+".txt";
    File f = new File(writeName);
    if (!f.exists())
      f.createNewFile();
    BufferedWriter bw = new BufferedWriter(new FileWriter(writeName, true));
    int nrOfProcessors = Runtime.getRuntime().availableProcessors();
    bw.write("Processors: "+Integer.toString(nrOfProcessors));
    bw.newLine();
    // bw.write(Float.toString(time)+" seconds");
    bw.newLine();
    // for (int j = 0;j<arr.length;j++){
    //   bw.write(Integer.toString(j+1)+" ");
    //   bw.write(Double.toString(seqArr[j]));
    //   bw.newLine();
    // }
    // for (Float fl : seqTimes){
    bw.write(Float.toString(seqAve));
      // bw.newLine();
    // }
    bw.flush();
    bw.close();


  // Create new filter object and invoke filter method on data array and time
  for(int n=0; n<11; n++){
      tick();
      parArr = parFilter(arr, fSize);
      float parTime = tock();
      parTimes.add(parTime);
    }
    float parAve=0.0f;
    for (int q=1;q<11;q++){
      parAve+=parTimes.get(q);
    }
    parAve/=11;
  // Write new array to new file with line numbers
    String parWriteName = "Resources/MainParallelResult"+fSize+".txt";
      File f1 = new File(parWriteName);
      if (!f1.exists())
        f1.createNewFile();
      BufferedWriter bw1 = new BufferedWriter(new FileWriter(parWriteName, true));
      nrOfProcessors = Runtime.getRuntime().availableProcessors();
      bw1.write("Processors: "+Integer.toString(nrOfProcessors));
      bw1.newLine();
      // bw1.write(Float.toString(parTime)+" seconds");
      bw1.newLine();
      // for (int j = 0;j<arr.length;j++){
      //   bw1.write(Integer.toString(j+1)+" ");
      //   bw1.write(Double.toString(parArr[j]));
      //   bw1.newLine();
      // }
      // for (Float fl1 : parTimes){
        bw1.write(Float.toString(parAve));
      //   bw1.newLine();
      // }
      bw1.flush();
      bw1.close();
  }
}
