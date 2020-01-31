package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        //new account
        assertEquals(200, bankAccount.getBalance());
        //after withdraw
        bankAccount.withdraw(.01);
        assertEquals(199.99, bankAccount.getBalance());
        //negative account
        BankAccount bankAccount2 = new BankAccount("a@b.com", 200);
        assertEquals(200, bankAccount2.getBalance());
    }

    @Test
    void isAmountValidTest() {
        //Positive 2 decimals
        assertTrue(BankAccount.isAmountValid(11.23));

        //Positive 2 decimals
        //border
        assertTrue(BankAccount.isAmountValid(1.00));

        //smallest valid decimal
        //border
        assertTrue(BankAccount.isAmountValid(0.01));

        //biggest valid decimal
        //border
        assertTrue(BankAccount.isAmountValid(0.99));

        //Positive 3 decimals, last isn't 0
        //border
        assertFalse(BankAccount.isAmountValid(0.001));

        //Positive 3 decimals, last isn't 0
        assertFalse(BankAccount.isAmountValid(0.006));

        //Positive 3 decimals, last isn't 0
        //border
        assertFalse(BankAccount.isAmountValid(0.009));

        //Positive 3 decimals, last decimal is 0
        //border
        assertTrue(BankAccount.isAmountValid(1.000));

        //0
        //border
        assertFalse(BankAccount.isAmountValid(0));

        //Negative 2 decimals
        assertFalse(BankAccount.isAmountValid(-1.23));

        //Negative 2 decimals
        //border
        assertFalse(BankAccount.isAmountValid(-1.00));

        //Negative 2 decimals
        //border
        assertFalse(BankAccount.isAmountValid(-0.99));

        //smallest invalid decimal
        //border
        assertFalse(BankAccount.isAmountValid(-0.01));

        //3 decimals last 0 negative
        //border
        assertFalse(BankAccount.isAmountValid(-1.000));

        //3 decimals last not 0 negative
        //border
        assertFalse(BankAccount.isAmountValid(-1.001));

        //3 decimals last not 0 negative
        //border
        assertFalse(BankAccount.isAmountValid(-1.009));

        //3 decimals last not 0 negative
        assertFalse(BankAccount.isAmountValid(-1.006));

        //3 decimals last not 0 negative
        assertFalse(BankAccount.isAmountValid(-1.004));

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

    //new- This will be accurate to the cents
        //withdraw 0
        BankAccount bankAccount2 = new BankAccount("a@b.com", 200);
        bankAccount2.withdraw(0);

        //withdraw .01
        bankAccount2.withdraw(0.01);
        assertEquals(199.99, bankAccount2.getBalance());

        //withdraw -.01
        assertThrows(IllegalArgumentException.class, ()-> bankAccount2.withdraw(-0.01));


        //withdraw .01 more than you have
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.withdraw(200));

        //withdraw .01 less than you have
        BankAccount bankAccount3 = new BankAccount("a@b.com", 200);
        bankAccount3.withdraw(199.99);
        assertEquals(0.01, bankAccount3.getBalance());

        //withdraw the exact amount you have
        BankAccount bankAccount4 = new BankAccount("a@b.com", 200);
        bankAccount4.withdraw(200);
        assertEquals(0, bankAccount4.getBalance());
    //end

        assertThrows(IllegalArgumentException.class, ()-> bankAccount2.withdraw(-0.001));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount2.withdraw(-12.000001));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount2.withdraw(0));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount2.withdraw(-1.0));

        assertThrows(IllegalArgumentException.class, ()-> bankAccount2.withdraw(0.001));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount2.withdraw(12.000001));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount2.withdraw(0.00099));


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

    //new
        //Dash in middle, not border
        assertTrue(BankAccount.isEmailValid("abc-de@mail.com"));

        //dash in middle, left border
        assertTrue(BankAccount.isEmailValid("a-bc@mail.com"));

        //dash on left edge
        assertTrue(BankAccount.isEmailValid("-ed@mail.com"));
    //end

    //underscores valid
        // Equivalence Class - Underscore in prefix not on edge
        // Border Case - No
        assertTrue(BankAccount.isEmailValid("abc_def@mail.com"));

    //underscores invalid (not followed by letter or number)
        // Equivalence Class - Underscore on right edge of prefix
        // Border Case - Yes (Single case)
        assertFalse(BankAccount.isEmailValid("abc_@mail.com"));

    //new
        //dash in middle, left border
        assertTrue(BankAccount.isEmailValid("a_def@mail.com"));

        //dash on right edge
        assertFalse(BankAccount.isEmailValid("abc_@mail.com"));

        //dash on left edge
        assertTrue(BankAccount.isEmailValid("_def@mail.com"));
    //end

    //periods valid
        // Equivalence Class - period in prefix, not on edge
        // Border Case - No
        assertTrue(BankAccount.isEmailValid("abc.def@mail.com"));

    //periods invalid (not followed by letter or number)
        // Equivalence Class - period on right edge of prefix
        // Border Case - Yes (Single case)
        assertFalse(BankAccount.isEmailValid("abc.@mail.com"));

    //new
        //period in middle, left border
        assertTrue(BankAccount.isEmailValid("a.def@mail.com"));

        //period on right edge
        assertFalse(BankAccount.isEmailValid("abc.@mail.com"));

        //period on left edge
        assertTrue(BankAccount.isEmailValid(".def@mail.com"));
    //end

    //special symbols invalid
        // Equivalence Class - * in prefix
        // Border Case - No
        assertFalse(BankAccount.isEmailValid("abc*de@mail.com"));
    //new
        //special character right edge
        assertFalse(BankAccount.isEmailValid("abc*@mail.com"));

        //special character right border
        assertFalse(BankAccount.isEmailValid("abc*d@mail.com"));

        //special character left edge
        assertFalse(BankAccount.isEmailValid("*de@mail.com"));

        //special character left border
        assertFalse(BankAccount.isEmailValid("a*de@mail.com"));

        //special character double border
        assertFalse(BankAccount.isEmailValid("c*d@mail.com"));

        //special character double edge
        assertFalse(BankAccount.isEmailValid("*@mail.com"));
    //end

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

    //new
        //special character
        assertFalse(BankAccount.isEmailValid("abc!def@mail.com"));

        //special character
        assertFalse(BankAccount.isEmailValid("abc@def@mail.com"));

        //special character
        assertFalse(BankAccount.isEmailValid("abc#def@mail.com"));

        //special character
        assertFalse(BankAccount.isEmailValid("abc$def@mail.com"));

        //special character
        assertFalse(BankAccount.isEmailValid("abc%def@mail.com"));

        //special character
        assertFalse(BankAccount.isEmailValid("abc^def@mail.com"));

        //special character
        assertFalse(BankAccount.isEmailValid("abc&def@mail.com"));

        //special character
        assertFalse(BankAccount.isEmailValid("abc(def@mail.com"));

        //special character
        assertFalse(BankAccount.isEmailValid("abc)def@mail.com"));

        //special character
        assertFalse(BankAccount.isEmailValid("abc+def@mail.com"));

        //special character
        assertFalse(BankAccount.isEmailValid("abc=def@mail.com"));

        //special character
        assertFalse(BankAccount.isEmailValid("abc'def@mail.com"));

        //special character
        assertFalse(BankAccount.isEmailValid("abc;def@mail.com"));

        //special character
        assertFalse(BankAccount.isEmailValid("abc,def@mail.com"));

        //special character
        assertFalse(BankAccount.isEmailValid("abc[def@mail.com"));

        //special character
        assertFalse(BankAccount.isEmailValid("abc]def@mail.com"));

        //special character
        assertFalse(BankAccount.isEmailValid("abc{def@mail.com"));

        //special character
        assertFalse(BankAccount.isEmailValid("abc}def@mail.com"));
    //end

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
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", -100));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", -1));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", -.01));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", .0001));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", -.001));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", -100));






        BankAccount bankAccount2 = new BankAccount("a@b.com", 0);
        assertEquals("a@b.com", bankAccount2.getEmail());
        assertEquals(0, bankAccount2.getBalance());
    }

}