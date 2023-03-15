package com.example.doan.retrofit;


import com.example.doan.model.ChuyenXePhoBienModel;
import com.example.doan.model.DonHangModel;
import com.example.doan.model.LoaiHangXeModel;
import com.example.doan.model.UserModel;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiNhaXe {
    //get data
    @GET("gethangxe.php")
    Observable<LoaiHangXeModel> getHangXe();

    @GET("getchuyenxephobien.php")
    Observable<ChuyenXePhoBienModel> getChuyenXePhoBien();


    //post data
    @POST("chitiet.php")
    @FormUrlEncoded
    Observable<ChuyenXePhoBienModel> getChuyenXe(
            @Field("page") int page,
            @Field("MaHangXe") int MaHangXe
    );

    @POST("dangki.php")
    @FormUrlEncoded
    Observable<UserModel> dangKi(
            @Field("email") String email,
            @Field("pass") String pass,
            @Field("username") String username,
            @Field("mobile") String mobile
    );

    @POST("dangnhap.php")
    @FormUrlEncoded
    Observable<UserModel> dangNhap(
            @Field("email") String email,
            @Field("pass") String pass
    );

    @POST("reset.php")
    @FormUrlEncoded
    Observable<UserModel> resetPass(
            @Field("email") String email
    );
    @POST("donhang.php")
    @FormUrlEncoded
    Observable<UserModel> createOder(
            @Field("email") String email,
            @Field("sdt") String sdt,
            @Field("tongtien") String tongtien,
            @Field("iduser") int id,
            @Field("diachi") String diachi,
            @Field("soluong") int soluong,
            @Field("chitiet") String chitiet
    );
    @POST("xemdonhang.php")
    @FormUrlEncoded
    Observable<DonHangModel> xemDonHang(
            @Field("iduser") int id
    );
    @POST("timkiem.php")
    @FormUrlEncoded
    Observable<ChuyenXePhoBienModel> search(
            @Field("search") String search
    );
}
