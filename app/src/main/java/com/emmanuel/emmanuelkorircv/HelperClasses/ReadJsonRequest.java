package com.emmanuel.emmanuelkorircv.HelperClasses;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.emmanuel.emmanuelkorircv.Activities.MyAboutAdmin;
import com.emmanuel.emmanuelkorircv.Activities.MyBlogAdmin;
import com.emmanuel.emmanuelkorircv.Activities.MyExperiencesAdmin;
import com.emmanuel.emmanuelkorircv.Model.BlogModel;
import com.emmanuel.emmanuelkorircv.Model.ExperiencesModel;
import com.emmanuel.emmanuelkorircv.Utility.AppConstants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ReadJsonRequest {

    private static final String TAG = "web request";
    private Context context;
    private String Url;

    private final MyBlogAdmin myBlogAdmin = new MyBlogAdmin();
    private final MyAboutAdmin myAboutAdmin = new MyAboutAdmin();
    private final MyExperiencesAdmin myExperiencesAdmin = new MyExperiencesAdmin();

    private String Tag ;


    private final ArrayList<BlogModel> Blog_List = new ArrayList<>();
    private final ArrayList<ExperiencesModel> Experiences_List = new ArrayList<>();

    public void Get_Json_File(Context context, String Url, String Tag) {
        this.Url = Url;
        this.context = context;
        this.Tag = Tag;

        new Execute().execute();
    }


    private class Execute extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
          //  Toast.makeText(context, "Json Data is downloading", Toast.LENGTH_LONG).show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = Url;
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONArray jsonArr = new JSONArray(jsonStr);

                    /*----------------------------------------------------------Blog------------------------------------------------------------------------*/

                    if(Tag.equals(AppConstants.my_blog))
                    {
                // looping through All Blogs

                        for (int i = 0; i < jsonArr.length(); i++) {
                            JSONObject c = jsonArr.getJSONObject(i);
                            JSONArray internationalizations = c.getJSONArray("internationalizations");
                            String thumbnail = c.getString("thumbnail");
                            String http = c.getString("http");
                            String date = c.getString("date");
                            String language ="";
                            String title ="";
                            String description = "";
                            //loop nested array
                            for (int x = 0; x < internationalizations.length(); x++) {
                                JSONObject internalizationsobj = internationalizations.getJSONObject(x);
                                language = internalizationsobj.getString("language");
                                title = internalizationsobj.getString("title");
                                description = internalizationsobj.getString("description");

                            }

                            Blog_List.add(new BlogModel(thumbnail,http,date,language,title,description));

                        }

                    }
      /*-----------------------------------------------------------------------------------------------------------------------------------------------------------*/




                    /*----------------------------------------------------------Experiences------------------------------------------------------------------------*/

                    if(Tag.equals(AppConstants.my_experiences))
                    {
                        // looping through All Blogs

                        for (int i = 0; i < jsonArr.length(); i++) {
                            JSONObject c = jsonArr.getJSONObject(i);
                            JSONArray internationalizations = c.getJSONArray("internationalizations");
                            JSONArray technologies = c.getJSONArray("technologies");
                            JSONArray medias = c.getJSONArray("medias");

                            String id =  c.getString("id");
                            String position = c.getString("position");
                            String start_at = c.getString("startAt");
                            String end_at = c.getString("endAt");
                            String company_name =  c.getString("companyName");
                            String website =  c.getString("website");
                            String logo =  c.getString("logo");
                            String language ="";
                            String city ="";
                            String country ="";
                            String role ="";
                            String description = "";

                            //loop nested array
                            for (int x = 0; x < internationalizations.length(); x++) {
                                JSONObject internalizationsobj = internationalizations.getJSONObject(x);
                                language = internalizationsobj.getString("language");
                                city = internalizationsobj.getString("city");
                                country = internalizationsobj.getString("country");
                                role = internalizationsobj.getString("role");
                                description = internalizationsobj.getString("description");

                            }

                            //loop nested array
                            String[] Technologies = new String[technologies.length()];
                            for (int x = 0; x < technologies.length(); x++) {
                                Technologies[i] = technologies.getString(i);
                            }


                            String[]  icon = new String[medias.length()];
                            String[] title = new String[medias.length()];
                            String[] http = new String[medias.length()];
                            //loop nested array
                            for (int x = 0; x < medias.length(); x++) {
                                JSONObject media = medias.getJSONObject(x);
                                icon[x] = media.getString("icon");
                                title[x] = media.getString("title");
                                http[x] = media.getString("http");
                            }



                            Experiences_List.add(new ExperiencesModel(id,position,start_at,end_at,company_name,website,logo,language,city,country,role,description,Technologies,icon,title,http));

                        }

                    }
                    /*-----------------------------------------------------------------------------------------------------------------------------------------*/

                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    ((Activity) context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "Json parsing error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                ((Activity) context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "Couldn't get json from server. Check LogCat for possible errors!", Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if(Tag.equals(AppConstants.my_blog)) {
                myBlogAdmin.Json_Populate(Blog_List,context);
            }

            if(Tag.equals(AppConstants.my_about)) {
                myBlogAdmin.Json_Populate(Blog_List,context);
            }

            if(Tag.equals(AppConstants.my_experiences)) {
                myExperiencesAdmin.Json_Populate(Experiences_List,context);
            }
        }
    }
}


