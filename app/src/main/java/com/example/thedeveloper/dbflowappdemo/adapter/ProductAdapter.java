package com.example.thedeveloper.dbflowappdemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.thedeveloper.dbflowappdemo.R;
import com.example.thedeveloper.dbflowappdemo.entitys.Products;

import java.util.ArrayList;

/**
 * Created by The Developer on 5/23/2017.
 */

public class ProductAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<Products> productsArrayList;


    public ProductAdapter(Context context, ArrayList<Products> productsArrayList) {
        this.context = context;
        this.productsArrayList = productsArrayList;
    }

    @Override
    public int getCount() {
        return productsArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return productsArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        if(convertView == null){

            convertView = View.inflate(context , R.layout.product_list_layout,null);
        }

        TextView names = (TextView) convertView.findViewById(R.id.productNametextView);
        TextView price = (TextView) convertView.findViewById(R.id.productPricetextView);
        ImageView icons = (ImageView) convertView.findViewById(R.id.iconImageView);

        Products products = productsArrayList.get(position);

        names.setText(products.getProductName());
        price.setText("$ " + products.getProductPrice());

        String alphabet = String.valueOf(products.getProductName().toUpperCase().charAt(0));

        ColorGenerator generator = ColorGenerator.MATERIAL;

        TextDrawable drawable = TextDrawable.builder()
                .beginConfig()
                .width(60)  // width in px
                .height(60) // height in px
                .endConfig()
                .buildRect(alphabet, generator.getRandomColor());

        icons.setImageDrawable(drawable);

        return convertView;
    }
}
