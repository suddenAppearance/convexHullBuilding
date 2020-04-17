package Others;

public class Vector2D {
    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D() {
        this(0, 0);
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public Vector2D add(Vector2D a) {
        return new Vector2D(this.getX() + a.getX(), this.getY() + a.getY());
    }

    public void add2(Vector2D a) {
        this.setX(this.getX() + a.getX());
        this.setY(this.getY() + a.getY());
    }

    public Vector2D sub(Vector2D a) {
        return new Vector2D(this.getX() - a.getX(), this.getY() - a.getY());
    }

    public void sub2(Vector2D a) {
        this.setX(this.getX() - a.getX());
        this.setY(this.getY() - a.getY());
    }

    public Vector2D mult(double a) {
        return new Vector2D(this.getX() * a, this.getY() * a);
    }

    public void mult2(double a) {
        this.setX(this.getX() * a);
        this.setY(this.getY() * a);
    }
    @Override
    public String toString() {
        return getX() + " " + getY();
    }

    public double length() {
        return Math.sqrt(this.getX() * this.getX() + this.getY() * this.getY());
    }

    public double scalarProduct(Vector2D a) {
        return this.getX() * a.getX() + this.getY() * a.getY();
    }

    public double cos(Vector2D a) {
        return this.scalarProduct(a) / (this.length() * a.length());
    }

    public boolean equals(Vector2D a) {
        if (this.getY() == a.getY() & this.getX() == a.getX()) {
            return true;
        }
        return false;
    }
}