import Nave.BaseEnemy;

import java.util.ArrayList;
import java.util.List;

public class Background {
    private final double [] X;
    private final double [] Y;
    private final double speed;
    private double count;

    public Background(int x, double speed) {
        X = new double[x];
        Y = new double[x];
        this.speed = speed;
        count = 0;
    }

    public double[] getX() {
        return X;
    }

    public void setX(int position, int width) {
        X[position] = Math.random() * width;
    }

    public double[] getY() {
        return Y;
    }

    public void setY(int position, int height) {
        Y[position] = Math.random() * height;
    }

    public double getSpeed() {
        return speed;
    }

    public double getCount() {
        return count;
    }

    public void setCount(long delta) {
        this.count = count + speed * delta;
    }
}