package Assign;


abstract class Shape {
    private static int counter = 1000;
    private final String shapeId;

    Shape() {
        shapeId = "SH-" + (++counter);
    }

    public abstract double calculateArea();

    public void scale(double factor) {
        scale(factor, factor);
    }

    public void scale(double xFactor, double yFactor) {
        // Implemented by each shape
    }

    public String getShapeId() {
        return shapeId;
    }
}

class CircleShape extends Shape {
    private double radius;

    CircleShape(double radius) {
        this.radius = radius;
    }

    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public void scale(double xFactor, double yFactor) {
        radius *= Math.sqrt(xFactor * yFactor);
    }
}

class SquareShape extends Shape {
    private double side;

    SquareShape(double side) {
        this.side = side;
    }

    public double calculateArea() {
        return side * side;
    }

    @Override
    public void scale(double xFactor, double yFactor) {
        side *= Math.sqrt(xFactor * yFactor);
    }
}

public class Question1
{
    static void printArea(Shape s) {
        System.out.println("Area: " + s.calculateArea());
    }

    public static void main(String[] args) {
        CircleShape c = new CircleShape(5.0);
        SquareShape sq = new SquareShape(4.0);

        printArea(c);
        printArea(sq);

        sq.scale(2.0);
        printArea(sq);

        System.out.println(sq.getShapeId());
    }
}
