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
import com.example.doan.model.Item;

import java.util.List;

public class ChitietAdapter extends RecyclerView.Adapter<ChitietAdapter.MyViewHolder>{
    Context context;
    List<Item> itemList;


    public ChitietAdapter(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    public ChitietAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chititet,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.txtten.setText(item.getTenXe() + " ");
        holder.txtsoluong.setText("Số Lượng Vé : "+item.getIdsoluong() );
        holder.txtbiensoxe.setText("Biển Số Xe : "+item.getBienSoXe()+"");
        holder.txtdiadiem.setText("Địa Điểm : "+item.getDiaDiem()+"");
        holder.txtgiogiac.setText("Giờ : "+item.getGioGiac()+"");
        holder.txtngaydi.setText("Ngày : "+item.getNgayDi()+"");
        Glide.with(context).load(item.getHinhanh()).into(holder.imgchitiet);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgchitiet;
        TextView txtten, txtsoluong, txtgiogiac, txtdiadiem, txtbiensoxe, txtngaydi;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgchitiet = itemView.findViewById(R.id.item_imgchitiet);
            txtten = itemView.findViewById(R.id.item_tenhangxechitiet);
            txtsoluong = itemView.findViewById(R.id.item_soluongchitiet);
            txtbiensoxe = itemView.findViewById(R.id.item_BienSoXe);
            txtdiadiem = itemView.findViewById(R.id.item_diadiem);
            txtgiogiac = itemView.findViewById(R.id.item_giogiac);
            txtngaydi = itemView.findViewById(R.id.item_ngay);
        }
    }
}
