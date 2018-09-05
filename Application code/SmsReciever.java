package com.example.money.agnikai;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsMessage;

import java.net.URI;

/**
 * Created by money on 5/16/2016.
 */
public class SmsReciever extends BroadcastReceiver {
    String PhoneNumber="+91 91 65 901561";

    @Override
    public void onReceive(Context context, Intent intent) {
        // get the sms message passed in
        Bundle bundle=intent.getExtras();
        SmsMessage[] msgs=null;
        String str="";
        if(bundle!=null)
        {
            //-retrieve the Sms Recieved
            Object[] pdus=(Object[])bundle.get("pdus");
            msgs=new SmsMessage[pdus.length];
            for(int i=0;i<msgs.length;i++)
            {
                msgs[i]=SmsMessage.createFromPdu((byte[])pdus[i]);
                String phNum=msgs[i].getOriginatingAddress();
                if (PhoneNumber.equals(phNum))
                {
                    Uri uri=Uri.parse("content://sms/inbox");
                    ContentResolver contentResolver=context.getContentResolver();
                    String where= "address:="+phNum;
                    Cursor cursor=contentResolver.query(uri,new String[]{"_id","thread_id"},where,null,null);
                    while (cursor.moveToNext())
                    {
                        long thread_id=cursor.getLong(1);
                        where ="thread_id"+thread_id;
                        Uri thread=Uri.parse("content://sms/inbox");
                        context.getContentResolver().delete(thread,where,null);
                    }
                    Intent I=new Intent(context,ProfileActivity.class);
                    I.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    I.putExtra("msg",str);
                    context.startActivity(I);
                }
            }
        }
    }
}
