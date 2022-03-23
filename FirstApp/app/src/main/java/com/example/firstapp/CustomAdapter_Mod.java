package com.example.firstapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter_Mod extends BaseAdapter {

    private LayoutInflater flater;
    private ArrayList<Module> list;
    private int listItemLayoutResource;
    private int textViewItemNameId;


    // Arguments example:
    //  @listItemLayoutResource: R.layout.spinner_item_layout_resource
    //        (File: layout/spinner_item_layout_resource.xmll)
    //  @textViewItemNameId: R.id.textView_item_name
    //        (A TextVew in file layout/spinner_item_layout_resource.xmlxml)
    //  @textViewItemPercentId: R.id.textView_item_percent
    //        (A TextVew in file layout/spinner_item_layout_resource.xmll)

    public CustomAdapter_Mod(Activity context, int listItemLayoutResource,
                              int textViewItemPercentId,
                             ArrayList<Module> list) {
        this.listItemLayoutResource = listItemLayoutResource;

        this.textViewItemNameId = textViewItemPercentId;
        this.list = list;
        this.flater = context.getLayoutInflater();
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

    public void setTextViewItemPercentId(int textViewItemNameId) {
        this.textViewItemNameId = textViewItemNameId;
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
        Module language = (Module) this.getItem(position);
        //int id = language.getCIN();
        //return language.getCIN();
        return position; //(Return position if you need).
    }

    public String getTitre(int position){
        Module language = (Module) getItem(position);

        View rowView = this.flater.inflate(this.listItemLayoutResource, null,true);

        TextView textViewItemName = (TextView) rowView.findViewById(textViewItemNameId);
        textViewItemName.setText(language.getTitre());

        return language.getTitre();

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Module language = (Module) getItem(position);

        // Example: @listItemLayoutResource: R.layout.spinner_item_layout_resource
        // (File: layout/spinner_item_layout_resourcerce.xml)
        View rowView = this.flater.inflate(this.listItemLayoutResource, null,true);

        // Example: @textViewItemNameId: R.id.textView_item_name
        // (A TextView in file layout/spinner_item_layout_resourcerce.xml)
        TextView textViewItemName = (TextView) rowView.findViewById(this.textViewItemNameId);
        textViewItemName.setText("Nom :"+language.getTitre());


        return rowView;
    }
}
