package com.rose.healthcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry1="create table users(username text,email text,password text)";
        sqLiteDatabase.execSQL(qry1);

        //labtestdet icin olusturalım
        String qry2="create table cart(username text,product text,price float,otype text)";//otype=order type
        sqLiteDatabase.execSQL(qry2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void Register(String username,String email,String password){
        ContentValues cv = new ContentValues();
        cv.put("username",username);
        cv.put("email",email);
        cv.put("password",password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("users",null,cv);
        db.close();
    }

    public int Login(String username,String password){
        int result=0;
        SQLiteDatabase db =getReadableDatabase();
        Cursor c=db.rawQuery("select * from users",null);
        int usernamee=c.getColumnIndex("username");
        int passwordd=c.getColumnIndex("password");
        while(c.moveToNext()){
            String us,pass;
            us=c.getString(usernamee);
            pass=c.getString(passwordd);
            if(username.equals(us) && password.equals(pass)){
                result = 1;
                break;
            }
        }
        c.close();
        db.close();
        return result;
    }


    //addcart icin fonk olusturalım
    public void addCart(String username,String product,float price,String otype )
    {
        ContentValues cv=new ContentValues();
        cv.put("username",username);
        cv.put("product",product);
        cv.put("price",price);
        cv.put("otype",otype);
        SQLiteDatabase db=getWritableDatabase();
        db.insert("cart",null,cv);
        db.close();
    }

    //sepet kontrolü icin bir metot yazalım
    public int checkCart(String username,String product)
    {
        int result=0;
        String str[]=new String[2];
        str[0]=username;
        str[1]=product;
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM cart WHERE username = ? AND product = ? ",str);
        if(c.moveToFirst()){
            result=1;
        }
        db.close();
        return result;
    }

    //sepeti temizlemek icin metot yazalım
    public void removeCart(String username,String otype)
    {
        String str[]=new String[2];
        str[0]=username;
        str[1]=otype;
        SQLiteDatabase db=getWritableDatabase();
        db.delete("cart","username=? and otype=?",str);
        db.close();

    }

    public ArrayList getCartData(String username,String otype)
    {
        ArrayList<String> arr=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();
        String str[]=new String[2];
        str[0]=username;
        str[1]=otype;
        Cursor c=db.rawQuery("SELECT * FROM cart WHERE username = ? AND otype = ? ",str);
        if(c.moveToFirst()){
            do{
                String product=c.getString(1);
                String price=c.getString(2);
                arr.add(product+"TL"+price);
            }while(c.moveToNext());
        }
        db.close();
        return arr;


    }

}
