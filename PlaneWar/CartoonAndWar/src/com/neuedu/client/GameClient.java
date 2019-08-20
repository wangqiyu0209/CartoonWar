package com.neuedu.client;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.entity.BackGround;
import com.neuedu.entity.Boom;
import com.neuedu.entity.Boss;
import com.neuedu.entity.Bullet;
import com.neuedu.entity.EnemyPlane;
import com.neuedu.entity.Plane;
import com.neuedu.entity.Prop;
import com.neuedu.util.GetImageUtil;
import com.neuedu.util.SoundPlayer;

/**
* @ClassName: GameClient
* @Description: 游戏的客户端
* @author wqy
* @date 2019年8月17日 下午1:55:27
*
*/
public class GameClient extends Frame{
	
	// 创建一个我方飞机的集合
	public List<Plane> planes = new ArrayList<>();
	
	// 创建一个子弹的集合
	// 因为GameClient和Plane属于不同的包 所有修饰符要用public
	public List<Bullet> bullets = new ArrayList<>();
	
	// 创建一个道具的集合
	public List<Prop> props = new ArrayList<>();
	
	// 创建一个背景图
	BackGround backImg = new BackGround(0,0,"bg6.jpg");
	
	// 创建一个爆炸的集合
	public List<Boom> booms = new ArrayList<>();

	// 创建敌方集合
	public List<Plane> enemys = new ArrayList<>();
	
	// 创建一个boss集合 (boss继承Plane)
	public List<Plane> bosss = new ArrayList<>();
	
	// 解决图片闪烁问题
	@Override
	public void update(Graphics g){
	   Image backImg = createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
	   Graphics backg= backImg.getGraphics();
	   Color color = backg.getColor(); 
       backg.setColor(Color.WHITE);
       backg.fillRect(0,0,Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
	   backg.setColor(color);
	   paint(backg);
	   g.drawImage(backImg,0,0,null);
	}
	
	Plane plane = null;
	// 生成客户端
	public void LaunchFrame() {
		// 创建一个音乐线程
		SoundPlayer soundPlayer = new SoundPlayer("com/neuedu/sound/bgSound.mp3");
		soundPlayer.start();
		
		// 设置窗口大小
		this.setSize(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
		// 设置标题
		this.setTitle("飞机大战");
		// 设置是否能显示窗口
		this.setVisible(true);
		// 禁止最大化
		this.setResizable(false);
		// 窗口居中
		this.setLocationRelativeTo(null);
		
		Image img = GetImageUtil.getImg("icon.png");
		this.setIconImage(img);
		
		// 关闭窗口
		  // 添加一个关闭监听器   //匿名内部类
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		// 往我方容器添加飞机
		plane = new Plane(100,500,"plane/2.png",this,true);
		planes.add(plane);

		    // 添加键盘监听
		    this.addKeyListener(new KeyAdapter() {
			@Override
			// 键盘按下的动作
			public void keyPressed(KeyEvent e) {
				plane.keyPressed(e);
			}
			
			@Override
			// 释放键盘
			public void keyReleased(KeyEvent e) {
				plane.keyReleased(e);
			}
		});
		
		// 启动线程
		new paintThread().start();
		
		// 往敌方容器添加敌人
		EnemyPlane enemy1 = new EnemyPlane(210,110,1,this,false);
		EnemyPlane enemy2 = new EnemyPlane(100,80,2,this,false);
		EnemyPlane enemy3 = new EnemyPlane(300,100,3,this,false);
		EnemyPlane enemy4 = new EnemyPlane(150,90,4,this,false);
		EnemyPlane enemy5 = new EnemyPlane(250,190,5,this,false);
		EnemyPlane enemy6 = new EnemyPlane(350,70,6,this,false);
		EnemyPlane enemy7 = new EnemyPlane(50,370,7,this,false);
		enemys.add(enemy1);
		enemys.add(enemy2);
		enemys.add(enemy3);
		enemys.add(enemy4);
		enemys.add(enemy5);
		enemys.add(enemy6);
		enemys.add(enemy7);
		
		// 添加boss                        // 坏的
		bosss.add(new Boss(300, -500, this,false));
	}
	
	// 重写paint()方法
	@Override
	public void paint(Graphics g) {
		backImg.draw(g);
		g.drawLine(0, 230, 520, 230);
		
		// 画我方飞机
		for(int i = 0;i<planes.size();i++) {
			Plane plane2 = planes.get(i);
			plane2.draw(g);
			plane2.cantainItems(props);
			
		}
		
		// 循环画出每个子弹
		// 增强for循环只能遍历  不能做任何操作  所以用传统for循环
		for (int i = 0; i < bullets.size(); i++ ) {
			Bullet bullet = bullets.get(i);
			bullet.draw(g);
			bullet.hitMonsters(enemys);
			bullet.hitMonsters(planes);
			bullet.hitMonsters(bosss);
		}
		// 设置字体颜色
		g.setColor(Color.green);
		g.drawString("子弹的数量："+bullets.size(), 10, 50);
		g.drawString("敌人的数量："+enemys.size(), 10, 70);
		//g.drawString("当前爆炸的数量："+booms.size(), 10, 90);
		g.drawString("英雄的数量："+planes.size(), 10, 90);
		g.drawString("道具的数量："+props.size(), 10, 110);
		
		
		// 循环画敌方
		for(int i = 0;i < enemys.size();i++) {
			enemys.get(i).draw(g);
		}
		
		// 循环爆炸
		for(int i = 0 ; i < booms.size() ; i++) {
			// 如果爆炸活着 
			if(booms.get(i).isLive()==true) {
				// 画
				booms.get(i).draw(g);	
			}
		}
		// 循环画道具
		for(int i = 0 ; i < props.size() ; i++) {
			 {	
				props.get(i).draw(g);	
			}
		}
		//如果敌机全击打灭
		if(enemys.size() == 0) {
			//循环遍历boss集合
			for(int i = 0 ; i < bosss.size() ; i++) {
				 {
					bosss.get(i).draw(g);	
				}
			}
		}
		
	}
	
	// 创建一个内部类(线程类)  
	class paintThread extends Thread{
		
		@Override
		public void run() {// 重写Thread中的run方法
			while(true) {
				repaint();// 重复画
				try {
					Thread.sleep(40);// 画一次休息时间
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
}
