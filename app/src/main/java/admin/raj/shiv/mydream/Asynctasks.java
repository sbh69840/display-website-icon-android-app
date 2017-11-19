package admin.raj.shiv.mydream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.graphics.BitmapCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import static android.R.string.copy;

/**
 * Created by admin on 21/03/2017.
 */

public class Asynctasks extends AppCompatActivity {
    EditText text;
    Button but;
    ImageView img;
   public static String url;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web);
        text=(EditText) findViewById(R.id.tv);
        but=(Button)findViewById(R.id.but12);
        img=(ImageView)findViewById(R.id.img);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ShivAsyncTask().execute("http://icons.better-idea.org/allicons.json?url="+text.getText());
            }
        });

    }
    public class ShivAsyncTask extends AsyncTask<String,Void,Bitmap>{

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap=BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher_round);
            try {
                HttpClient client = new DefaultHttpClient();
                HttpPost post = new HttpPost(params[0]);

                HttpResponse response = client.execute(post);
                int status=response.getStatusLine().getStatusCode();
                if(status==200){
                    HttpEntity entity=response.getEntity();
                    String data= EntityUtils.toString(entity);
                    JSONObject jsonObject=new JSONObject(data);
                    JSONArray jsonArray=jsonObject.getJSONArray("icons");
                    for(int i=0;i<jsonArray.length();i++){
                         JSONObject jsonObject1=jsonArray.getJSONObject(i);
                       String url=jsonObject1.getString("url");
                        bitmap=BitmapFactory.decodeStream((InputStream)new URL(url).getContent());




                    }


                    return bitmap;
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
img.setImageBitmap(bitmap);

        }
    }
    public static void setImage(ImageView img,String imageurl){
        try {
          Bitmap bitmap=BitmapFactory.decodeStream((InputStream)new URL(imageurl).getContent());
            img.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static void url(String urli){
        url=urli;
    }
    public static Bitmap loadBitmap(String url){
        int IO_BUFFER_SIZE=4*1024;
        Bitmap bitmap=null;
        InputStream in=null;
        BufferedOutputStream out=null;
        try{
            in=new BufferedInputStream(new URL(url).openStream(),IO_BUFFER_SIZE);
            final ByteArrayOutputStream dataStream=new ByteArrayOutputStream();
            out=new BufferedOutputStream(dataStream,IO_BUFFER_SIZE);

            out.flush();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }
        return bitmap;
    }
}
