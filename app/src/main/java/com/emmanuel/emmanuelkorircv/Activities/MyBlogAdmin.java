package com.emmanuel.emmanuelkorircv.Activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.emmanuel.emmanuelkorircv.Adapters.BlogAdapter;
import com.emmanuel.emmanuelkorircv.HelperClasses.ReadJsonRequest;
import com.emmanuel.emmanuelkorircv.Model.BlogModel;
import com.emmanuel.emmanuelkorircv.R;
import com.emmanuel.emmanuelkorircv.Utility.App;
import com.emmanuel.emmanuelkorircv.Utility.AppConstants;
import com.emmanuel.emmanuelkorircv.Utility.Shared_Preferences;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MyBlogAdmin extends AppCompatActivity {

    private RecyclerView RecyclerViewBlog;
    private FirebaseAuth mAuth;
    private ProgressBar loadingPB;

    //initilize thes here coz the method using them is called from another public class
    private final ArrayList<BlogModel> blogModelArrayList = new ArrayList<>();
    // get app constant inititlized in application
    private final BlogAdapter blogAdapter = new BlogAdapter(blogModelArrayList, App.getContext());

    private ImageView Back;
    private android.content.SharedPreferences sharedPreferences;
    int position = 0;
    private LinearLayout homeRL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_blog_admin);

        Back = findViewById(R.id.back_my_blog);
        mAuth = FirebaseAuth.getInstance();
        //on below line we are getting database reference.
        sharedPreferences= getSharedPreferences(Shared_Preferences.SHARED_PREF, Context.MODE_PRIVATE);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyBlogAdmin.this.onBackPressed();
            }
        });

        new ReadJsonRequest().Get_Json_File(MyBlogAdmin.this,"https://emmanuel1017.github.io/Angular-Resume/assets/data/posts.json", AppConstants.my_blog);

    }



    public void Json_Populate(ArrayList<BlogModel> Blog_List,Context context)
    {
        //retain original context
        Activity activity =(Activity) context;
        RecyclerViewBlog = activity.findViewById(R.id.Recycler_My_Blog_Admin);
        loadingPB = activity.findViewById(R.id.idPBLoading_my_blog_admin);

        RecyclerViewBlog.setLayoutManager(new LinearLayoutManager(MyBlogAdmin.this,LinearLayoutManager.VERTICAL,false));
        //setting adapter to recycler view on below line.
        RecyclerViewBlog.setAdapter(blogAdapter);

       // Collections.reverse(Blog_List);
        this.blogModelArrayList.addAll(Blog_List);
        blogAdapter.notifyDataSetChanged();
        loadingPB.setVisibility(View.GONE);
    }
}