package com.example.rekening.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rekening.R;
import com.example.rekening.activities.MainActivity;
import com.example.rekening.activities.UpdateActivity;
import com.example.rekening.db.DbHelper;
import com.example.rekening.model.Rekening;

import java.io.Serializable;
import java.util.ArrayList;

public class RekeningAdapter extends RecyclerView.Adapter<RekeningAdapter.RekeningViewHolder> {
    private final ArrayList<Rekening> listRekenings = new ArrayList<>();
    private final Activity activity;
    private final DbHelper dbHelper;

    public RekeningAdapter(Activity activity) {
        this.activity = activity;
        dbHelper = new DbHelper(activity);
    }

    public ArrayList<Rekening> getListRekenings() {
        return listRekenings;
    }

    public void setListRekenings(ArrayList<Rekening> listNotes) {
        if (listNotes.size() > 0) {
            this.listRekenings.clear();
        }{
            this.listRekenings.clear();
        }
        this.listRekenings.addAll(listNotes);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RekeningViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rekening, parent, false);
        return new RekeningViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RekeningViewHolder holder, int position) {
        holder.tvRekening.setText(listRekenings.get(position).getRekening());
        holder.tvName.setText(listRekenings.get(position).getName());
        holder.btnEdit.setOnClickListener((View v) -> {
            Intent intent = new Intent(activity, UpdateActivity.class);
            intent.putExtra("user", (Serializable) listRekenings.get(position));
            activity.startActivity(intent);
        });
        holder.btnDelete.setOnClickListener((View v) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle("Konfirmasi hapus");
            builder.setMessage("Apakah yakin akan dihapus?");
            builder.setPositiveButton("YA", (dialog, which) -> {

                dbHelper.deleteUser(listRekenings.get(position).getId());
                Toast.makeText(activity, "Hapus berhasil!", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(activity, MainActivity.class);
                activity.startActivity(myIntent);
                activity.finish();
            });
            builder.setNegativeButton("TIDAK", (dialog, which) -> dialog.dismiss());
            AlertDialog alert = builder.create();
            alert.show();
        });
    }

    @Override
    public int getItemCount() {
        return listRekenings.size();
    }

    public class RekeningViewHolder extends RecyclerView.ViewHolder {
        final TextView tvRekening, tvName;
        final Button btnEdit, btnDelete;

        public RekeningViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRekening = itemView.findViewById(R.id.tv_item_rekening);
            tvName = itemView.findViewById(R.id.tv_item_name);
            btnEdit = itemView.findViewById(R.id.btn_edit);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }
}