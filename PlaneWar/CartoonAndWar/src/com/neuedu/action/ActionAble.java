package com.neuedu.action;

import java.awt.GradientPaint;
import java.awt.Graphics;

/**
* @ClassName: ActionAble
* @Description: ��Ϊ�ӿ� ������淶��
* @author wqy
* @date 2019��8��17�� ����4:18:21
*
*/
public interface ActionAble {

	// �ƶ�����
	void move();
	
	// ������
	void draw(Graphics g);
}
