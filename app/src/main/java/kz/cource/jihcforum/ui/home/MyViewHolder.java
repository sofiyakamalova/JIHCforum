package kz.cource.jihcforum.ui.home;

import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.BreakIterator;

import kz.cource.jihcforum.R;
public class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView, postImageView;
    TextView nameView, emailView, dateView;


    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageview);
        nameView = itemView.findViewById(R.id.name);
        emailView = itemView.findViewById(R.id.email);
        postImageView = itemView.findViewById(R.id.postImage);
        dateView = itemView.findViewById(R.id.date);
    }
}