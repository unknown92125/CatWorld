package com.unknown.catworld;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        RecyclerView recyclerView = findViewById(R.id.rv_cat_list);
        CatListRecyclerAdapter catListRecyclerAdapter = new CatListRecyclerAdapter(arrListAllCats);
        recyclerView.setAdapter(catListRecyclerAdapter);

    }

    public class CatListRecyclerAdapter extends RecyclerView.Adapter {

        ArrayList<Cats> arrListAllCats;

        public CatListRecyclerAdapter(ArrayList<Cats> arrListAllCats) {
            this.arrListAllCats = arrListAllCats;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = getLayoutInflater();
            View itemView = inflater.inflate(R.layout.cat_list_recycler_view, parent, false);

            VHolder vHolder = new VHolder(itemView);

            return vHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

            VHolder vHolder = (VHolder) holder;
            Cats cats = arrListAllCats.get(position);

            vHolder.tvName.setText(cats.getCatName());
            vHolder.tvAt.setText(cats.getIsAt());
            vHolder.ivCat.setImageResource(cats.getIdle()[0]);

        }

        @Override
        public int getItemCount() {
            return arrListAllCats.size();
        }

        public class VHolder extends RecyclerView.ViewHolder {

            private TextView tvName, tvAt;
            private ImageView ivCat;

            public VHolder(@NonNull View itemView) {
                super(itemView);

                tvName = itemView.findViewById(R.id.tv_name);
                tvAt = itemView.findViewById(R.id.tv_at);
                ivCat = itemView.findViewById(R.id.iv_cat);

//                itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        int position = getLayoutPosition();
//                        selectedUID = arrListUID.get(position);
//                        Log.e("MCF:", selectedUID + "");
//                        startActivity(new Intent(getActivity(), ChatActivity.class));
//                    }
//                });
            }
        }
    }

}
