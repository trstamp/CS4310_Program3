import java.util.*;
public class Main {

    public static void main(String[] args){
        Scanner kb = new Scanner(System.in);
        MemoryManagement mm = new MemoryManagement();

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
        }
        int algPolicy = kb.nextInt();

        if(memPolicy == 1){
            mm.VSP(algPolicy);
        }
        else if(memPolicy == 2){
            mm.Paging();
        }
        else if(memPolicy == 3){
            mm.Segmentation(algPolicy);
        }

    }

}
