package com.esp.fly.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.esp.fly.R;
import com.esp.fly.adapters.ChatAdapter;
import com.esp.fly.models.Message;
import com.esp.fly.models.User;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.text.DateFormat;
import java.util.Date;

public class ChatActivity extends AppCompatActivity {

    private static final String NEW_MESSAGE = "new_message";
    private static final String SEND_MESSAGE = "send_message";
    private static final String RECEIVER = "receiver";
    private static final String SENDER = "sender";
    private static final String MESSAGE = "content";
    private static final String TIME = "time";
    private static final String USER_READY = "user_ready";
    private static final String JOIN = "user_join";

    private EditText receiverEditText;
    private EditText messageEditText;
    private ListView listView;
    private ChatAdapter adapter;

    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://10.0.2.2:3000");
        } catch (URISyntaxException e) {}
    }


    private User user = User.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initViews();
        initSocketIO();

    }

    private void initSocketIO() {
        mSocket.on(USER_READY, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                mSocket.emit(JOIN, user.getId());
            }
        });

        mSocket.on(SEND_MESSAGE, new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                ChatActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject data = (JSONObject) args[0];
                            String receiver = data.getString(RECEIVER);
                            String sender = data.getString(SENDER);
                            String message = data.getString(MESSAGE);
                            String time = DateFormat.getDateTimeInstance().format(new Date());
                            if (data.has(TIME)) {
                                time = data.getString(TIME);
                            }
                            if (adapter != null) {
                                adapter.addMessage(new Message(message, time, false));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        mSocket.connect();

    }

    private void initViews() {
        listView = findViewById(R.id.chat_listview);
        messageEditText = findViewById(R.id.message_edt);
        adapter = new ChatAdapter(this, null);
        listView.setAdapter(adapter);
        receiverEditText = findViewById(R.id.receiver_edt);
    }

    public void onSendClick(View view) throws JSONException {
        String receiver = receiverEditText.getText().toString();
        String message = messageEditText.getText().toString();
        if (!message.equals("")) {
            String time = DateFormat.getDateTimeInstance().format(new Date());
            adapter.addMessage(new Message(message, time, true));
            listView.setSelection(listView.getCount() - 1);
            JSONObject msg = new JSONObject();
            msg.put(RECEIVER, receiver);
            msg.put(SENDER, user.getName());
            msg.put(MESSAGE, message);
            msg.put(TIME, time);
            mSocket.emit(NEW_MESSAGE, msg);
        }
    }


}
