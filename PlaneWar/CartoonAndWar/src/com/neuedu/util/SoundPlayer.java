package com.neuedu.util;

import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

/**
* @ClassName: SoundPlayer
* @Description:���ű������ֵĹ�����
* @author wqy
* @date 2019��8��19�� ����6:48:39
*
*/
public class SoundPlayer extends Thread{

	private String mp3Name;
	
	public SoundPlayer() {
		
	}
	
	public SoundPlayer(String mp3Name) {
		this.mp3Name = mp3Name;
	}
	
	@Override
	public void run() {
		for(;;) {
			InputStream resoceAsStream = SoundPlayer.class.getClassLoader().getResourceAsStream(mp3Name);
			try {
				AdvancedPlayer advancedPlayer = new AdvancedPlayer(resoceAsStream);
				advancedPlayer.play();
			} catch (JavaLayerException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
