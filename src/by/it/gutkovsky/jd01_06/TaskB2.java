package by.it.gutkovsky.jd01_06;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

class TaskB2 {
    public static void main(String[] args) {

        String test = Poem.text.replace('.', '&'); // избавление от трех точек "..."
        StringBuilder sbTest = new StringBuilder(test);
        Pattern patternTest = Pattern.compile("&{2,}");
        Matcher matcherTest = patternTest.matcher(sbTest);
        while (matcherTest.find()) {
            int pos = matcherTest.start();
            sbTest.replace(pos, pos + 3, "   ");
        }

        String testTemp = sbTest.toString();
        test = testTemp.replaceAll("&", ".");

        String[] sentence = test.split("!?\\.\\??");

//        String[] sentence = {};
//        sentence = Poem.text.split("!?\\.\\??");

        String[] newLine = new String[sentence.length];

        Pattern pattern = Pattern.compile("[\\n\\p{P}]"); // не букенные символы  \n - меняю на пробел

        for (int i = 0; i < sentence.length; i++) {
            StringBuilder poemLine = new StringBuilder(sentence[i]);
            Matcher matcher = pattern.matcher(poemLine);
            while (matcher.find()) {
//                newLine[i]= matcher.replaceAll("");
                int position = matcher.start();
                poemLine.replace(position, position + 1, " ");
            }
            newLine[i] = String.valueOf(poemLine);
        }

        // проверка вывода на печать
//        for (int i = 0; i < newLine.length; i++) {
//            System.out.println(i + ":" + newLine[i]);
//        }

        // trim() - начало и конца строки
        for (int i = 0; i < newLine.length; i++) {
            sentence[i] = newLine[i].trim();
        }

        // проверка вывода на печать
//        for (int i = 0; i < sentence.length; i++) {
//            System.out.println(i + ":" + sentence[i]);
//        }

        // удаление лишних пробелов
        for (int i = 0; i < sentence.length; i++) {
            StringBuilder nextIteration = new StringBuilder(sentence[i]);
            Pattern pattern2 = Pattern.compile("[\\s-\\s]{2,}");
            Matcher matcher2 = pattern2.matcher(nextIteration);
            sentence[i] = matcher2.replaceAll(" ");
        }

        String temp;
        boolean needSort = true;
        while (needSort) {
            needSort = false;
            for (int i = 0; i < sentence.length - 1; i++) {
                if (sentence[i].length() > sentence[i + 1].length()) {
                    temp = sentence[i];
                    sentence[i] = sentence[i + 1];
                    sentence[i + 1] = temp;
                    needSort = true;
                }
            }
        }

        for (String s : sentence) {
//            if (s.length() != 0) {
            System.out.printf("%s" + "\n", s);
//            }
        }

        //варианты вывода
//        String[] newLine2 = new String[sentence.length];
//        String mytext = "";
//        for (int i = 0; i < sentence.length; i++) {
//            StringBuilder sb = new StringBuilder(sentence[i]);
////            sb.append(sentence[i]);
//            sb.append("\n");
////            newLine2[i] = sb.toString();
//
//                   mytext = mytext.concat(sb.toString());
//        }
//
//        System.out.println(mytext);

//                for (int i = 0; i < newLine2.length; i++) {
//            if (newLine2[i].length() != 0 && newLine2[i] != "") {
//                System.out.printf("%s", newLine2[i]);
//            }
//        }
    }
}
