package com.pc.gridimagesearch.activities;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.pc.gridimagesearch.R;
import com.pc.gridimagesearch.adapters.ImageResultsAdapter;
import com.pc.gridimagesearch.models.ImageRequestFilters;
import com.pc.gridimagesearch.models.ImageResult;
import com.pc.gridimagesearch.models.ImageSize;
import com.pc.gridimagesearch.models.ImageType;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.Set;

import static com.pc.gridimagesearch.models.ImageSize.SMALL;


public class SearchActivity extends ActionBarActivity {

    //private EditText etQuery;
    private GridView gvResults;
    private static ArrayList<ImageResult> imageResults;
    private static ImageResultsAdapter aImageResults;
    private static String queryTerm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Set a ToolBar to replace the ActionBar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupViews();
        imageResults = new ArrayList<ImageResult>();
        aImageResults = new ImageResultsAdapter(this, imageResults);
        gvResults.setAdapter(aImageResults);
        queryTerm="";
    }

    private void setupViews() {

//        etQuery = (EditText) findViewById(R.id.etQuery);
        gvResults = (GridView) findViewById(R.id.gvResults);

        gvResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Launch the image display activity

                //Create an intent
                Intent i = new Intent(SearchActivity.this, ImageDisplayActivity.class);
                //get the image results to display
                ImageResult result = imageResults.get(position);
                //pass the image results into intent
                i.putExtra("url", result.getFullUrl());
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        /*getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;*/

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                queryTerm = query;
                // perform query here
                DoImageSearch();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onFilterAction(MenuItem mi) {
        // handle click here

        //Toast.makeText(this, "Filters button clicked", Toast.LENGTH_SHORT).show();
        FragmentManager fm = getSupportFragmentManager();
        FiltersDialog editNameDialog = FiltersDialog.newInstance("Advanced Filters");
        editNameDialog.show(fm, "fragment_filters");
    }

      public static void DoImageSearch(){
        AsyncHttpClient client = new AsyncHttpClient();

          String searchUrl = getSearchUrl(queryTerm);
          Log.d("ERROR", searchUrl);

          // https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=android&rsz=1


        client.get(searchUrl, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //Log.d("DEBUG", response.toString());
                try {
                    JSONArray imageResultsJson = response.getJSONObject("responseData").getJSONArray("results");
                    imageResults.clear();
                    //imageResults.addAll(ImageResult.fromJsonArray(imageResultsJson));
                    //When you make changes to the adapter, it does modify the underlying data
                    aImageResults.addAll(ImageResult.fromJsonArray(imageResultsJson));
                } catch(JSONException e){
                    e.printStackTrace();
                }

            }
        });

        //Toast.makeText(this, "search for " + query, Toast.LENGTH_SHORT).show();

    }

    private static String getSearchUrl(String query) {

        StringBuilder searchUrl = new StringBuilder("https://ajax.googleapis.com/ajax/services/search/images?v=1.0&q=");
        searchUrl.append(query);
        searchUrl.append("&rsz=8");
        String color = ImageRequestFilters.getColor();
        if( color  != null && !TextUtils.isEmpty(color)) {
            searchUrl.append("&imgcolor=");
            searchUrl.append(color);
        }
        String site = ImageRequestFilters.getSite();
        if ( site != null && !TextUtils.isEmpty(site)) {
            searchUrl.append("&as_sitesearch=");
            searchUrl.append(site);
        }
        ImageSize size = ImageRequestFilters.getSize();
        if(size != null){
            switch (size)
            {
                case ICON:
                    searchUrl.append("&imgsz=icon");
                    break;
                case SMALL:
                    searchUrl.append("&imgsz=small");
                    break;
                case MEDIUM:
                    searchUrl.append("&imgsz=medium");
                    break;
                case LARGE:
                    searchUrl.append("&imgsz=large");
                    break;
                case EXTRA_LARGE:
                    searchUrl.append("&imgsz=xlarge");
                    break;
                case EXTRA_EXTRA_LARGE:
                    searchUrl.append("&imgsz=xxlarge");
                    break;
                case HUGE:
                    searchUrl.append("&imgsz=huge");
                    break;
                case NONE:
                default:
                    break;
            }
        }

        ImageType type = ImageRequestFilters.getType();
        if(type!= null) {
            switch (type) {
                case FACE:
                    searchUrl.append("&imgtype=face");
                    break;

                case PHOTO:
                    searchUrl.append("&imgtype=photo ");
                    break;
                case CLIPART:
                    searchUrl.append("&imgtype=clipart ");
                    break;
                case LINEART:
                    searchUrl.append("&imgtype=lineart");
                    break;
                case NONE:
                default:
                    break;
            }
        }
        return searchUrl.toString();
    }

    public void onFilterSave(View view) {

        SearchActivity.DoImageSearch();

    }

}
