package com.rose.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LabTestDetailsActivity extends AppCompatActivity {

    TextView tvPackageName,tvTotalCost;
    EditText edDetails;

    Button btnaddcard,btnback;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_details);

        tvPackageName=findViewById(R.id.textViewLTDetailsPackageName);
        tvTotalCost=findViewById(R.id.textViewTotalCost);
        edDetails=findViewById(R.id.editTextTextMultiLine);
        btnback=findViewById(R.id.buttonLTDetailsBack);
        btnaddcard=findViewById(R.id.buttonLTDetailsAddToCart);
        edDetails.setKeyListener(null);


        //LabTestAct deki verileri cekelim
        Intent intent = getIntent();
        tvPackageName.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("text2"));
        tvTotalCost.setText("Total Ücret: "+intent.getStringExtra("text3")+"TL");

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestDetailsActivity.this,LabTestActivity.class));
            }
        });

        btnaddcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences=getSharedPreferences("shared pref", Context.MODE_PRIVATE);
                String username=sharedPreferences.getString("username","").toString();
                String product=tvPackageName.getText().toString();
                float price=Float.parseFloat(intent.getStringExtra("text3").toString());

                Database db=new Database(getApplicationContext(),"healthcare",null,1);

                if(db.checkCart(username,product)==1){
                    Toast.makeText(getApplicationContext(), "Bu ürün zaten sepetinizde var!", Toast.LENGTH_SHORT).show();
                }
                else//eger urun sepette yoksa ekleyelim
                {
                    db.addCart(username,product,price,"lab");
                    Toast.makeText(getApplicationContext(), "Sepete eklendi", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabTestDetailsActivity.this,LabTestActivity.class));
                }



            }
        });



    }
}