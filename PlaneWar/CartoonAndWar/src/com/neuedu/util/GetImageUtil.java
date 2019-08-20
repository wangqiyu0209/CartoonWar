package com.neuedu.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
* @ClassName: GetImageUtil
* @Description: ��ȡͼƬ�Ĺ�����
* @author wqy
* @date 2019��8��17�� ����2:10:37
*
*/
public class GetImageUtil {
	
	// ��ȡͼƬ�ķ���
	public static Image getImg(String imgName) {
		//URL ͳһ��Դ��λ��
		// ����                    // �������            // �õ���Դ
		URL resource = GetImageUtil.class.getClassLoader().getResource("com/neuedu/img/"+imgName);
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(resource);
		} catch (IOException e) {	
			e.printStackTrace();
		}
		return bufferedImage;
	}
}
