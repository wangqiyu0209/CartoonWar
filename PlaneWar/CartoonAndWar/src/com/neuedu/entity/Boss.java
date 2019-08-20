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
* @date 2019年8月19日 下午7:53:53
*
*/
public class Boss extends Plane implements ActionAble{
	
	private boolean right;
	
	private Integer speed = 5;
	
	public Boss() {
		
	}	
									// 给boss添加一个是不是好的的变量 这样子弹打怪物时 
										//判断两个矩形是否碰撞时的逻辑与左右两边就都可以成立
	public Boss(int x,int y,GameClient gc,boolean isGood) {
		this.x = x;
		this.y = y;
		this.gc = gc;
		this.isGood = isGood;
		this.blood = 200;
	}


	// 动态初始化一个数组
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
		// 在x,y的地方绘制一个长为200，宽为10的小方框，此时内部为透明
		g.drawRect(x, y, 201, 11);
		// 在相同位置填充方框，根据血量的大小。
		g.fillRect(x, y,blood, 10);
		g.drawString("Boss血值："+blood, x, y-5);
	}
	
	@Override
	public void move() {
		y += speed; 
		// boss的移动轨迹不能越界 (开关法)
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
		// boss随机开火数
		if(random.nextInt(500)>450) {
			fire();
		}
	}
	// 生成随机数
	Random random = new Random();
	
	// 获取到boss所在的矩形
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
