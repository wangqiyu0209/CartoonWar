package com.neuedu.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.List;

import com.neuedu.action.ActionAble;
import com.neuedu.client.Constant;
import com.neuedu.client.GameClient;
import com.neuedu.util.GetImageUtil;
import com.neuedu.util.PropPlay;
import com.neuedu.util.SinglePlay;

/**
* @ClassName: Plane
* @Description: 创建一个飞机类
* @author wqy
* @date 2019年8月17日 下午4:47:46
*
*/
public class Plane extends GameObj implements ActionAble{

	SinglePlay play = new SinglePlay();
	//PropPlay propSound =new PropPlay();
	
	// 速度
	private int speed;
	// 方向布尔变量
	private boolean left;
	private boolean up;
	private boolean right;
	private boolean down;
	
	// 客户端拿过来
	public GameClient gc;
	
	// 判断是否是我军还是敌军
	public boolean isGood;
	
	// 添加飞机子弹等级变量
	public int bulletLevel = 1;
	
	// 添加血值
	public int blood;
	
	public Plane() {
		
	}
	
	public Plane(int x,int y,String imgName,GameClient gc,boolean isGood) {
		this.x = x;
		this.y = y;
		this.img = GetImageUtil.getImg(imgName);
		this.speed = 15;
		this.gc = gc;
		this.isGood = isGood;
		this.blood = 100;
	}
	// 移动的方法
	@Override
	public void move() {
		if(left) {
			x -= speed;
		}
		if(up) {
			y -= speed;
		}
		if(right) {
			x += speed;
		}
		if(down) {
			y += speed;
		}
		outOfBound();
	}
	// 画一个飞机
	@Override
	public void draw(Graphics g) {
		
		g.drawImage(img, x, y, null);
		move(); //调用move
		//设置字体颜色
		g.setColor(Color.green);
		g.drawRect(10, 140, 100, 11);
		g.fillRect(10, 140, blood, 10);
		g.drawString("英雄血值："+blood,10,130);
	}
	
	// 键盘按下方法
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			// 开关法 来操作飞机上下左右
			left = true;
			break;
		case KeyEvent.VK_W:
			up = true;
			break;
		case KeyEvent.VK_D:
			right = true;
			break;
		case KeyEvent.VK_S:
			down = true;
			break;
		case KeyEvent.VK_M:
			fire();
			break;
		default:
			break;
		}	
	}

	// 键盘释放方法
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			// 开关法 来操作飞机上下左右
			left = false;
			break;
		case KeyEvent.VK_W:
			up = false;
			break;
		case KeyEvent.VK_D:
			right = false;
			break;
		case KeyEvent.VK_S:
			down = false;
			break;
		case KeyEvent.VK_M:
			fire();
			break;
		default:
			break;
		}	
	}
	
	// 处理飞机的边界问题
	public void outOfBound() {
		if(y <= 40) {
			y = 50;
		}
		if(x <= 5){
			x = 1;
		}
		if(x >= Constant.GAME_WIDTH-img.getWidth(null)) {//框的宽-图片的宽
			x = Constant.GAME_WIDTH-img.getWidth(null);
		}
		if(y >= Constant.GAME_HEIGHT-img.getHeight(null)) {// 框的高-飞机的高
			y = Constant.GAME_HEIGHT-img.getHeight(null);
		}
	}
	// 我方飞机的开火
	public void fire() {
		// 开火时的音效
		play.play("com/neuedu/sound/fire.mp3");
		Bullet b = new Bullet(x+this.img.getWidth(null)-45,y+this.img.getHeight(null)/2-80,"bullet/myPlane_bullet_0"+bulletLevel+".png",gc,true);
		Bullet b1 = new Bullet(x+this.img.getWidth(null)-115,y+this.img.getHeight(null)/2-80,"bullet/myPlane_bullet_0"+bulletLevel+".png",gc,true);
		gc.bullets.add(b);
		gc.bullets.add(b1);
	}
	
	// 获取到当前的矩形
	public Rectangle getRec() {
		return new Rectangle(x,y,this.img.getWidth(null),this.img.getHeight(null));		
	}
		
	// 检测我方道具碰撞道具
	public void containItem(Prop prop) {
        // 	吃道具的声音
		//propSound.play("com/neuedu/sound/prop.mp3");
		if(this.getRec().intersects(prop.getRect())) {
			// 移除道具
			gc.props.remove(prop);
			// 更改子弹等级
			if(bulletLevel > 8) {
				bulletLevel = 8;
				return;
			}
			this.bulletLevel += 1;
		}
	}
	
	// 检测我方飞机碰撞到多个道具
	public void cantainItems(List<Prop> props) {

		if(props == null) {
			return;
		}else{
			for(int i = 0 ; i < props.size() ; i++) {
					containItem(props.get(i));
				}
			}
		}
}
