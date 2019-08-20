package com.neuedu.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;
import java.util.Random;

import com.neuedu.action.ActionAble;
import com.neuedu.client.Constant;
import com.neuedu.client.GameClient;
import com.neuedu.util.GetImageUtil;
import com.neuedu.util.SinglePlay;

/**
* @ClassName: Bullet
* @Description: 子弹类
* @author wqy
* @date 2019年8月17日 下午7:01:13
*
*/
public class Bullet extends GameObj implements ActionAble{

	// 单击播放音乐的对象
	SinglePlay singlePlay = new SinglePlay();
	
	// 创建速度的属性
	private Integer speed;
	
	// 拿到客户端（调停者模式）
	public GameClient gc = new GameClient();
	
	// 子弹类型
	public boolean isGood;
	
	public Bullet() {
		
	}
	
	public Bullet(int x,int y,String imgName,GameClient gc,boolean isGood) {
		this.x = x;
		this.y = y;
		this.img = GetImageUtil.getImg(imgName);
		this.speed = 50;
		this.gc = gc;
		this.isGood = isGood;
	}

	@Override
	public void move() {
		if(isGood) {
			y -= speed;
		}else {
			y += speed;	
		}	
	}

	@Override
	public void draw(Graphics g) {// 画一颗子弹
		g.drawImage(img, x, y, null);
		move();
		outOfBounds();
	}
	
	
	
	// 子弹越界销毁*
	public void outOfBounds() {
		if(y > Constant.GAME_HEIGHT || y < 0 ) {
			gc.bullets.remove(this);
		}
	}
	
	// 子弹打一个怪物
	public boolean hitMonster(Plane plane) {
		Boom boom =  new Boom(plane.x , plane.y , gc);
		// 判断两个矩形是否相交  （碰撞检测）
		if(this.getRec().intersects(plane.getRec()) && this.isGood != plane.isGood) {
			
			// 打中敌人的声音
			singlePlay.play("com/neuedu/sound/peng.mp3");
			
			if(plane.isGood) {
				// 飞机减血
				plane.blood -= 2;
				if(plane.blood == 0) {
					// 销毁自身
					gc.planes.remove(plane);
					gc.enemys.remove(plane);	
				}
				// 移除子弹
				gc.bullets.remove(this);
				
			}else {	
				// boss减血
				if(plane instanceof Boss) {
					plane.blood -= 2;
					if( plane.blood <= 0) {
						// 移除boss
						gc.bosss.remove(plane);
						// 移除子弹
						gc.bullets.remove(this);
					}
					
				}else {
					//移除打中敌人
					gc.enemys.remove(plane);
					// 移除子弹
					gc.bullets.remove(this);
					// 随机生成一个道具出来
					if(random.nextInt(500)>300) {
						// 转型 
						if(plane instanceof EnemyPlane) {
							EnemyPlane enemyPlane = (EnemyPlane)plane;
							Prop prop = new Prop(plane.x+enemyPlane.imgs1[0].getWidth(null)/2 ,  plane.y+enemyPlane.imgs1[0].getHeight(null)/2,"prop/prop.png");
							gc.props.add(prop);
						}
					}	
				}
			}
			// 添加爆炸
			gc.booms.add(boom);
			return true;
		}
		return false;
	}
	// 随机生成道具
	Random random = new Random();
	
	// 子弹打多个怪物
	public boolean hitMonsters(List<Plane> monsters) {
		for(int i=0 ; i<monsters.size();i++) {
			if(hitMonster(monsters.get(i))) {
				return true;
			}
		}
		return false;
	}
	// 获取到子弹的矩形
	public Rectangle getRec() {
		return new Rectangle(x,y,this.img.getWidth(null),this.img.getHeight(null));	
	}

}
