package com.mlclassifier.ecgclaissfier.network_calls;

import com.mlclassifier.ecgclaissfier.model.Success;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RestApiInterface {

    @POST("varify_user")
    @FormUrlEncoded
    Call<Success> registration(
            @Field("id") String id,
            @Field("full_name") String full_name,
            @Field("phone") String phone,
            @Field("password") String password,
            @Field("email") String email
    );


    @POST("login")
    @FormUrlEncoded
    Call<Success> login(
            @Field("password") String password,
            @Field("email") String email);

    @Multipart
    @POST("attachment")
    Call<Success> postAttachment(
            @Part MultipartBody.Part attachment,
            @Part("attachment_id") RequestBody attachment_id,
            @Part("attachment_name") RequestBody attachment_name,
            @Part("attachment_file_type") RequestBody attachment_file_type,
            @Part("complain_id") RequestBody complain_id
    );


}
