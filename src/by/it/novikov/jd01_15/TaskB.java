package by.it.novikov.jd01_15;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

class TaskB {
    private static String fileName = Helper.getPath("TaskB.java", TaskB.class);
    private static String resultFileName = Helper.getPath("TaskB.txt", TaskB.class);

    public static void main(String[] args) {
        String text = readCodeFromFile(fileName); //коммент
        String textWithoutComments = deleteComments(text);
        System.out.println(textWithoutComments);
        writeTextToFile(textWithoutComments, resultFileName);
    }

    private static String readCodeFromFile(String fileName) {
        StringBuilder textConstructor = new StringBuilder();
        try (FileReader fileReader = new FileReader(fileName)) {
            int oneSymbol;
            while ((oneSymbol = fileReader.read()) != -1) {
                textConstructor.append((char) oneSymbol);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return textConstructor.toString();
    }

    private static String deleteComments(String text) {
        StringBuilder textBuilder = new StringBuilder(text);
        for (int i = 0; i < textBuilder.length() - 2; i++) {
            if (textBuilder.charAt(i) == '/') {
                switch (textBuilder.charAt(i + 1)) {
                    case '/': {
                        Comment.SINGLE_lINE_COMMENT.deleteComment(textBuilder, i);
                        break;
                    }
                    case '*': {
                        Comment.MULTIPLE_LINE_COMMENT.deleteComment(textBuilder, i);
                        break;
                    }
                }
            }
        }
        return textBuilder.toString();
    }

    private static void writeTextToFile(String text, String fileName) {
        try (PrintWriter pw = new PrintWriter(fileName)
        ) {
            pw.print(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}