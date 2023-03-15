package com.example.doan.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.doan.R;
import com.example.doan.adapter.PhuongTrangAdapter;
import com.example.doan.model.ChuyenXePhoBien;
import com.example.doan.retrofit.ApiNhaXe;
import com.example.doan.retrofit.RetrofitClient;
import com.example.doan.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchActivity extends AppCompatActivity {
    Toolbar toolbar1;
    RecyclerView recyclerView;
    EditText edtSearch;
    PhuongTrangAdapter adapterPT;
    List<ChuyenXePhoBien> chuyenXePhoBienList;
    ApiNhaXe apiNhaXe;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        ActionToolBar();
    }

    private void initView() {
        chuyenXePhoBienList = new ArrayList<>();
        apiNhaXe = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiNhaXe.class);
        edtSearch = findViewById(R.id.editsearch);
        toolbar1 = findViewById(R.id.too2bar);
        recyclerView = findViewById(R.id.recyclerview_search);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
               if(s.length()==0){
                   chuyenXePhoBienList.clear();
                   adapterPT = new PhuongTrangAdapter(getApplicationContext(),chuyenXePhoBienList);
                   recyclerView.setAdapter(adapterPT);

               }else {
                   getDataSearch(s.toString());
               }

            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });


    }

    private void getDataSearch(String x) {
        chuyenXePhoBienList.clear();
        compositeDisposable.add(apiNhaXe.search(x)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                   chuyenXePhoBienModel -> {
                       if(chuyenXePhoBienModel.isSuccess()){
                           chuyenXePhoBienList = chuyenXePhoBienModel.getResult();
                           adapterPT = new PhuongTrangAdapter(getApplicationContext(),chuyenXePhoBienList);
                           recyclerView.setAdapter(adapterPT);
                       }

                   },throwable -> {
                            Toast.makeText(getApplicationContext(),throwable.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                ));
    }

    private void ActionToolBar() {
        //setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}