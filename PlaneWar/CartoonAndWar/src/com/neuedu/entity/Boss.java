package com.neuedu.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import com.neuedu.action.ActionAble;
import com.neuedu.client.Constant;
import com.neuedu.client.GameClient;
import com.neuedu.util.GetImageUtil;

/**
* @ClassName: Boss
* @Description: boss
* @author wqy
* @date 2019��8��19�� ����7:53:53
*
*/
public class Boss extends Plane implements ActionAble{
	
	private boolean right;
	
	private Integer speed = 5;
	
	public Boss() {
		
	}	
									// ��boss���һ���ǲ��Ǻõĵı��� �����ӵ������ʱ 
										//�ж����������Ƿ���ײʱ���߼����������߾Ͷ����Գ���
	public Boss(int x,int y,GameClient gc,boolean isGood) {
		this.x = x;
		this.y = y;
		this.gc = gc;
		this.isGood = isGood;
		this.blood = 200;
	}


	// ��̬��ʼ��һ������
	private static Image[] imgs = new Image[10];
	static  
	{
		for(int i = 0; i<imgs.length;i++) {
			imgs[i] = GetImageUtil.getImg("boss/feng"+(i+1)+".png");
		}
	}
	int count = 0;
	@Override
	public void draw(Graphics g) {
		if(count > 9) {
			count = 0;
		}
		g.drawImage(imgs[count++],x,y,null);
		move();
		g.setColor(Color.red);
		// ��x,y�ĵط�����һ����Ϊ200����Ϊ10��С���򣬴�ʱ�ڲ�Ϊ͸��
		g.drawRect(x, y, 201, 11);
		// ����ͬλ����䷽�򣬸���Ѫ���Ĵ�С��
		g.fillRect(x, y,blood, 10);
		g.drawString("BossѪֵ��"+blood, x, y-5);
	}
	
	@Override
	public void move() {
		y += speed; 
		// boss���ƶ��켣����Խ�� (���ط�)
		if(y > 130) {
			y = 130;
			if(right) {
				x -= speed;
			}
			if(!right) {
				x += speed;
			}
			if(x > Constant.GAME_WIDTH-imgs[0].getWidth(null)) {
				right = true;
			}
			if(x < 30) {
				right = false;
			}
		}	
		// boss���������
		if(random.nextInt(500)>450) {
			fire();
		}
	}
	// ���������
	Random random = new Random();
	
	// ��ȡ��boss���ڵľ���
	public Rectangle getRec() {
		return new Rectangle(x,y,this.imgs[0].getWidth(null),this.imgs[0].getHeight(null));		
	}
	
	@Override
	public void fire() {
		//play.play("com/neuedu/sound/fire.mp3");
		Bullet b = new Bullet(x+this.imgs[0].getWidth(null)/2-50,y+this.imgs[0].getHeight(null)/2-80,"bullet/bossZD.png",gc,false);
		gc.bullets.add(b);
	}
}
