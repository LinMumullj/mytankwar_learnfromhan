package gamecode;

import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JPanel;

public class MyPanel extends JPanel implements KeyListener,Runnable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    // 定义画板中要画的东西，比如坦克,子弹
    Herotank hero;
    Vector<Enemytank> enemytanks=new Vector<>();
    Vector<Bullet> bullets=new Vector<>();
    Vector<Bomb> bombs=new Vector<>();
    int score;
    // 定义部分资源，如图片
    Image bg = null;
    Image board = null;
    Image bullet=null;
    Image bomb1=null;
    Image bomb2=null;
    Image bomb3=null;
    Image bomb4=null;
    Image bomb5=null;
    Image bomb6=null;

    public MyPanel() {
        setSize(1000, 700);
        score=0;
        hero = new Herotank(400, 640, 0);
        createEnemy();
        bg = Toolkit.getDefaultToolkit().getImage("gameresources/image/bg-sand.jpg");
        board = Toolkit.getDefaultToolkit().getImage("gameresources/image/bg-grass.jpg");
        bullet = Toolkit.getDefaultToolkit().getImage("gameresources/image/bullet/normal.png");
        bomb1 = Toolkit.getDefaultToolkit().getImage("gameresources/image/explosion/6.png");
        bomb2 = Toolkit.getDefaultToolkit().getImage("gameresources/image/explosion/5.png");
        bomb3 = Toolkit.getDefaultToolkit().getImage("gameresources/image/explosion/4.png");
        bomb4 = Toolkit.getDefaultToolkit().getImage("gameresources/image/explosion/3.png");
        bomb5 = Toolkit.getDefaultToolkit().getImage("gameresources/image/explosion/2.png");
        bomb6 = Toolkit.getDefaultToolkit().getImage("gameresources/image/explosion/1.png");
    }

    public void createEnemy()
    {
        for(int i=0;i<3;i++)
        {
            Enemytank enemyadd=new Enemytank(20+i*100, 10, 2);
            enemytanks.add(enemyadd);
            //enemyadd.setOthers(enemytanks);
            new Thread(enemyadd).start();
        }
    }

    public void addBomb(int x,int y)
    {
        Bomb bombadd=new Bomb(x, y);
        bombs.add(bombadd);
        new Thread(bombadd).start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // 绘制所有游戏中出现的东西

        // 1.绘制背景和得分
        g.drawImage(bg, 0, 0, 700, 700, this);
        g.drawImage(board, 700, 0, 300, 700, this);
        


        // 2.画出坦克,并删除死亡坦克
        //己方角色
        if(hero.isLive())
            drawtank(g, hero.getX(), hero.getY(), hero.getDir(), 0, hero.getHp(),100);
        else
            System.out.println("Game Over");

        //敌方坦克
        for(Iterator<Enemytank> it=enemytanks.iterator();it.hasNext();)
        {
            Enemytank tmp=it.next();
            if(tmp.isLive())
                drawtank(g, tmp.getX(), tmp.getY(), tmp.getDir(), 1,tmp.getHp(),50);
            else
            {
                addBomb(tmp.getX(), tmp.getY());
                it.remove();
                score++;
            }

            //是否存在问题？？for不能遍历删除
        }

        // 3.画出子弹，并删除死亡子弹
        //己方角色的子弹
        for(Iterator<Bullet> it = hero.getBullets().iterator();it.hasNext();)
        {
            Bullet tmpb=it.next();
            if(tmpb.getIsLive())
                drawbullet(g,tmpb.getX(),tmpb.getY());
            else
                it.remove();
        }
        //敌方坦克的子弹
        for(int i=0;i<enemytanks.size();i++)
        {
            Enemytank tmp=enemytanks.get(i);
            for(Iterator<Bullet> it = tmp.getBullets().iterator();it.hasNext();)
            {
                Bullet tmpb2=it.next();
                if(tmpb2.getIsLive())
                    drawbullet(g,tmpb2.getX(),tmpb2.getY());
                else
                    it.remove();
            }
        }

        //4.画出爆炸
        for(Iterator<Bomb> it=bombs.iterator();it.hasNext();)
        {
            Bomb tmp=it.next();
            if(tmp.isLive())
            {
                int tmp2=tmp.getNowtime();
                if(tmp2>7){
                    g.drawImage(bomb1, tmp.getX(), tmp.getY(), 50, 50, this);
                }else if(tmp2>6){
                    g.drawImage(bomb2, tmp.getX(), tmp.getY(), 50, 50, this);
                }else if(tmp2>5){
                    g.drawImage(bomb3, tmp.getX(), tmp.getY(), 50, 50, this);
                }else if(tmp2>4){
                    g.drawImage(bomb4, tmp.getX(), tmp.getY(), 50, 50, this);
                }else if(tmp2>3){
                    g.drawImage(bomb5, tmp.getX(), tmp.getY(), 50, 50, this);
                }else{
                    g.drawImage(bomb6, tmp.getX(), tmp.getY(), 50, 50, this);
                }
            }else{
                it.remove();
            }
        }
    }

    // 类型自己为0，敌人为1
    // 方向上为0，顺时针增加
    public void drawtank(Graphics g, int x, int y, int dir, int type, int hp,int maxhp) {
        if (type == 0) {
            switch (dir) {
                case 0:
                    g.drawImage(Toolkit.getDefaultToolkit().getImage("gameresources/image/tank/enemy/2/tank-u.png"), x,
                            y, 50, 50, this);
                    break;
                case 1:
                    g.drawImage(Toolkit.getDefaultToolkit().getImage("gameresources/image/tank/enemy/2/tank-r.png"), x,
                            y, 50, 50, this);
                    break;
                case 2:
                    g.drawImage(Toolkit.getDefaultToolkit().getImage("gameresources/image/tank/enemy/2/tank-d.png"), x,
                            y, 50, 50, this);
                    break;
                case 3:
                    g.drawImage(Toolkit.getDefaultToolkit().getImage("gameresources/image/tank/enemy/2/tank-l.png"), x,
                            y, 50, 50, this);
                    break;
            }
        } else {
            switch (dir) {
                case 0:
                    g.drawImage(Toolkit.getDefaultToolkit().getImage("gameresources/image/tank/enemy/1/tank-u.png"), x,
                            y, 50, 50, this);
                    break;
                case 1:
                    g.drawImage(Toolkit.getDefaultToolkit().getImage("gameresources/image/tank/enemy/1/tank-r.png"), x,
                            y, 50, 50, this);
                    break;
                case 2:
                    g.drawImage(Toolkit.getDefaultToolkit().getImage("gameresources/image/tank/enemy/1/tank-d.png"), x,
                            y, 50, 50, this);
                    break;
                case 3:
                    g.drawImage(Toolkit.getDefaultToolkit().getImage("gameresources/image/tank/enemy/1/tank-l.png"), x,
                            y, 50, 50, this);
                    break;
            }
        }
        g.setColor(Color.white);
        g.fillRect(x, y-5, 50, 5);
        g.setColor(Color.red);
        g.fillRect(x, y-5, (int)(50*((double)hp/maxhp)), 5);
    }

    public void drawbullet(Graphics g, int x, int y)
    {
        g.drawImage(bullet, x, y, 2,2,this);
    }
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case (KeyEvent.VK_UP):
                hero.moveup();
                hero.setDir(0);
                break;
            case (KeyEvent.VK_RIGHT):
                hero.moveright();
                hero.setDir(1);
                break;
            case (KeyEvent.VK_DOWN):
                hero.movedown();
                hero.setDir(2);
                break;
            case (KeyEvent.VK_LEFT):
                hero.moveleft();
                hero.setDir(3);
                break;
            case (KeyEvent.VK_SPACE):
                hero.shoot();
                break;
            default:
                break;
        }

        //repaint();

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    //碰撞检测函数
    public void isCollide(Bullet bullet,Tank tank)
    {
        if((bullet.getX()>tank.getX()&&bullet.getX()<tank.getX()+50)&&(bullet.getY()>tank.getY()&&bullet.getY()<tank.getY()+50))
        {
            tank.getattack(10);
            bullet.setIsLive(false);
        }
    }
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //判断己方子弹与敌方碰撞
            if(hero.getBullets()!=null)
            {
                for(int i=0;i<hero.getBullets().size();i++)
                {
                    Bullet tmp=hero.getBullets().get(i);
                    for(int j=0;j<enemytanks.size();j++)
                    {
                        Enemytank tmp2=enemytanks.get(j);
                        isCollide(tmp,tmp2);
                    }
                }
            }   
            //判断敌方子弹与己方碰撞
            for(int m=0;m<enemytanks.size();m++)
            {
                Vector<Bullet> tmp=enemytanks.get(m).getBullets();
                if(tmp!=null)
                {
                    for(int i=0;i<tmp.size();i++)
                    {
                        Bullet tmp2=tmp.get(i);
                        isCollide(tmp2, hero);
                    }
                }
            }
            repaint();
        }

    }
}