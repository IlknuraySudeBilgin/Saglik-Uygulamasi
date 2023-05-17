package com.rose.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class LabTestActivity extends AppCompatActivity {

    private String[][] packages=
            {
                    {"1.paket:Ful CheckUp","","","","2200"},
                    {"2.paket:Blood Glucose Fasting","","","","500"},
                    {"3.paket:COVID-19 Antibody","","","","200"},
                    {"4.paket:Thyroid Check","","","","350"},
                    {"5.paket:Immunity Check","","","","600"}

            };

    private String[] package_details=
            {
                    "Blood Glucose Fasting\n"+//1. String
                            "Complete Hemogram\n"+
                            "HbA1c\n"+"Iron Studies\n"+
                            "Kidney Function test\n"+"LDH Lactate Degdrogenase, Serum\n"+
                            "Lipid profile\n"+
                            "Liver Function Test"+
                    "Blood Glucose Fasting",//2.
                    "COVID-19 Antibody",//3.
                    "Thyroid Profile-Total(T3,T4 & TSH Ultra1-sensitive) ",//4.
                    "Complete Hemogram\n"+//5.
                            "CRP (C Reactive Protein) Quantitative, Serum\n"+
                            "Kidney Function test\n"+
                            "Vitamin D Total-25 Hydroxy\n"+
                            "Liver Function Test\n"+
                            "Lipid Profile"
            };

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button btnGoToCart,btnBack;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);

        btnGoToCart=findViewById(R.id.buttonLTGoToCart);
        btnBack=findViewById(R.id.buttonLTBack);
        listView=findViewById(R.id.listViewLT);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LabTestActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });

        list=new ArrayList();
        for(int i=0;i<packages.length;i++)
        {
            item=new HashMap<String,String>();
            item.put("sıra 1",packages[i][0]);
            item.put("sıra 2",packages[i][1]);
            item.put("sıra 3",packages[i][2]);
            item.put("sıra 4",packages[i][3]);
            item.put("sıra 5","Toplam Ucret"+packages[i][4]+"TL");
            list.add(item);
        }

        sa=new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"sıra 1","sıra 2","sıra 3","sıra 4","sıra 5" },
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});

        listView.setAdapter(sa);

        //Test detaylarını görüntüleyelim
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent= new Intent(LabTestActivity.this,LabTestDetailsActivity.class);
                //verileri aktaralım
                intent.putExtra("text1",packages[i][0]);
                intent.putExtra("text2",package_details[i]);
                intent.putExtra("text3",packages[i][4]);
                startActivity(intent);

            }
        });

        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestActivity.this,CartLabActivity.class));

            }
        });



    }
}