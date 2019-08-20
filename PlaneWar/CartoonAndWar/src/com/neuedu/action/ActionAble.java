package com.neuedu.action;

import java.awt.GradientPaint;
import java.awt.Graphics;

/**
* @ClassName: ActionAble
* @Description: 行为接口 （定义规范）
* @author wqy
* @date 2019年8月17日 下午4:18:21
*
*/
public interface ActionAble {

	// 移动方法
	void move();
	
	// 画方法
	void draw(Graphics g);
}
