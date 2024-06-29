package Nave;

public class Enemy1 extends BaseEnemy{
    long next_shoot;

    public Enemy1(int state, double x, double y, double v) {
        super(state, x, y, v);
        radius = 9.0;
        next_shoot =  System.currentTimeMillis();
        next_enemy = System.currentTimeMillis() + 2000;
    }

    @Override
    public void updatePosition(long delta) {
        x += v * Math.cos(angle) * delta;
        y += v * Math.sin(angle) * delta * (-1.0);
    }

}
