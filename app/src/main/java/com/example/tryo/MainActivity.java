package com.example.tryo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.Spinner;

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

    private RecyclerView recyclerView;
    private ArrayList<items> recyclerDataArrayList;
    adapter ada;
    RelativeLayout filter;
    DrawerLayout drawerLayout;
    ArrayList<Item> items;
    SpinnerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.rv);
        filter=findViewById(R.id.filter);
        drawerLayout=findViewById(R.id.drawer_layout);

        filter.setOnClickListener(v->{
            drawerLayout.openDrawer(Gravity.RIGHT);
        });

        // created new array list..
        recyclerDataArrayList=new ArrayList<>();
         ada=new adapter(this,recyclerDataArrayList);

        // setting grid layout manager to implement grid view.
        // in this method '2' represents number of columns to be displayed in grid view.
        GridLayoutManager layoutManager=new GridLayoutManager(this,3);

        // at last set adapter to recycler view.
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(ada);
        getdata();
        initList();
        Spinner spinner = findViewById(R.id.duration);

        // we pass our item list and context to our Adapter.
        adapter = new SpinnerAdapter(this, items);
        spinner.setAdapter(adapter);


    }

    private void initList() {
        items = new ArrayList<>();
        items.add(new Item("Abhinandan"));
        items.add(new Item("Shivam"));
        items.add(new Item("Aditya"));
    }

    private void getdata() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://3.108.207.62:3003/api/user/workout/all?category_id=14",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if(jsonObject.getString("success").equals("true")){
                                JSONArray j=jsonObject.getJSONArray("data");
                                for(int i=0;i<j.length();i++){
                                    recyclerDataArrayList.add(new items(((JSONObject)j.get(i)).getString("name"),"with "+((JSONObject)j.get(i)).getString("trainer_name"),((JSONObject)j.get(i)).getString("difficulty_level_name"),String.valueOf((Integer.parseInt(((JSONObject)j.get(i)).getString("duration")))/60)+"m"));
                                    ada.notifyDataSetChanged();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            ////Log.v("Error_", "Some error occureed");
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String s=error.getLocalizedMessage();

                        //Log.v("Json Response: ",error.getMessage());

                    }
                }){
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}