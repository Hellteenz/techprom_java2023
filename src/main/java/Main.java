import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Чтобы добавить слова в дерево, введите 1" +
                "Чтобы удалить слово из дерева, введите 2" +
                "Чтобы узнать, есть ли строка в дереве, введите 3" +
                "Чтобы вывести все слова по заданному префиксу, введите 4");
        Scanner in = new Scanner(System.in);
        int command = in.nextInt();

        PrefixTree xyi = new PrefixTree();

        if (command == 1) {
            System.out.println("Добавление новых слов. После завершения операции введите 0");
            Scanner input = new Scanner(System.in);
            String word = input.next();
            while (word != "0") {
                xyi.add(word);
                input = new Scanner(System.in);
                word = input.next();
            }
        }


    }

}
