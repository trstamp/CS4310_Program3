import java.io.*;
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

        //filewriter exitWrite
        //if policy is 1 vsp_best.out

        int timer = 0;
        int memSizeLeft = memSize;
        boolean finished = false;
        //add first on queue to process array slot 0

        if(algPolicy == 1) { //vsp first fit

            File file = new File("vsp_first.out");
            PrintWriter pw = null;
            try {
                pw = new PrintWriter(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            beingProcessedArray[0] = processArray[0];
            processArray[0] = null;
            timer += 100;
            //write to file that first process enters memory
            pw.print("t = " + timer + ":");
            pw.println("\tProcess " + processArray[0] + "arrives");
            pw.println("\tInput Queue : " + beingProcessedArray);

            while (finished == false || timer == 100000) {

                for(int i = 0; i < beingProcessedArray.length; i++){

                    if(beingProcessedArray[i] != null){

                        beingProcessedArray[i].oneTimeUnit();

                        pw.print("t = " + timer + ":");
                        pw.println("\tProcess " + processArray[i] + "arrives");
                        pw.println("\tInput Queue : " + beingProcessedArray);

                        if(beingProcessedArray[i].processLife == 0){

                            beingProcessedArray[i] = null;
                            //print that this process left memory to file
                        }

                    }
                }

                for (int j = 0; j < processArray.length; j++) {

                    if(processArray[j] != null){
                        if(processArray[j].arrivalTime == 0){

                            pw.println("MM moves Process " + processArray[j] + "to memory");
                            //add this to memory array
                            processArray[j] = null;
                            //print that this was removed from the queue
                        } else {

                            processArray[j].arrivalTimeUnit();
                        }

                    }

                }

                timer += 100;

            }
        } if(algPolicy == 2){ //vsp best fit algorithmn

            beingProcessedArray[0] = processArray[0];
            System.out.println("Process Number "+processArray[0].processNumber+" has been put in memory slot: 1 with MemSize: "+processArray[0].memTotal);
            memSizeLeft -= processArray[0].memTotal;
            processArray[0] = null;
            timer += 100;

            //write to file that first process enters memory

            File file = new File("vsp_best.out");
            PrintWriter pw = null;
            try {
                pw = new PrintWriter(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            pw.print("t = " + timer + ":");
            pw.println("\tProcess " + processArray[0] + "arrives");
            pw.println("\tInput Queue : " + beingProcessedArray);

            while (finished == false || timer == 100000) {

                for(int i = 0; i < beingProcessedArray.length; i++){

                    if(beingProcessedArray[i] != null){

                        beingProcessedArray[i].oneTimeUnit();

                        pw.print("t = " + timer + ":");
                        pw.println("\tProcess " + processArray[i] + "arrives");
                        pw.println("\tInput Queue : " + beingProcessedArray);

                        if(beingProcessedArray[i].processLife == 0){

                            System.out.println("Process Number "+beingProcessedArray[0].processNumber+" has finished.");
                            beingProcessedArray[i] = null;

                            //print that this process left memory to file
                        }

                    }
                }

                for (int j = 0; j < processArray.length; j++) {

                    if(processArray[j] != null){
                        if(processArray[j].arrivalTime == 0){

                            pw.println("MM moves Process " + processArray[j] + "to memory");
                            //add this to memory array

                            System.out.println("Process Number "+processArray[j].processNumber+" has been put into memory slot: ");
                            processArray[j] = null;

                            //print that this was removed from the queue
                        } else {

                            processArray[j].arrivalTimeUnit();
                        }

                    }

                }

                timer += 100;




            }
            if (algPolicy == 3) { //vsp worst fit algorithm

                beingProcessedArray[0] = processArray[0];
                processArray[0] = null;
                timer += 100;
                //write to file that first process enters memory

                File file = new File("vsp_worst.out");
                PrintWriter pw = null;
                try {
                    pw = new PrintWriter(file);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                pw.print("t = " + timer + ":");
                pw.println("\tProcess " + processArray[0] + "arrives");
                pw.println("\tInput Queue : " + beingProcessedArray);

                while (finished == false || timer == 100000) {

                    for (int i = 0; i < beingProcessedArray.length; i++) {

                        if (beingProcessedArray[i] != null) {

                            beingProcessedArray[i].oneTimeUnit();

                            pw.print("t = " + timer + ":");
                            pw.println("\tProcess " + processArray[i] + "arrives");
                            pw.println("\tInput Queue : " + beingProcessedArray);

                            if (beingProcessedArray[i].processLife == 0) {

                                beingProcessedArray[i] = null;
                                //print that this process left memory to file
                            }

                        }
                    }


                    for (int j = 0; j < processArray.length; j++) {

                        if (processArray[j] != null) {
                            if (processArray[j].arrivalTime == 0) {

                                pw.println("MM moves Process " + processArray[j] + "to memory");
                                //add this to memory array
                                processArray[j] = null;
                                //print that this was removed from the queue
                            } else {

                                processArray[j].arrivalTimeUnit();
                            }

                        }

                    }

                    timer += 100;

                }

            }

        }
    }
    public static void pagingLoop(Process[] processArray, int memSize){


        int biggestMemSize = processArray[0].finalLineArray[0];

        for (int i = 0; i < processArray.length;i++){

            for(int j = 0; j < processArray[i].finalLineArray.length; j++){
                if(biggestMemSize < processArray[i].finalLineArray[j]){
                    biggestMemSize = processArray[i].memTotal;
                }
            }

        }

        System.out.println("The biggest Mem Size: "+biggestMemSize);

        int division = memSize / biggestMemSize;
        int dExtra = memSize % biggestMemSize;
        int processArraySize = division;
        int remainderSize = 0;

        if(dExtra > 0){

            //processArraySize += 1;
            remainderSize = memSize - (division * biggestMemSize);

        }

        System.out.println("There are "+division+" of memory slot size "+biggestMemSize);
        if(dExtra > 0){
            // System.out.println("and there is a memory slot size "+remainderSize);
        }
        System.out.println("For a total of "+processArraySize+" slots.");
        System.out.println("");

        Process[] beingProcessedArray = new Process[processArraySize];

        //filewriter exitWrite
        //if policy is 1 vsp_best.out
        int memSlot = 0;
        int timer = 0;
        int memSizeLeft = memSize;
        boolean finished = false;
        int counter = 0;
        //add first on queue to process array slot 0

        beingProcessedArray[0] = processArray[0];
        System.out.println("Process Number "+processArray[0].processNumber+" has been put into memory slot: 1 with MemSize: "+processArray[0].memTotal);
        memSizeLeft -= processArray[0].memTotal;
        processArray[0] = null;
        timer += 100;

        //write to file that first process enters memory

        while (finished == false || timer == 100000) {




            for(int i = 0; i < beingProcessedArray.length; i++){

                if(beingProcessedArray[i] != null) {

                    beingProcessedArray[i].oneTimeUnit();

                    if (beingProcessedArray[i].processLife == 0) {

                        System.out.println("Process Number " + beingProcessedArray[i].processNumber + " has finished.");
                        beingProcessedArray[i] = null;
                        counter++;

                        //print that this process left memory to file
                    }
                }


            }

            for (int j = 0; j < processArray.length; j++) {

                if(processArray[j] != null){
                    if(processArray[j].arrivalTime == 0){
                        //add this to memory array
                        for(int h = 0; h < beingProcessedArray.length; h++){
                            if(beingProcessedArray[h] == null){
                                beingProcessedArray[h] = processArray[j];
                                System.out.println("Process Number "+processArray[j].processNumber +" has been put into memory slot: "+(h + 1));
                                processArray[j] = null;
                                break;

                            }
                        }

                        //print that this was removed from the queue
                    } else {

                        processArray[j].arrivalTimeUnit();

                    }

                }

            }

            timer += 100;
            System.out.println("Timer: "+timer);
            if (counter == processArray.length){
                finished = true;
            }

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