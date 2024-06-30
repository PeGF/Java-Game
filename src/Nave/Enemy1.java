package Nave;

public class Enemy1 extends BaseEnemy{
    long next_shoot;

    public Enemy1(int state, double x, double y, double v, long currentTime) {
        super(state, x, y, v);
        radius = 9.0;
        next_shoot =  0;
        next_enemy = currentTime + 2000;
    }

    @Override
    public void updatePosition(long delta) {
        x += v * Math.cos(angle) * delta;
        y += v * Math.sin(angle) * delta * (-1.0);
    }

    public long getNext_shoot() {
        return next_shoot;
    }

    public void setNext_shoot(long next_shoot) {
        this.next_shoot = next_shoot;
    }

}
