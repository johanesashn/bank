import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.text.NumberFormat;
import java.util.ArrayList;

public class Main {

    static boolean showMenu = true;
    static String name, pin, citizen;
    static boolean check = true;
    static long balance = 100000;
    static NumberFormat format = NumberFormat.getInstance();

    static ArrayList<Account> accounts = new ArrayList<Account>();
    static Scanner input = new Scanner(System.in);

    public static void line(){
        System.out.println("======================================");
    }

    public static void miniLine(){
        System.out.println("--------------------------------------");
    }

    public static String showBalance(long balance){
        String formattedBalance = format.format(balance);
        return formattedBalance;
    }

    public static void showMenus(){
        line();
        System.out.println("|                MENUS               |");
        miniLine();
        System.out.println("| 1. show accounts                   |");
        System.out.println("| 2. register account                |");
        System.out.println("| 3. deposite                        |");
        System.out.println("| 4. send                            |");
        System.out.println("| 5. Exit                            |");
        line();
        chooseMenu();
    }

    public static void chooseMenu(){
        System.out.print("Choose the menu : ");
        try {
            int chosen = input.nextInt();
            if (chosen == 1){
                showAccounts();
            } else if (chosen == 2){
                registerAccount();
            } else if (chosen == 3){
                deposite();
            } else if (chosen == 4){
                send();
            } else if (chosen == 5){
                miniLine();
                System.out.println("Thanks for accessing the bank account");
                line();
                showMenu = false;
            } else {
                System.out.print("\033[H\033[2J");
                miniLine();
                System.out.println("  '''Your input doesn't exist'''");
                System.out.println("'''Please select the existing menus'''");
                showMenus();
            }
        } catch(Exception er){
            miniLine();
            System.out.println("warning: ");
            System.out.println("'''input must be numbers''''");
            miniLine();
            System.out.println("Press Enter to continue...");
            try {
                System.in.read();
            } catch (Exception e){}
            input.nextLine();
            showMenus();
        }
    }

    public static String getTime(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String formattedDate = formatter.format(date);

        return formattedDate;
    }

    public static void registerAccount(){
        Random random = new Random();
        System.out.print("How many account you want to make : ");
        int ammount = input.nextInt();
        
        System.out.print("\033[H\033[2J");
        line();
        System.out.println("|           MAKING ACCOUNTS          |");
        line();
        for(int i = 0; i < ammount; i++){
            System.out.println("Input account " + (i + 1));
            miniLine();

            System.out.print("name    : ");
            name = input.next();
            System.out.print("citizen : ");
            citizen = input.next();
            
            int accountNumber = random.nextInt(900000) + 100000;
            int count = 0;
            while (check){
                accountNumber = random.nextInt(900000) + 100000;
                for(int j = 0; j < accounts.size(); j++){
                    if (accountNumber == accounts.get(j).accountNumber){
                        check = true;
                        count++;
                    }
                }

                if (count == accounts.size()) {
                    check = false;
                }
            }
            
            Account tempAccount = new Account(name, citizen, accountNumber, getTime(), balance);
            accounts.add(tempAccount);
            if (i != ammount - 1){
                System.out.println();
            }
        }

        line();
        System.out.println("Press the Enter key to continue...");
        try {
            System.in.read();
        } catch (Exception e){}
        
        System.out.print("\033[H\033[2J");
        showMenus();
    }

    public static void showAccounts() {
        System.out.print("\033[H\033[2J");
        line();
        System.out.println("|              ACCOUNTS              |");
        line();
        for(int i = 0; i < accounts.size(); i++){
            if (i != 0){
                miniLine();
            } 
            System.out.println("Account " + (i + 1));
            miniLine();

            System.out.println("name            : " + accounts.get(i).name);
            System.out.println("account number  : " + accounts.get(i).accountNumber);
            System.out.println("register date   : " + accounts.get(i).registerDate);
            System.out.println("Balance         : " + showBalance(accounts.get(i).balance));
        }
        line();
        System.out.println("Press the Enter key to continue...");
        try {
            System.in.read();
        } catch (Exception e){}
        
        System.out.print("\033[H\033[2J");
        showMenus();
    }

