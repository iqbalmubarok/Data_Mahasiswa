package com.example.data_mahasiswa;

import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerDataAdapter extends RecyclerView.Adapter<RecyclerDataAdapter.UserViewHolder> {

    Context context;
    OnUserActionListener listener;

    List<Person> listPersonInfo;

    public interface OnUserActionListener{
        void onUserAction(Person person);
    }

    public RecyclerDataAdapter(Context context, List<Person> listPersonInfo, OnUserActionListener listener) {
        this.context = context;
        this.listPersonInfo = listPersonInfo;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerDataAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_user_row_item, parent, false);
        UserViewHolder userViewHolder = new UserViewHolder(view);

        return userViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerDataAdapter.UserViewHolder holder, final int position) {
        final Person currentPerson = listPersonInfo.get(position);
        holder.txtNama.setText(currentPerson.getNama());
        Log.d("sdsd", currentPerson.getNama());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onUserAction(currentPerson);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPersonInfo.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView txtNama;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNama = itemView.findViewById(R.id.txtNamaRow);
        }
    }
}
