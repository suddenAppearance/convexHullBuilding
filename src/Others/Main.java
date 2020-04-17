package Others;

import Algorithms.GrahamAlgorithm;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    private static GraphicsContext gc;

    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        Point[] array = new Point[n];
        for (int i = 0; i < n; i++) {
            array[i] = new Point(s.nextDouble(), s.nextDouble());
        }
        Stack<Point> stack = GrahamAlgorithm.run(array);
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(stack.toArray()));
    }

}
