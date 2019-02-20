package bharatnaganath.scarnesdice_bnworkshop;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    int O_userscore = 0, userscore = 0, O_compscore = 0, compscore = 0, turnscore = 0;
    int count = 0;
    boolean started = false;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            TextView cscore=(TextView)findViewById(R.id.compview);
            cscore.setText("AI: "+compscore);
            final Random random = new Random();
            int i = random.nextInt(2 - 0 + 1) + 0;
            OnClickRoll(null);
            if(turnscore==0)
            {
               stop();

            }
            else if(turnscore>20) {
                handler.removeCallbacks(runnable);
                started=false;
                compscore=compscore+turnscore;
                count++;
                cscore.setText("AI: "+compscore);
                TurnDecider(count);

            }
                if (started) {
                start();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView uscore = (TextView) findViewById(R.id.userview);
        TextView cscore = (TextView) findViewById(R.id.compview);
        uscore.setText("You: " + userscore);
        cscore.setText("AI: " + compscore);


    }
    public void OnClickRoll(View view) {

        ImageView im = (ImageView) findViewById(R.id.imageView);
        TextView tsc = (TextView) findViewById(R.id.turnScore);
        Random rn = new Random();
        int num = rn.nextInt(6) + 1;
        if (num == 1) {
            im.setImageResource(R.drawable.dice1);
            turnscore = 0;
            count++;
            TurnDecider(count);
        } else if (num == 2)
            im.setImageResource(R.drawable.dice2);
        else if (num == 3)
            im.setImageResource(R.drawable.dice3);
        else if (num == 4)
            im.setImageResource(R.drawable.dice4);
        else if (num == 5)
            im.setImageResource(R.drawable.dice5);

        else if (num == 6)
            im.setImageResource(R.drawable.dice6);
        else {
        }

        if (num != 1) {
            turnscore = turnscore + num;
        }

        tsc.setText("Score: " + turnscore);
    }

    public void OnClickReset(View view) {
        userscore = compscore = turnscore = count = 0;
        TextView uscore = (TextView) findViewById(R.id.userview);
        TextView cscore = (TextView) findViewById(R.id.compview);
        TextView tsc = (TextView) findViewById(R.id.turnScore);
        uscore.setText("You: " + userscore);
        cscore.setText("AI: " + compscore);
        tsc.setText("Score: " + turnscore);
        TurnDecider(count);

    }

    public void OnClickHold(View view) {
        TextView uscore = (TextView) findViewById(R.id.userview);
        //TextView cscore=(TextView)findViewById(R.id.compview);
        userscore = userscore + turnscore;
        uscore.setText("You: " + userscore);
        //cscore.setText("AI: "+compscore);
        count++;
        TurnDecider(count);
    }

    protected void TurnDecider(int c) {
        Button roll = (Button) findViewById(R.id.rbtn);
        Button hold = (Button) findViewById(R.id.hbtn);
        TextView Tinfo=(TextView)findViewById(R.id.textView2);
        TextView tsc = (TextView) findViewById(R.id.turnScore);
        if(userscore>=100)
        {
            Tinfo.setText(" -- YOU WON -- ");
            Tinfo.setTextColor(Color.parseColor("#00aa29"));
            Toast.makeText(this,"Hit 'reset' to start a new game!",Toast.LENGTH_LONG).show();
        }
        else if(compscore>=100)
        {
            Tinfo.setText(" -- AI WON -- ");
            Tinfo.setTextColor(Color.parseColor("#cc0029"));
            Toast.makeText(this,"Hit 'reset' to start a new game!",Toast.LENGTH_LONG).show();

        }
        else if (c % 2 == 0) {
            turnscore=0;
            Tinfo.setText("Your Turn");
            Tinfo.setTextColor(Color.parseColor("#00aa29"));
            roll.setEnabled(true);
            hold.setEnabled(true);
        } else {
            Tinfo.setText("AI's Turn");
            Tinfo.setTextColor(Color.parseColor("#cc0029"));
            roll.setEnabled(false);
            hold.setEnabled(false);
            turnscore = 0;


                computerTurn();
        }
        tsc.setText("Score: "+turnscore);
    }

    protected void computerTurn() {
        start();

    }
    public void stop(){

        handler.removeCallbacks(runnable);
        started=false;
    }
    public void start() {
        started = true;
        handler.postDelayed(runnable, 2000);
    }
}


