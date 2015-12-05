package com.srcdevbin.buildbot.voice.festival;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class Speaker {
	
	private static String voiceName = "kevin";
	private VoiceManager voiceManager;
	
	public Speaker(){
        voiceManager = VoiceManager.getInstance();
	}
	
	public void speak(String text){
		Voice voice = voiceManager.getVoice(voiceName);

        if (voice == null) {
            System.err.println(
                "Cannot find a voice named "
                + voiceName + ".  Please specify a different voice.");
            System.exit(1);
        }
        
        /* Allocates the resources for the voice.
         */
        voice.allocate();
        
        /* Synthesize speech.
         */
        voice.speak(text);

        /* Clean up and leave.
         */
        voice.deallocate();
	}

}
