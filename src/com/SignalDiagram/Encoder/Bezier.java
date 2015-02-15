package com.SignalDiagram.Encoder;

import javafx.geometry.Point2D;

public class Bezier {

    public static Point2D getControlPointTangentTo(Point2D p0, Point2D p2, double y) {
        // Retourne le point de controle pour obtenir la tangente en y
        Point2D xy;
        double t = 0.5;
        double x = t * (p2.getX() - p0.getX());

        double h = ((Math.pow((1 - t), 2) * (p0.getY())) + (Math.pow((t), 2) * p2.getY()) - y) / (2 * t * (1 - t));

        xy = new Point2D(x + p0.getX(), h);
        return xy;
    }

    public static Point2D getPoint(Point2D p0, Point2D p1, Point2D p2, double t) {
        Point2D xy;
        //double t = 0.5; //%distance [0,1]
        double x = (1 - t) * (1 - t) * p0.getX() + 2 * (1 - t) * t * p1.getX() + t * t * p2.getX();
        double y = (1 - t) * (1 - t) * p0.getY() + 2 * (1 - t) * t * p1.getY() + t * t * p2.getY();

        xy = new Point2D(x, y);
        return xy;
    }
}
