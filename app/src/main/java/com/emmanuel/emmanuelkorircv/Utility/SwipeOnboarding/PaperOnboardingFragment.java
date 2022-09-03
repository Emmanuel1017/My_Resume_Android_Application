package com.emmanuel.emmanuelkorircv.Utility.SwipeOnboarding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.emmanuel.emmanuelkorircv.R;
import com.emmanuel.emmanuelkorircv.Utility.SwipeOnboarding.listeners.PaperOnboardingOnChangeListener;
import com.emmanuel.emmanuelkorircv.Utility.SwipeOnboarding.listeners.PaperOnboardingOnLeftOutListener;
import com.emmanuel.emmanuelkorircv.Utility.SwipeOnboarding.listeners.PaperOnboardingOnRightOutListener;

import java.util.ArrayList;

/**
 * Ready to use PaperOnboarding fragment
 */
public class PaperOnboardingFragment extends Fragment {

    private static final String ELEMENTS_PARAM = "elements";

    private PaperOnboardingOnChangeListener mOnChangeListener;
    private PaperOnboardingOnRightOutListener mOnRightOutListener;
    private PaperOnboardingOnLeftOutListener mOnLeftOutListener;
    private ArrayList<PaperOnboardingPage> mElements;


    public static PaperOnboardingFragment newInstance(ArrayList<PaperOnboardingPage> elements) {
        PaperOnboardingFragment fragment = new PaperOnboardingFragment();
        Bundle args = new Bundle();
        args.putSerializable(ELEMENTS_PARAM, elements);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mElements = (ArrayList<PaperOnboardingPage>) getArguments().get(ELEMENTS_PARAM);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.onboarding_main, container, false);


        // create engine for onboarding element
        PaperOnboardingEngine mPaperOnboardingEngine = new PaperOnboardingEngine(view.findViewById(R.id.onboardingRootView), mElements, getActivity().getApplicationContext());
        // set listeners

        mPaperOnboardingEngine.setOnChangeListener(mOnChangeListener);
        mPaperOnboardingEngine.setOnLeftOutListener(mOnLeftOutListener);
        mPaperOnboardingEngine.setOnRightOutListener(mOnRightOutListener);

        return view;

    }

    public void setElements(ArrayList<PaperOnboardingPage> elements) {
        this.mElements = elements;
    }

    public ArrayList<PaperOnboardingPage> getElements() {
        return mElements;
    }

    public void setOnChangeListener(PaperOnboardingOnChangeListener onChangeListener) {
        this.mOnChangeListener = onChangeListener;
    }

    public void setOnRightOutListener(PaperOnboardingOnRightOutListener onRightOutListener) {
        this.mOnRightOutListener = onRightOutListener;
    }

    public void setOnLeftOutListener(PaperOnboardingOnLeftOutListener onLeftOutListener) {
        this.mOnLeftOutListener = onLeftOutListener;
    }

}
