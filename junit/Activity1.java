package Activities;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class Activity1 {
    static List<String> list;
    @BeforeAll
    public static void setUp(){
        // Initialize a new ArrayList
        list = new ArrayList<String>();

        // Add values to the list
        list.add("alpha");
        list.add("beta");
    }

    @Test
    public void insertTest(){
        //Assert the size of the list
        assertEquals(2, list.size(),"Size should be 2");
        // Add an other element to ArrayList
        list.add(2, "gamma");
        assertEquals(3, list.size(),"Size should be 3");
    }

    @Test
    public void replaceTest(){
        //replace the value on index 1
        list.set(1,"test");
        assertEquals(2, list.size(),"Size should be 2");
        assertEquals("alpha",list.get(0),"first list value is alpha");
        assertEquals("test",list.get(1),"second list value is test");
    }

}