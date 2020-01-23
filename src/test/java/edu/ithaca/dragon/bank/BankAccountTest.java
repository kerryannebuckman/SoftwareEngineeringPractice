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

        // Equivalence Class - Valid Numbers
        // Border Case - No
        bankAccount.withdraw(100);
        assertEquals(100, bankAccount.getBalance());

        //negative amount
        // Equivalence Class -  Negative Numbers
        // Border Case - No
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.withdraw(-100));

        //balance - amount is negative
        // Equivalence Class - Numbers Too Large
        // Border Case - No
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.withdraw(101));

        // All Border Cases
    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com"));
        assertFalse( BankAccount.isEmailValid(""));


    //prefix
    //dashes valid
        // Equivalence Class - Dashes in prefix not on edge
        // Border Case - Yes (1 away from right side)
        assertTrue(BankAccount.isEmailValid("abc-d@mail.com"));

    //dashes invalid (not followed by letter or number)
        // Equivalence Class - Dash on right edge of prefix
        // Border Case - Yes (Single case)
        assertFalse(BankAccount.isEmailValid("abc-@mail.com"));

    //underscores valid
        // Equivalence Class - Underscore in prefix not on edge
        // Border Case - No
        assertTrue(BankAccount.isEmailValid("abc_def@mail.com"));

    //underscores invalid (not followed by letter or number)
        // Equivalence Class - Underscore on right edge of prefix
        // Border Case - Yes (Single case)
        assertFalse(BankAccount.isEmailValid("abc_@mail.com"));

    //periods valid
        // Equivalence Class - period in prefix, not on edge
        // Border Case - No
        assertTrue(BankAccount.isEmailValid("abc.def@mail.com"));

    //periods invalid (not followed by letter or number)
        // Equivalence Class - period on right edge of prefix
        // Border Case - Yes (Single case)
        assertFalse(BankAccount.isEmailValid("abc.@mail.com"));

    //special symbols invalid
        // Equivalence Class - * in prefix
        // Border Case - No
        assertFalse(BankAccount.isEmailValid("abc*de@mail.com"));
        // Equivalence Class - @ in prefix
        // Border Case - No
        assertFalse(BankAccount.isEmailValid("abc@de@mail.com"));
        // Equivalence Class - ( in prefix
        // Border Case - No
        assertFalse(BankAccount.isEmailValid("abc(de@mail.com"));
        // Equivalence Class - % in prefix
        // Border Case - No
        assertFalse(BankAccount.isEmailValid("abc%de@mail.com"));
        // Equivalence Class - $ in prefix
        // Border Case - No
        assertFalse(BankAccount.isEmailValid("abc$de@mail.com"));

        // Missing alot of border cases
        // Missing equivalence cases for other special characters

    //domain

    //special characters invalid
        // Equivalence Class - % in suffix
        // Border Case - No
        assertFalse(BankAccount.isEmailValid("abc@mai%l.com"));
        // Equivalence Class - ^ in suffix
        // Border Case - No
        assertFalse(BankAccount.isEmailValid("abc@mai^l.com"));
        // Equivalence Class - * in suffix
        // Border Case - No
        assertFalse(BankAccount.isEmailValid("abc@mai*l.com"));
        // Equivalence Class - * in suffix
        // Border Case - No
        assertFalse(BankAccount.isEmailValid("abc@mai*l.com")); // Duplicate btw

    //valid end of domain (two or more characters after ".")
        // Equivalence Class - address suffixes allowed
        // Border Case - ??
        assertTrue(BankAccount.isEmailValid("abcdef@mail.com"));
        // Equivalence Class - address suffixes allowed
        // Border Case - ??
        assertTrue(BankAccount.isEmailValid("abcdef@mail.co"));
        // Equivalence Class - address suffixes allowed
        // Border Case - ??
        assertTrue(BankAccount.isEmailValid("abcdef@mail.net"));

    //invalid end of domain (less than two characters after ".")
        // Equivalence Class - address suffixes not allowed
        // Border Case - ??
        assertFalse(BankAccount.isEmailValid("abcdef@mail.c"));
        // Equivalence Class - address suffixes not allowed
        // Border Case - ??
        assertFalse(BankAccount.isEmailValid("abcdef@mail."));
        // Equivalence Class - address suffixes not allowed
        // Border Case - ??
        assertFalse(BankAccount.isEmailValid("abcdef@mail.l"));

    //needs period after @
        // Equivalence Class - suffix contains .
        // Border Case - No
        assertTrue(BankAccount.isEmailValid("abcdef@mail.com"));
        // Equivalence Class - suffix doesn't contain .
        // Border Case - No
        assertFalse(BankAccount.isEmailValid("abcdef@mailcom"));

        // A lot of border cases missing
        // Not sure about equivalence classes missing


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