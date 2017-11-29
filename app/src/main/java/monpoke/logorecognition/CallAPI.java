package monpoke.logorecognition;

import android.content.ContentValues;
import android.os.AsyncTask;

import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by A643012 on 28/11/2017.
 */
public class CallAPI extends AsyncTask<String, String, String> {

    public CallAPI() {
        //set context variables if required
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected String doInBackground(String... params) {

        String urlString = params[0]; // URL to call

        String data = params[1]; //data to post

        try {

            HttpRequest request = getHttpRequestFactory()//
                    .buildPostRequest(new GenericUrl(urlString), ByteArrayContent.fromString("text/plain", "data=" + data));
            //request.setParser(new JacksonFactory().createJsonObjectParser());

            HttpResponse execute = request.execute();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(execute.getContent()));

            System.out.println("============");
            String t = null;
            while ((t = bufferedReader.readLine()) != null) {
                System.out.println(t);
            }

            return "OK";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "ERROR";
        }
    }

    private HttpRequestFactory getHttpRequestFactory() {
        NetHttpTransport netHttpTransport = new NetHttpTransport();
        return netHttpTransport
                .createRequestFactory(new HttpRequestInitializer() {
                    @Override
                    public void initialize(HttpRequest request) {
                        HttpHeaders headers = new HttpHeaders();
                        //headers.setContentType("application/json");
                        request.setHeaders(headers);
                    }
                });
    }
}
