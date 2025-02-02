public class PhoneDirectory {
    Set<Integer> set;
       Queue<Integer> queue;

       public PhoneDirectory(int maxNumbers) {
           set = new HashSet<>();
           queue = new LinkedList<>();
           for(int i=0;i<maxNumbers;i++){
               set.add(i);
               queue.add(i);
           }
       }

       public int get() {
           if(queue.isEmpty())
               return -1;
           else{
               int num = queue.poll();
               set.remove(num);  
               return num;  
           }
       }

       public boolean check(int number) {
           return set.contains(number);
       }

       public void release(int number) {
           if(!set.contains(number))
           { 
           queue.add(number);
           set.add(number);
           }

       }
}