package com.example.doan.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.doan.Interface.ItemClickListener;
import com.example.doan.R;
import com.example.doan.activity.ChiTietActivity;
import com.example.doan.model.ChuyenXePhoBien;

import java.text.DecimalFormat;
import java.util.List;

public class PhuongTrangAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context context;
    List<ChuyenXePhoBien>array;
    private static final int View_TYPE_DATA = 0;
    private static final int View_TYPE_LOADING = 1;

    public PhuongTrangAdapter(Context context, List<ChuyenXePhoBien> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType ==View_TYPE_DATA){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phuongtrang,parent,false);
            return new MyViewHolder(view);
        }else  {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }
    }




    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MyViewHolder){
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            ChuyenXePhoBien chuyenXePhoBien = array.get(position);
            myViewHolder.tenxe.setText(chuyenXePhoBien.getTenXe());
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            myViewHolder.giaxe.setText("Giá: "+decimalFormat.format(chuyenXePhoBien.getGiaTien())+ " VNĐ" );
            myViewHolder.diadiem.setText(chuyenXePhoBien.getDiaDiem());
            myViewHolder.gio.setText(chuyenXePhoBien.getGioGiac());
            myViewHolder.txtngay.setText(chuyenXePhoBien.getNgaiDi());
            myViewHolder.loai.setText(chuyenXePhoBien.getLoai());
            Glide.with(context).load(chuyenXePhoBien.getHinhanh()).into(myViewHolder.hinhanh);
            myViewHolder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int pos, boolean isLongClick) {
                    if (!isLongClick){
                        //click
                        Intent intent = new Intent(context, ChiTietActivity.class);
                        intent.putExtra("chitiet",chuyenXePhoBien);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                }
            });
        }else {
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return array.get(position) ==null ? View_TYPE_LOADING:View_TYPE_DATA;
    }

    @Override
    public int getItemCount() {
        return array.size();
    }
    public class LoadingViewHolder extends RecyclerView.ViewHolder{
        ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressbar);
        }
    }

    public class MyViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tenxe, giaxe, diadiem,gio,loai,txtngay;
        ImageView hinhanh;
        private ItemClickListener itemClickListener;
        public MyViewHolder (@NonNull View itemView){
            super(itemView);
            tenxe = itemView.findViewById(R.id.itempt_ten);
            diadiem = itemView.findViewById(R.id.itempt_diadiem);
            gio = itemView.findViewById(R.id.itempt_Gio);
            loai = itemView.findViewById(R.id.itempt_loai);
            giaxe = itemView.findViewById(R.id.itempt_gia);
            hinhanh = itemView.findViewById(R.id.itempt_image);
           txtngay = itemView.findViewById(R.id.itempt_Ngay);
            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), false);
        }
    }
}

