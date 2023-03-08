import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Test_pfTree {
    String data = "hello, hell, cat, command, computer, helin, helloland, fender, funny, function";
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
        assertEquals(tree.findAllByPrefix("hell").stream().sorted(), Arrays.stream("hello, hell, helloland".split(",")).sorted());
        assertEquals(tree.findAllByPrefix("cat").stream().sorted(), Arrays.stream("cat".split(",")).sorted());
        assertEquals(tree.findAllByPrefix("co").stream().sorted(), Arrays.stream("command, computer".split(",")).sorted());
    }
}
