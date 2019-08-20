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
* @Description: ��Ϸ�Ŀͻ���
* @author wqy
* @date 2019��8��17�� ����1:55:27
*
*/
public class GameClient extends Frame{
	
	// ����һ���ҷ��ɻ��ļ���
	public List<Plane> planes = new ArrayList<>();
	
	// ����һ���ӵ��ļ���
	// ��ΪGameClient��Plane���ڲ�ͬ�İ� �������η�Ҫ��public
	public List<Bullet> bullets = new ArrayList<>();
	
	// ����һ�����ߵļ���
	public List<Prop> props = new ArrayList<>();
	
	// ����һ������ͼ
	BackGround backImg = new BackGround(0,0,"bg6.jpg");
	
	// ����һ����ը�ļ���
	public List<Boom> booms = new ArrayList<>();

	// �����з�����
	public List<Plane> enemys = new ArrayList<>();
	
	// ����һ��boss���� (boss�̳�Plane)
	public List<Plane> bosss = new ArrayList<>();
	
	// ���ͼƬ��˸����
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
	// ���ɿͻ���
	public void LaunchFrame() {
		// ����һ�������߳�
		SoundPlayer soundPlayer = new SoundPlayer("com/neuedu/sound/bgSound.mp3");
		soundPlayer.start();
		
		// ���ô��ڴ�С
		this.setSize(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
		// ���ñ���
		this.setTitle("�ɻ���ս");
		// �����Ƿ�����ʾ����
		this.setVisible(true);
		// ��ֹ���
		this.setResizable(false);
		// ���ھ���
		this.setLocationRelativeTo(null);
		
		Image img = GetImageUtil.getImg("icon.png");
		this.setIconImage(img);
		
		// �رմ���
		  // ���һ���رռ�����   //�����ڲ���
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		// ���ҷ�������ӷɻ�
		plane = new Plane(100,500,"plane/2.png",this,true);
		planes.add(plane);

		    // ��Ӽ��̼���
		    this.addKeyListener(new KeyAdapter() {
			@Override
			// ���̰��µĶ���
			public void keyPressed(KeyEvent e) {
				plane.keyPressed(e);
			}
			
			@Override
			// �ͷż���
			public void keyReleased(KeyEvent e) {
				plane.keyReleased(e);
			}
		});
		
		// �����߳�
		new paintThread().start();
		
		// ���з�������ӵ���
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
		
		// ���boss                        // ����
		bosss.add(new Boss(300, -500, this,false));
	}
	
	// ��дpaint()����
	@Override
	public void paint(Graphics g) {
		backImg.draw(g);
		g.drawLine(0, 230, 520, 230);
		
		// ���ҷ��ɻ�
		for(int i = 0;i<planes.size();i++) {
			Plane plane2 = planes.get(i);
			plane2.draw(g);
			plane2.cantainItems(props);
			
		}
		
		// ѭ������ÿ���ӵ�
		// ��ǿforѭ��ֻ�ܱ���  �������κβ���  �����ô�ͳforѭ��
		for (int i = 0; i < bullets.size(); i++ ) {
			Bullet bullet = bullets.get(i);
			bullet.draw(g);
			bullet.hitMonsters(enemys);
			bullet.hitMonsters(planes);
			bullet.hitMonsters(bosss);
		}
		// ����������ɫ
		g.setColor(Color.green);
		g.drawString("�ӵ���������"+bullets.size(), 10, 50);
		g.drawString("���˵�������"+enemys.size(), 10, 70);
		//g.drawString("��ǰ��ը��������"+booms.size(), 10, 90);
		g.drawString("Ӣ�۵�������"+planes.size(), 10, 90);
		g.drawString("���ߵ�������"+props.size(), 10, 110);
		
		
		// ѭ�����з�
		for(int i = 0;i < enemys.size();i++) {
			enemys.get(i).draw(g);
		}
		
		// ѭ����ը
		for(int i = 0 ; i < booms.size() ; i++) {
			// �����ը���� 
			if(booms.get(i).isLive()==true) {
				// ��
				booms.get(i).draw(g);	
			}
		}
		// ѭ��������
		for(int i = 0 ; i < props.size() ; i++) {
			 {	
				props.get(i).draw(g);	
			}
		}
		//����л�ȫ������
		if(enemys.size() == 0) {
			//ѭ������boss����
			for(int i = 0 ; i < bosss.size() ; i++) {
				 {
					bosss.get(i).draw(g);	
				}
			}
		}
		
	}
	
	// ����һ���ڲ���(�߳���)  
	class paintThread extends Thread{
		
		@Override
		public void run() {// ��дThread�е�run����
			while(true) {
				repaint();// �ظ���
				try {
					Thread.sleep(40);// ��һ����Ϣʱ��
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
}
