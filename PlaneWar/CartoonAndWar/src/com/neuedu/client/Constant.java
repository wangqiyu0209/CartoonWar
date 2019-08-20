package com.neuedu.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
* @ClassName: Constant
* @Description: ������
* @author wqy
* @date 2019��8��17�� ����1:59:38
*
*/
public class Constant {

	// ��ζ�ȡ�����ļ�
	public static Properties prop = new Properties();
	static Integer Game_Width = null;
	static Integer Game_Height = null;
	
	static 
	{
																	// �ӡ�/����ʾ�Ӹ�Ŀ¼ȥ��
		InputStream inStream = Constant.class.getResourceAsStream("/gameConfiguration.properties");
		try {
			prop.load(inStream);
			Game_Width = Integer.parseInt(prop.getProperty("Game_Width"));
			Game_Height = Integer.parseInt(prop.getProperty("Game_Height"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//���崰�ڵĿ�͸�
	public static final int GAME_WIDTH = Game_Width;
	public static final int GAME_HEIGHT = Game_Height;
	
}
