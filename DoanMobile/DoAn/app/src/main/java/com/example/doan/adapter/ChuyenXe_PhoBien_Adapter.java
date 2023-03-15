package com.example.doan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.doan.R;
import com.example.doan.model.ChuyenXePhoBien;

import java.text.DecimalFormat;
import java.util.List;

public class ChuyenXe_PhoBien_Adapter extends RecyclerView.Adapter<ChuyenXe_PhoBien_Adapter.MyViewHolder>{
    Context context;

    public ChuyenXe_PhoBien_Adapter(Context context, List<ChuyenXePhoBien> array) {
        this.context = context;
        this.array = array;
    }

    List<ChuyenXePhoBien> array;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_xe_moi, parent, false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ChuyenXePhoBien sanPhamMoi = array.get(position);
        holder.txtdiadiem.setText(sanPhamMoi.getDiaDiem());
        holder.txtGio.setText(sanPhamMoi.getGioGiac());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtgia.setText("Giá: "+decimalFormat.format(sanPhamMoi.getGiaTien())+ " VNĐ" );
        Glide.with(context).load(sanPhamMoi.getHinhanh()).into(holder.imghinhanh);
    }

    @Override
    public int getItemCount() {
        return array.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtgia,txtdiadiem,txtGio;
        ImageView imghinhanh;
        public MyViewHolder(@NonNull View itemview){
            super(itemview);
            txtGio = itemview.findViewById(R.id.itemGio_ten);
            txtgia =itemview.findViewById(R.id.itemxe_gia);
            txtdiadiem=itemview.findViewById(R.id.itemdia_diem);
            imghinhanh=itemview.findViewById(R.id.itemxe_image);
        }
    }
}
