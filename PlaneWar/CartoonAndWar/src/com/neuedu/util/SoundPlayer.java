package com.neuedu.util;

import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

/**
* @ClassName: SoundPlayer
* @Description:播放背景音乐的工具类
* @author wqy
* @date 2019年8月19日 下午6:48:39
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
