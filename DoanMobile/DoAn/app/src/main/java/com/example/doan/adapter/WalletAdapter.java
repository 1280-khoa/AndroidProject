package com.example.doan.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.doan.Interface.ImageClickListenner;
import com.example.doan.R;
import com.example.doan.model.ChuyenXePhoBien;
import com.example.doan.model.EventBus.TinhTongEvent;
import com.example.doan.model.Wallet;
import com.example.doan.utils.Utils;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;
import java.util.List;

public class WalletAdapter extends RecyclerView.Adapter<WalletAdapter.MyViewHolder> {
    Context context;
    List<Wallet> walletList;



    public WalletAdapter(Context context, List<Wallet> walletList) {
        this.context = context;
        this.walletList = walletList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wallet, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Wallet wallet = walletList.get(position);

        holder.item_wallet_tenxe.setText(wallet.getTenXe());
        holder.item_wallet_soluong.setText(wallet.getSoluong() +" ");
//        holder.txtloai.setText((wallet.getLoai()));
//        holder.txtdiadiem.setText(wallet.getDiaDiem());
//        holder.txtgio.setText(wallet.getGioGiac());
        Glide.with(context).load(wallet.getHinhanh()).into(holder.item_wallet_image);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.item_wallet_gia.setText(decimalFormat.format((wallet.getGiaTien())));
        long gia = wallet.getSoluong() * wallet.getGiaTien();
        holder.item_wallet_giasp2.setText(decimalFormat.format(gia));
        holder.setListenner(new ImageClickListenner() {
            @Override
            public void onImageClick(View view, int pos, int giatri) {
                Log.d("TAG", "onImageClick: "+ pos  + " ..."+giatri);
                if (giatri == 1){
                    if (walletList.get(pos).getSoluong() > 1){
                        int soluongmoi = walletList.get(pos).getSoluong()-1;
                        walletList.get(pos).setSoluong(soluongmoi);

                        holder.item_wallet_soluong.setText(walletList.get(pos).getSoluong() +" ");
                        long gia = walletList.get(pos).getSoluong() * walletList.get(pos).getGiaTien();
                        holder.item_wallet_giasp2.setText(decimalFormat.format(gia));
                        EventBus.getDefault().postSticky(new TinhTongEvent());
                    }else if(walletList.get(pos).getSoluong() == 1){
                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
                        builder.setTitle("Thông báo");
                        builder.setMessage("Bạn có chắc muốn xóa sản phẩm?");
                        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Utils.manggiohang.remove(pos);
                                notifyDataSetChanged();
                                EventBus.getDefault().postSticky(new TinhTongEvent());
                            }
                        });
                        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        builder.show();
                    }
                }else if (giatri == 2){
                    if (walletList.get(pos).getSoluong() < 11){
                        int soluongmoi = walletList.get(pos).getSoluong()+1;
                        walletList.get(pos).setSoluong(soluongmoi);
                    }
                    holder.item_wallet_soluong.setText(walletList.get(pos).getSoluong() +" ");
                    long gia = walletList.get(pos).getSoluong() * walletList.get(pos).getGiaTien();
                    holder.item_wallet_giasp2.setText(decimalFormat.format(gia));
                    EventBus.getDefault().postSticky(new TinhTongEvent());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return walletList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView item_wallet_image, imgtru, imgcong;
        TextView item_wallet_tenxe, item_wallet_gia, item_wallet_soluong, item_wallet_giasp2,txtloai,txtdiadiem,txtgio;
        ImageClickListenner listenner;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            item_wallet_image = itemView.findViewById(R.id.item_giohang_image);
            item_wallet_tenxe = itemView.findViewById(R.id.item_giohang_tensp);
            item_wallet_gia = itemView.findViewById(R.id.item_giohang_gia);
            item_wallet_soluong = itemView.findViewById(R.id.item_giohang_soluong);
            item_wallet_giasp2 = itemView.findViewById(R.id.item_giohang_giasp2);
//            txtloai = itemView.findViewById(R.id.wallet_loai);
//            txtgio = itemView.findViewById(R.id.wallet_Gio);
//            txtdiadiem = itemView.findViewById(R.id.wallet_diadiem);
            imgtru = itemView.findViewById(R.id.item_giohang_tru);
            imgcong = itemView.findViewById(R.id.item_giohang_cong);
            imgcong.setOnClickListener(this);
            imgtru.setOnClickListener(this);

        }
        public void setListenner(ImageClickListenner listenner) {
            this.listenner = listenner;
        }

        @Override
        public void onClick(View v) {
            if (v == imgtru){
                listenner.onImageClick(v, getAdapterPosition(), 1);
                //1 tru
            }else if (v == imgcong)
                //2 cong
                listenner.onImageClick(v, getAdapterPosition(), 2);
        }

    }

}
