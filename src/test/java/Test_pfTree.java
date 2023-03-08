import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Test_pfTree {
    String data = "hell, hello, cat, command, computer, helin, helloland, fender, funny, function";
    @Test
    void add() {
        PrefixTree tree = new PrefixTree();
        tree.add(data);
        PrefixTree checkTree = tree;
        checkTree.add("hello, command");
        assertEquals(tree, checkTree);
    }

    @Test
    void delete() {
        PrefixTree tree = new PrefixTree();
        tree.add(data);
        PrefixTree checkTree = tree;
        tree.delete("helloland");
        tree.delete("function");
        tree.add("helloland");
        tree.add("function");
        assertEquals(tree, checkTree);
    }

    @Test
    void searching() {
        PrefixTree tree = new PrefixTree();
        tree.add(data);
        assertTrue(tree.searching("hello"));
        assertTrue(tree.searching("command"));
        assertFalse(tree.searching("falcon"));
        assertFalse(tree.searching("hella"));
    }

    @Test
    void findAllByPrefix() {
        PrefixTree tree = new PrefixTree();
        tree.add(data);
        List<String> test1 = new ArrayList<>();
        test1.add("hell");
        test1.add("hello");
        test1.add("helloland");
        List<String> test2 = new ArrayList<>();
        test2.add("cat");
        List<String> test3 = new ArrayList<>();
        test3.add("cat");
        test3.add("command");
        test3.add("computer");
        assertEquals(tree.findAllByPrefix("hell"), test1);
        assertEquals(tree.findAllByPrefix("cat"), test2);
        assertEquals(tree.findAllByPrefix("c"), test3);
    }
}
