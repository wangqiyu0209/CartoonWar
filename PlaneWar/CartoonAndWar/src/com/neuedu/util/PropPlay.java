package com.neuedu.util;

import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

/**
* @ClassName: PropPlay
* @Description: 碰到道具音效
* @author wqy
* @date 2019年8月19日 下午7:35:38
*
*/
public class PropPlay extends Thread{

	private String mp3Name;
	
	public PropPlay() {
		
	}
	public PropPlay(String mp3Name) {
		this.mp3Name = mp3Name;
	}
	public void play(String mp3Name) {
		PropPlay propPlay = new PropPlay(mp3Name);
		propPlay.start();
	}
	@Override
	public void run() {
		InputStream resoceAsStream = SoundPlayer.class.getClassLoader().getResourceAsStream(mp3Name);
		try {
			AdvancedPlayer advancedPlayer = new AdvancedPlayer(resoceAsStream);
			advancedPlayer.play();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
	}
}
