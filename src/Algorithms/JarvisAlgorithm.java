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
        Point startPoint = list.get(0);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getY() < startPoint.getY()) {
                startPoint = list.get(i);
            } else if (list.get(i).getY() == startPoint.getY() && list.get(i).getX() > startPoint.getX()) {
                startPoint = list.get(i);
            }
        }
        Vector2D vector2D = new Vector2D(1, 0);
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
        Point maxPoint = points.get(0);
        Vector2D vector = new Vector2D(maxPoint.getX()-point.getX(),maxPoint.getY() - point.getY());
        double maxCos = vector.cos(vector2D);
        for (Point p: points
             ) {
            vector = new Vector2D(p.getX()-point.getX(), p.getY() - point.getY());
            if (vector.cos(vector2D)> maxCos){
                maxPoint = p;
                maxCos = vector.cos(vector2D);
            }
            else if (vector.cos(vector2D)==maxCos && vector.length()<vector2D.length()){
                maxPoint = p;
            }
        }
        return maxPoint;
    }
}
