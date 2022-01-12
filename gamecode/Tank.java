package gamecode;

import java.util.Vector;

public class Tank {
    
    private int hp;
    private int x;
    private int y;
    private int dir;
    private int speed;
    private int type;
    private boolean isLive;
    protected Vector<Bullet> bullets;
    private Vector<? extends Tank> others;

    public boolean iscollide(Tank tank1, Vector<? extends Tank> tanks)
    {
        return false;
    }
    Tank()
    {
        hp=100;
        x=0;
        y=0;
        dir=2;
        speed=1;
        type=1;
        isLive=true;
        bullets=new Vector<>();
    }

    public Tank(int hp, int x, int y, int dir,int type) {
        this.hp = hp;
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.speed = 3;
        this.type=type;
        this.isLive = true;
        bullets=new Vector<>();
    }
    public void move(int dir)
    {
        switch(getDir()){
            case 0:
                moveup();
                break;
            case 1:
                moveright();
                break;
            case 2:
                movedown();
                break;
            case 3:
                moveleft();
                break;
            default:
                break;
        }
    }
    public void moveup(){
        if((y-speed)>=0&&(!iscollide(this,others))){
            y -= speed;
            dir=0;
        }
        else
            y=0;
    }
    public void moveright(){
        if((x+speed)<=650&&(!iscollide(this,others)))
        {
            x += speed;
            dir=1;
        }
        else
            x=650;
    }
    public void movedown(){
        if((y+speed)<=650&&(!iscollide(this,others)))
        {
            y += speed;
            dir=2;
        }
        else
            y=650;
    }
    public void moveleft(){
        if((x-speed)>=0&&(!iscollide(this,others)))
        {
            x -= speed;
            dir=3;
        }
        else
            x=0;
    }

    public void shoot(){}
    public void addBullet(int x,int y,int dir,int speed){}


    //getter&setter
    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }
    public void getattack(int hp){
        this.hp-=hp;
        if(this.hp<=0)
        {
            this.hp=0;
            setLive(false);
        }
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean isLive) {
        this.isLive = isLive;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Vector<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(Vector<Bullet> bullets) {
        this.bullets = bullets;
    }

    public Vector<? extends Tank> getOthers() {
        return others;
    }

    public void setOthers(Vector<? extends Tank> others) {
        this.others = others;
    }
    
}