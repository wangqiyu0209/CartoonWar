package com.neuedu.entity;

import java.awt.Graphics;
import java.awt.Image;

import org.omg.PortableServer.ImplicitActivationPolicyValue;

import com.neuedu.action.ActionAble;
import com.neuedu.client.GameClient;
import com.neuedu.util.GetImageUtil;

/**
* @ClassName: Boom
* @Description:��ը��
* @author wqy
* @date 2019��8��19�� ����1:04:34
*
*/
public class Boom extends GameObj implements ActionAble{

	private boolean isLive;
	private GameClient gc;

	public boolean isLive() {
		return isLive;
	}

	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}

	// ��̬��ʼ����ըͼ
	private static Image[] boomImgs = new Image[8];
	static
	{
		for(int i = 1;i < 8;i++) {
			boomImgs[i] = GetImageUtil.getImg("Boom/"+"boom"+(i+1)+".png");
		}
	}
	
	public Boom() {
		
	}
	
	public Boom(int x,int y,GameClient gc) {
		this.x = x;
		this.y = y;
		this.isLive = true;
		this.gc = gc;
	}
	
	@Override
	public void move() {
		
	}

	int count = 0;
	@Override
	public void draw(Graphics g) {
		// �����ըtrueִ��
		if(isLive) {
			// ����ը
			if(count > 7) {
				// ��ը�Ƴ�
				gc.booms.remove(this);
				this.setLive(false);
				return;
			}
			g.drawImage(boomImgs[count++],x,y,null);
		}
	}
}
