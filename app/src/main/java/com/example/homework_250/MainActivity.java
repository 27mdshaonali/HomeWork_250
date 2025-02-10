package com.example.homework_250;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
//    HashMap<String, String> hashMap = new HashMap<>();
    ListView listView;
//    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initialize();

        MyAdapter myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);

        populateListView();

    }

    public void initialize() {

        listView = findViewById(R.id.listView);
//        textView = findViewById(R.id.ServerResponse);

    }

    public void populateListView() {

        HashMap<String, String> input1 = new HashMap<>();
        input1.put("title", "Gutting USAID Will Have a Monumental Effect on Combating Climate Change");
        input1.put("description", "The agency was a key player in renewable energy and disaster protection around the world—until Elon Musk showed up.");
        input1.put("jsonUrl", "http://192.168.0.103/Class%20250/PHP_1.php");
        input1.put("image", "http://192.168.0.103/Class%20250/Images/USAID.jpeg");
        arrayList.add(input1);

        HashMap<String, String> input2 = new HashMap<>();
        input2.put("title", "Trump to announce 25% aluminium and steel tariffs as China’s levies against US come into effect");
        input2.put("description", "US president accused of ‘shifting goalposts’ by premier of Ontario for adding further tariffs on top of existing metal duties, as China’s fossil fuel levies come into force");
        input2.put("jsonUrl", "http://192.168.0.103/Class%20250/PHP_2.php");
        input2.put("image", "http://192.168.0.103/Class%20250/Images/Trump.jpg");
        arrayList.add(input2);

        HashMap<String, String> input3 = new HashMap<>();
        input3.put("title", "India slammed in new US report on religious freedom");
        input3.put("description", "Religious Freedom Report provides blistering account of violence perpetuated against Muslims and Christians in India");
        input3.put("jsonUrl", "http://192.168.0.103/Class%20250/PHP_3.php");
        input3.put("image", "http://192.168.0.103/Class%20250/Images/India.jpg");
        arrayList.add(input3);

        HashMap<String, String> input4 = new HashMap<>();
        input4.put("title", "ShAoN");
        input4.put("description", "Description 1");
        input4.put("jsonUrl", "http://192.168.0.103/Class%20250/PHP_4.php");
        arrayList.add(input4);

    }


    public class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            @SuppressLint("ViewHolder") View myView = layoutInflater.inflate(R.layout.item_layout, viewGroup, false);

            TextView itemTV = myView.findViewById(R.id.itemTitle);
            TextView itemTV2 = myView.findViewById(R.id.itemDescription);
            LinearLayout itemLayout = myView.findViewById(R.id.itemLayout);
            ImageView itemIV = myView.findViewById(R.id.itemImage);


            String titleItem = arrayList.get(i).get("title");
            String descriptionItem = arrayList.get(i).get("description");
            String jsonUrl = arrayList.get(i).get("jsonUrl");
            String imageUrl = arrayList.get(i).get("image");

            Picasso.get().load(imageUrl).placeholder(R.drawable.shaon).into(itemIV);

            itemTV.setText(titleItem);
            itemTV2.setText(descriptionItem);

            itemLayout.setOnClickListener(v -> {

                Bitmap bitmapCoverImage = ((BitmapDrawable) itemIV.getDrawable()).getBitmap();

                DataResponse.JSON_URL = jsonUrl;
                DataResponse.TITLE = titleItem;
                DataResponse.IMAGE_BITMAP = bitmapCoverImage;

                startActivity(new Intent(MainActivity.this, DataResponse.class));

//                Toast.makeText(MainActivity.this, titleItem + " has been Clicked", Toast.LENGTH_SHORT).show();

            });

            return myView;
        }
    }


}