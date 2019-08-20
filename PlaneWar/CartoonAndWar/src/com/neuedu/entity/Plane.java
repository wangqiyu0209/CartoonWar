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
* @Description: ����һ���ɻ���
* @author wqy
* @date 2019��8��17�� ����4:47:46
*
*/
public class Plane extends GameObj implements ActionAble{

	SinglePlay play = new SinglePlay();
	//PropPlay propSound =new PropPlay();
	
	// �ٶ�
	private int speed;
	// ���򲼶�����
	private boolean left;
	private boolean up;
	private boolean right;
	private boolean down;
	
	// �ͻ����ù���
	public GameClient gc;
	
	// �ж��Ƿ����Ҿ����ǵо�
	public boolean isGood;
	
	// ��ӷɻ��ӵ��ȼ�����
	public int bulletLevel = 1;
	
	// ���Ѫֵ
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
	// �ƶ��ķ���
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
	// ��һ���ɻ�
	@Override
	public void draw(Graphics g) {
		
		g.drawImage(img, x, y, null);
		move(); //����move
		//����������ɫ
		g.setColor(Color.green);
		g.drawRect(10, 140, 100, 11);
		g.fillRect(10, 140, blood, 10);
		g.drawString("Ӣ��Ѫֵ��"+blood,10,130);
	}
	
	// ���̰��·���
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			// ���ط� �������ɻ���������
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

	// �����ͷŷ���
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			// ���ط� �������ɻ���������
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
	
	// ����ɻ��ı߽�����
	public void outOfBound() {
		if(y <= 40) {
			y = 50;
		}
		if(x <= 5){
			x = 1;
		}
		if(x >= Constant.GAME_WIDTH-img.getWidth(null)) {//��Ŀ�-ͼƬ�Ŀ�
			x = Constant.GAME_WIDTH-img.getWidth(null);
		}
		if(y >= Constant.GAME_HEIGHT-img.getHeight(null)) {// ��ĸ�-�ɻ��ĸ�
			y = Constant.GAME_HEIGHT-img.getHeight(null);
		}
	}
	// �ҷ��ɻ��Ŀ���
	public void fire() {
		// ����ʱ����Ч
		play.play("com/neuedu/sound/fire.mp3");
		Bullet b = new Bullet(x+this.img.getWidth(null)-45,y+this.img.getHeight(null)/2-80,"bullet/myPlane_bullet_0"+bulletLevel+".png",gc,true);
		Bullet b1 = new Bullet(x+this.img.getWidth(null)-115,y+this.img.getHeight(null)/2-80,"bullet/myPlane_bullet_0"+bulletLevel+".png",gc,true);
		gc.bullets.add(b);
		gc.bullets.add(b1);
	}
	
	// ��ȡ����ǰ�ľ���
	public Rectangle getRec() {
		return new Rectangle(x,y,this.img.getWidth(null),this.img.getHeight(null));		
	}
		
	// ����ҷ�������ײ����
	public void containItem(Prop prop) {
        // 	�Ե��ߵ�����
		//propSound.play("com/neuedu/sound/prop.mp3");
		if(this.getRec().intersects(prop.getRect())) {
			// �Ƴ�����
			gc.props.remove(prop);
			// �����ӵ��ȼ�
			if(bulletLevel > 8) {
				bulletLevel = 8;
				return;
			}
			this.bulletLevel += 1;
		}
	}
	
	// ����ҷ��ɻ���ײ���������
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
