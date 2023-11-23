package kz.cource.jihcforum.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import kz.cource.jihcforum.R;

public class HomeFragment extends Fragment {
    kz.cource.jihcforum.ui.home.MyAdapter myAdapter;
    private RecyclerView recyclerView;
    ImageButton buttonPlus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);


        List<Item> items = new ArrayList<Item>();
        items.add(new Item("Joni j", "joni.j@email.com", R.drawable.joni, R.drawable.romashka, getCurrentDate()));
        items.add(new Item("Nuka n", "nuka.n@email.com", R.drawable.nuko, R.drawable.default_background, getCurrentDate()));
        items.add(new Item("Sofi k", "sofi.k@email.com", R.drawable.sofi, R.drawable.img2, getCurrentDate()));
        items.add(new Item("Sabi s", "sabi.s@email.com", R.drawable.sabi, R.drawable.img3, getCurrentDate()));
        items.add(new Item("Madi h", "madi.h@email.com", R.drawable.madi, R.drawable.img4, getCurrentDate()));
        items.add(new Item("Anel d", "anel.d@email.com", R.drawable.anel, R.drawable.img5, getCurrentDate()));
        items.add(new Item("Joni j", "joni.j@email.com", R.drawable.joni, R.drawable.ing6, getCurrentDate()));
        items.add(new Item("Nuka n", "nuka.n@email.com", R.drawable.nuko, R.drawable.img1, getCurrentDate()));
        items.add(new Item("Sofi k", "sofi.k@email.com", R.drawable.sofi, R.drawable.img8, getCurrentDate()));
        items.add(new Item("Sabi s", "sabi.s@email.com", R.drawable.sabi, R.drawable.img9, getCurrentDate()));
        items.add(new Item("Madi h", "mona.h@email.com", R.drawable.madi, R.drawable.img1, getCurrentDate()));
        items.add(new Item("Anel d", "anel.d@email.com", R.drawable.anel, R.drawable.ing6, getCurrentDate()));
        items.add(new Item("Joni j", "joni.j@email.com", R.drawable.joni, R.drawable.sofimonkey, getCurrentDate()));
        items.add(new Item("Nuka n", "nuka.n@email.com", R.drawable.nuko, R.drawable.img3, getCurrentDate()));
        items.add(new Item("Sofi k", "sofi.k@email.com", R.drawable.sofi, R.drawable.img2, getCurrentDate()));
        items.add(new Item("Sabi s", "sabi.s@email.com", R.drawable.sabi, R.drawable.img5, getCurrentDate()));
        items.add(new Item("Madi h", "mona.h@email.com", R.drawable.madi, R.drawable.default_background, getCurrentDate()));
        items.add(new Item("Anel d", "anel.d@email.com", R.drawable.anel, R.drawable.img8, getCurrentDate()));
        myAdapter = new MyAdapter(items);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(myAdapter);
        recyclerView.setHasFixedSize(true);


        buttonPlus = view.findViewById(R.id.buttonPlus);
        buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                items.add(new Item("Madina Kauhanova", "madina.mkauhanova@email.com", R.drawable.maddikenn, R.drawable.img13, getCurrentDate()));
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
                myAdapter.notifyDataSetChanged();

            }
        });

        return view;
    }



    // Метод для получения текущей даты в формате "posted: dd MMM"
    private String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM", Locale.getDefault());
        return "posted: " + dateFormat.format(new Date());
    }

}





