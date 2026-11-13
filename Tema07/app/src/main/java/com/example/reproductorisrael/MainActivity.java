package com.example.reproductorisrael;

import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.media3.common.MediaItem;
import androidx.media3.common.Player;
import androidx.media3.exoplayer.ExoPlayer;

import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private ExoPlayer player;
    private SeekBar seekbar;
    private TextView actual, total;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private SoundPool soundPool;
    private final int[] sonidos = new int[4];
    private int cargados = 0;
    private final Runnable actualizar = new Runnable() {
        @Override
        public void run() {
            int posicion = (int) player.getCurrentPosition();
            seekbar.setProgress(posicion);
            actual.setText(formatear(posicion));
            handler.postDelayed(this, 500);
        }
    };

    private String formatear(long ms) {
        long segundos = ms / 1000;
        return String.format(Locale.getDefault(), "%02d:%02d", segundos / 60, segundos % 60);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        player = new ExoPlayer.Builder(this).build();
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.beliver);
        player.setMediaItem(MediaItem.fromUri(uri));
        player.prepare();

        Button play = findViewById(R.id.play);
        Button pausa = findViewById(R.id.pausa);

        play.setOnClickListener(v -> {
            if (player.getPlaybackState() == Player.STATE_ENDED) {
                player.seekTo(0);
            }
            player.play();
        });
        pausa.setOnClickListener(v -> player.pause());

        seekbar = findViewById(R.id.seekbar);
        actual = findViewById(R.id.actual);
        total = findViewById(R.id.total);

        player.addListener(new Player.Listener() {
            @Override
            public void onPlaybackStateChanged(int estado) {
                if (estado == Player.STATE_READY) {
                    int duracion = (int) player.getDuration();
                    seekbar.setMax(duracion);
                    total.setText(formatear(duracion));
                }
            }
        });

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar barra, int progreso, boolean usuario) { }

            @Override
            public void onStartTrackingTouch(SeekBar barra) { }

            @Override
            public void onStopTrackingTouch(SeekBar barra) {
                player.seekTo(barra.getProgress());
            }
        });

        Button[] botones = {
                findViewById(R.id.aplauso),
                findViewById(R.id.campana),
                findViewById(R.id.laser),
                findViewById(R.id.moneda)
        };

        AudioAttributes atributos = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(4)
                .setAudioAttributes(atributos)
                .build();

        soundPool.setOnLoadCompleteListener((pool, id, estado) -> {
            cargados++;
            if (cargados == 4) {
                for (Button boton : botones) {
                    boton.setEnabled(true);
                }
            }
        });

        sonidos[0] = soundPool.load(this, R.raw.aplausos, 1);
        sonidos[1] = soundPool.load(this, R.raw.campana, 1);
        sonidos[2] = soundPool.load(this, R.raw.laser, 1);
        sonidos[3] = soundPool.load(this, R.raw.moneda, 1);

        for (int i = 0; i < botones.length; i++) {
            final int sonido = sonidos[i];
            botones[i].setOnClickListener(v -> soundPool.play(sonido, 1f, 1f, 1, 0, 1f));
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        handler.post(actualizar);
    }
    @Override
    protected void onStop() {
        super.onStop();
        player.pause();
        handler.removeCallbacks(actualizar);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        player.release();
        soundPool.release();
    }
}