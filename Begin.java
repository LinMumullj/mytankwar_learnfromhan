import javax.swing.JFrame;

import gamecode.MyPanel;

public class Begin extends JFrame{


    /**
     *
     */
    private static final long serialVersionUID = 1L;
    MyPanel mypanel;
    public Begin()
    {
        //初始化面板
        mypanel=new MyPanel();
        //把面板放入窗口
        this.add(mypanel);   
        //添加键盘监听
        this.addKeyListener(mypanel);
        //让Panel自动刷新跑起来
        Thread flashpanel =new Thread(mypanel);
        flashpanel.start();
        //设置窗口属性：
        this.setSize(1010,730);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Tank War");
        this.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        Begin newgame=new Begin();

    }
}