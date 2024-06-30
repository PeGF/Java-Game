package Nave;

import java.util.ArrayList;
import java.util.List;

public class BulletsManager {
    private final int max_bullets;
    private List<Bullet> bullets;

    public BulletsManager(int max_bullets) {
        this.max_bullets = max_bullets;
        this.bullets = new ArrayList<>();
    }

    public boolean canShoot() {
        return bullets.size() < max_bullets;
    }

    public void addBullet(Bullet bullet) {
        if (canShoot()) {
            bullets.add(bullet);
        }
    }

    public void updateBullets(int bullet_vx, int bullet_vy, long delta, int width, int height) {
        List<Bullet> bulletsToRemove = new ArrayList<>();
        for (Bullet bullet : bullets) {
            // Verifica se o projétil saiu da tela (exemplo simplificado)
            if (bullet.getX() < 0 || bullet.getX() > width || bullet.getY() < 0 || bullet.getY() > height) {
                bulletsToRemove.add(bullet);
            }
            else{
                bullet.updatePosition(delta);
            }
        }
        bullets.removeAll(bulletsToRemove);
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public int getMax_bullets() {
        return max_bullets;
    }
}
