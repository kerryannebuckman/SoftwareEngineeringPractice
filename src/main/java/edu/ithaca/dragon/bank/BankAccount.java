package edu.ithaca.dragon.bank;

public class BankAccount {

    private String email;
    private double balance;

    public static final String[] prefix_invalid_endings = {"-", ".", "_"};
    public static final String[] invalid_overall = {"*", "(", "%", "%", "$", "^", "*"};

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     * if amount is negative, throw invalid argument
     * if balance - amount = to a negative number, throw runtime exception
     */
    public void withdraw (double amount) throws IllegalArgumentException, InsufficientFundsException {
        if(amount < 0){
            throw new IllegalArgumentException("Must withdraw a positive amount!");
        }
        if(amount > balance){
            throw new IllegalArgumentException("Cannot draw more funds than are contained");
        }
        balance -= amount;

    }


    public static boolean isEmailValid(String email){
        if(email.split("@").length != 2){
            return false;
        }

        for(String i : invalid_overall){
            if(email.contains(i)){
                return false;
            }
        }

        String[] a = email.split("@");
        String prefix = a[0];
        String suffix = a[1];

        /// Prefix Tests
        if(prefix.length()==0){
            return false;
        }

        for(String i : prefix_invalid_endings){
            if(prefix.endsWith(i)){
                return false;
            }
        }

        /// Suffix Tests
        String[] suffixsplit = suffix.split("\\.");

        if(suffixsplit.length < 2){
            return false;
        }

        if(suffixsplit[suffixsplit.length - 1].length() < 2) {
            return false;
        }

        return true;
    }
}
