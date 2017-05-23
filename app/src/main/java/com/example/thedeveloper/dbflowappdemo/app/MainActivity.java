package com.example.thedeveloper.dbflowappdemo.app;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.thedeveloper.dbflowappdemo.R;
import com.example.thedeveloper.dbflowappdemo.adapter.ProductAdapter;
import com.example.thedeveloper.dbflowappdemo.entitys.Products;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {

    private Toolbar toolbar;
    private Products products;
    private ArrayList<Products> productsArrayList;
    //private ArrayList<Products> productses;
    private ListView listView;
    private ProductAdapter adapter;
    private List<Products> productsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Product List");
        toolbar.inflateMenu(R.menu.main_menu);
        toolbar.setOnMenuItemClickListener(this);

        listView = (ListView) findViewById(R.id.my_list);
        productsArrayList = new ArrayList<>();
        productsList = new Select().from(Products.class).queryList();

        for (int i = 0; i < productsList.size(); i++) {
            products = productsList.get(i);
            productsArrayList.add(products);
        }

        adapter = new ProductAdapter(MainActivity.this, productsArrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                products = productsArrayList.get(position);

                final Dialog dialog = new Dialog(MainActivity.this, R.style.DialogTheme);
                dialog.setTitle("Edit Products");
                dialog.setCanceledOnTouchOutside(true);
                dialog.setContentView(R.layout.edit_product_layout);
                final EditText name = (EditText) dialog.findViewById(R.id.updatenameEditText);
                final EditText price = (EditText) dialog.findViewById(R.id.updatepriceEditText);
                Button editBtn = (Button) dialog.findViewById(R.id.editButton);

                name.setText(products.getProductName());
                price.setText("" + products.getProductPrice());

                editBtn.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {


                        products.setProductName(name.getText().toString());
                        products.setProductPrice(Double.parseDouble(price.getText().toString()));
                        products.save();
                        productsArrayList.remove(products);
                        productsArrayList.add(products);
                        listView.setAdapter(adapter);
                        TastyToast.makeText(getApplicationContext(), "Product Edit Success !" + products.getId(), TastyToast.LENGTH_LONG, TastyToast.INFO);
                        adapter.notifyDataSetChanged();
                        dialog.hide();

                    }
                });

                dialog.show();
            }
        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                products = productsArrayList.get(position);
                products.delete();
                productsArrayList.remove(position);
                TastyToast.makeText(MainActivity.this,"Delete Product Successfully",TastyToast.LENGTH_LONG,TastyToast.WARNING);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
               return true;
            }
        });



    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {


        switch (item.getItemId()) {

            case R.id.add_menu:


                final Dialog dialog = new Dialog(MainActivity.this, R.style.DialogTheme);
                dialog.setTitle("Add Product");
                dialog.setCanceledOnTouchOutside(true);
                dialog.setContentView(R.layout.add_product_layout);

                final EditText name = (EditText) dialog.findViewById(R.id.nameEditText);
                final EditText price = (EditText) dialog.findViewById(R.id.priceEditText);
                Button addBtn = (Button) dialog.findViewById(R.id.addButton);

                addBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String product_name = name.getText().toString();
                        String product_price = price.getText().toString();


                        if (product_name.equals("")) {

                            Toast.makeText(MainActivity.this, "require name", Toast.LENGTH_SHORT).show();

                            return;
                        }
                        if (product_price.equals("")) {

                            Toast.makeText(MainActivity.this, "require price", Toast.LENGTH_SHORT).show();

                            return;
                        }

                        products = new Products();
                        products.setProductName(product_name);
                        products.setProductPrice(Double.parseDouble(product_price));
                        products.save();
                        productsArrayList.add(products);
                        Toast.makeText(MainActivity.this, "insert data into database", Toast.LENGTH_SHORT).show();
                        listView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        dialog.hide();
                    }
                });
                dialog.show();
                break;


        }

        return true;
    }
}
