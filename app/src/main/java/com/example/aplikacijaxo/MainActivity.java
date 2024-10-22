package com.example.aplikacijaxo;

import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private int brojacIgre;

    private Button[] dugmad = new Button[9];
    private Button kliknutoDugme;
    boolean naPotezu = true; //true znaci da je na potezu X igrac
    private int[] nizVrednostiPolja = new int[9];

    private TextView var_textView_naPotezu;

    private int brojac_X, brojac_O;
    private TextView textView_rezultat_igracX, textView_rezultat_igracO;

    private Button dugmeSledecaIgra;

    private boolean igraZavrsena = false;

    private MediaPlayer mp;

    private MediaPlayer zvonoPobede;

    //0 - prazno polje
    //1 - X
    //2 - O
    //3 - polja su zakljucana, igra je zavrsena

    /*
    *       [0]     polje 00
    *       [1]     polje 01
    *       [2]     polje 02
    *       [3]     polje 10
    *       [4]     polje 11
    *       [5]     polje 12
    *       [6]     polje 20
    *       [7]     polje 21
    *       [8]     polje 22
    * */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        brojacIgre = 1;

        brojac_X = 0;
        brojac_O = 0;

        var_textView_naPotezu = (TextView)findViewById(R.id.textView_naPotezu);
        if(naPotezu){
            var_textView_naPotezu.setText("На потезу: Играч X");
        }else {
            var_textView_naPotezu.setText("На потезу: Играч O");
        }

        textView_rezultat_igracX = (TextView) findViewById(R.id.igracX_rezultat);
        textView_rezultat_igracX.setText("0");
        textView_rezultat_igracO = (TextView) findViewById(R.id.igracO_rezultat);
        textView_rezultat_igracO.setText("0");

        dugmeSledecaIgra = (Button)findViewById(R.id.dugmeSledeca);

        mp = MediaPlayer.create(MainActivity.this, R.raw.kvizmuzika);

        zvonoPobede = MediaPlayer.create(MainActivity.this, R.raw.pobedazvono);
    }

    private boolean stopiranMp = false;

    @Override
    protected void onStop(){
        super.onStop();

        if(mp.isPlaying()){
            mp.stop();
            mp = MediaPlayer.create(MainActivity.this, R.raw.kvizmuzika);

            stopiranMp = true;
        }
    }

    @Override
    protected void onResume(){
        super.onResume();

        if(stopiranMp){
            mp.start();
            stopiranMp = false;
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        if (mp != null) {
            mp.release();
            mp = null;
        }
    }

    public void kliknutoPolje00(View view){
        if(nizVrednostiPolja[0] == 0){
            kliknutoDugme = (Button)findViewById(R.id.dugme_00);

            if(naPotezu){
                nizVrednostiPolja[0] = 1;
                kliknutoDugme.setTextColor(Color.parseColor("#FF5733"));
                kliknutoDugme.setText("X");

                var_textView_naPotezu.setText("На потезу: Играч O");

                proveraNeresenog();
                proveraPobede(1);
            }else{
                nizVrednostiPolja[0] = 2;
                kliknutoDugme.setTextColor(Color.parseColor("#6082B6"));
                kliknutoDugme.setText("O");

                var_textView_naPotezu.setText("На потезу: Играч X");

                proveraNeresenog();
                proveraPobede(2);
            }

            naPotezu = !naPotezu;
        }else{
            if(nizVrednostiPolja[0] == 3){
                Toast.makeText(this, "Игра је завршена.", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Поље је већ заузето!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void kliknutoPolje01(View view){
        if(nizVrednostiPolja[1] == 0){
            kliknutoDugme = (Button)findViewById(R.id.dugme_01);

            if(naPotezu){
                nizVrednostiPolja[1] = 1;
                kliknutoDugme.setTextColor(Color.parseColor("#FF5733"));
                kliknutoDugme.setText("X");

                var_textView_naPotezu.setText("На потезу: Играч O");

                proveraNeresenog();
                proveraPobede(1);
            }else{
                nizVrednostiPolja[1] = 2;
                kliknutoDugme.setTextColor(Color.parseColor("#6082B6"));
                kliknutoDugme.setText("O");

                var_textView_naPotezu.setText("На потезу: Играч X");

                proveraNeresenog();
                proveraPobede(2);
            }

            naPotezu = !naPotezu;
        }else{
            if(nizVrednostiPolja[1] == 3){
                Toast.makeText(this, "Игра је завршена.", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Поље је већ заузето!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void kliknutoPolje02(View view){
        if(nizVrednostiPolja[2] == 0){
            kliknutoDugme = (Button)findViewById(R.id.dugme_02);

            if(naPotezu){
                nizVrednostiPolja[2] = 1;
                kliknutoDugme.setTextColor(Color.parseColor("#FF5733"));
                kliknutoDugme.setText("X");
                var_textView_naPotezu.setText("На потезу: Играч O");

                proveraNeresenog();
                proveraPobede(1);
            }else{
                nizVrednostiPolja[2] = 2;
                kliknutoDugme.setTextColor(Color.parseColor("#6082B6"));
                kliknutoDugme.setText("O");

                var_textView_naPotezu.setText("На потезу: Играч X");

                proveraNeresenog();
                proveraPobede(2);
            }

            naPotezu = !naPotezu;
        }else{
            if(nizVrednostiPolja[2] == 3){
                Toast.makeText(this, "Игра је завршена.", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Поље је већ заузето!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void kliknutoPolje10(View view){
        if(nizVrednostiPolja[3] == 0){
            kliknutoDugme = (Button)findViewById(R.id.dugme_10);

            if(naPotezu){
                nizVrednostiPolja[3] = 1;
                kliknutoDugme.setTextColor(Color.parseColor("#FF5733"));
                kliknutoDugme.setText("X");
                var_textView_naPotezu.setText("На потезу: Играч O");

                proveraNeresenog();
                proveraPobede(1);
            }else{
                nizVrednostiPolja[3] = 2;
                kliknutoDugme.setTextColor(Color.parseColor("#6082B6"));
                kliknutoDugme.setText("O");

                var_textView_naPotezu.setText("На потезу: Играч X");

                proveraNeresenog();
                proveraPobede(2);
            }

            naPotezu = !naPotezu;
        }else{
            if(nizVrednostiPolja[3] == 3){
                Toast.makeText(this, "Игра је завршена.", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Поље је већ заузето!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void kliknutoPolje11(View view){
        if(nizVrednostiPolja[4] == 0){
            kliknutoDugme = (Button)findViewById(R.id.dugme_11);

            if(naPotezu){
                nizVrednostiPolja[4] = 1;
                kliknutoDugme.setTextColor(Color.parseColor("#FF5733"));
                kliknutoDugme.setText("X");
                var_textView_naPotezu.setText("На потезу: Играч O");

                proveraNeresenog();
                proveraPobede(1);
            }else{
                nizVrednostiPolja[4] = 2;
                kliknutoDugme.setTextColor(Color.parseColor("#6082B6"));
                kliknutoDugme.setText("O");

                var_textView_naPotezu.setText("На потезу: Играч X");

                proveraNeresenog();
                proveraPobede(2);
            }

            naPotezu = !naPotezu;
        }else{
            if(nizVrednostiPolja[4] == 3){
                Toast.makeText(this, "Игра је завршена.", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Поље је већ заузето!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void kliknutoPolje12(View view){
        if(nizVrednostiPolja[5] == 0){
            kliknutoDugme = (Button)findViewById(R.id.dugme_12);

            if(naPotezu){
                nizVrednostiPolja[5] = 1;
                kliknutoDugme.setTextColor(Color.parseColor("#FF5733"));
                kliknutoDugme.setText("X");

                var_textView_naPotezu.setText("На потезу: Играч O");

                proveraNeresenog();
                proveraPobede(1);
            }else{
                nizVrednostiPolja[5] = 2;
                kliknutoDugme.setTextColor(Color.parseColor("#6082B6"));
                kliknutoDugme.setText("O");

                var_textView_naPotezu.setText("На потезу: Играч X");

                proveraNeresenog();
                proveraPobede(2);
            }

            naPotezu = !naPotezu;
        }else{
            if(nizVrednostiPolja[5] == 3){
                Toast.makeText(this, "Игра је завршена.", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Поље је већ заузето!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void kliknutoPolje20(View view){
        if(nizVrednostiPolja[6] == 0){
            kliknutoDugme = (Button)findViewById(R.id.dugme_20);

            if(naPotezu){
                nizVrednostiPolja[6] = 1;
                kliknutoDugme.setTextColor(Color.parseColor("#FF5733"));
                kliknutoDugme.setText("X");
                var_textView_naPotezu.setText("На потезу: Играч O");

                proveraNeresenog();
                proveraPobede(1);
            }else{
                nizVrednostiPolja[6] = 2;
                kliknutoDugme.setTextColor(Color.parseColor("#6082B6"));
                kliknutoDugme.setText("O");

                var_textView_naPotezu.setText("На потезу: Играч X");

                proveraNeresenog();
                proveraPobede(2);
            }

            naPotezu = !naPotezu;
        }else{
            if(nizVrednostiPolja[6] == 3){
                Toast.makeText(this, "Игра је завршена.", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Поље је већ заузето!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void kliknutoPolje21(View view){
        if(nizVrednostiPolja[7] == 0){
            kliknutoDugme = (Button)findViewById(R.id.dugme_21);

            if(naPotezu){
                nizVrednostiPolja[7] = 1;
                kliknutoDugme.setTextColor(Color.parseColor("#FF5733"));
                kliknutoDugme.setText("X");
                var_textView_naPotezu.setText("На потезу: Играч O");

                proveraNeresenog();
                proveraPobede(1);
            }else{
                nizVrednostiPolja[7] = 2;
                kliknutoDugme.setTextColor(Color.parseColor("#6082B6"));
                kliknutoDugme.setText("O");

                var_textView_naPotezu.setText("На потезу: Играч X");

                proveraNeresenog();
                proveraPobede(2);
            }

            naPotezu = !naPotezu;
        }else{
            if(nizVrednostiPolja[7] == 3){
                Toast.makeText(this, "Игра је завршена.", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Поље је већ заузето!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void kliknutoPolje22(View view){
        if(nizVrednostiPolja[8] == 0){
            kliknutoDugme = (Button)findViewById(R.id.dugme_22);

            if(naPotezu){
                nizVrednostiPolja[8] = 1;
                kliknutoDugme.setTextColor(Color.parseColor("#FF5733"));
                kliknutoDugme.setText("X");
                var_textView_naPotezu.setText("На потезу: Играч O");

                proveraNeresenog();
                proveraPobede(1);
            }else{
                nizVrednostiPolja[8] = 2;
                kliknutoDugme.setTextColor(Color.parseColor("#6082B6"));
                kliknutoDugme.setText("O");

                var_textView_naPotezu.setText("На потезу: Играч X");

                proveraNeresenog();
                proveraPobede(2);
            }

            naPotezu = !naPotezu;
        }else{
            if(nizVrednostiPolja[8] == 3){
                Toast.makeText(this, "Игра је завршена.", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Поље је већ заузето!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void proveraNeresenog(){

       if(nizVrednostiPolja[0] != 0 && nizVrednostiPolja[1] != 0 &&
               nizVrednostiPolja[2] != 0 && nizVrednostiPolja[3] != 0 &&
               nizVrednostiPolja[4] != 0 && nizVrednostiPolja[5] != 0 &&
               nizVrednostiPolja[6] != 0 && nizVrednostiPolja[7] != 0 && nizVrednostiPolja[8] != 0) {

           var_textView_naPotezu.setText("Нерешено");

           igraZavrsena = true;
           brojacIgre++;
       }

    }

    public void proveraPobede(int znak){
        /*
        *       0   1   2
        *       3   4   5
        *       6   7   8
        *
        *       vodoravne kombinacije: 0-1-2, 3-4-5, 6-7-8
        *       uspravne kombinacije: 0-3-6, 1-4-7, 2-5-8
        *       kose kombinacije: 0-4-8, 2-4-6
        * */
        if(znak == 1){
            if(nizVrednostiPolja[0] == 1 && nizVrednostiPolja[1] == 1 && nizVrednostiPolja[2] == 1){
                for(int i = 0; i < 9; i++){
                    nizVrednostiPolja[i] = 3;
                }

                Toast.makeText(this, "Свака част, играчу x!", Toast.LENGTH_SHORT).show();
                var_textView_naPotezu.setText("Победник је Играч X");

                zvonoPobede.start();

                brojac_X++;
                //textView_rezultat_igracX.setText(brojac_X);
                textView_rezultat_igracX.setText(String.valueOf(brojac_X));
                textView_rezultat_igracO.setText(String.valueOf(brojac_O));

                igraZavrsena = true;
                brojacIgre++;
            }

            if(nizVrednostiPolja[3] == 1 && nizVrednostiPolja[4] == 1 && nizVrednostiPolja[5] == 1){
                for(int i = 0; i < 9; i++){
                    nizVrednostiPolja[i] = 3;
                }
                Toast.makeText(this, "Свака част, играчу x!", Toast.LENGTH_SHORT).show();
                var_textView_naPotezu.setText("Победник је Играч X");

                zvonoPobede.start();

                brojac_X++;
                //textView_rezultat_igracX.setText(brojac_X);
                textView_rezultat_igracX.setText(String.valueOf(brojac_X));
                textView_rezultat_igracO.setText(String.valueOf(brojac_O));

                igraZavrsena = true;
                brojacIgre++;
            }

            if(nizVrednostiPolja[6] == 1 && nizVrednostiPolja[7] == 1 && nizVrednostiPolja[8] == 1){
                for(int i = 0; i < 9; i++){
                    nizVrednostiPolja[i] = 3;
                }
                Toast.makeText(this, "Свака част, играчу x!", Toast.LENGTH_SHORT).show();
                var_textView_naPotezu.setText("Победник је Играч X");

                zvonoPobede.start();

                brojac_X++;
                //textView_rezultat_igracX.setText(brojac_X);
                textView_rezultat_igracX.setText(String.valueOf(brojac_X));
                textView_rezultat_igracO.setText(String.valueOf(brojac_O));

                igraZavrsena = true;
                brojacIgre++;
            }

            if(nizVrednostiPolja[0] == 1 && nizVrednostiPolja[3] == 1 && nizVrednostiPolja[6] == 1){
                for(int i = 0; i < 9; i++){
                    nizVrednostiPolja[i] = 3;
                }
                Toast.makeText(this, "Свака част, играчу x!", Toast.LENGTH_SHORT).show();
                var_textView_naPotezu.setText("Победник је Играч X");

                zvonoPobede.start();

                brojac_X++;
                //textView_rezultat_igracX.setText(brojac_X);
                textView_rezultat_igracX.setText(String.valueOf(brojac_X));
                textView_rezultat_igracO.setText(String.valueOf(brojac_O));

                igraZavrsena = true;
                brojacIgre++;
            }

            if(nizVrednostiPolja[1] == 1 && nizVrednostiPolja[4] == 1 && nizVrednostiPolja[7] == 1){
                for(int i = 0; i < 9; i++){
                    nizVrednostiPolja[i] = 3;
                }
                Toast.makeText(this, "Свака част, играчу x!", Toast.LENGTH_SHORT).show();
                var_textView_naPotezu.setText("Победник је Играч X");

                zvonoPobede.start();

                brojac_X++;
                //textView_rezultat_igracX.setText(brojac_X);
                textView_rezultat_igracX.setText(String.valueOf(brojac_X));
                textView_rezultat_igracO.setText(String.valueOf(brojac_O));

                igraZavrsena = true;
                brojacIgre++;
            }

            if(nizVrednostiPolja[2] == 1 && nizVrednostiPolja[5] == 1 && nizVrednostiPolja[8] == 1){
                for(int i = 0; i < 9; i++){
                    nizVrednostiPolja[i] = 3;
                }
                Toast.makeText(this, "Свака част, играчу x!", Toast.LENGTH_SHORT).show();
                var_textView_naPotezu.setText("Победник је Играч X");

                zvonoPobede.start();

                brojac_X++;
                //textView_rezultat_igracX.setText(brojac_X);
                textView_rezultat_igracX.setText(String.valueOf(brojac_X));
                textView_rezultat_igracO.setText(String.valueOf(brojac_O));

                igraZavrsena = true;
                brojacIgre++;
            }

            if(nizVrednostiPolja[0] == 1 && nizVrednostiPolja[4] == 1 && nizVrednostiPolja[8] == 1){
                for(int i = 0; i < 9; i++){
                    nizVrednostiPolja[i] = 3;
                }
                Toast.makeText(this, "Свака част, играчу x!", Toast.LENGTH_SHORT).show();
                var_textView_naPotezu.setText("Победник је Играч X");

                zvonoPobede.start();

                brojac_X++;
                //textView_rezultat_igracX.setText(brojac_X);
                textView_rezultat_igracX.setText(String.valueOf(brojac_X));
                textView_rezultat_igracO.setText(String.valueOf(brojac_O));

                igraZavrsena = true;
                brojacIgre++;
            }

            if(nizVrednostiPolja[2] == 1 && nizVrednostiPolja[4] == 1 && nizVrednostiPolja[6] == 1){
                for(int i = 0; i < 9; i++){
                    nizVrednostiPolja[i] = 3;
                }
                Toast.makeText(this, "Свака част, играчу x!", Toast.LENGTH_SHORT).show();
                var_textView_naPotezu.setText("Победник је Играч X");

                zvonoPobede.start();

                brojac_X++;
                //textView_rezultat_igracX.setText(brojac_X);
                textView_rezultat_igracX.setText(String.valueOf(brojac_X));
                textView_rezultat_igracO.setText(String.valueOf(brojac_O));

                igraZavrsena = true;
                brojacIgre++;
            }
        }

        if(znak == 2){
            if(nizVrednostiPolja[0] == 2 && nizVrednostiPolja[1] == 2 && nizVrednostiPolja[2] == 2){
                for(int i = 0; i < 9; i++){
                    nizVrednostiPolja[i] = 3;
                }
                Toast.makeText(this, "Свака част, играчу O!", Toast.LENGTH_SHORT).show();
                var_textView_naPotezu.setText("Победник је Играч O");

                zvonoPobede.start();

                brojac_O++;
                //textView_rezultat_igracO.setText(brojac_O);
                textView_rezultat_igracX.setText(String.valueOf(brojac_X));
                textView_rezultat_igracO.setText(String.valueOf(brojac_O));

                igraZavrsena = true;
                brojacIgre++;
            }

            if(nizVrednostiPolja[3] == 2 && nizVrednostiPolja[4] == 2 && nizVrednostiPolja[5] == 2){
                for(int i = 0; i < 9; i++){
                    nizVrednostiPolja[i] = 3;
                }
                Toast.makeText(this, "Свака част, играчу O!", Toast.LENGTH_SHORT).show();
                var_textView_naPotezu.setText("Победник је Играч O");

                zvonoPobede.start();

                brojac_O++;
                //textView_rezultat_igracO.setText(brojac_O);
                textView_rezultat_igracX.setText(String.valueOf(brojac_X));
                textView_rezultat_igracO.setText(String.valueOf(brojac_O));

                igraZavrsena = true;
                brojacIgre++;
            }

            if(nizVrednostiPolja[6] == 2 && nizVrednostiPolja[7] == 2 && nizVrednostiPolja[8] == 2){
                for(int i = 0; i < 9; i++){
                    nizVrednostiPolja[i] = 3;
                }
                Toast.makeText(this, "Свака част, играчу O!", Toast.LENGTH_SHORT).show();
                var_textView_naPotezu.setText("Победник је Играч O");

                zvonoPobede.start();

                brojac_O++;
                //textView_rezultat_igracO.setText(brojac_O);
                textView_rezultat_igracX.setText(String.valueOf(brojac_X));
                textView_rezultat_igracO.setText(String.valueOf(brojac_O));

                igraZavrsena = true;
                brojacIgre++;
            }

            if(nizVrednostiPolja[0] == 2 && nizVrednostiPolja[3] == 2 && nizVrednostiPolja[6] == 2){
                for(int i = 0; i < 9; i++){
                    nizVrednostiPolja[i] = 3;
                }
                Toast.makeText(this, "Свака част, играчу O!", Toast.LENGTH_SHORT).show();
                var_textView_naPotezu.setText("Победник је Играч O");

                zvonoPobede.start();

                brojac_O++;
                //textView_rezultat_igracO.setText(brojac_O);
                textView_rezultat_igracX.setText(String.valueOf(brojac_X));
                textView_rezultat_igracO.setText(String.valueOf(brojac_O));

                igraZavrsena = true;
                brojacIgre++;
            }

            if(nizVrednostiPolja[1] == 2 && nizVrednostiPolja[4] == 2 && nizVrednostiPolja[7] == 2){
                for(int i = 0; i < 9; i++){
                    nizVrednostiPolja[i] = 3;
                }
                Toast.makeText(this, "Свака част, играчу O!", Toast.LENGTH_SHORT).show();
                var_textView_naPotezu.setText("Победник је Играч O");

                zvonoPobede.start();

                brojac_O++;
                //textView_rezultat_igracO.setText(brojac_O);
                textView_rezultat_igracX.setText(String.valueOf(brojac_X));
                textView_rezultat_igracO.setText(String.valueOf(brojac_O));

                igraZavrsena = true;
                brojacIgre++;
            }

            if(nizVrednostiPolja[2] == 2 && nizVrednostiPolja[5] == 2 && nizVrednostiPolja[8] == 2){
                for(int i = 0; i < 9; i++){
                    nizVrednostiPolja[i] = 3;
                }
                Toast.makeText(this, "Свака част, играчу O!", Toast.LENGTH_SHORT).show();
                var_textView_naPotezu.setText("Победник је Играч O");

                zvonoPobede.start();

                brojac_O++;
                //textView_rezultat_igracO.setText(brojac_O);
                textView_rezultat_igracX.setText(String.valueOf(brojac_X));
                textView_rezultat_igracO.setText(String.valueOf(brojac_O));

                igraZavrsena = true;
                brojacIgre++;
            }

            if(nizVrednostiPolja[0] == 2 && nizVrednostiPolja[4] == 2 && nizVrednostiPolja[8] == 2){
                for(int i = 0; i < 9; i++){
                    nizVrednostiPolja[i] = 3;
                }
                Toast.makeText(this, "Свака част, играчу O!", Toast.LENGTH_SHORT).show();
                var_textView_naPotezu.setText("Победник је Играч O");

                zvonoPobede.start();

                brojac_O++;
                //textView_rezultat_igracO.setText(brojac_O);
                textView_rezultat_igracX.setText(String.valueOf(brojac_X));
                textView_rezultat_igracO.setText(String.valueOf(brojac_O));

                igraZavrsena = true;
                brojacIgre++;
            }

            if(nizVrednostiPolja[2] == 2 && nizVrednostiPolja[4] == 2 && nizVrednostiPolja[6] == 2){
                for(int i = 0; i < 9; i++){
                    nizVrednostiPolja[i] = 3;
                }
                Toast.makeText(this, "Свака част, играчу O!", Toast.LENGTH_SHORT).show();
                var_textView_naPotezu.setText("Победник је Играч O");

                zvonoPobede.start();

                brojac_O++;
                //textView_rezultat_igracO.setText(brojac_O);
                textView_rezultat_igracX.setText(String.valueOf(brojac_X));
                textView_rezultat_igracO.setText(String.valueOf(brojac_O));

                igraZavrsena = true;
                brojacIgre++;
            }
        }
    }

    public void sledecaIgra(View view){

            obrisiPolja();

            if(brojacIgre % 2 == 0){
                naPotezu = false;
                var_textView_naPotezu.setText("На потезу: Играч O");
            }else{
                naPotezu = true;
                var_textView_naPotezu.setText("На потезу: Играч X");
            }
    }

    public void obrisiPolja(){
        nizVrednostiPolja[0] = 0;
        nizVrednostiPolja[1] = 0;
        nizVrednostiPolja[2] = 0;
        nizVrednostiPolja[3] = 0;
        nizVrednostiPolja[4] = 0;
        nizVrednostiPolja[5] = 0;
        nizVrednostiPolja[6] = 0;
        nizVrednostiPolja[7] = 0;
        nizVrednostiPolja[8] = 0;

        dugmad[0] = (Button)findViewById(R.id.dugme_00);
        dugmad[1] = (Button)findViewById(R.id.dugme_01);
        dugmad[2] = (Button)findViewById(R.id.dugme_02);
        dugmad[3] = (Button)findViewById(R.id.dugme_10);
        dugmad[4] = (Button)findViewById(R.id.dugme_11);
        dugmad[5] = (Button)findViewById(R.id.dugme_12);
        dugmad[6] = (Button)findViewById(R.id.dugme_20);
        dugmad[7] = (Button)findViewById(R.id.dugme_21);
        dugmad[8] = (Button)findViewById(R.id.dugme_22);

        dugmad[0].setText("");
        dugmad[1].setText("");
        dugmad[2].setText("");
        dugmad[3].setText("");
        dugmad[4].setText("");
        dugmad[5].setText("");
        dugmad[6].setText("");
        dugmad[7].setText("");
        dugmad[8].setText("");
    }

    public void noviMec(View view){
        brojacIgre = 1;
        brojac_X = 0;
        brojac_O = 0;

        obrisiPolja();

        naPotezu = true;
        var_textView_naPotezu.setText("На потезу: Играч X");

        textView_rezultat_igracX.setText(String.valueOf(brojac_X));
        textView_rezultat_igracO.setText(String.valueOf(brojac_O));

        igraZavrsena = false;
    }

    public void dugmeZvuk_Klik(View view){


        if(mp.isPlaying()){
            mp.stop();
            mp = MediaPlayer.create(MainActivity.this, R.raw.kvizmuzika);
        }else{
            mp.start();
        }
    }
}