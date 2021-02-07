package tr.edu.medipol.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button btnStart, btnOk;
    LinearLayout llNumber;
    int number, score, errorNumber;
    EditText etNumber;
    TextView tvScore;
    ImageView iv1,iv2,iv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        llNumber = findViewById(R.id.llNumber);

        btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnStart.setVisibility(View.GONE);
                llNumber.setVisibility(View.VISIBLE);
                generateNumber();
            }
        });

        etNumber = findViewById(R.id.etNumber);
        tvScore = findViewById(R.id.tvScore);

        iv1 = findViewById(R.id.iv1);
        iv2 = findViewById(R.id.iv2);
        iv3 = findViewById(R.id.iv3);

        btnOk = findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = etNumber.getText().toString();
                if (input.length() == 0)
                {
                    Toast.makeText(MainActivity.this, R.string.must_number, Toast.LENGTH_SHORT).show();
                    return;
                }

                int inputNumber = Integer.parseInt(input);

                if( number == inputNumber ){
                    MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.clap);
                    mediaPlayer.start();

                    Toast.makeText(MainActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
                    score += 10;

                    generateNumber();


                }else{
                    //TODO: later
                    Toast.makeText(MainActivity.this, R.string.wrong, Toast.LENGTH_SHORT).show();
                    errorNumber += 1;

                    switch (errorNumber){
                        case 1:
                            iv1.setVisibility(View.GONE);
                            break;
                        case 2:
                            iv2.setVisibility(View.GONE);
                            break;
                        case 3:
                            iv3.setVisibility(View.GONE);
                            Toast.makeText(MainActivity.this, R.string.game_over, Toast.LENGTH_SHORT).show();
                            score = 0;
                            iv1.setVisibility(View.VISIBLE);
                            iv2.setVisibility(View.VISIBLE);
                            iv3.setVisibility(View.VISIBLE);
                            generateNumber();
                            errorNumber = 0;

                            break;
                    }

                }
                tvScore.setText(String.valueOf(score));
                etNumber.setText("");

            }
        });
    }

    private void generateNumber() {
        Random rnd = new Random();
        number = rnd.nextInt(10) + 1;
        Log.e("selected number", String.valueOf(number));
    }
}