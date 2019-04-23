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

    public static class AddAPennyTask implements Runnable {
        public void run(){
            account.deposit(2000);
        }
    }

    public static class RemoveAPennyTask implements Runnable {
        public void run(){
            account.withdraw(1000);
        }
    }

    private static class Account {
        private int balance = 0;

        public int getBalance(){
            return balance;
        }

        public void deposit(int amount){
            int newBalance = amount + balance;

            //delay deliberately added to magnify the data-corruption and make it easy to see
            try{
                Thread.sleep(5);
            }
            catch (InterruptedException ex){
            }

            balance = newBalance;
        }

        public void withdraw(int amount){
            int newBalance = balance - amount;

            //delay deliberately added to magnify the data-corruption and make it easy to see
            try{
                Thread.sleep(5);
            }
            catch (InterruptedException ex){
            }

            balance = newBalance;
        }
    }
}

