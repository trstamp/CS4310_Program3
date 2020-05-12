import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
public class Main {



    public static void makeProcessObject(int processNumber, int orderOfArrival, int memSize, int[] lastLineIntArray){

        Process process = new Process(processNumber, orderOfArrival, memSize, lastLineIntArray);
        process.printProcess();
        //add process to queue
    }
    
    public void VSP(int policy){
        if(policy == 1){}
        else if(policy == 2){}
        else{}
    }

    public void Paging(){
        
    }

    public void Segmentation(int policy){
        if(policy == 1){}
        else if(policy == 2){}
        else{}
    }
    
    public static void readFile(File file){

        int numberOfProcesses;
        int processNumber;
        int arrivalTime;
        int processLife;
        int[] lastLineIntArray;

        String lastLine;
        String[] lastLineArray;

        try{

            Scanner fileScan = new Scanner(new FileInputStream(file)); //open file
            numberOfProcesses = fileScan.nextInt();
            System.out.println("Number of processes: "+ numberOfProcesses);

            for(int i = 0; i < numberOfProcesses; i++){ //separate the processes by looping over the file x (process number) times

                processNumber = fileScan.nextInt();
                //System.out.println("Process Number: "+ processNumber);
                arrivalTime = fileScan.nextInt();
                //System.out.println("Order of Arrival: "+ orderOfArrival);
                processLife = fileScan.nextInt();
                //System.out.println("memSize: "+ memSize);
                lastLine = fileScan.next();
                lastLine += fileScan.nextLine();
                //System.out.println("Last Line: "+ lastLine);

                lastLineArray = lastLine.split(" ");
                lastLineIntArray = new int[lastLineArray.length];

                for (int j = 0; j < lastLineArray.length; j++){

                    lastLineIntArray[j] = Integer.parseInt(lastLineArray[j]);
                    //System.out.println(lastLineIntArray[j]);

                }

                makeProcessObject(processNumber,arrivalTime, processLife, lastLineIntArray);


            }

            // FileReader fr = new FileReader(file);
            // BufferedReader br = new BufferedReader(fr);

        } catch(IOException e){ }


    }

    public static void main(String[] args){
        Scanner kb = new Scanner(System.in);
        //MemoryManagement mm = new MemoryManagement();
        int algPolicy = 0;

        System.out.println("What is the size of your memory?");
        int memSize = kb.nextInt();

        System.out.println("Which memory management policy do you want?");
        System.out.println("1. VSP");
        System.out.println("2. Paging");
        System.out.println("3. Segmentation");
        int memPolicy = kb.nextInt();

        if(memPolicy == 1 || memPolicy == 3){
            System.out.println("Which Fit Algorithm do you want?");
            System.out.println("1. Best-Fit");
            System.out.println("2. First-Fit");
            System.out.println("3. Worst-Fit");
            algPolicy = kb.nextInt();
        }
       

        if(memPolicy == 1){
          //  mm.VSP(algPolicy);
        }
        else if(memPolicy == 2){
          //  mm.Paging();
        }
        else if(memPolicy == 3){
         //   mm.Segmentation(algPolicy);
        }

        System.out.println("Enter the pathname of the file you wish to read: "); //ask for file path
        Scanner scan = new Scanner(System.in);
        File file = new File(scan.nextLine());
        readFile(file); //node takes this in

    }
    
    public void BestFit(){
        //Iterate through all indices and find the index value that
        //is closest to the requested size
        int bestIndex = -1;
        int difference = 0;
        int newdif = 0;
        for(int i = 0; i < processArray.length; i++){
            for(int j = 0; j < memSize.length; j++){
                difference = processArray[i] - memSize[j];
                if(memSize[j] > processArray[i]){
                    difference = memSize[j] - processArray[i];
                    if(j == 0){
                        newdif = difference;
                        bestIndex = j;
                    }
                    else{
                        if(memSize[j] - processArray[i] > 0 && memSize[j] - processArray[i] < difference){
                            bestIndex = j;
                        }
                    }
                }
            }
        }
    }
    
   public void FirstFit(){
        //Iterate in a for loop and in an array and as soon as you find
        //a space that fits the requested size, choose it
        for(int i = 0; i < processArray.length; i++){
            for(int j = 0; j < memSize.length; j++){
                if(processArray[i] < memSize[j]){
                    memSize[j] -= processArray[i];
                    break;
                }
            }
        }

    }

    public void WorstFit(){
        //Iterate in a for loop in every spot in an array.
        //The memory size with the largest value will go in the spot that
        //has the highest value.
        int largestIndex = -1;
        for(int i = 0; i < processArray.length; i++){
            for(int j = 0; j < memSize.length; j++) {
                if (j > 0){
                    if(memSize[j] > memSize[j-1]) {
                        largestIndex = j;
                    }
                }
            }
        }

}
