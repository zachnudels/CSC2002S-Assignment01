import java.io.*;
import java.util.*;
public class Main{
  static long startTime = 0;

	private static void tick(){
		startTime = System.currentTimeMillis();
	}
	private static float tock(){
		return (System.currentTimeMillis() - startTime) / 1000.0f;
	}
  public static void main(String[] args) throws IOException{

//Accept arguments from terminal
    String fileName = args[0];
    int fSize = Integer.parseInt(args[1]);

// Read file and write into initialised arrayList
    BufferedReader br = new BufferedReader(new FileReader(fileName));
    ArrayList<String> lines = new ArrayList<String>(Integer.parseInt(br.readLine()));
    String line = br.readLine();
    while (line!=null){
      lines.add(line);
      line=br.readLine();
    }

// Seperate lines into lineNumbers and actual data save actual data into new array
    double[] arr = new double[lines.size()];
    String[] tmp = new String[2];
    for (int i=0;i<arr.length;i++){
      tmp=lines.get(i).split("\\s");
      arr[i] = Double.parseDouble(tmp[1]);
    }

// Create new filter object and invoke filter method on data array and time
    tick();
    MedianFilter mf = new MedianFilter(arr, fSize);
    arr = mf.filter();
    float time = tock();
// Write new array to new file with line numbers
  String writeName = "Resources/MainResult"+fSize+".txt";
    File f = new File(writeName);
    if (!f.exists())
      f.createNewFile();
    BufferedWriter bw = new BufferedWriter(new FileWriter(writeName, true));
    bw.write(Float.toString(time)+" seconds");
    bw.newLine();
    for (int j = 0;j<arr.length;j++){
      bw.write(Integer.toString(j+1)+" ");
      bw.write(Double.toString(arr[j]));
      bw.newLine();
    }
    bw.flush();
    bw.close();
  }
}
