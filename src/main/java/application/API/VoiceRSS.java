package application.API;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import com.voicerss.tts.AudioFormat;
import com.voicerss.tts.VoiceParameters;
import com.voicerss.tts.VoiceProvider;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class VoiceRSS {
    private static final String key = "6adccc1ed7514fe0a3908794c2449bda";
    private static final String path = "src/main/resources/media/tts.mp3";

    public static void speakWords(String content, String name, String language)
            throws Exception {
        VoiceProvider tts = new VoiceProvider(key);
        VoiceParameters params = new VoiceParameters(content, AudioFormat.Format_44KHZ.AF_44khz_16bit_stereo);
        params.setBase64(false);
        params.setLanguage(language);
        params.setVoice(name);
        params.setRate(0);

        byte[] voice = tts.speech(params);

        FileOutputStream fos = new FileOutputStream(path);
        fos.write(voice, 0, voice.length);
        fos.flush();
        fos.close();

        File audioFile = new File(path);
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        clip.start();
    }
}
