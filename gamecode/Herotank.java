package gamecode;

public class Herotank extends Tank{

    public Herotank(int x, int y ,int dir) {
        super(100, x, y, dir, 0);
    }
    
    @Override
    public void addBullet(int x, int y, int dir, int speed) {
        Bullet newbl=new Bullet(x,y,dir,speed);
        bullets.add(newbl);
        new Thread(newbl).start();
    }
    @Override
    public void shoot() {
        switch(getDir()){
            case 0:
                addBullet(getX()+24,getY()-2,0,2);
                break;
            case 1:
                addBullet(getX()+50,getY()+24,1,2);
                break;
            case 2:
                addBullet(getX()+24,getY()+50,2,2);
                break;
            case 3:
                addBullet(getX()-2,getY()+24,3,2);
                break;
            default:
                break;
        }
    }
}