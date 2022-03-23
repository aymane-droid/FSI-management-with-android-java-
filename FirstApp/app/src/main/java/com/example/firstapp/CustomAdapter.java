package com.example.firstapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter  extends BaseAdapter {

    private LayoutInflater flater;
    private ArrayList<Professeur> list;
    private int listItemLayoutResource;
    private int textViewItemNameId;
    private int textViewItemPercentId;


    // Arguments example:
    //  @listItemLayoutResource: R.layout.spinner_item_layout_resource
    //        (File: layout/spinner_item_layout_resource.xmll)
    //  @textViewItemNameId: R.id.textView_item_name
    //        (A TextVew in file layout/spinner_item_layout_resource.xmlxml)
    //  @textViewItemPercentId: R.id.textView_item_percent
    //        (A TextVew in file layout/spinner_item_layout_resource.xmll)

    public CustomAdapter(Activity context, int listItemLayoutResource,
                         int textViewItemNameId, int textViewItemPercentId,
                         ArrayList<Professeur> list) {
        this.listItemLayoutResource = listItemLayoutResource;

        this.textViewItemNameId = textViewItemNameId;
        this.textViewItemPercentId = textViewItemPercentId;
        this.list = list;
        this.flater = context.getLayoutInflater();
    }

    public CustomAdapter(int listItemLayoutResource,
                         int textViewItemNameId, int textViewItemPercentId,
                         ArrayList<Professeur> list) {
        this.listItemLayoutResource = listItemLayoutResource;

        this.textViewItemNameId = textViewItemNameId;
        this.textViewItemPercentId = textViewItemPercentId;
        this.list = list;
    }

    public int getListItemLayoutResource() {
        return listItemLayoutResource;
    }

    public void setListItemLayoutResource(int listItemLayoutResource) {
        this.listItemLayoutResource = listItemLayoutResource;
    }

    public int getTextViewItemNameId() {
        return textViewItemNameId;
    }

    public void setTextViewItemNameId(int textViewItemNameId) {
        this.textViewItemNameId = textViewItemNameId;
    }

    public int getTextViewItemPercentId() {
        return textViewItemPercentId;
    }

    public void setTextViewItemPercentId(int textViewItemPercentId) {
        this.textViewItemPercentId = textViewItemPercentId;
    }

    @Override
    public int getCount() {
        if(this.list == null)  {
            return 0;
        }
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public long getItemId(int position) {
        Professeur language = (Professeur) this.getItem(position);
        //int id = language.getCIN();
        //return language.getCIN();
         return position; //(Return position if you need).
    }

    public String getCIN_P(int position){
        Professeur language = (Professeur) getItem(position);

        View rowView = this.flater.inflate(this.listItemLayoutResource, null,true);

        TextView textViewItemPercent = (TextView) rowView.findViewById(textViewItemPercentId);
        textViewItemPercent.setText(language.getCIN());

        return language.getCIN();

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Professeur language = (Professeur) getItem(position);

        // Example: @listItemLayoutResource: R.layout.spinner_item_layout_resource
        // (File: layout/spinner_item_layout_resourcerce.xml)
        View rowView = this.flater.inflate(this.listItemLayoutResource, null,true);

        // Example: @textViewItemNameId: R.id.textView_item_name
        // (A TextView in file layout/spinner_item_layout_resourcerce.xml)
        TextView textViewItemName = (TextView) rowView.findViewById(this.textViewItemNameId);
        textViewItemName.setText("Nom :"+language.getNom());

        // Example: @textViewItemPercentId: R.id.textView_item_percent
        // (A TextView in file layout/spinner_item_layout_resource.xmlxml)
        TextView textViewItemPercent = (TextView) rowView.findViewById(textViewItemPercentId);
        textViewItemPercent.setText("CIN : "+language.getCIN());

        return rowView;
    }
}
