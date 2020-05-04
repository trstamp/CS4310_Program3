package com.company;

public class ProcQueue {
    //global variables
    private int capacity,size,front,rear;
    private Process[] elements;
    //constructor
    public ProcQueue() {
        int capacity,size,front,rear;
        Process elements;
    }


    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getFront() {
        return front;
    }

    public void setFront(int front) {
        this.front = front;
    }

    public int getRear() {
        return rear;
    }

    public void setRear(int rear) {
        this.rear = rear;
    }

    public Process[] getElements() {
        return elements;
    }

    public void setElements(Process[] elements) {
        this.elements = elements;
    }

    //method to initialize queue
    public ProcQueue createProcQueue(int length){
        ProcQueue q = new ProcQueue();  //initialize queue variable (q)
        q.elements = new Process[length];        //initialize the process array/ array of processes
        q.size = 0;                 //initialize size to be 0 aka empty
        q.capacity = length;        //maximum capacity of queue
        q.front = 0;                //pointer to first index of queue
        q.rear = 0;                 //pointer to last index of queue

        return q;                   //return queue variable q
    }
    //method to insert a node into the back of the queue cause FIFO
    void enqueue(ProcQueue q, Process proc){
        if(q.size == q.capacity){       //check to see if the capacity and size are the same and if they are return an error
            System.out.println("Error: queue is at max capacity");
            //break;
        }
        q.size++;               //increase the size of the queue
        q.rear++;               //increment the rear pointer to be

        if(q.rear == q.capacity){
            q.rear = 0;         //not sure if this is correct
        }
        q.elements[q.rear] = proc;
    }

    //method to determine if there is an empty space in the next index of the queue
    boolean queueHasNext(ProcQueue q){
        return q.size == 0 ? true : false;      //this might be backwards not 100% sure
    }

    //method to "peek" at what element is at the front of the queue
    Process peekQueue(ProcQueue q){
        if(!queueHasNext(q)){
            System.out.println("ERROR: queue is empty! Cant retrieve the next element");
            //break;
        }
        return q.elements[q.front];
    }

    //method to "peek" at what element is at a specified index in the queue
    Process peekQueueAtIndex(ProcQueue q, int index){
        return q.elements[index];
    }

    //method to remove the first element in the queue and adjust the size of the queue (FIFO)
    void dequeue(ProcQueue q){
        if(!queueHasNext(q)){
            System.out.println("Error: queue is empty! Cant dequeue anything!");
            //break;
        }

        q.size--;
        q.front++;

        if(q.front == q.capacity){
            q.front = 0;
        }
    }

    //method to iterate the index of the queue (helper method to make code easier to read)
    int iterateQueueIndex(ProcQueue q, int index){
        return q.front + index;
    }

    //method to remove  a process at a specified index
    void dequeueProcAtIndex(ProcQueue q, int index){
        int i, prev = 0;

        for(i = 0; i < q.size; i+=1){
            if(i > index){
                q.elements[prev] = q.elements[i];
            }

            prev = i;
        }

        q.size--;
        q.rear--;
    }

    //method to print out the process queue
    void printProcQueue(ProcQueue q){
        int counter;
        Process proc;

        System.out.println("\tInput queue: [ ");
        for(counter = 0; counter < q.size; counter += 1) {
            proc = peekQueueAtIndex(q,iterateQueueIndex(q, counter));

            System.out.printf("%d ", proc.getPid());
        }
        System.out.println("]");

    }
}
