package com.example.reproductorisrael;

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


    }
}