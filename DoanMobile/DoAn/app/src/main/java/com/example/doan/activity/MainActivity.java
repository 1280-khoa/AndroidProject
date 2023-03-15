package com.example.doan.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.doan.R;
import com.example.doan.adapter.ChuyenXe_PhoBien_Adapter;
import com.example.doan.adapter.LoaiHangXeadapter;
import com.example.doan.model.ChuyenXePhoBien;
import com.example.doan.model.ChuyenXePhoBienModel;
import com.example.doan.model.LoaiHangXeModel;
import com.example.doan.model.LoaiHangxe;
import com.example.doan.retrofit.ApiNhaXe;
import com.example.doan.retrofit.RetrofitClient;
import com.example.doan.utils.Utils;
import com.google.android.material.navigation.NavigationView;
import com.nex3z.notificationbadge.NotificationBadge;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar1;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewManHinhChinh;
    NavigationView navigationView;
    ListView listViewManHinhChinh;
    DrawerLayout drawerLayout;
    LoaiHangXeadapter loaiHangXeadapter;
    List<LoaiHangxe> mangloaihangxe;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiNhaXe apiNhaXe;
    List<ChuyenXePhoBien> mangchuyenxephobien;
    ChuyenXe_PhoBien_Adapter chuyenXeadapter;
    NotificationBadge badge;
    FrameLayout frameLayout;
    ImageView imgsearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiNhaXe = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiNhaXe.class);
        Anhxa();
        ActionBar();
        if(isConnected(this)){
            ActionViewFlipper();
            getHangXes();
            getChuyenXePhoBien();
            getEventClick();
        }else {
            Toast.makeText(getApplicationContext(),"khong ket noi internet,vui long ket noi",Toast.LENGTH_LONG).show();
        }
    }

    private void getEventClick() {
       listViewManHinhChinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
            switch (i){
                case 0:
                    Intent trangchu = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(trangchu);
                    break;
                case 1:
                    Intent phuongtrang = new Intent(getApplicationContext(),PhuongTrangActivity.class);
                    phuongtrang.putExtra("MaHangXe", 1);
                    startActivity(phuongtrang);
                    break;
                case 2:
                    Intent hungcuong = new Intent(getApplicationContext(),PhuongTrangActivity.class);
                    hungcuong.putExtra("MaHangXe", 2);
                    startActivity(hungcuong);
                    break;
                case 4:
                    Intent donhang = new Intent(getApplicationContext(),XemDonHangActivity.class);
                    startActivity(donhang);
                    break;
            }
           }
       });

    }

    private void getChuyenXePhoBien() {
        compositeDisposable.add(apiNhaXe.getChuyenXePhoBien()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        chuyenXePhoBienModel -> {
                            if (chuyenXePhoBienModel.isSuccess()){
                                mangchuyenxephobien = chuyenXePhoBienModel.getResult();
                                chuyenXeadapter = new ChuyenXe_PhoBien_Adapter(getApplicationContext(), mangchuyenxephobien);
                                recyclerViewManHinhChinh.setAdapter(chuyenXeadapter);
                            }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(), "Không kết nối được server"+throwable.getMessage(), Toast.LENGTH_LONG).show();
                        }
                ));
    }

    private void getHangXes(){
        compositeDisposable.add(apiNhaXe.getHangXe()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        loaiHangXeModel -> {
                            if(loaiHangXeModel.isSuccess()){
                                mangloaihangxe = loaiHangXeModel.getResult();
                                loaiHangXeadapter = new LoaiHangXeadapter(getApplicationContext(),mangloaihangxe);
                                listViewManHinhChinh.setAdapter(loaiHangXeadapter);
                            }
                        }
                ));
    }
    private void ActionViewFlipper(){
        List<String> mangquangcao=new ArrayList<>();
        mangquangcao.add("https://ksdalat.com/wp-content/uploads/2019/08/nha-xe-phuong-trang-da-lat.jpg");
        mangquangcao.add("https://angiangtourism.vn/xe-khach-hung-cuong-an-giang/imager_5298.jpg");
        mangquangcao.add("https://carshop.vn/wp-content/uploads/2022/07/images1151040_xekhach.jpg");
        for(int i=0;i<mangquangcao.size();i++){
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(mangquangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(slide_in);
        viewFlipper.setOutAnimation(slide_out);
    }
    private void ActionBar(){
        // setSupportActionBar(toolbar1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar1.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
    private void Anhxa(){
        imgsearch = findViewById(R.id.imgsearch);
        toolbar1 = findViewById(R.id.toobarmanhinhchinh);
        viewFlipper = findViewById(R.id.viewlipper);
        recyclerViewManHinhChinh = findViewById(R.id.recycleview);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);
        recyclerViewManHinhChinh.setLayoutManager(layoutManager);
        recyclerViewManHinhChinh.setHasFixedSize(true);
        listViewManHinhChinh = findViewById(R.id.Listviewmanhinhchinh);
        navigationView=findViewById(R.id.navigationview);
        drawerLayout = findViewById(R.id.drawerlayout);
        badge = findViewById(R.id.menu_sl);
        frameLayout = findViewById(R.id.framegiohang);
        //khởi tạo list
        mangloaihangxe = new ArrayList<>();
        mangchuyenxephobien = new ArrayList<>();
        if (Utils.manggiohang == null){
            Utils.manggiohang = new ArrayList<>();
        }else {
            int totalItem = 0;
            for (int i = 0; i < Utils.manggiohang.size(); i++) {
                totalItem = totalItem + Utils.manggiohang.get(i).getSoluong();
            }
            badge.setText(String.valueOf(totalItem));
        }
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent giohang = new Intent(getApplicationContext(), WalletActivity.class);
                startActivity(giohang);
            }
        });

        imgsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SearchActivity.class);
                startActivity(intent);

            }
        });

    }

    private boolean isConnected (Context context){
        ConnectivityManager connectivityManager =(ConnectivityManager)  context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if((wifi !=null && wifi.isConnected())||(mobile!=null && mobile.isConnected())){
            return true;
        }else {
            return false;
        }
    }
    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}