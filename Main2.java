import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
public class Main {

    static int processCounter = 0;

    public static Process[] makeProcessObject(Process[] processArray, int processNumber, int orderOfArrival, int memSize, int[] lastLineIntArray){

        Process process = new Process(processNumber, orderOfArrival, memSize, lastLineIntArray);
        process.printProcess();

        if(processCounter < processArray.length) {

            processArray[processCounter] = process;
            processCounter++;

        }

        return processArray;

        //add process to queue
    }

    public static void vspLoop(Process[] processArray, int memSize, int algPolicy){

        Process[] beingProcessedArray = new Process[processArray.length];

        int timer = 0;
        boolean finished = false;
        //add first on queue to process array slot 0

        if(algPolicy == 1) {

            while (finished = false) {

                //algorithm to place in memory
                //subtract 100 from all in process array
                for (int j = 0; j < processArray.length; j++) {

                }

                if (processArray.length == 0) {

                    printFile();

                }

                timer += 100;

            }
        } if(algPolicy == 2){}
        if (algPolicy == 3) {}
    }
    public static void pagingLoop(Process[] processArray, int memSize){

        Process[] beingProcessedArray = new Process[processArray.length];
        int timer = 0;
        boolean finished = false;
        //add first on queue to process array slot 0

        while(finished = false){

            //algorithm to place in memory
            //subtract 100 from all in process array
            for (int j = 0; j < processArray.length; j++){

            }

            if (processArray.length == 0){

                printFile();

            }

            timer += 100;

        }
    }
    public static void segmentationLoop(Process[] processArray, int memSize, int algPolicy){

        Process[] beingProcessedArray = new Process[processArray.length];
        int timer = 0;
        boolean finished = false;
        //add first on queue to process array slot 0

        while(finished = false){

            //algorithm to place in memory
            //subtract 100 from all in process array
            for (int j = 0; j < processArray.length; j++){

            }

            if (processArray.length == 0){

                printFile();

            }

            timer += 100;

        }
    }

    public static void printFile(){}

    //enque method

    public static Process[] readFile(File file){

        int processNumber;
        int arrivalTime;
        int processLife;
        int[] lastLineIntArray;
        Process[] processArray = new Process[0];

        String lastLine;
        String[] lastLineArray;

        try{

            Scanner fileScan = new Scanner(new FileInputStream(file)); //open file
            int numberOfProcesses = fileScan.nextInt();
            processArray = new Process[numberOfProcesses];
            System.out.println("Number of processes: "+ numberOfProcesses);

            for(int i = 0; i < numberOfProcesses; i++){ //separate the processes by looping over the file x (process number) times

                processNumber = fileScan.nextInt();
                //System.out.println("Process Number: "+ processNumber);for testing
                arrivalTime = fileScan.nextInt();
                //System.out.println("Order of Arrival: "+ orderOfArrival); for testing
                processLife = fileScan.nextInt();
                //System.out.println("memSize: "+ memSize);for testing
                lastLine = fileScan.next();
                lastLine += fileScan.nextLine();
                //System.out.println("Last Line: "+ lastLine);for testing

                lastLineArray = lastLine.split(" ");
                lastLineIntArray = new int[lastLineArray.length];

                for (int j = 0; j < lastLineArray.length; j++){

                    lastLineIntArray[j] = Integer.parseInt(lastLineArray[j]);
                    //System.out.println(lastLineIntArray[j]);for testing

                }

                processArray = makeProcessObject(processArray,processNumber,arrivalTime, processLife, lastLineIntArray);

            }


        } catch(IOException e){}

        return processArray;


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


        System.out.println("Enter the pathname of the file you wish to read: "); //ask for file path
        Scanner scan = new Scanner(System.in);
        File file = new File(scan.nextLine());
        Process[] processArray = readFile(file); //node takes this in

        for(int i = 0; i < processArray.length; i++){

            processArray[i].printProcess();
        }

        if(memPolicy == 1){

            if(algPolicy == 1){

                vspLoop(processArray, memSize, algPolicy);
            }
            if(algPolicy == 2){

                vspLoop(processArray, memSize, algPolicy);
            }
            if(algPolicy == 3){

                vspLoop(processArray, memSize, algPolicy);
            }

        }
        else if(memPolicy == 2){

            pagingLoop(processArray, memSize);

        }
        else if(memPolicy == 3){

            if(algPolicy == 1){

                segmentationLoop(processArray, memSize, algPolicy);
            }
            if(algPolicy == 2){

                segmentationLoop(processArray, memSize, algPolicy);
            }
            if(algPolicy == 3){

                segmentationLoop(processArray, memSize, algPolicy);
            }

        }



        //enter loop depending on chosen policy
        //function for each loop
        //a way to properly manage process in CPU
        //

        //printfile
        //finish

    }

}
