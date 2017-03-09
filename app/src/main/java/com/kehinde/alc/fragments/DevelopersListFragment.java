package com.kehinde.alc.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.kehinde.alc.API.APIService;
import com.kehinde.alc.R;
import com.kehinde.alc.adapters.DevAdapter;
import com.kehinde.alc.models.Developer;

import java.io.Console;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class DevelopersListFragment extends Fragment {


    private RecyclerView dev_recycler_view;
    private ArrayList<Developer> devList = new ArrayList<>();
    private ProgressDialog progressDialog;
    private DevAdapter devAdapter;
    private boolean isTwoPane=false;


    public DevelopersListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mView=  inflater.inflate(R.layout.fragment_developers_list, container, false);
        progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Getting a list of Java developers in Lagos...");
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);

        dev_recycler_view = (RecyclerView) mView.findViewById(R.id.devListRecyclerView);

        getAllDevelopers();

        return mView;
    }

    private void getAllDevelopers() {
        progressDialog.show();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService apiService=retrofit.create(APIService.class);
        Call<JsonObject> call=apiService.getDevelopers();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                JsonObject jsonObject= response.body();
                JsonArray developerList=jsonObject.getAsJsonArray("items");

                devList.clear();
                for (JsonElement object:developerList){
                    devList.add(new Developer(object.getAsJsonObject().get("login").getAsString(),
                            object.getAsJsonObject().get("avatar_url").getAsString(),
                            object.getAsJsonObject().get("html_url").getAsString()));

                }

                devAdapter = new DevAdapter(getActivity(), devList);
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
                dev_recycler_view.setLayoutManager(linearLayoutManager);
                dev_recycler_view.setAdapter(devAdapter);

                progressDialog.hide();
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Snackbar snackbar=Snackbar.make(dev_recycler_view,t.getLocalizedMessage(),Snackbar.LENGTH_INDEFINITE);
                snackbar.setAction("Reload", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getAllDevelopers();
                    }
                });
                snackbar.show();

                progressDialog.hide();
            }
        });
    }
}
