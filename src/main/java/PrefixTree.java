/*
Хранит строки в виде префиксного дерева. Корневой узел такого дерева не хранит
ничего, узлы 1-го уровня хранят первый символ строки, 2-го уровня -- второй символ и
так далее.
Методы: добавление строки в дерево, удаление строки из дерева, поиск строки в
дереве, поиск всех строк в дереве с заданным префиксом.
 */

import java.util.ArrayList;
import java.util.List;

public class PrefixTree {
    static class Node {
        private char key;
        private boolean check;

        private List<Node> childrenList;

        public char getKey(){
            return key;
        }

        public boolean getCheck() {
            return check;
        }

        public List<Node> getChildrenList() {
            return childrenList;
        }

        public Node(char data, boolean isEndOfWord, List<Node> childrenList) {
            this.key = data;
            this.check = isEndOfWord;
            this.childrenList = childrenList;

        }
    }
    List<Node> list = new ArrayList<>();
    private Node head = new Node(' ', false, list);

    public Node getHead(){
        return head;
    }

    public void add(String data) {
        char[] word = data.toCharArray();
        Node parent = head;
        for (char letter: word) {
            boolean checker = false;
            List<Node> list = new ArrayList<>();
            if (data.indexOf(letter) == (data.length() - 1)) checker = true;
            Node symbolToAdd = new Node(letter, checker, list);
            if (!parent.childrenList.contains(symbolToAdd)) parent.childrenList.add(symbolToAdd);
            parent = symbolToAdd;
        }
    }

}
