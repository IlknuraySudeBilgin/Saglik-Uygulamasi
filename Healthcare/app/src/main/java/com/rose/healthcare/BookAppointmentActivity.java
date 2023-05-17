package com.rose.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class BookAppointmentActivity extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    EditText isim,hastane,iletisimno,ucret;
    TextView tv;

    private Button btntarih,btnsaat,btnRandevu,btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        tv=findViewById(R.id.textViewCartTitle);
        isim=findViewById(R.id.editTextAppAd);
        hastane=findViewById(R.id.editTextAppAddress);
        iletisimno=findViewById(R.id.editTextAppContactNumber);
        ucret=findViewById(R.id.editTextAppFees);
        btntarih=findViewById(R.id.buttonCartDatePİcker);
        btnsaat=findViewById(R.id.buttonCartTimePicker);
        btnRandevu=findViewById(R.id.buttonCheckout);
        btnBack=findViewById(R.id.buttonCartBack);


        //?bu setkey ler ne ise yarıyor bak
        isim.setKeyListener(null);
        hastane.setKeyListener(null);
        iletisimno.setKeyListener(null);
        ucret.setKeyListener(null);


        //intent ile aktarılan doktora ait bilgileri cekelim
        Intent it=getIntent();
        String title=it.getStringExtra("text1");
        String fullname=it.getStringExtra("text2");
        String address=it.getStringExtra("text3");
        String contact=it.getStringExtra("text4");
        String fees=it.getStringExtra("text5");
        //simdi cekilen verilerle istenilen bilgileri güncelleyelim
        tv.setText(title);
        isim.setText(fullname);
        hastane.setText(address);
        iletisimno.setText(contact);
        ucret.setText(fees+"TL");



        //back butonuyla FindDrAct geri donelim
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(BookAppointmentActivity.this,FindDoctorActivity.class);
                startActivity(intent);
            }
        });


        //simdi RandevuAl butonunu yapalım
        btnRandevu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //burada kaldım 2.38.30dk
            }
        });


        //tarih ve saat secebilmek icin setOnClickler olusturalım
        initDatePicker();//asagida olusturdugumuz metot
        btntarih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        initTimePicker();//asagida olusturdugumuz metot
        btnsaat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });

    }

    //Randevu tarihi secebilmek icin bir takvim oluşturalım

    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener= new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1=i1+1;
                btntarih.setText(i2+"/"+i1+"/"+i);
            }
        };
        Calendar cal=Calendar.getInstance();
        int yıl=cal.get(Calendar.YEAR);
        int ay=cal.get(Calendar.MONTH);
        int gun=cal.get(Calendar.DAY_OF_MONTH);

        int style= AlertDialog.THEME_HOLO_DARK;
        datePickerDialog=new DatePickerDialog(this,style,dateSetListener,yıl,ay,gun);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis()+86400000);
    }

    //simdi saat secebilmek icin bir metot yazalim
    private void initTimePicker(){
        TimePickerDialog.OnTimeSetListener timeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                btnsaat.setText(i+"."+i1);
            }
        };

        Calendar cal=Calendar.getInstance();
        int saat=cal.get(Calendar.HOUR);
        int dk=cal.get(Calendar.MINUTE);

        int style= AlertDialog.THEME_HOLO_DARK;
        timePickerDialog=new TimePickerDialog(this,style,timeSetListener,saat,dk,true);
    }


}