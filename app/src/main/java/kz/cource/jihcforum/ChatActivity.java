package kz.cource.jihcforum;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ChatActivity extends AppCompatActivity {

    private LinearLayout chatLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        chatLayout = findViewById(R.id.chatLayout);

        String name = getIntent().getStringExtra("name");
        setActionBarTitle(name);

        // Пример загрузки сообщений
        loadChatMessages();

        ImageButton sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    private void setActionBarTitle(String title) {
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.action_bar_title, null);
        TextView titleTextView = view.findViewById(R.id.titleTextView);
        titleTextView.setText(title);

        getSupportActionBar().setCustomView(view);
    }

    private void loadChatMessages() {
        // Пример данных с сообщениями
        List<Message> messages = new ArrayList<>();
        messages.add(new Message("Madina Kauhanova", "Hello", new Date()));
        messages.add(new Message("You", "Hi, John!", new Date()));
        messages.add(new Message("Madina Kauhanova", "How are you?", new Date()));
        messages.add(new Message("You", "I'm good, thanks!", new Date()));
        messages.add(new Message("Madina Kauhanova", "That's great!", new Date()));
        messages.add(new Message("Madina Kauhanova", "By the way, how was your trip?", new Date()));
        messages.add(new Message("You", "It was amazing!", new Date()));
        messages.add(new Message("You", "I visited so many beautiful places.", new Date()));
        messages.add(new Message("Madina Kauhanova", "I'm glad to hear that.", new Date()));

        // Очищаем чат перед загрузкой новых сообщений
        chatLayout.removeAllViews();

        Date currentDate = null;

        for (Message message : messages) {
            if (!isSameDate(currentDate, message.getTimestamp())) {
                currentDate = message.getTimestamp();
                addDateSeparator(currentDate);
            }

            addMessage(message);
        }
    }

    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        return sdf.format(date);
    }

    private String formatTime(Date time) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return sdf.format(time);
    }

    private boolean isSameDate(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        return sdf.format(date1).equals(sdf.format(date2));
    }

    private void addDateSeparator(Date date) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View dateSeparatorView = inflater.inflate(R.layout.date_separator_item, chatLayout, false);
        TextView dateTextView = dateSeparatorView.findViewById(R.id.dateTextView);
        dateTextView.setText(formatDate(date));
        chatLayout.addView(dateSeparatorView);
    }

    private void addMessage(Message message) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View messageView = inflater.inflate(R.layout.message_item, chatLayout, false);
        TextView senderTextView = messageView.findViewById(R.id.senderTextView);
        TextView messageTextView = messageView.findViewById(R.id.messageTextView);
        TextView timestampTextView = messageView.findViewById(R.id.timestampTextView);

        // Скрыть отправителя
        senderTextView.setVisibility(View.GONE);

        messageTextView.setText(message.getMessage());
        timestampTextView.setText(formatTime(message.getTimestamp()));

        LinearLayout.LayoutParams layoutParams;
        if (message.getSender().equals("You")) {
            layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.gravity = Gravity.END;
            messageView.setBackgroundResource(R.drawable.your_message_background);
        } else {
            layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.gravity = Gravity.START;
            messageView.setBackgroundResource(R.drawable.other_message_background);
        }

        // Добавить отступ между сообщениями
        layoutParams.bottomMargin = 10;

        chatLayout.addView(messageView, layoutParams);
    }



    private class Message {
        private String sender;
        private String message;
        private Date timestamp;

        public Message(String sender, String message, Date timestamp) {
            this.sender = sender;
            this.message = message;
            this.timestamp = timestamp;
        }

        public String getSender() {
            return sender;
        }

        public String getMessage() {
            return message;
        }

        public Date getTimestamp() {
            return timestamp;
        }
    }

    private void sendMessage() {
        EditText messageEditText = findViewById(R.id.messageEditText);
        String message = messageEditText.getText().toString().trim();
        if (!message.isEmpty()) {
            Message newMessage = new Message("You", message, new Date());
            addMessage(newMessage);
            messageEditText.setText("");
        }
    }
}
