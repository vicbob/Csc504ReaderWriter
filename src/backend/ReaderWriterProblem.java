package backend;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReaderWriterProblem {
    private static Account account = new Account();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        //create and launch 100 threads
        for(int i = 0; i < 5; i++){
            executor.execute(new ReaderWriter.AddAPennyTask());
        }

        for(int i = 0; i < 5; i++){
            executor.execute(new ReaderWriter.RemoveAPennyTask());
        }
        executor.shutdown();

        //wait until all tasks are finished
        while(!executor.isTerminated()){
        }

        System.out.println("what is balance? " + account.getBalance());
    }

    public static int start(){
        account.balance = 0;
        ExecutorService executor = Executors.newCachedThreadPool();

        //create and launch 100 threads
        for(int i = 0; i < 5; i++){
            executor.execute(new ReaderWriterProblem.AddAPennyTask());
        }

        for(int i = 0; i < 5; i++){
            executor.execute(new ReaderWriterProblem.RemoveAPennyTask());
        }
        executor.shutdown();

        //wait until all tasks are finished
        while(!executor.isTerminated()){
        }

        System.out.println("what is balance? " + account.getBalance());
        return account.getBalance();
    }

    public static void resetBalance(){
        account.resetBalance();
    }

    public static class AddAPennyTask extends Thread{
        public int initialBalance;
        public int finalBalance;

        public void run(){
            int[] result = account.deposit(2000);
            this.initialBalance = result[0];
            this.finalBalance = result[1];
        }
    }

    public static class RemoveAPennyTask extends Thread{
        public int initialBalance;
        public int finalBalance;

        public void run(){
            int[] result = account.withdraw(1000);
            this.initialBalance = result[0];
            this.finalBalance = result[1];
        }
    }

    private static class Account {
        private int balance = 0;

        public int getBalance(){
            return balance;
        }

        public void resetBalance(){
            this.balance = 0;
        }

        public int[] deposit(int amount){
            int[] result = new int[2];
            result[0] = balance;
            int newBalance = amount + result[0];

            //delay deliberately added to magnify the data-corruption and make it easy to see
            try{
                Thread.sleep(100);
            }
            catch (InterruptedException ex){
            }

            balance = newBalance;
            result[1] = newBalance;
            return result;
        }

        public int[] withdraw(int amount){
            int[] result = new int[2];
            result[0] = balance;
            int newBalance = result[0] - amount;

            //delay deliberately added to magnify the data-corruption and make it easy to see
            try{
                Thread.sleep(5);
            }
            catch (InterruptedException ex){
            }

            balance = newBalance;
            result[1] = newBalance;
            return result;
        }
    }
}

