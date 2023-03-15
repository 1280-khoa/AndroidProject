package com.example.doan.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.doan.R;
import com.example.doan.model.ChuyenXePhoBien;
import com.example.doan.model.Wallet;
import com.example.doan.utils.Utils;
import com.nex3z.notificationbadge.NotificationBadge;

import java.text.DecimalFormat;

public class ChiTietActivity extends AppCompatActivity {

    TextView tenxe,giaxe,loai,diadiem,gio;
    Spinner spinner;
    Button btnthem;
    ImageView hinhanh;
    Toolbar toolbar;
    NotificationBadge badge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet);
        initView();
        ActionToolBar();
        initData();
        initConTrol();
    }

    private void initConTrol() {
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themGioHang();
            }
        });
    }

    private void themGioHang() {
        ChuyenXePhoBien chuyenXePhoBien = (ChuyenXePhoBien) getIntent().getSerializableExtra("chitiet");
        if (Utils.manggiohang.size() > 0){
            boolean flag = false;
            int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
            for (int i = 0 ; i < Utils.manggiohang.size(); i++){
                if (Utils.manggiohang.get(i).getMaxe() ==chuyenXePhoBien.getMaxe()){
                    Utils.manggiohang.get(i).setSoluong(soluong + Utils.manggiohang.get(i).getSoluong());
                    long gia = chuyenXePhoBien.getGiaTien() * Utils.manggiohang.get(i).getSoluong();
                    Utils.manggiohang.get(i).setGiaTien(gia);
                    flag = true;
                }
            }
            if (flag == false){
                long gia = chuyenXePhoBien.getGiaTien() * soluong;
                Wallet gioHang = new Wallet();
                gioHang.setGiaTien(gia);
                gioHang.setSoluong(soluong);
                gioHang.setMaxe(chuyenXePhoBien.getMaxe());
                gioHang.setTenXe(chuyenXePhoBien.getTenXe());
                gioHang.setHinhanh(chuyenXePhoBien.getHinhanh());
                Utils.manggiohang.add(gioHang);
            }

        }else {
            int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
            long gia = chuyenXePhoBien.getGiaTien() * soluong;
            Wallet gioHang = new Wallet();
            gioHang.setGiaTien(gia);
            gioHang.setSoluong(soluong);
            gioHang.setMaxe(chuyenXePhoBien.getMaxe());
            gioHang.setTenXe(chuyenXePhoBien.getTenXe());
            gioHang.setHinhanh(chuyenXePhoBien.getHinhanh());
            Utils.manggiohang.add(gioHang);
        }
        int totalItem = 0;
        for (int i = 0; i < Utils.manggiohang.size(); i++) {
            totalItem = totalItem + Utils.manggiohang.get(i).getSoluong();
        }
        badge.setText(String.valueOf(totalItem));
    }


    private void initData() {
        ChuyenXePhoBien chuyenXePhoBien = (ChuyenXePhoBien) getIntent().getSerializableExtra("chitiet");
        tenxe.setText(chuyenXePhoBien.getTenXe());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        giaxe.setText("Giá: "+decimalFormat.format(chuyenXePhoBien.getGiaTien())+ " VNĐ" );
        diadiem.setText(chuyenXePhoBien.getDiaDiem());
        gio.setText(chuyenXePhoBien.getGioGiac());
        loai.setText(chuyenXePhoBien.getLoai());
        Glide.with(getApplicationContext()).load(chuyenXePhoBien.getHinhanh()).into(hinhanh);
        Integer[] so = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> adapterspin = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,so);
        spinner.setAdapter(adapterspin);
    }
    private void initView() {
        tenxe=findViewById(R.id.itemct_ten);
        giaxe=findViewById(R.id.itemct_gia);
        loai=findViewById(R.id.itemct_loai);
        diadiem=findViewById(R.id.itemct_diadiem);
        gio=findViewById(R.id.itemct_Gio);
        btnthem = findViewById(R.id.btnthemvaogiohang);
        hinhanh = findViewById(R.id.imgchitiet);
        spinner = findViewById(R.id.spinner);
        toolbar = findViewById(R.id.toobar);
        badge = findViewById(R.id.menu_sl);
        FrameLayout frameLayoutwallet = findViewById(R.id.framegiohang);
        frameLayoutwallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent giohang = new Intent(getApplicationContext(), WalletActivity.class);
                startActivity(giohang);
            }
        });
        if (Utils.manggiohang != null) {
            int totalItem = 0;
            for (int i = 0; i < Utils.manggiohang.size(); i++) {
                totalItem = totalItem + Utils.manggiohang.get(i).getSoluong();
            }
            badge.setText(String.valueOf(totalItem));
        }

    }
    private void ActionToolBar() {
        //setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Utils.manggiohang != null) {
            int totalItem = 0;
            for (int i = 0; i < Utils.manggiohang.size(); i++) {
                totalItem = totalItem + Utils.manggiohang.get(i).getSoluong();
            }
            badge.setText(String.valueOf(totalItem));
        }
    }
}