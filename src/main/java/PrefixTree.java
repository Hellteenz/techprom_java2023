/*
Хранит строки в виде префиксного дерева. Корневой узел такого дерева не хранит
ничего, узлы 1-го уровня хранят первый символ строки, 2-го уровня -- второй символ и
так далее.
Методы: добавление строки в дерево, удаление строки из дерева, поиск строки в
дереве, поиск всех строк в дереве с заданным префиксом.
 */

import java.util.List;

public class PrefixTree {
    static class Node {
        private char key;
        private boolean check;
        public List<Node> children;

        public char getKey(){
            return key;
        }

        public boolean getCheck() {
            return check;
        }

        public Node(char data, boolean isEndOfWord) {
            this.key = data;
            this.check = isEndOfWord;
        }
    }

    private Node head = new Node(' ', false);

    public Node getHead(){
        return head;
    }

    public void add(String data) {
        char[] word = data.toCharArray();
        for (char letter: word) {
            boolean checker = false;
            if (data.indexOf(letter) == (data.length() - 1)) checker = true;
            Node wordToAdd = searchingNodeChar(letter);
            if (wordToAdd == null) {
                wordToAdd = new Node(letter, checker);
                head.children.add(wordToAdd);
            }
        }
    }


    private Node searchingNodeChar (char letter) {
        if (searchingNodeChar(letter).children != null) {
            for (Node listOfKidNode: searchingNodeChar(letter).children) {
                if (listOfKidNode.key == letter) return listOfKidNode;
            }
        }
        return null;
    }

}
