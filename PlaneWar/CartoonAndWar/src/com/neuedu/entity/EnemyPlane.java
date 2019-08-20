package com.neuedu.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.Action;

import com.neuedu.action.ActionAble;
import com.neuedu.client.GameClient;
import com.neuedu.util.GetImageUtil;

public class EnemyPlane extends Plane implements ActionAble{

	private Integer enemyType;
	
	private Integer speed;
	
	private GameClient gc;
	
	public static Image[] imgs1 = 
		{
				GetImageUtil.getImg("enemy/1073-11.png"),
				GetImageUtil.getImg("enemy/1073-12.png"),
				GetImageUtil.getImg("enemy/1073-13.png"),
				GetImageUtil.getImg("enemy/1073-14.png"),
				GetImageUtil.getImg("enemy/1073-15.png"),
				GetImageUtil.getImg("enemy/1073-16.png"),
				GetImageUtil.getImg("enemy/1073-17.png"),
				GetImageUtil.getImg("enemy/1073-18.png"),
				GetImageUtil.getImg("enemy/1073-19.png"),
				GetImageUtil.getImg("enemy/1073-20.png"),
		};
	
	public  EnemyPlane() {
		
	}
	
	public EnemyPlane( int x ,int y,int enemyType,GameClient gc,boolean isGood) {
		this.x = x;
		this.y = y;
		this.enemyType = enemyType;
		this.speed = 3;
		this.gc = gc;
		this.isGood = isGood;
	}
	
	@Override
	public void move() {
		y += speed;
	}
	int count = 0;
	@Override
	public void draw(Graphics g) {
		if(count>9) {
			count = 0;
		}
		g.drawImage(imgs1[count++],x,y,null);
		move();
		if(random.nextInt(500) > 470) {
			fire();
		}
	}
	// 随机数
	Random random = new Random();	

	// 敌军发火
	public void fire() {
		
		Bullet bullet = new Bullet(x+imgs1[0].getHeight(null)/2-15, y, "bullet/huo1.png",gc,false);
		// 添加到子弹类
		gc.bullets.add(bullet);
		
	}	
		
	// 获取到子弹的矩形
		public Rectangle getRec() {
			return new Rectangle(x, y, this.imgs1[0].getWidth(null),this.imgs1[0].getHeight(null));
			
		}
			
}
