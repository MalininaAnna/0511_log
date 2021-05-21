package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("SYSTEM INFO:", "Метор onCreate() запущен");
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        Users users = new Users();
        List<String> userList = users.getUserList();
        userAdapter = new UserAdapter(userList);
        recyclerView.setAdapter(userAdapter);
    }
    // UserHolder отвечает за каждый элемнет списка по отдельности
    // Именно здесь мы будем наполнять нашу activity_item контентом

    private class UserHolder extends RecyclerView.ViewHolder {
        TextView itemTextView;
        public UserHolder(LayoutInflater inflater, ViewGroup viewGroup) {
            super(inflater.inflate(R.layout.activity_item, viewGroup,false));
            itemTextView = itemView.findViewById(R.id.itemTextView);
        }
        public void bind(String userName){

            itemTextView.setText(userName);
        }
    }
    // UserAdapter нужен для того, чтобы разместить элементы на RecyclerView
    private class UserAdapter extends RecyclerView.Adapter<UserHolder>{
        List<String> userList;
        public UserAdapter(List<String> userList){
            this.userList = userList;
        }

        @Override
        public UserHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            // LayoutInflater - наполнитель
            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
            return new UserHolder(inflater,viewGroup);
        }

        @Override
        public void onBindViewHolder(UserHolder userHolder, int position) {
            String userName = userList.get(position);
            userHolder.bind(userName);
        }

        @Override // Этот метод возвращает количество элементов, доступных в данный момент в адаптере
        public int getItemCount() {
            return userList.size();
            Log.d("SYSTEM INFO:", "Метод getItemCount() запущен");
        }
    }
    @Override
    public void onStart(){
        super.onStart();
        Log.d("SYSTEM INFO:", "Метор onStart() запущен");
    }
    @Override
    public void onResume(){
        super.onResume();
        Log.d("SYSTEM INFO:", "Метор onResume() запущен");
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.d("SYSTEM INFO:", "onSaveInstanceState() запущен");
    }
    @Override
    public void onPause(){
        super.onPause();
        Log.d("SYSTEM INFO:", "Метор onPause() запущен");
    }
    @Override
    public void onStop(){
        super.onStop();
        Log.d("SYSTEM INFO:", "Метор onStop() запущен");
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d("SYSTEM INFO:", "Метор onDestroy() запущен");
    }
}