package kz.cource.jihcforum.ui.chat;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.recyclerview.widget.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import kz.cource.jihcforum.ChatActivity;
import kz.cource.jihcforum.R;
import kz.cource.jihcforum.ui.adapter.ChatAdapter;
import kz.cource.jihcforum.ui.model.Chat;

public class ChatFragment extends Fragment {
    private List<Chat> chatList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        chatList = getChatList();

        RecyclerView chatRecyclerView = view.findViewById(R.id.chatRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        chatRecyclerView.setLayoutManager(layoutManager);
        ChatAdapter adapter = new ChatAdapter(chatList);
        chatRecyclerView.setAdapter(adapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                chatRecyclerView.getContext(),
                layoutManager.getOrientation()
        );
        chatRecyclerView.addItemDecoration(dividerItemDecoration);

        adapter.setOnItemClickListener(new ChatAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // Переход к активности чата с выбранным человеком
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                intent.putExtra("name", chatList.get(position).getName());
                startActivity(intent);
            }
        });

        return view;
    }

    private List<Chat> getChatList() {
        List<Chat> chatList = new ArrayList<>();
        chatList.add(new Chat("Madina Kauhanova", "Привет!"));
        chatList.add(new Chat("Sofiya Kamalova", "Привееет!"));
        chatList.add(new Chat("Zhaniya Sabit", "Что делаешь?"));
        chatList.add(new Chat("Malika Mynzhasar", "отправила"));
        chatList.add(new Chat("Aliya Bekbosyn", "Ок"));
        chatList.add(new Chat("Uldana Adilbek", "хорошо"));
        chatList.add(new Chat("Bolat Akbota", "Просто добавь"));


        return chatList;
    }
}
