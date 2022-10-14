package com.example.kopapirollo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button ko, papir, ollo;
    private ImageView enKep, oKep;
    private TextView ember, computer;
    private Random r;
    private int enEredmeny;
    private int gepEredmeny;

    private int en;
    private int gep;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        ko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                en = 0;
                enKep.setImageResource(R.drawable.rock);
                jatekValaszt();
                vizsgal();
            }
        });

        papir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                en = 1;
                enKep.setImageResource(R.drawable.paper);
                jatekValaszt();
                vizsgal();
            }
        });

        ollo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                en = 2;
                enKep.setImageResource(R.drawable.scissors);
                jatekValaszt();
                vizsgal();
            }
        });
    }

    public void init() {
        ko = findViewById(R.id.ko);
        papir = findViewById(R.id.papir);
        ollo = findViewById(R.id.ollo);
        enKep = findViewById(R.id.enKep);
        oKep = findViewById(R.id.oKep);
        ember = findViewById(R.id.ember);
        computer = findViewById(R.id.computer);
        r = new Random();
        enEredmeny = 0;
        gepEredmeny = 0;
    }

    public void jatekValaszt() {
        gep = r.nextInt(3);

        if (gep == 0) {
            oKep.setImageResource(R.drawable.rock);
        } else if (gep == 1) {
            oKep.setImageResource(R.drawable.paper);
        } else {
            oKep.setImageResource(R.drawable.scissors);
        }
    }

    public void vizsgal() {
        if (gep == en) {
            Toast.makeText(this, "Döntetlen", Toast.LENGTH_SHORT).show();
        } else if (gep == 0 && en == 1) {
            Toast.makeText(this, "Nyertél", Toast.LENGTH_SHORT).show();
            enEredmeny++;
            beallit();
        } else if (gep == 1 && en == 2) {
            Toast.makeText(this, "Nyertél", Toast.LENGTH_SHORT).show();
            enEredmeny++;
            beallit();
        } else if (gep == 2 && en == 0) {
            Toast.makeText(this, "Nyertél", Toast.LENGTH_SHORT).show();
            enEredmeny++;
            beallit();
        } else {
            Toast.makeText(this, "Vesztettél", Toast.LENGTH_SHORT).show();
            gepEredmeny++;
            beallit();
        }

        if (enEredmeny == 3) {
            nyertem();
        }

        if (gepEredmeny == 3) {
            vesztettem();
        }
    }

    public void beallit() {
        ember.setText("Ember: " + enEredmeny);
        computer.setText("Computer: " + gepEredmeny);
    }

    public void nyertem() {
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setCancelable(false);
        alert.setTitle("Gratulálok nyertél!");
        alert.setMessage("Szeretnél még játszani?");
        alert.setPositiveButton("Igen",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        enEredmeny = 0;
                        gepEredmeny = 0;
                        beallit();
                    }
                });

        alert.setNegativeButton("Nem", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }


        });

        alert.show();

    }

    public void vesztettem() {
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setCancelable(false);
        alert.setTitle("Sajnos vesztettél!");
        alert.setMessage("Szeretnél még játszani?");
        alert.setPositiveButton("Igen",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        enEredmeny = 0;
                        gepEredmeny = 0;
                        beallit();
                    }
                });

        alert.setNegativeButton("Nem", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        alert.show();

    }

}