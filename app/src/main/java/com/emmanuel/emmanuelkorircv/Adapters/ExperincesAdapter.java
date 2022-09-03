package com.emmanuel.emmanuelkorircv.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.emmanuel.emmanuelkorircv.HelperClasses.ImageHelpers.ImageGlideHelper;
import com.emmanuel.emmanuelkorircv.Model.ExperiencesModel;
import com.emmanuel.emmanuelkorircv.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.skydoves.transformationlayout.TransformationLayout;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class ExperincesAdapter extends RecyclerView.Adapter<ExperincesAdapter.ViewHolder> {
    //creating variables for our list, context, interface and position.
    private final ArrayList<ExperiencesModel> ExperiencesModelArrayList;
    private final Context context;
    int lastPos = -1;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    private final ImageGlideHelper imageGlideHelper = new ImageGlideHelper();

    //creating a constructor.
    public ExperincesAdapter(ArrayList<ExperiencesModel> ExperiencesModelArrayList, Context context) {
        this.ExperiencesModelArrayList = ExperiencesModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating our layout file on below line.
        View view = LayoutInflater.from(context).inflate(R.layout.experiences_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //setting data to our recycler view item on below line.
        ExperiencesModel experiencesModel = ExperiencesModelArrayList.get(position);

        holder.Experiences_Company.setText(experiencesModel.getCompany_Name());
        holder.Experiences_Website.setText(experiencesModel.getWebsite());
        holder.Experiences_City.setText(experiencesModel.getCity());
        holder.Experiences_Country.setText(experiencesModel.getCountry());
        holder.Experiences_Role.setText(experiencesModel.getRole());
        holder.Experiences_Start_At.setText(experiencesModel.getStartAt());
        holder.Experiences_End_At.setText(experiencesModel.getEndAt());
        holder.Experiences_Description.setText("      "+experiencesModel.getDescription().replaceAll("<b>","").replaceAll("</b>",""));


        imageGlideHelper.load_And_Cache_image(context,holder.Experiences_Dp,experiencesModel.getLogo());



        //adding animation to recycler view item on below line.
        setAnimation(holder.itemView, position);

        holder.CardLinearLayOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.Experiences_Website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.info(context,position+" ",Toasty.LENGTH_SHORT).show();

            }
        });

        holder.Experiences_Dp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /*Intent intent = new Intent(context, PhotoViewer.class);
               intent.putExtra("uri",blogModel.getThumbnail());
                TransformationCompat.startActivity(holder.Blog_Dp_Transformation, intent);*/
            }
        });
    }

    //animate on scroll
    private void setAnimation(View itemView, int position) {
        if (position > lastPos) {
            //on below line we are setting animation.
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lastPos = position;
        }
    }

    @Override
    public int getItemCount() {
        return ExperiencesModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //creating variable for our image view and text view on below line.
        private final ImageView Experiences_Dp;
        private final LinearLayout CardLinearLayOut;
        private final TextView Experiences_Company;
        private final TextView Experiences_Website;
        private final TextView Experiences_Country;
        private final TextView Experiences_City;
        private final TextView Experiences_Role;
        private final TextView Experiences_Description;
        private  final TextView Experiences_Start_At;
        private  final TextView Experiences_End_At;
        private final TransformationLayout Experiences_Dp_Transformation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //initializing all our variables on below line.
            Experiences_Dp = itemView.findViewById(R.id.Experiences_Thumbnail);
            Experiences_Company = itemView.findViewById(R.id.Experince_Company_Name);
            Experiences_Website = itemView.findViewById(R.id.Experiences_Website);
            Experiences_Country = itemView.findViewById(R.id.Experiences_Country);
            Experiences_City = itemView.findViewById(R.id.Experiences_City);
            Experiences_Role = itemView.findViewById(R.id.Experiences_Role);
            Experiences_Description = itemView.findViewById(R.id.Experiences_Description);
            Experiences_Start_At = itemView.findViewById(R.id.Experiences_StartAt);
            Experiences_End_At = itemView.findViewById(R.id.Experiences_EndAt);
            CardLinearLayOut=itemView.findViewById(R.id.idItemCardLinearlayout);
            Experiences_Dp_Transformation = itemView.findViewById(R.id.transformation_Layout_Experiences_Image);
        }
    }

    //creating a interface for on click
    public interface BlogClickInterface {
        void onBlogClick(int position);
    }
}
