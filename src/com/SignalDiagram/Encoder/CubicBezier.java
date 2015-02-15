package com.SignalDiagram.Encoder;

import javafx.geometry.Point2D;

public class CubicBezier {

    public static Point2D getControlPointTangentTo(Point2D p0, Point2D p2, double y) {
        // Retourne le point de controle pour obtenir la tangente en y
        Point2D xy;
        double t = 0.5;
        double x = t * (p2.getX() - p0.getX());

        double h = ((Math.pow((1 - t), 2) * (p0.getY())) + (Math.pow((t), 2) * p2.getY()) - y) / (2 * t * (1 - t));

        xy = new Point2D(x + p0.getX(), h);
        return xy;
    }

    public static Point2D getPoint(Point2D p0, Point2D p1, Point2D p2, Point2D p3, double t) {
        Point2D xy;
        //double t = 0.5; //%distance [0,1]
        double x = (p0.getX() * Math.pow(((1 - t)), 3)) + (3 * p1.getX() * t * Math.pow(((1 - t)), 2)) + (3 * p2.getX() * t * t * Math.pow(((1 - t)), 2)) + (p3.getX() * t * t * t);
        double y = (p0.getY() * Math.pow(((1 - t)), 3)) + (3 * p1.getY() * t * Math.pow(((1 - t)), 2)) + (3 * p2.getY() * t * t * Math.pow(((1 - t)), 2)) + (p3.getY() * t * t * t);

        xy = new Point2D(x, y);
        return xy;
    }

    public static double test(double p0, double p1, double p2, double p3, double tangent) {
        double yTangent = tangent;// = (3 * p1.getY() * t * Math.pow(((1 - t)), 2)) + (3 * p1.getY() * t * t * Math.pow(((1 - t)), 2));
        double t = 0.5;

        //yTangent = 10;
        //p1 == p2
        double yControlPoint;
        //yTangent = p1.getY() * ((3 * 1 * t * Math.pow(((1 - t)), 2)) + (3 * 1 * t * t * Math.pow(((1 - t)), 2)));
        yControlPoint = yTangent / ((3 * 1 * t * Math.pow(((1 - t)), 2)) + (3 * 1 * t * t * ((1 - t))));
        return yControlPoint;
    }
}
