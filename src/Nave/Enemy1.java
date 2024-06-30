package Nave;

public class Enemy1 extends BaseEnemy{
    long next_shoot;
    private BulletsManager bulletsManager;

    public Enemy1(int state, double x, double y, double v, long currentTime, BulletsManager bulletsManager) {
        super(state, x, y, v);
        radius = 2.0;
        next_shoot =  0;
        next_enemy = currentTime + 2000;
        bullet_vx = Math.cos(getAngle()) * 0.45;
        bullet_vy = Math.sin(getAngle()) * 0.15 * (-1);
        this.bulletsManager = bulletsManager;
    }

    @Override
    public void updatePosition(long delta) {
        x += v * Math.cos(angle) * delta;
        y += v * Math.sin(angle) * delta * (-1.0);
    }

    public void shoot(){
        bulletsManager.addBullet(new Bullet(x, y, bullet_vx, bullet_vy, radius));
    }


    public long getNext_shoot() {
        return next_shoot;
    }

    public void setNext_shoot(long next_shoot) {
        this.next_shoot = next_shoot;
    }

}
