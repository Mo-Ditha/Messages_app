package com.example.smsmanagerclass;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText phonenumberEditText,messageEditText;
    private Button sendbtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phonenumberEditText = findViewById(R.id.txtnumber);
        messageEditText = findViewById(R.id.txtsms);
        sendbtn = findViewById(R.id.btnname);

        sendbtn.setOnClickListener(v -> {
            String phoneNumebr = phonenumberEditText.getText().toString();
            String message = messageEditText.getText().toString();
            if(!phoneNumebr.isEmpty() && !message.isEmpty()){
               sendSMS(phoneNumebr,message);
            }
            else{
                Toast.makeText(MainActivity.this,"Please enter a phone number and message:", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendSMS(String phoneNumber, String message){
        SmsManager smsManager = SmsManager.getDefault();

        Intent sentIntent = new Intent("SMS_SENT");
        PendingIntent sentPendingIntent = PendingIntent.getBroadcast(this, 0, sentIntent, PendingIntent.FLAG_IMMUTABLE);
        smsManager.sendTextMessage(phoneNumber,null,message,sentPendingIntent,null);
    }
}
