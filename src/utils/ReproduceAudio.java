package utils;

import java.io.File;
import utils.*;
import javax.sound.sampled.*;

public class ReproduceAudio {
	
	public void reproducir(){
		try{
			Clip sonido = AudioSystem.getClip();
			sonido.open(AudioSystem.getAudioInputStream(new File("Sonidos","M_sica_de_Pokemon_Red_Blue_-_Batalla_VS_.wav")));
			
			sonido.start();
			
			sonido.loop(Clip.LOOP_CONTINUOUSLY);
		}catch (Exception e){
			System.out.println("Error en el audio" + e);
		}
	}
	
	public void audioAtaque(String archivo){
		try{
			Clip sonido = AudioSystem.getClip();
			sonido.open(AudioSystem.getAudioInputStream(new File("Sonidos",archivo)));
			
			sonido.start();
			
		
		}catch (Exception e){
			System.out.println("Error en el audio" + e);
		}
	}

}
