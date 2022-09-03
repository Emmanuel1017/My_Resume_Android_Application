package com.emmanuel.emmanuelkorircv.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.emmanuel.emmanuelkorircv.Adapters.MessegesAdapter;
import com.emmanuel.emmanuelkorircv.Model.ContactsModel;
import com.emmanuel.emmanuelkorircv.R;
import com.emmanuel.emmanuelkorircv.Utility.Shared_Preferences;
import com.emmanuel.emmanuelkorircv.Utility.Snackbar.SnackBarHelper;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.skydoves.powermenu.PowerMenu;

import java.util.ArrayList;
import java.util.Collections;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdminMyMessegesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdminMyMessegesFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;


    private View root;

    private FloatingActionButton addCourseFAB;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private RecyclerView RecyclerViewContacts;
    private FirebaseAuth mAuth;
    private ProgressBar loadingPB;
    private ArrayList<ContactsModel> contactsModelArrayList;
    private MessegesAdapter contactsAdapter;
    private ImageView Back,Menu;
    private int Position_Clicked;
    private PowerMenu powerMenu;
    private Intent intent,intent2;
    private final int CODE =1;
    private final String callPhoneNumber ="";
    private RelativeLayout relativeLayout;
    private ValueEventListener valueEventListener;
    private android.content.SharedPreferences sharedPreferences;
    int position = 0;
    private LinearLayout homeRL;

    public AdminMyMessegesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyMesseges.
     */
    public static AdminMyMessegesFragment newInstance(String param1, String param2) {
        AdminMyMessegesFragment fragment = new AdminMyMessegesFragment();
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
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_admin_my_messeges, container, false);
        Init(root);
        getContacts();
        return root;
    }

    private void getContacts() {
        //on below line clearing our list.
        contactsModelArrayList.clear();
        //to sort by date based on timestamp
        Query query = databaseReference.orderByChild("TimeStamp");
        //on below line we are calling add child event listener method to read the data.3
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //on below line we are hiding our progress bar.

                //adding snapshot to our array list on below line.
                try {
                    contactsModelArrayList.add(snapshot.getValue(ContactsModel.class));

                }catch (Exception e)
                {
                    Toast.makeText(getContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }

                Collections.reverse(contactsModelArrayList);
                RecyclerViewContacts.scrollToPosition(contactsModelArrayList.size() - 1);
                //notifying our adapter that data has changed.

                contactsAdapter.notifyDataSetChanged();
                //scroll to top  item
                loadingPB.setVisibility(View.GONE);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //this method is called when new child is added we are notifying our adapter and making progress bar visibility as gone.

                String id = snapshot.getValue(ContactsModel.class).getContactId();
                for(int i =0; i<contactsModelArrayList.size(); i++)
                {
                    if(contactsModelArrayList.get(i).getContactId().equals(id))
                    {
                        contactsModelArrayList.set(i,snapshot.getValue(ContactsModel.class));
                    }
                }
                loadingPB.setVisibility(View.GONE);
               // RecyclerViewContacts.scrollToPosition(contactsModelArrayList.size() - 1);
                contactsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                //notifying our adapter when child is removed.


                contactsAdapter.notifyDataSetChanged();
               // RecyclerViewContacts.scrollToPosition(contactsModelArrayList.size() - 1);
                loadingPB.setVisibility(View.GONE);

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //notifying our adapter when child is moved.

                contactsAdapter.notifyDataSetChanged();
                //RecyclerViewContacts.scrollToPosition(contactsModelArrayList.size() - 1);
                loadingPB.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void Init(View root)
    {
        RecyclerViewContacts = root.findViewById(R.id.Recycler_Contacts_Messeges);
        loadingPB = root.findViewById(R.id.idPBLoading);
        firebaseDatabase = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        contactsModelArrayList = new ArrayList<>();
        //on below line we are getting database reference.
        databaseReference = firebaseDatabase.getReference("contacts-list");
        databaseReference.keepSynced(true);
        sharedPreferences= requireContext().getSharedPreferences(Shared_Preferences.SHARED_PREF, Context.MODE_PRIVATE);


        homeRL = root.findViewById(R.id.idRLBSheet);

        //on below line initializing our adapter class.
        contactsAdapter = new MessegesAdapter(contactsModelArrayList, requireContext(), this::onContactClick);
        //setting layout malinger to recycler view on below line.  LinearLayoutManager.VERTICAL,true to set new items be added at index 0 inverse layout
        RecyclerViewContacts.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        //setting adapter to recycler view on below line.
        RecyclerViewContacts.setAdapter(contactsAdapter);
        //on below line calling a method to fetch courses from database.
    }

    public void onContactClick(int position) {
        //calling a method to display a bottom sheet on below line.
        displayBottomSheet(contactsModelArrayList.get(position));
        Position_Clicked = contactsModelArrayList.indexOf(contactsModelArrayList.get(position));
        //Toast.makeText(MainActivity.this,position, Toast.LENGTH_LONG).show();
        // Log.e("position", String.valueOf(position));
        Log.e("position", String.valueOf(Position_Clicked));

    }



    @SuppressLint("UseCompatLoadingForDrawables")
    private void displayBottomSheet(ContactsModel model) {
        //on below line we are creating our bottom sheet dialog.
        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext(), R.style.BottomSheetDialogTheme);
        //on below line we are inflating our layout file for our bottom sheet.
        View layout = LayoutInflater.from(getContext()).inflate(R.layout.bottom_sheet_layout, homeRL);
        //setting content view for bottom sheet on below line.
        bottomSheetDialog.setContentView(layout);
        //on below line we are setting a cancelable
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setCanceledOnTouchOutside(true);
        //calling a method to display our bottom sheet.
        bottomSheetDialog.show();
        //on below line we are creating variables for our text view and image view inside bottom sheet
        //and initialing them with their ids.
        TextView Name = layout.findViewById(R.id.idName);
        TextView Email = layout.findViewById(R.id.idEmail);
        TextView Date = layout.findViewById(R.id.idDate);
        TextView Message = layout.findViewById(R.id.idMessage);
        ImageView Logo = layout.findViewById(R.id.idlogo);
        ImageView EmailContact =layout.findViewById(R.id.Emailcontact);

        TextView From = layout.findViewById(R.id.RequestForm);
        //on below line we are setting data to different views on below line.
        Name.setText(model.getName());
        Email.setText(model.getEmail());
        Date.setText(model.getDate());
        Message.setText(model.getMessage());
        From.append(" " + model.getName());


        Logo.setImageDrawable(getContext().getResources().getDrawable(R.drawable.user_icons_7));

        //Picasso.get().load(modal.getCourseImg()).into(Logo);
        Button DeleteContact = layout.findViewById(R.id.delete_contact);

        // Toast.makeText(getApplicationContext(),String.valueOf(position),Toast.LENGTH_SHORT).show();




        EmailContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    Uri data = Uri.parse("mailto:"
                            + model.getEmail()
                            + "?subject=" + "Re: Thans for reaching out"  + "&body=" + "Thanks for contacting me.  I shall get back to you soon.\nThank You.\n\n Regards Emmanuel3 ");
                    intent.setData(data);
                    startActivity(intent);
                } catch (android.content.ActivityNotFoundException e) {
                    Snackbar snack = Snackbar.make(bottomSheetDialog.getWindow().getDecorView().findViewById(android.R.id.content),"No Email Apllication Installed!",      Snackbar.LENGTH_LONG);
                    SnackBarHelper.configSnackbar(requireContext().getApplicationContext(), snack);
                    snack.show();
                }


            }
        });




        //adding click listener for our view button on below line.
        DeleteContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //on below line calling a method to delete the course.
                new SweetAlertDialog(requireContext(), SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Delete?")
                        .setContentText("Are you Sure!")
                        .setConfirmText("Yes!")
                        .setCancelText("Cancel")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @SuppressLint("NotifyDataSetChanged")
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismiss();
                                try {
                                    firebaseDatabase.getReference("contacts-list").child(model.getContactId()).removeValue();
                                    String id = model.getContactId();
                                    int position = -1;
                                    for (int i = 0; i < contactsModelArrayList.size(); i++) {
                                        if (contactsModelArrayList.get(i).getContactId().equals(id)) {
                                            position = i;
                                            // break;  // uncomment to get the first instance
                                        }
                                    }
                                    if(Position_Clicked==position)
                                    {
                                        contactsModelArrayList.remove(Position_Clicked);
                                    }
                                    contactsAdapter.notifyDataSetChanged();
                                }
                                catch (Exception e)
                                {
                                    Log.e("Delete item error","Error");
                                }
                                //displaying a toast message on below line.
                                //Toast.makeText(MainActivity.this, "Deleted..", Toast.LENGTH_SHORT).show();
                                Snackbar snack = Snackbar.make(requireActivity().findViewById(android.R.id.content),"Deleted",Snackbar.LENGTH_SHORT);
                                SnackBarHelper.configSnackbar(requireContext(), snack);
                                snack.show();
                                bottomSheetDialog.dismiss();
                            }
                        })
                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                            }
                        })
                        .show();
            }
        });

    }
}