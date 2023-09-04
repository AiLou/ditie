package com.tea.lesson03;

import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Cra2iTeT
 * @version 1.0
 * @date 2022/3/25 17:34
 */
public class test {
    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
    }

    public static void test1() {
        try {
            String[] str = new String[]{"python",
                    "C:\\CS\\Java\\IdeaProjects\\Lesson03\\src\\main\\java\\com\\tea\\lesson03\\function\\count.py"};
            Process proc = Runtime.getRuntime().exec(str);      // 执行py文件

            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;

            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }

            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void test2() {
        String str = "苹果园-1(8)-军事博物馆-9(3)-国家图书馆-4(4)-中关村";
        System.out.println(str.substring(str.indexOf('(') + 1, str.indexOf(')')));
    }

    public static void test3() {
        String haystack = "hello";
        String needle = "ll";

        int[] pi = new int[needle.length()];

        for (int i = 1, j = 0; i < needle.length(); i++) {
            while (j > 0 && needle.charAt(i) != needle.charAt(j)) {
                j = pi[j - 1];
            }
            if (needle.charAt(i) == needle.charAt(j)) {
                j++;
            }
            pi[i] = j;
        }
    }

}
