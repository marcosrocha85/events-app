package com.marcosrocha85.events.data.service.cloud

import com.google.gson.GsonBuilder
import com.marcosrocha85.events.BuildConfig
import com.marcosrocha85.events.data.service.cloud.model.EventCheckInData
import com.marcosrocha85.events.data.service.cloud.model.EventData
import io.reactivex.rxjava3.core.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

interface EventService {
    @GET("events")
    fun getEvents(): Observable<List<EventData>>
    @GET("event/:id")
    fun getEvent(@Query("id") id: String): Observable<EventData>
    @POST("checkin")
    fun checkIn(@Body checkInData: EventCheckInData): Observable<Any>

    companion object Factory {
        private const val BASE_URL = BuildConfig.API_HOSTNAME

        fun makeRequest(): EventService {
            val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .disableHtmlEscaping()
                .excludeFieldsWithoutExposeAnnotation()
                .create()

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(getClient())
                .build()

            return retrofit.create(EventService::class.java)
        }

        private fun getClient(): OkHttpClient {
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                override fun checkClientTrusted(
                    p0: Array<out java.security.cert.X509Certificate>?,
                    p1: String?
                ) { }

                override fun checkServerTrusted(
                    p0: Array<out java.security.cert.X509Certificate>?,
                    p1: String?
                ) { }

                override fun getAcceptedIssuers(): Array<out java.security.cert.X509Certificate> {
                    return arrayOf()
                }
            })
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())
            val sslSocketFactory = sslContext.socketFactory

            val builder = OkHttpClient.Builder()
                .addInterceptor {
                    val requestBuild = it.request().newBuilder()
                    requestBuild.apply {
                        addHeader("Accept", "application/json")
                        addHeader("User-Agent", BuildConfig.APPLICATION_ID)
                        addHeader("X-App-Version-Name", BuildConfig.VERSION_NAME)
                        addHeader("X-App-Version-Code", BuildConfig.VERSION_CODE.toString())

                        it.request().header("Authorization")?.let { token ->
                            header("Authorization", "Bearer $token")
                        }
                    }
                    return@addInterceptor it.proceed(requestBuild.build())
                }
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier { _, _ -> true }

            if (BuildConfig.DEBUG) {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

                builder.addInterceptor(loggingInterceptor)
            }

            return builder.build()
        }
    }
}