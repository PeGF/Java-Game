package Nave;

import java.util.ArrayList;
import java.util.List;

public class BulletsManager {
    private final int max_bullets;
    private List<Bullet> bullets;
    private int current_bullet_count;

    public BulletsManager(int max_bullets) {
        this.max_bullets = max_bullets;
        this.bullets = new ArrayList<>();
        this.current_bullet_count = 0;
    }

    public boolean canShoot() {
        return current_bullet_count < max_bullets;
    }

    public void addBullet(Bullet bullet) {
        if (canShoot()) {
            bullets.add(bullet);
            current_bullet_count++;
        }
    }

    public void updateBullets(long delta, int width, int height) {
        List<Bullet> bulletsToRemove = new ArrayList<>();
        for (Bullet bullet : bullets) {
            // Verifica se o projétil saiu da tela (exemplo simplificado)
            if (bullet.getX() < 0 || bullet.getX() > width || bullet.getY() < 0 || bullet.getY() > height) {
                bulletsToRemove.add(bullet);
                current_bullet_count--;
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
