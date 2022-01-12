package gamecode;

public class Enemytank extends Tank implements Runnable {
    public Enemytank(int x, int y, int dir) {
        super(50, x, y, dir, 1);
    }

    @Override
    public void run() {
        while (isLive()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setDir((int) (Math.random() * 4));
            for (int i = 0; i < 5; i++) {
                move(getDir());
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}