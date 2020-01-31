package edu.ithaca.dragon.bank;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class BankAccount {

    private String email;
    private double balance;

    public static final String prefix_invalid_endings = "-._";
    public static final String invalid_overall = "!#$%^&*()+=,<>/?;:'\"[{]}\\|";

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            this.email = email;
            if(isAmountValid(startingBalance) || startingBalance == 0){
                this.balance = startingBalance;
                roundToCent();
            }
            else{
                throw new IllegalArgumentException("Balance: " + startingBalance + " is invalid, cannot create account");
            }
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
     * adds money to users account
     * Throws illegal argument exception if amount is negative or has a significant digit past 2 digits
     * @param amount
     */
    public void deposit (double amount){
        if(!isAmountValid(amount)){
            throw new IllegalArgumentException("Must withdraw a positive amount!");
        }
        balance += amount;
        roundToCent();
    }

    /**
     * Withdraws amount from one account and deposits it in the receivingAccount
     * Throws illegal argument exception if amount is negative or has a significant digit past 2 digits
     * @param receivingAccount
     * @param amount
     */
    public void transfer (BankAccount receivingAccount, double amount){

    }



    /**
     *Takes an amount and if it has a significant digit past the second digit or is negative, it will return false
     * @param amount
     * @return true/false
     */

    public static boolean isAmountValid(double amount) {
        if(amount<=0){
            return false;
        }
        else{
            DecimalFormat df = new DecimalFormat("#.##");
            df.setRoundingMode(RoundingMode.FLOOR);
            DecimalFormat df2 = new DecimalFormat("#.##########"); //up to 10 decimals
            df2.setRoundingMode(RoundingMode.FLOOR);
            String amountR2 = (df.format(amount));
            String amountR3 = (df2.format(amount));

            if ((Double.parseDouble(amountR2) < (Double.parseDouble(amountR3)))){
                return false;
            }
            return true;
        }
    }




    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     * if amount is negative, throw invalid argument
     * if balance - amount = to a negative number, throw runtime exception
     * accurate to cents (the 100ths place) because you should not be able to take out half a penny)
     */
    public void withdraw (double amount) throws IllegalArgumentException {
        if(!isAmountValid(amount)){
            throw new IllegalArgumentException("Must withdraw a positive amount!");
        }
        if(amount > balance){
            throw new IllegalArgumentException("Cannot draw more funds than are contained");
        }
        balance -= amount;
        roundToCent();
    }


    private void roundToCent(){
        balance = ((double)Math.round(balance*100))/100;
    }


    public static boolean isEmailValid(String email){
        if(email.split("@").length != 2){
            return false;
        }

        for(char i : invalid_overall.toCharArray()){
            if(email.contains(i+"")){
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

        for(char i : prefix_invalid_endings.toCharArray()){
            if(prefix.endsWith(i+"")){
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
