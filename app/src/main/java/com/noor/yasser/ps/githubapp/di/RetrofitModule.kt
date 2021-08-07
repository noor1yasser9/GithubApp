package com.noor.yasser.ps.githubapp.di


import com.noor.yasser.ps.githubapp.network.DataInterface
import com.noor.yasser.ps.githubapp.utils.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun InstanceRetrofit() =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .apply {
                val builder = OkHttpClient.Builder()
                builder.authenticator { route, response ->
                    response.request.newBuilder()
                        .header(KET_AUTH, VALUE_AUTH)
                        .build();
                }
                builder.connectTimeout(5, TimeUnit.MINUTES) // connect timeout
                    .writeTimeout(5, TimeUnit.MINUTES) // write timeout
                    .readTimeout(5, TimeUnit.MINUTES) // read timeout

                this.client(builder.build())
            }
            .build()


    @Provides
    @Singleton
    fun dataInterface() =
        InstanceRetrofit().create(DataInterface::class.java)
}