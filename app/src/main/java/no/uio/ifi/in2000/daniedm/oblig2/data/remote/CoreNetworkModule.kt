package no.uio.ifi.in2000.daniedm.oblig2.data.remote

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object CoreNetworkModule {
    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)

        builder.addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) })

        return builder.build()
    }

    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Singleton
    @Provides
    fun providesRetrofit(
        networkJson: Json,
        okHttpClient: OkHttpClient,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(
                networkJson.asConverterFactory("application/json".toMediaType()),
            )
            .build()
    }


    @Provides
    fun providesPartyInfoApi(retrofit: Retrofit): PartyInfoApi = retrofit.create(PartyInfoApi::class.java)

    @Provides
    fun providesDistrict1InfoApi(retrofit: Retrofit): District1Api = retrofit.create(District1Api::class.java)

    @Provides
    fun providesDistrict2InfoApi(retrofit: Retrofit): District2Api = retrofit.create(District2Api::class.java)

    @Provides
    fun providesDistrict3InfoApi(retrofit: Retrofit): District3Api = retrofit.create(District3Api::class.java)
}


private const val NETWORK_TIMEOUT = 30L
private const val BASE_URL = "https://in2000-proxy.ifi.uio.no/alpacaapi/v2/"
