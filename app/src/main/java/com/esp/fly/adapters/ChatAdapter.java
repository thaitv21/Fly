package com.esp.fly.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.esp.fly.R;
import com.esp.fly.models.Message;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends BaseAdapter {


    private Context context;
    private List<Message> messages;

    public ChatAdapter(Context context, List<Message> messages) {
        this.context = context;
        this.messages = messages;
        if (this.messages == null) {
            this.messages = new ArrayList<>();
        }
    }

    public void addMessage(Message message) {
        this.messages.add(message);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Message getItem(int i) {
        return messages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Message message = messages.get(i);
        View convertView = null;
        TextView text;
        if (message.isFromUser()) {
            convertView = LayoutInflater.from(context).inflate(R.layout.right_chat_row, viewGroup, false);
            text = convertView.findViewById(R.id.message_text);
            text.setText(message.getContent());
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.left_chat_row, viewGroup, false);
            text = convertView.findViewById(R.id.message_text);
            text.setText(message.getContent());
        }
        return convertView;
    }
}
