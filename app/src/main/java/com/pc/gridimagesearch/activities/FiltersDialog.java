package com.pc.gridimagesearch.activities;

import android.content.res.Resources;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.pc.gridimagesearch.R;
import com.pc.gridimagesearch.models.ImageRequestFilters;
import com.pc.gridimagesearch.models.ImageSize;
import com.pc.gridimagesearch.models.ImageType;

import static android.widget.ArrayAdapter.*;


public class FiltersDialog extends DialogFragment {

    Spinner spImageSize;
    Spinner spImageType;
    EditText etImageColor;
    EditText etImageSite;

    ArrayAdapter<CharSequence> aspImageSize;
    ArrayAdapter<CharSequence> aspImageType;

    //Empty constructor required for DialogFragment
    public FiltersDialog() {

    }

    public static FiltersDialog newInstance(String title) {
        FiltersDialog frag = new FiltersDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_filters, container);
        //spImageSize = (Spinner) view.findViewById(R.id.spImageSize);
        String title = getArguments().getString("title", "Advanced Filters");
        getDialog().setTitle(title);
        Button btnSave = (Button) view.findViewById(R.id.btnSave);
        Resources res = getResources();
        spImageSize= (Spinner) view.findViewById(R.id.spImageSize);

        // Create an ArrayAdapter using the string array and a default spinner layout
        aspImageSize = createFromResource(this.getActivity(),
                R.array.size_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        aspImageSize.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spImageSize.setAdapter(aspImageSize);
        spImageSize.setSelection(aspImageSize.getPosition(ImageRequestFilters.getSize().getName()));

        spImageType= (Spinner) view.findViewById(R.id.spImageType);
        aspImageType = createFromResource(this.getActivity(),
                R.array.type_array, android.R.layout.simple_spinner_item);
        aspImageType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spImageType.setAdapter(aspImageType);
        spImageType.setSelection(aspImageType.getPosition(ImageRequestFilters.getType().getName()));

        etImageColor= (EditText) view.findViewById(R.id.etImageColor);
        String colorFilter = ImageRequestFilters.getColor();
        etImageColor.setText(colorFilter==null? "" : colorFilter);
        etImageSite= (EditText) view.findViewById(R.id.etImageSite);
        String siteFilter = ImageRequestFilters.getSite();
        etImageSite.setText(siteFilter==null? "" : siteFilter);

        btnSave.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           String size = (String) spImageSize.getSelectedItem();
                                           String type = (String) spImageType.getSelectedItem();
                                           String color = etImageColor.getText().toString();
                                           String site = etImageSite.getText().toString();

                                           if(size != null && !TextUtils.isEmpty(size)){
                                               switch (size.toLowerCase()){
                                                   case "small":
                                                       ImageRequestFilters.setSize(ImageSize.ICON);
                                                       break;
                                                   case "medium":
                                                       ImageRequestFilters.setSize(ImageSize.MEDIUM);
                                                       break;
                                                   case "large":
                                                       ImageRequestFilters.setSize(ImageSize.EXTRA_EXTRA_LARGE);
                                                       break;
                                                   case "extra large":
                                                       ImageRequestFilters.setSize(ImageSize.HUGE);
                                                       break;
                                               }
                                           }
                                           if(type != null && !TextUtils.isEmpty(type)){

                                               switch (type.toLowerCase()){
                                                   case "face":
                                                       ImageRequestFilters.setType(ImageType.FACE);
                                                       break;
                                                   case "photo":
                                                       ImageRequestFilters.setType(ImageType.PHOTO);
                                                       break;
                                                   case "clipart":
                                                       ImageRequestFilters.setType(ImageType.CLIPART);
                                                       break;
                                                   case "lineart":
                                                       ImageRequestFilters.setType(ImageType.LINEART);
                                                       break;
                                               }
                                           }
                                           if(color != null && !TextUtils.isEmpty(color)) {
                                                ImageRequestFilters.setColor(color);
                                           }
                                           if(site!=null && !TextUtils.isEmpty(site)){
                                               ImageRequestFilters.setSite(site);
                                           }
                                           SearchActivity.DoImageSearch();
                                           getDialog().dismiss();
                                       }
                                   }
        );

        Button btnClear = (Button) view.findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageRequestFilters.clearFilters();
                SearchActivity.DoImageSearch();
                getDialog().dismiss();

            }
        });

        return view;
    }

}
