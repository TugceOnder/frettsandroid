package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioFormat;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.renderscript.Type;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sun.speech.freetts.FreeTTS;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.audio.AudioPlayer;
import com.sun.speech.freetts.audio.SingleFileAudioPlayer;

import java.net.Proxy;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText Text;
    Button btnText;
    TextToSpeech textToSpeech;
    VoiceManager freeVM;
    FreeTTS freeTTS;
    Voice voice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Text = findViewById(R.id.Text);
        btnText = findViewById(R.id.btnText);

        // create an object textToSpeech and adding features into it
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

                // if No error is found then only it will run
                if(i== TextToSpeech.SUCCESS){
                    // To Choose language of speech
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });

        // Adding OnClickListener
        btnText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.setProperty("freetts.voices","com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
            voice = VoiceManager.getInstance().getVoice("kevin16");


                Voice [] voicelist = VoiceManager.getInstance().getVoices();
                for (int i=0;i<voicelist.length ;i++){
                    System.out.println("Voice "+voicelist[i].getName());
                }

                if(voice == null){
      voice.allocate();

                    System.out.println("Voice Rate: "+voice.getRate());
                    System.out.println("Voice Rate: "+voice.getRate());
                    System.out.println("Voice Rate: "+voice.getPitch());
                    System.out.println("Voice Rate: "+voice.getVolume());
                    //AudioPlayer aplayer = new SingleFileAudioPlayer();
                    //voice.setAudioPlayer(aplayer);
                    MediaPlayer aplayer = new MediaPlayer();
                    voice.setAudioPlayer((AudioPlayer) aplayer);
                    boolean status = voice.speak("hello ");
                    System.out.println("Status"+status);
                    voice.deallocate();
                }
                try {
                    voice.setRate(190);//Setting the rate of the voice
                    voice.setPitch(150);//Setting the Pitch of the voice
                    voice.setVolume(3);//Setting the volume of the voice
                    // Calling speak() method


                } catch (Exception e1) {
                    e1.printStackTrace();
                }


            }
        });

    }
}
