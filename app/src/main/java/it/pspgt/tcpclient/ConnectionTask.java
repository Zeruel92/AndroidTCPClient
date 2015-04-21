package it.pspgt.tcpclient;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by gabry on 20/04/15.
 */
public class ConnectionTask extends AsyncTask<String,Integer,String> {
    String outString;
    Context context;

    public ConnectionTask(String text,Context con){
        context=con;
        outString=text+"\n";
        System.out.println(outString);
    }

    @Override
    protected String doInBackground(String... params) {
        String result="Errore nella ricezione";
        try {
            Socket sock = new Socket("192.168.1.67",12345);
            System.out.println("Socket creato"+sock.toString());
            OutputStream out=sock.getOutputStream();
            InputStream in=sock.getInputStream();
            BufferedReader buffIn=new BufferedReader(new InputStreamReader(in));
            out.write(outString.getBytes());
            result=buffIn.readLine();
            in.close();
            out.close();
            sock.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    protected void onPostExecute(String result){
        Toast.makeText(context,result,Toast.LENGTH_LONG).show();
    }
}