    public static void deposite(){
        int accountNumber, count = 0;
        long money;

        miniLine();
        System.out.print("account number      : ");
        accountNumber = input.nextInt();
   
        try {
            System.out.print("ammount of money    : ");
            money = input.nextLong();

            for(int i = 0; i < accounts.size(); i++){
                if (accountNumber == accounts.get(i).accountNumber){
                    accounts.get(i).balance += money;
                    miniLine();
                    System.out.println(accounts.get(i).name + "'" + " balance is " + showBalance(accounts.get(i).balance) + " now");
                    line();
                } else {
                    count += 1;
                }
            }

            if (count == accounts.size()){
                miniLine();
                System.out.println("warning");
                System.out.println("'''account number doesn't exist'''");
                miniLine();
            }
        } catch(Exception er){
            miniLine();
            System.out.println("warning: ");
            System.out.println("'''money must be numbers'''");
        } finally {
            input.nextLine();
            System.out.println("Press the Enter key to continue...");
            try {
                System.in.read();
            } catch (Exception e){}
            System.out.print("\033[H\033[2J");
            showMenus();
        }

    }

    public static void send(){
        System.out.print("\033[H\033[2J");
        line();
        System.out.println("|           SENDING MONEY            |");
        line();
        
        int customerAccountNumber, recipientAccountNumber, count = 0;
        long money;

        System.out.print("customer account number  : ");
        customerAccountNumber = input.nextInt();
        System.out.print("recipient account number : ");
        recipientAccountNumber = input.nextInt();
        try {
            System.out.print("money ammount            : ");
            money = input.nextLong();
            

            if (customerAccountNumber == recipientAccountNumber){
                miniLine();
                System.out.println("warning: ");
                System.out.println("'''both account number must be different'''");
            } else {
                for(int i = 0; i < accounts.size(); i++){
                    if (accounts.get(i).accountNumber == customerAccountNumber || 
                        accounts.get(i).accountNumber == recipientAccountNumber) {
                            count += 1;
                        }
                }
        
                if (count == 2){
                    for(int i = 0; i < accounts.size(); i++){
                        if (accounts.get(i).accountNumber == customerAccountNumber){
                            for (int j = 0; j < accounts.size(); j++){
                                if (accounts.get(j).accountNumber == recipientAccountNumber){
                                    boolean result = accounts.get(i).transfer(accounts.get(j), money);
            
                                    if (result) {
                                        miniLine();
                                        System.out.println("Transfer successfully");
                                        miniLine();
                                        System.out.println("Your balance is " + showBalance(accounts.get(i).balance) + " now");
                                        System.out.println(accounts.get(j).name + " balance is " + showBalance(accounts.get(j).balance));
                                    } else {
                                        miniLine();
                                        System.out.println("'''Transfer unsuccessfully'''");
                                        miniLine();
                                        System.out.println("warning:");
                                        System.out.println("[ YOUR BALANCE IS " + showBalance(accounts.get(i).balance)  + " NOW ]");
                                        System.out.println("'''You can't send the money,");
                                        System.out.println("you must have at least 100,000");
                                        System.out.println("in your bank account'''");
                                    }
                                }
                            }
                        }
                    }
                } else {
                    miniLine();
                    System.out.println("warning: ");
                    System.out.println("'''account number doesn't exist'''");
                }
            } 
        } catch (Exception er){
            miniLine();
            System.out.println("warning:");
            System.out.println("'''money must be numbers'''");
        } finally {
            input.nextLine();
            line();
            System.out.println("Press the Enter key to continue...");
            try {
                System.in.read();
            } catch (Exception e){}
        }
        
        System.out.print("\033[H\033[2J");
        showMenus();
    }

    public static void main(String[] args){
        System.out.print("\033[H\033[2J");

        line();
        System.out.println("|      WELCOME TO BANK JOHANES       |");
        line();
        System.out.println("'''You have to make account first");
        System.out.println("before get into another menu'''");
        miniLine();

        registerAccount();
    }
}