package Algorithms;

import Others.Point;
import Others.Vector2D;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class JarvisAlgorithm {
    public static Stack<Point> run(Point[] array) throws IOException {
        if (array.length == 0) throw new IOException("No points to cover");
        return solve(array);
    }

    private static Stack<Point> solve(Point[] array) {
        Stack<Point> stack = new Stack<>();
        ArrayList<Point> list = new ArrayList<>(Arrays.asList(array));
        list.add(list.get(0));
        Point startPoint = list.get(0);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getY() < startPoint.getY()) {
                startPoint = list.get(i);
            } else if (list.get(i).getY() == startPoint.getY() && list.get(i).getX() > startPoint.getX()) {
                startPoint = list.get(i);
            }
        }
        Vector2D vector2D = new Vector2D(0, 1);
        list.remove(startPoint);
        stack.add(startPoint);
        Point point = returnPointWithMaxPolarAngle(vector2D, startPoint, list);
        Point point1 = startPoint;
        while (!point.equals(startPoint)) {
            vector2D = new Vector2D(point.getX() - point1.getX(),
                    point.getY() - point1.getY());
            list.remove(point);
            stack.add(point);
            point1 = point;
            point = returnPointWithMaxPolarAngle(vector2D, point, list);
        }
        return stack;
    }

    private static Point returnPointWithMaxPolarAngle(Vector2D vector2D, Point point, ArrayList<Point> points) {
        
    }
}
