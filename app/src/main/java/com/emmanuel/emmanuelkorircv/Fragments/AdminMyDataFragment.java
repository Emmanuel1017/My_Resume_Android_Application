package com.emmanuel.emmanuelkorircv.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.emmanuel.emmanuelkorircv.Activities.MyAboutAdmin;
import com.emmanuel.emmanuelkorircv.Activities.MyBlogAdmin;
import com.emmanuel.emmanuelkorircv.Activities.MyExperiencesAdmin;
import com.emmanuel.emmanuelkorircv.HelperClasses.MyBounceInterpolator;
import com.emmanuel.emmanuelkorircv.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminMyDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminMyDataFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    private View root;

    private CardView Blog,Experiences,AboutMe;

    public AdminMyDataFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Data.
     */
    public static AdminMyDataFragment newInstance(String param1, String param2) {
        AdminMyDataFragment fragment = new AdminMyDataFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_admin_my_data, container, false);
        // Inflate the layout for this fragment

        Init(root);
        Experiences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Animation myAnim = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
                // Use bounce interpolator with amplitude 0.2 and frequency 20
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.001, 1);
                myAnim.setInterpolator(interpolator);
                Experiences.startAnimation(myAnim);

                Intent intent = new Intent(getContext(), MyExperiencesAdmin.class);
                startActivity(intent);
            }
        });

        Blog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Animation myAnim = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
                // Use bounce interpolator with amplitude 0.2 and frequency 20
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.001, 1);
                myAnim.setInterpolator(interpolator);
                Blog.startAnimation(myAnim);

                Intent intent = new Intent(getContext(), MyBlogAdmin.class);
                startActivity(intent);
            }
        });
        AboutMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final Animation myAnim = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
                // Use bounce interpolator with amplitude 0.2 and frequency 20
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.001, 1);
                myAnim.setInterpolator(interpolator);
                AboutMe.startAnimation(myAnim);

                Intent intent = new Intent(getContext(), MyAboutAdmin.class);
                startActivity(intent);
            }
        });

        return root;
    }

    public void Init(View root)
    {
        Experiences = root.findViewById(R.id.experiences_card);
        Blog = root.findViewById(R.id.blog_card);
        AboutMe = root.findViewById(R.id.about_card);
    }
}