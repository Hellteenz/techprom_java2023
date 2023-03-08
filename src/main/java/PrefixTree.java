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
        private final char key;
        private boolean check;
        private final List<Node> childrenList;

        public Node(char data, boolean isEndOfWord, List<Node> childrenList) {
            this.key = data;
            this.check = isEndOfWord;
            this.childrenList = childrenList;

        }
    }
    List<Node> list = new ArrayList<>();
    private Node head = new Node(' ', false, list);

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
            Node resultPCBK = parentContainsByKey(parent, word[letterIndex]);
            if (resultPCBK != null) {
                if (letterIndex == word.length - 1) {
                    if (resultPCBK.check) {
                        System.out.println("Ура! Победа! Ура! Такое слово найдено!");
                        return true;
                    } else {
                        System.out.println("OH NO... Такого слова нет...");
                        return false;
                    }
                }
                parent = resultPCBK;
            }
            else return false;
        }
        return false;
    }
    private boolean checkSubstring = false;
    public void delete(String data) throws Error {
        boolean breakFlag = false;
        char[] word = data.trim().toCharArray();
        Node parent = head;
        for (int letterIndex = 0; letterIndex < word.length; letterIndex++) {
            Node resultPCBK = parentContainsByKey(parent, word[letterIndex]);
            if (resultPCBK != null) {
                if (letterIndex == word.length - 1) {
                    if (resultPCBK.childrenList != null) {
                        if (resultPCBK.check) {
                            if (!checkSubstring) resultPCBK.check = false;
                            else breakFlag = true;
                        } else if (checkSubstring) parent.childrenList.remove(resultPCBK);
                        else throw new Error("Wrong data 1");
                    } else parent.childrenList.remove(resultPCBK);
                }
            } else throw new Error("Wrong data 2");
            parent = resultPCBK;
        }
        checkSubstring = true;
        String newData = data.substring(0, data.length() - 1);
        if (newData.length() > 0 && !breakFlag) {
            delete(newData);
        }
        checkSubstring = false;
    }

    public List<String> findAllByPrefix(String data) {
        char[] word = data.trim().toCharArray();
        Node parent = head;
        List<String> listOfResWord = new ArrayList<>();
        String strByPrefix = "";
        for (char prefixLetter : word) {
            Node resultPCBK = parentContainsByKey(parent, prefixLetter);
            if (resultPCBK != null) {
                strByPrefix += prefixLetter;
                parent = resultPCBK;
            } else return null;
        }
        if (strByPrefix != null && parent.check) {
            listOfResWord.add(strByPrefix);
        }
        if (parent.childrenList.size() != 0) listOfResWord.addAll(takeAllWordsByPrefix(parent, strByPrefix));
        return listOfResWord;
    }

    private List<String> takeAllWordsByPrefix(Node prefix, String strByPrefix) {
        List<String> listOfResWord = new ArrayList<>();
        String data = strByPrefix;
        for (Node children: prefix.childrenList) {
            strByPrefix += children.key;
            if (children.check) listOfResWord.add(strByPrefix);
            if (children.childrenList != null) {
                listOfResWord.addAll(takeAllWordsByPrefix(children, strByPrefix));
                strByPrefix = data;
            }
        }
        return listOfResWord;
    }
}
