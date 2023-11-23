package kz.cource.jihcforum.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import kz.cource.jihcforum.R;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<Item> items;

    public MyAdapter(List<Item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Item item = items.get(position);
        holder.nameView.setText(item.getName());
        holder.emailView.setText(item.getEmail());
        holder.imageView.setImageResource(item.getImage());
        holder.postImageView.setImageResource(item.getPostImage());
        holder.dateView.setText(item.getDate());
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }
}
