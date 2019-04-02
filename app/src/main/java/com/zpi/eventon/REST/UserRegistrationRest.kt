package com.zpi.eventon.REST

import com.zpi.eventon.Model.RegistrationFormData
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import com.google.gson.GsonBuilder
import com.zpi.eventon.Model.ClientRest
import com.zpi.eventon.Model.LoginFormData


class UserRegistrationRest{


    private var api: Api? = null

    val BASE_URL: String get() = "http://10.182.17.223:8080/rest/"

    constructor(token: String) {
        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(ClientRest.getRestClient(token))
            .build()
        api = retrofit.create(Api::class.java)
    }


    fun registrationRequest(data: RegistrationFormData): Call<String> {
        return api!!.userAccountRegistration(data)
    }

    fun loginRequest(data: LoginFormData): Call<String> {
        return api!!.userAccountLogin(data)
    }

    fun remindPassword(data : String): Call<String>{
        return api!!.remindPassword(data)
    }

    interface Api {

//        @get:GET("mountain_group")
//        val mountainGroups: Call<List<MountainGroup>>
//
//        @GET("mountain_group/{id}/subgroup")
//        fun getMountainSubgroupsByGroup(@Path("id") groupId: Int): Call<List<MountainSubgroup>>
//
//        @GET("mountain_subgroup/{id}/point")
//        fun getPointsBySubgroup(@Path("id") subgroupId: Int): Call<List<Point>>

        @POST("user_account/register")
        fun userAccountRegistration(@Body formData: RegistrationFormData): Call<String>

        @POST("user_account/login")
        fun userAccountLogin(@Body formData: LoginFormData): Call<String>

        @POST("user_account/remind_password")
        fun remindPassword(@Body email: String): Call<String>
    }


}