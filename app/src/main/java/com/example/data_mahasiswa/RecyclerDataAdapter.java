package com.example.data_mahasiswa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerDataAdapter extends RecyclerView.Adapter<RecyclerDataAdapter.UserViewHolder> {

    Context context;
    OnUserClickListener listener;

    List<Person> listPersonInfo;

    public RecyclerDataAdapter(Context context, List<Person> listPersonInfo, OnUserClickListener listener) {
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
        holder.txtNomor.setText(currentPerson.getNomor() + "");
        holder.txtNama.setText(currentPerson.getNama());
        holder.txtTgl.setText(currentPerson.getTanggal_lahir());
        holder.txtJenkel.setText(currentPerson.getJenis_kelamin());
        holder.txtAlamat.setText(currentPerson.getAlamat());
        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnUserClickListner(currentPerson, "Edit");
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.OnUserClickListner(currentPerson, "Delete");
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPersonInfo.size();
    }

    public interface OnUserClickListener {
        void OnUserClickListner(Person currentPerson, String action);
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView txtNomor, txtNama, txtTgl, txtJenkel, txtAlamat;
        Button btnDelete, btnEdit;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNomor = itemView.findViewById(R.id.txtNo);
            txtNama = itemView.findViewById(R.id.txtNama);
            txtTgl = itemView.findViewById(R.id.txtTgl);
            txtJenkel = itemView.findViewById(R.id.txtJenkel);
            txtAlamat = itemView.findViewById(R.id.txtAlamat);
//            btnEdit = itemView.findViewById(R.id.btnEdit);
//            btnDelete = itemView.findViewById(R.id.btnDelete);

        }
    }
}
