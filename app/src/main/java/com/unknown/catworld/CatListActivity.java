package com.unknown.catworld;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import static com.unknown.catworld.MainActivity.arrListCats;

public class CatListActivity extends AppCompatActivity {

    private ArrayList<Cats> arrListAllCats = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_list);

        arrListAllCats.clear();
        for (int i = 0; i < arrListCats.size(); i++) {
            Cats cats = arrListCats.get(i);
            if (cats.isUnlocked) {
                arrListAllCats.add(cats);
            }
        }

        GridView gridView = findViewById(R.id.gv_cat_list);
        CatListGridAdapter catListGridAdapter = new CatListGridAdapter(arrListAllCats);
        gridView.setAdapter(catListGridAdapter);

    }

    public class CatListGridAdapter extends BaseAdapter {

        ArrayList<Cats> arrListAllCats;

        public CatListGridAdapter(ArrayList<Cats> arrListAllCats) {
            this.arrListAllCats = arrListAllCats;
        }

        @Override
        public int getCount() {
            return arrListAllCats.size();
        }

        @Override
        public Object getItem(int position) {
            return arrListAllCats.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                LayoutInflater inflater = getLayoutInflater();
                convertView = inflater.inflate(R.layout.cat_list_grid_view, null);
                convertView.setLayoutParams(new GridView.LayoutParams(300, 300));
            }

            Cats cats = arrListAllCats.get(position);

            TextView tvName = convertView.findViewById(R.id.tv_name);
            TextView tvAt = convertView.findViewById(R.id.tv_at);
            ImageView ivCat = convertView.findViewById(R.id.iv_cat);

            tvName.setText(cats.getCatName());
            tvAt.setText(cats.getIsAt());
            ivCat.setImageResource(cats.getIdle()[0]);

            convertView.setTag(position);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = (int) v.getTag();
                    if (arrListAllCats.get(pos).isAt.equals("room")) {
                        arrListAllCats.get(pos).isAt = "forest";
                    }else {
                        arrListAllCats.get(pos).isAt="room";
                    }
                }
            });

            return convertView;
        }


//        public class VHolder extends RecyclerView.ViewHolder {
//
//            private TextView tvName, tvAt;
//            private ImageView ivCat;
//
//            public VHolder(@NonNull View itemView) {
//                super(itemView);
//
//                tvName = itemView.findViewById(R.id.tv_name);
//                tvAt = itemView.findViewById(R.id.tv_at);
//                ivCat = itemView.findViewById(R.id.iv_cat);
//
//                itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        int position = getLayoutPosition();
//                        selectedUID = arrListUID.get(position);
//                        Log.e("MCF:", selectedUID + "");
//                        startActivity(new Intent(getActivity(), ChatActivity.class));
//                    }
//                });
//            }
//        }
    }

}
