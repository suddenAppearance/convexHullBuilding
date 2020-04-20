package Others;

import Algorithms.JarvisAlgorithm;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        Point[] array = new Point[n];
        for (int i = 0; i < n; i++) {
            array[i] = new Point(s.nextDouble(), s.nextDouble());
        }
        Stack<Point> stack = JarvisAlgorithm.run(array);
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(stack.toArray()));
    }

}
