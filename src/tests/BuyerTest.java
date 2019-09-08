package tests;

import customer.Buyer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuyerTest {
    Buyer b1 = new Buyer("1a","1b","1b","1c");

    @BeforeEach
    void setUp() {
    }

    @Test
    void addSuburb() {
        for(int i = 0; i<10; i++){
        b1.addSuburb("3053");}
        assertEquals(10, b1.getNumOfSuburb());

    }

}