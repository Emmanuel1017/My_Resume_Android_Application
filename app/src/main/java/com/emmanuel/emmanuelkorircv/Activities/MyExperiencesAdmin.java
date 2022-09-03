package com.emmanuel.emmanuelkorircv.Activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.emmanuel.emmanuelkorircv.Adapters.ExperincesAdapter;
import com.emmanuel.emmanuelkorircv.HelperClasses.ReadJsonRequest;
import com.emmanuel.emmanuelkorircv.Model.ExperiencesModel;
import com.emmanuel.emmanuelkorircv.R;
import com.emmanuel.emmanuelkorircv.Utility.App;
import com.emmanuel.emmanuelkorircv.Utility.AppConstants;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Collections;

public class MyExperiencesAdmin extends AppCompatActivity {


    private RecyclerView RecyclerViewExperiences;
    private FirebaseAuth mAuth;
    private ProgressBar loadingPB;

    //initilize thes here coz the method using them is called from another public class
    private final ArrayList<ExperiencesModel> ExperiencesModelArrayList = new ArrayList<>();
    // get app constant inititlized in application
    private final ExperincesAdapter experiencesAdapter = new ExperincesAdapter(ExperiencesModelArrayList, App.getContext());

    private ImageView Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_experiences_admin);

        Back = findViewById(R.id.back_my_experiences);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyExperiencesAdmin.this.onBackPressed();
            }
        });

        new ReadJsonRequest().Get_Json_File(MyExperiencesAdmin.this,"https://emmanuel1017.github.io/Angular-Resume/assets/data/experiences.json", AppConstants.my_experiences);
    }


    public void Json_Populate(ArrayList<ExperiencesModel> ExperiencesModelArrayList, Context context)
    {
        //retain original context
        Activity activity =(Activity) context;
        RecyclerViewExperiences = activity.findViewById(R.id.Recycler_My_experiences_Admin);
        loadingPB = activity.findViewById(R.id.idPBLoading_experience_admin);

        RecyclerViewExperiences.setLayoutManager(new LinearLayoutManager(MyExperiencesAdmin.this,LinearLayoutManager.VERTICAL,false));
        //setting adapter to recycler view on below line.
        RecyclerViewExperiences.setAdapter(experiencesAdapter);

        // Collections.reverse(Blog_List);

        this.ExperiencesModelArrayList.addAll(ExperiencesModelArrayList);
        Collections.reverse(this.ExperiencesModelArrayList);
        experiencesAdapter.notifyDataSetChanged();
        loadingPB.setVisibility(View.GONE);
    }
}