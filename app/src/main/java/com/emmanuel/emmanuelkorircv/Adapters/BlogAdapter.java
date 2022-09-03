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
import com.emmanuel.emmanuelkorircv.Model.BlogModel;
import com.emmanuel.emmanuelkorircv.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.skydoves.transformationlayout.TransformationLayout;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.ViewHolder> {
    //creating variables for our list, context, interface and position.
    private final ArrayList<BlogModel> blogModelArrayList;
    private final Context context;
    int lastPos = -1;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    private final ImageGlideHelper imageGlideHelper = new ImageGlideHelper();

    //creating a constructor.
    public BlogAdapter(ArrayList<BlogModel> blogModelArrayList, Context context) {
        this.blogModelArrayList = blogModelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating our layout file on below line.
        View view = LayoutInflater.from(context).inflate(R.layout.blog_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //setting data to our recycler view item on below line.
        BlogModel blogModel = blogModelArrayList.get(position);

        holder.Blog_Title.setText(blogModel.getTitle());
        holder.Blog_Description.setText(blogModel.getDescription());
        holder.Blog_Date.setText( blogModel.getDate());


        imageGlideHelper.load_And_Cache_image(context,holder.Blog_Dp,blogModel.getThumbnail());



        //adding animation to recycler view item on below line.
        setAnimation(holder.itemView, position);
        holder.CardLinearLayOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.Blog_More.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.info(context,position+" ",Toasty.LENGTH_SHORT).show();

            }
        });

        holder.Blog_Dp.setOnClickListener(new View.OnClickListener() {
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
        return blogModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //creating variable for our image view and text view on below line.
        private final ImageView Blog_Dp;
        private final LinearLayout CardLinearLayOut;
        private final TextView Blog_Title;
        private final TextView Blog_Description;
        private final TextView Blog_Date;
        private  final TextView Blog_More;
        private final TransformationLayout Blog_Dp_Transformation;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //initializing all our variables on below line.
            Blog_Dp = itemView.findViewById(R.id.Blog_Thumbnail);
            Blog_Title= itemView.findViewById(R.id.Blog_Title);
            Blog_Description = itemView.findViewById(R.id.Blog_Description);
            CardLinearLayOut=itemView.findViewById(R.id.idItemCardLinearlayout);
            Blog_Date = itemView.findViewById(R.id.Blog_Date);
            Blog_More = itemView.findViewById(R.id.Read_More);
            Blog_Dp_Transformation = itemView.findViewById(R.id.transformation_Layout_Blog_Image);
        }
    }

    //creating a interface for on click
    public interface BlogClickInterface {
        void onBlogClick(int position);
    }
}
