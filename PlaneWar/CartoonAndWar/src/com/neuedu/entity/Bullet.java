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
* @Description: �ӵ���
* @author wqy
* @date 2019��8��17�� ����7:01:13
*
*/
public class Bullet extends GameObj implements ActionAble{

	// �����������ֵĶ���
	SinglePlay singlePlay = new SinglePlay();
	
	// �����ٶȵ�����
	private Integer speed;
	
	// �õ��ͻ��ˣ���ͣ��ģʽ��
	public GameClient gc = new GameClient();
	
	// �ӵ�����
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
	public void draw(Graphics g) {// ��һ���ӵ�
		g.drawImage(img, x, y, null);
		move();
		outOfBounds();
	}
	
	
	
	// �ӵ�Խ������*
	public void outOfBounds() {
		if(y > Constant.GAME_HEIGHT || y < 0 ) {
			gc.bullets.remove(this);
		}
	}
	
	// �ӵ���һ������
	public boolean hitMonster(Plane plane) {
		Boom boom =  new Boom(plane.x , plane.y , gc);
		// �ж����������Ƿ��ཻ  ����ײ��⣩
		if(this.getRec().intersects(plane.getRec()) && this.isGood != plane.isGood) {
			
			// ���е��˵�����
			singlePlay.play("com/neuedu/sound/peng.mp3");
			
			if(plane.isGood) {
				// �ɻ���Ѫ
				plane.blood -= 2;
				if(plane.blood == 0) {
					// ��������
					gc.planes.remove(plane);
					gc.enemys.remove(plane);	
				}
				// �Ƴ��ӵ�
				gc.bullets.remove(this);
				
			}else {	
				// boss��Ѫ
				if(plane instanceof Boss) {
					plane.blood -= 2;
					if( plane.blood <= 0) {
						// �Ƴ�boss
						gc.bosss.remove(plane);
						// �Ƴ��ӵ�
						gc.bullets.remove(this);
					}
					
				}else {
					//�Ƴ����е���
					gc.enemys.remove(plane);
					// �Ƴ��ӵ�
					gc.bullets.remove(this);
					// �������һ�����߳���
					if(random.nextInt(500)>300) {
						// ת�� 
						if(plane instanceof EnemyPlane) {
							EnemyPlane enemyPlane = (EnemyPlane)plane;
							Prop prop = new Prop(plane.x+enemyPlane.imgs1[0].getWidth(null)/2 ,  plane.y+enemyPlane.imgs1[0].getHeight(null)/2,"prop/prop.png");
							gc.props.add(prop);
						}
					}	
				}
			}
			// ��ӱ�ը
			gc.booms.add(boom);
			return true;
		}
		return false;
	}
	// ������ɵ���
	Random random = new Random();
	
	// �ӵ���������
	public boolean hitMonsters(List<Plane> monsters) {
		for(int i=0 ; i<monsters.size();i++) {
			if(hitMonster(monsters.get(i))) {
				return true;
			}
		}
		return false;
	}
	// ��ȡ���ӵ��ľ���
	public Rectangle getRec() {
		return new Rectangle(x,y,this.img.getWidth(null),this.img.getHeight(null));	
	}

}
