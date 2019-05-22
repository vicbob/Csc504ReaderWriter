/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author user
 */
public class ReaderWriter {
    private static Account account = new Account();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        //create and launch 100 threads
        for(int i = 0; i < 5; i++){
            executor.execute(new AddAPennyTask());
        }

        for(int i = 0; i < 5; i++){
            executor.execute(new RemoveAPennyTask());
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
            executor.execute(new AddAPennyTask());
        }

        for(int i = 0; i < 5; i++){
            executor.execute(new RemoveAPennyTask());
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
    
    public static class AddAPennyTask extends Thread {
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
        private Lock balanceLock = new ReentrantLock();
        
        public int getBalance(){
            return balance;
        }
        public void resetBalance(){
            this.balance = 0;
        }

        private int getBalanceInternally() {
            balanceLock.lock();
            return balance;
        }

        private void overwriteBalance(int newBalance) {
            try {
                balance = newBalance;
            }
            finally {
                System.out.println("balance mutated to " + this.getBalance());
                balanceLock.unlock();
            }
        }

        public int[] deposit(int amount){
            int[] result = new int[2];
            try {
                result[0] = this.getBalanceInternally();
                int newBalance = amount + result[0];

                // delay deliberately added to magnify the data-corruption and make it easy to see
                Thread.sleep(100);

                this.overwriteBalance(newBalance);
                result[1] = newBalance;
            }
            catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            return result;
        }

        public int[] withdraw(int amount) {
            int[] result = new int[2];
            try {
                result[0] = this.getBalanceInternally();
                int newBalance = result[0] - amount;

                // delay deliberately added to magnify the data-corruption and make it easy to see
                Thread.sleep(100);

                this.overwriteBalance(newBalance);
                result[1] = newBalance;
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            return result;
        }
    }
}
