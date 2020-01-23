package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance());
    }

    @Test
    void withdrawTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        try {
            bankAccount.withdraw(100);
        }catch (Exception e){

        }

        assertEquals(100, bankAccount.getBalance());

        //negative amount
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.withdraw(-100));


        //balance - amount is negative
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.withdraw(101));
    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com"));
        assertFalse( BankAccount.isEmailValid(""));


    //prefix
        //dashes valid
        assertTrue(BankAccount.isEmailValid("abc-d@mail.com"));

        //dashes invalid (not followed by letter or number)
        assertFalse(BankAccount.isEmailValid("abc-@mail.com"));

        //underscores valid
        assertTrue(BankAccount.isEmailValid("abc_def@mail.com"));

        //underscores invalid (not followed by letter or number)
        assertFalse(BankAccount.isEmailValid("abc_@mail.com"));

        //periods valid
        assertTrue(BankAccount.isEmailValid("abc.def@mail.com"));

        //periods invalid (not followed by letter or number)
        assertFalse(BankAccount.isEmailValid("abc.@mail.com"));

        //special symbols invalid
        assertFalse(BankAccount.isEmailValid("abc*de@mail.com"));
        assertFalse(BankAccount.isEmailValid("abc@de@mail.com"));
        assertFalse(BankAccount.isEmailValid("abc(de@mail.com"));
        assertFalse(BankAccount.isEmailValid("abc%de@mail.com"));
        assertFalse(BankAccount.isEmailValid("abc$de@mail.com"));

    //domain

        //special characters invalid
        assertFalse(BankAccount.isEmailValid("abc@mai%l.com"));
        assertFalse(BankAccount.isEmailValid("abc@mai^l.com"));
        assertFalse(BankAccount.isEmailValid("abc@mai*l.com"));
        assertFalse(BankAccount.isEmailValid("abc@mai*l.com"));

        //valid end of domain (two or more characters after ".")
        assertTrue(BankAccount.isEmailValid("abcdef@mail.com"));
        assertTrue(BankAccount.isEmailValid("abcdef@mail.co"));
        assertTrue(BankAccount.isEmailValid("abcdef@mail.net"));

        //invalid end of domain (less than two characters after ".")
        assertFalse(BankAccount.isEmailValid("abcdef@mail.c"));
        assertFalse(BankAccount.isEmailValid("abcdef@mail."));
        assertFalse(BankAccount.isEmailValid("abcdef@mail.l"));

        //needs period after @
        assertTrue(BankAccount.isEmailValid("abcdef@mail.com"));
        assertFalse(BankAccount.isEmailValid("abcdef@mailcom"));


    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

}