package com.rose.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {

    //doktorlar için double array oluşturalım
    private String[][] doctor_details1={
            {"Doktor Adı : Mustafa Yasin OLGUN","Hastane:Kavaklık Hastanesi","Deneyim Süresi:4 yıl","Tel No:5331254456","600"},
            {"Doktor Adı : İlknuray Sude BİLGİN","Hastane:Çamlık Hastanesi","Deneyim Süresi:5 yıl","Tel No:5495347896","800"},
            {"Doktor Adı : Kübranur KULAK","Hastane:Görele Hastanesi","Deneyim Süresi:3 yıl","Tel No:5325987221","400"},
            {"Doktor Adı : Sami Çakaloğlu","Hastane:Devrek Hastanesi","Deneyim Süresi:4 yıl","Tel No:5345697216","500"},
            {"Doktor Adı : Mustafakerem Sönmemiş","Hastane:Bağcılar Hastanesi","Deneyim Süresi:2 yıl","Tel No:5364987112","300"}

    };

    private String[][] doctor_details2={
            {"Doktor Adı : Sinem YILMAZ","Hastane:Kavaklık Hastanesi","Deneyim Süresi:7 yıl","Tel No:5331254456","800"},
            {"Doktor Adı : Büşra AYHAN","Hastane:Çamlık Hastanesi","Deneyim Süresi:1 yıl","Tel No:5495347896","200"},
            {"Doktor Adı : Tuğçe AKAR","Hastane:Görele Hastanesi","Deneyim Süresi:9 yıl","Tel No:5325987221","1400"},
            {"Doktor Adı : Aleyna KURUN","Hastane:Devrek Hastanesi","Deneyim Süresi:4 yıl","Tel No:5345697216","500"},
            {"Doktor Adı : Ahsen Bilgin","Hastane:Bağcılar Hastanesi","Deneyim Süresi:2 yıl","Tel No:5364987112","250"}

    };

    private String[][] doctor_details3={
            {"Doktor Adı : Sude OLGUN","Hastane:Kavaklık Hastanesi","Deneyim Süresi:6 yıl","Tel No:5331254456","900"},
            {"Doktor Adı : Aslıhan Demir","Hastane:Çamlık Hastanesi","Deneyim Süresi:8 yıl","Tel No:5495347896","800"},
            {"Doktor Adı : Anıl Bektaş","Hastane:Görele Hastanesi","Deneyim Süresi:3 yıl","Tel No:5325987221","200"},
            {"Doktor Adı : Hazal SOLAK","Hastane:Devrek Hastanesi","Deneyim Süresi:5 yıl","Tel No:5345697216","500"},
            {"Doktor Adı : Buse KORKMAZ","Hastane:Bağcılar Hastanesi","Deneyim Süresi:3 yıl","Tel No:5364987112","300"}

    };

    private String[][] doctor_details4={
            {"Doktor Adı : Serhat SAĞLIK","Hastane:Kavaklık Hastanesi","Deneyim Süresi:7 yıl","Tel No:5331254456","1000"},
            {"Doktor Adı : Cem KOÇ","Hastane:Çamlık Hastanesi","Deneyim Süresi:4 yıl","Tel No:5495347896","400"},
            {"Doktor Adı : Emirhan BAKAN","Hastane:Görele Hastanesi","Deneyim Süresi:6 yıl","Tel No:5325987221","500"},
            {"Doktor Adı : Ceyda YALDIZ","Hastane:Devrek Hastanesi","Deneyim Süresi:4 yıl","Tel No:5345697216","400"},
            {"Doktor Adı : Esra MERT","Hastane:Bağcılar Hastanesi","Deneyim Süresi:3 yıl","Tel No:5364987112","200"}

    };
    private String[][] doctor_details5={
            {"Doktor Adı : Nur DEMİRTAŞ","Hastane:Kavaklık Hastanesi","Deneyim Süresi:6 yıl","Tel No:5331254456","600"},
            {"Doktor Adı : Emir AKSU","Hastane:Çamlık Hastanesi","Deneyim Süresi:3 yıl","Tel No:5495347896","300"},
            {"Doktor Adı : Ayşe MEMİŞ","Hastane:Görele Hastanesi","Deneyim Süresi:8 yıl","Tel No:5325987221","1200"},
            {"Doktor Adı : Senem GÖKMEN","Hastane:Devrek Hastanesi","Deneyim Süresi:2 yıl","Tel No:5345697216","400"},
            {"Doktor Adı : Kasım SEZEN","Hastane:Bağcılar Hastanesi","Deneyim Süresi:5 yıl","Tel No:5364987112","700"}

    };

    TextView tv;
    Button exit;
    String[][] doctor_details={};

    ArrayList list;

    HashMap<String,String> item;

    SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
        tv=findViewById(R.id.textViewLTTitle);
        exit=findViewById(R.id.buttonLTBack);
        Intent it =getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Family Physicians")==0)
            doctor_details=doctor_details1; //boylece ilk double arraydeki herkes kardiyolog oldu
        else

        if(title.compareTo("Dietician")==0)
            doctor_details=doctor_details2;
        else

        if(title.compareTo("Dentist")==0)
            doctor_details=doctor_details3;
        else

        if(title.compareTo("Surgeon")==0)
            doctor_details=doctor_details4;
        else
            doctor_details=doctor_details5;


            exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this,FindDoctorActivity.class));
            }
        });

        list=new ArrayList();
        for(int i=0;i<doctor_details.length;i++)
        {
            item= new HashMap<String,String>();
            item.put("sıra1",doctor_details[i][0]);//doktor adı?
            item.put("sıra2",doctor_details[i][1]);//Hastane
            item.put("sıra3",doctor_details[i][2]);//deneyim suresi
            item.put("sıra4",doctor_details[i][3]);//tel no
            item.put("sıra5","Danışman ücreti:"+doctor_details[i][4]+" TL");//danisman ucreti
            list.add(item);//itemleri listeye ekledik
        }

        sa=new SimpleAdapter(this,list,R.layout.multi_lines,new String[]{"sıra1","sıra2","sıra3","sıra4","sıra5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});

        ListView lst= findViewById(R.id.listViewLT);
        lst.setAdapter(sa);


        //bir doktor secildiginde randevu almak icin BookAppAct atacagiz
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it=new Intent(DoctorDetailsActivity.this,BookAppointmentActivity.class);
                //doktora ait bilgileri atacagiz fakat doktorun deneyim yılı randevu alma sayfasında istenmiyor bu nedenle index 2 yi atladık
                it.putExtra("text1",title);
                it.putExtra("text2",doctor_details[i][0]);
                it.putExtra("text3",doctor_details[i][1]);
                it.putExtra("text4",doctor_details[i][3]);
                it.putExtra("text5",doctor_details[i][4]);
                startActivity(it);

            }
        });


    }

}