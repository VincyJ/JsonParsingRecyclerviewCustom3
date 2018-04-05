package example.com.jsonparsingrecyclerviewcustom3;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CustomAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    // Creating Progress dialog.
    ProgressDialog progressDialog;
    ArrayList<ListModel> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Views initialization
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        arrayList = new ArrayList<>();

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Please Wait, data loading");
        progressDialog.show();

        getStringRequest();
    }

    private void getStringRequest() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://www.androidbegin.com/tutorial/jsonparsetutorial.txt";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject mJsonObject;
                JSONArray mJsonArray;
                try {
                    mJsonObject = new JSONObject(response);
                    mJsonArray = mJsonObject.getJSONArray("worldpopulation");
                    for (int i = 0; i < mJsonArray.length(); i++) {
                        JSONObject person = (JSONObject) mJsonArray.get(i);
                        int rank = person.getInt("rank");
                        String country = person.getString("country");
                        String population = person.getString("population");
                        String flag = person.getString("flag");
                        ListModel listModel = new ListModel(rank, country, population,flag);
                        arrayList.add(listModel);

                    }
                    // Hiding the progress dialog after all task complete.
                    progressDialog.dismiss();
                    // setting datas into the adapter
                    adapter = new CustomAdapter(MainActivity.this, arrayList);
                    // setting adapter in the list
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Hiding the progress dialog after all task complete.
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Sorry, no data available", Toast.LENGTH_LONG).show();
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
