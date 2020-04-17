package Algorithms;

import Others.Main;
import Others.Point;
import Others.Vector2D;

import java.io.IOException;
import java.util.*;

public class GrahamAlgorithm {
    public static Stack<Point> run(Point[] array) throws IOException {
        if (array.length == 0) throw new IOException("There are no points to cover");
        Point min = array[0];//1st point we take is the lowest and the most right
        int iMin = 0;
        for (int i = 0; i < array.length; i++) {
            Point point = array[i];
            if (point.getY() < min.getY()) {
                min = point;
                iMin = i;
            } else if (point.getY() == min.getY() & point.getX() > min.getX()) {
                min = point;
                iMin = i;
            }
        }
        array[iMin] = array[array.length - 1];
        array[array.length - 1] = min;
        double xOffset = array[array.length-1].getX();
        double yOffset = array[array.length-1].getY();
        //to sort we will replace the origin to the minimal point;
        for (Point point: array
             ) {
            point.setX(point.getX()-xOffset);
            point.setY(point.getY()-yOffset);
        }
        Arrays.sort(array,0, array.length-1, (o1, o2) -> {
            Vector2D v1 = new Vector2D(o1.getX(), o1.getY());
            Vector2D v2 = new Vector2D(o2.getX(), o2.getY());
            Vector2D v3 = new Vector2D(1, 0);
            Double cos1 = v3.cos(v1);
            Double cos2 = v3.cos(v2);
            if (cos1.equals(cos2)){
                Double dbl = v1.length();
                return -1*dbl.compareTo(v2.length());
            }
            return cos2.compareTo(cos1);
        });
        for (Point point: array
             ) {
            point.setX(point.getX()+xOffset);
            point.setY(point.getY()+yOffset);
        };
        return solve(array);
    }

    private static Stack<Point> solve(Point[] sortedArray ) {
        Stack<Point> ans = new Stack<>();
        ans.push(sortedArray[sortedArray.length-1]);
        ans.push(sortedArray[0]);
        for (int i = 1; i < sortedArray.length; i++) {
            Point aj = ans.pop();
            Point aj2 = ans.pop();
            //Coordinates of vector are counted from coordinates
            //of end point minus the start point. Direction is important
            Vector2D a = new Vector2D(aj.getX() - aj2.getX(), aj.getY() - aj2.getY());
            Vector2D b = new Vector2D(sortedArray[i].getX() - aj.getX(),
                    sortedArray[i].getY() - aj.getY());

            while (!leftTurnCheck(a, b)) {
                if (ans.isEmpty()) break;
                else aj = aj2;
                aj2 = ans.pop();
                a = new Vector2D(aj.getX() - aj2.getX(), aj.getY() - aj2.getY());
                b = new Vector2D(sortedArray[i].getX() - aj.getX(),
                        sortedArray[i].getY() - aj.getY());
            }
            ans.push(aj2);
            ans.push(aj);
            ans.push(sortedArray[i]);
        }
        ans.pop();
        return ans;
    }

    private static boolean leftTurnCheck(Vector2D v1, Vector2D v2) {
        Vector2D v = new Vector2D(-v1.getY(), v1.getX()); //Perpendicular to v1 vector which is placed to the left
        return v.scalarProduct(v2) >= 0;
    }
}
