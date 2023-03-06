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

    public void add(String fullData) {
        String[] fullDataArray = fullData.split(",");
        for (String data: fullDataArray) {
            char[] word = data.trim().toCharArray();
            Node parent = head;
            for (int letterIndex = 0; letterIndex < word.length; letterIndex++) {
                boolean checker = false;
                List<Node> list = new ArrayList<>();
                if (letterIndex == (word.length - 1)) checker = true;
                Node symbolToAdd = new Node(word[letterIndex], checker, list);
                if (parentContainsByKey(parent, word[letterIndex]) == null) {
                    parent.childrenList.add(symbolToAdd);
                    parent = symbolToAdd;
                }
                else parent = parentContainsByKey(parent, word[letterIndex]);
            }
        }
    }
    private Node parentContainsByKey(Node parent, char letter) {
        for (Node element: parent.childrenList) {
            if (element.key == letter) {
                return element;
            }
        }
        return null;
    }

    public boolean searching(String data) {
        Node parent = head;
        char[] word = data.trim().toCharArray();
        for (int letterIndex = 0; letterIndex < word.length; letterIndex++) {
            if (parentContainsByKey(parent, word[letterIndex]) != null) {
                if (letterIndex == word.length - 1) {
                    if (parentContainsByKey(parent, word[letterIndex]).check) {
                        System.out.println("Ура! Победа! Ура! Такое слово найдено!");
                        return true;
                    } else {
                        System.out.println("OH NO... Такого слова нет...");
                        return false;
                    }
                }
                parent = parentContainsByKey(parent, word[letterIndex]);
            }
            else return false;
        }
        return false;
    }

    public void delete(String data) throws Error {
        char[] word = data.trim().toCharArray();
        Node parent = head;
        for (int letterIndex = 0; letterIndex < word.length; letterIndex++) {
            Node resultPCBK = parentContainsByKey(parent, word[letterIndex]);
            if (resultPCBK != null) {
                if (letterIndex == word.length - 1) {
                    if (resultPCBK.childrenList != null && resultPCBK.check) resultPCBK.check = false;
                    if (resultPCBK.childrenList == null) parent.childrenList.remove(resultPCBK);
                    else throw new Error("Wrong data");
                }
            }
            else throw new Error("Wrong data");
            parent = resultPCBK;
        }
        String newData = data.substring(0, data.length() - 2);
        if (newData.length() > 0) delete(newData);
    }

    public List<String> findAllByPrefix(String data) {
        char[] word = data.trim().toCharArray();
    }
}
