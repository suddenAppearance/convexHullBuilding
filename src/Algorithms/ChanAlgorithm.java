package Algorithms;

import Others.Point;
import Others.Vector2D;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class ChanAlgorithm {
    public static Stack<Point> run(Point[] points) throws IOException {
        Point startPoint = points[0];
        for (int i = 0; i < points.length; i++) {
            if(points[i].getY() < startPoint.getY() || (points[i].getY() == startPoint.getY() && points[i].getX() > startPoint.getX())){
                startPoint = points[i];
                points[i] = points[points.length -1];
                points[points.length - 1] = startPoint;
            }
        }
        ArrayList<Point> pointsList = new ArrayList<>(Arrays.asList(points));
        pointsList.remove(pointsList.size() - 1);
        return doAlgorithm(startPoint, pointsList, 0);
    }

    public static Stack<Point> doAlgorithm(Point startPoint, ArrayList<Point> points, int t) throws IOException {
        int m = (int) Math.pow(2,Math.pow(2,t));
        ArrayList<Stack<Point>> stacksPoints = pointGroups(points, m);
        ArrayList<Stack<Point>> newStacksPoints = new ArrayList<>();
        for (Stack<Point> stack:stacksPoints) {
            Stack<Point> newStack = new Stack<>();
            while(!stack.isEmpty()){
                newStack.push(stack.pop());
            }
            newStacksPoints.add(newStack);
        }
        Stack<Point> answers = new Stack<>();
        answers.push(startPoint);
        double maxCos = -1;
        int index = 0;
        double xMin = startPoint.getX();
        double yMax = startPoint.getY();
        Point point = startPoint;
        for(long j = 0; j < m; j++) {
            index = -1;
            maxCos = -2;
            for (int i = 0; i < newStacksPoints.size(); i++) {
                if(newStacksPoints.get(i).isEmpty()){
                    newStacksPoints.remove(newStacksPoints.get(i));
                    i--;
                    continue;
                }
                double cos = checkPolarAngle(point, newStacksPoints.get(i).peek());
                if (cos > maxCos && (newStacksPoints.get(i).peek().getY() > yMax || newStacksPoints.get(i).peek().getX() < xMin)) {
                    index = i;
                    maxCos = cos;
                    if (newStacksPoints.get(i).peek().getY() > yMax){
                        yMax = newStacksPoints.get(i).peek().getY();
                    }
                    if (newStacksPoints.get(i).peek().getX() < xMin){
                        xMin = newStacksPoints.get(i).peek().getX();
                    }
                }
                if(cos == maxCos && Math.abs(point.getY() - newStacksPoints.get(index) .peek().getY()) > Math.abs(point.getY() - newStacksPoints.get(i).peek().getY())){
                    index = i;
                }
            }
            if(j != 0 && checkPolarAngle(point, startPoint) > maxCos || index == -1 || checkPolarAngle(point, startPoint) > maxCos || newStacksPoints.size() == 0){
                return answers;
            }
            answers.push(newStacksPoints.get(index).pop());
            point = answers.peek();
        }
        return doAlgorithm(startPoint, points, t+1 );
    }
    public static ArrayList<Stack<Point>> pointGroups(ArrayList<Point> points, int m) throws IOException {
        ArrayList<ArrayList<Point>> list = new ArrayList<>();
        int k = 0;
        while(k < points.size()){
            int n = 0;
            ArrayList<Point> l = new ArrayList<>();
            if(k + m >= points.size()){
                n = (k + m - points.size());
            }
            while(n < m){
                l.add(points.get(k));
                n++;
                k++;
            }
            list.add(l);
        }
        ArrayList<Stack<Point>> stacksPoints = new ArrayList<>();
        for (ArrayList<Point> pointArrayList : list) {
            stacksPoints.add(sortPoints(pointArrayList));
        }
        return stacksPoints;
    }

    public static Stack<Point> sortPoints(ArrayList<Point> points) throws IOException {
        Point[] pointsArray = new Point[points.size()];
        for(int i = 0; i < points.size(); i++){
            pointsArray[i] = points.get(i);
        }
        return GrahamAlgorithm.run(pointsArray);
    }

    public static double checkPolarAngle(Point startPoint, Point point){
        Vector2D vector2D = new Vector2D(1,0);
        Vector2D vector = new Vector2D(point.getX() - startPoint.getX(), point.getY() - startPoint.getY());
        return vector.cos(vector2D);
    }
}
