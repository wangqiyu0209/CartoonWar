package com.neuedu.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
* @ClassName: Constant
* @Description: 常量类
* @author wqy
* @date 2019年8月17日 下午1:59:38
*
*/
public class Constant {

	// 如何读取配置文件
	public static Properties prop = new Properties();
	static Integer Game_Width = null;
	static Integer Game_Height = null;
	
	static 
	{
																	// 加“/”表示从根目录去找
		InputStream inStream = Constant.class.getResourceAsStream("/gameConfiguration.properties");
		try {
			prop.load(inStream);
			Game_Width = Integer.parseInt(prop.getProperty("Game_Width"));
			Game_Height = Integer.parseInt(prop.getProperty("Game_Height"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//定义窗口的宽和高
	public static final int GAME_WIDTH = Game_Width;
	public static final int GAME_HEIGHT = Game_Height;
	
}
