package com.example.doan.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.icu.text.CompactDecimalFormat;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.example.doan.R;
import com.example.doan.adapter.PhuongTrangAdapter;
import com.example.doan.model.ChuyenXePhoBien;
import com.example.doan.model.ChuyenXePhoBienModel;
import com.example.doan.retrofit.ApiNhaXe;
import com.example.doan.retrofit.RetrofitClient;
import com.example.doan.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PhuongTrangActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    ApiNhaXe apiNhaXe;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    int page =1;
    int MaHangXe;
    PhuongTrangAdapter adapterPT;
    List<ChuyenXePhoBien> chuyenXePhoBienList;
    LinearLayoutManager linearLayoutManager;
    Handler handler = new Handler();
    boolean isLoading = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phuong_trang2);
        apiNhaXe = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiNhaXe.class);
        MaHangXe = getIntent().getIntExtra("MaHangXe", 1);
        Anhxa();
        ActionToolBar();
        getData(page);
        addEventLoad();

    }

    private void addEventLoad() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(isLoading == false){
                    if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == chuyenXePhoBienList.size()-1){
                        isLoading = true;
                        loadMore();
                    }
                }
            }
        });
    }
    private void loadMore() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                //add null
                chuyenXePhoBienList.add(null);
                adapterPT.notifyItemInserted(chuyenXePhoBienList.size()-1);
            }
        });
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //remover null
                chuyenXePhoBienList.remove(chuyenXePhoBienList.size()-1);
                adapterPT.notifyItemRemoved(chuyenXePhoBienList.size());
                page = page+1;
                getData(page);
                adapterPT.notifyDataSetChanged();
                isLoading = false;

            }
        },22);
    }

    private void getData(int page) {
        compositeDisposable.add(apiNhaXe.getChuyenXe(page, MaHangXe)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        chuyenXePhoBienModel -> {
                            if (chuyenXePhoBienModel.isSuccess()) {
                                if (adapterPT == null) {
                                    chuyenXePhoBienList = chuyenXePhoBienModel.getResult();
                                    adapterPT = new PhuongTrangAdapter(getApplicationContext(), chuyenXePhoBienList);
                                    recyclerView.setAdapter(adapterPT);
                                } else {
                                    int vitri = chuyenXePhoBienList.size() - 1;
                                    int soluongadd = chuyenXePhoBienModel.getResult().size();
                                    for (int i = 0; i < soluongadd; i++) {
                                        chuyenXePhoBienList.add(chuyenXePhoBienModel.getResult().get(i));
                                    }
                                    adapterPT.notifyItemRangeInserted(vitri, soluongadd);
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Hết dữ liệu", Toast.LENGTH_LONG).show();
                                isLoading = true;
                            }
                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(), "Không kết nối server", Toast.LENGTH_LONG).show();
                        }
                ));
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

    private void Anhxa() {
        toolbar = findViewById(R.id.toobar);
        recyclerView = findViewById(R.id.recyclerview_pt);
        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        chuyenXePhoBienList = new ArrayList<>();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}