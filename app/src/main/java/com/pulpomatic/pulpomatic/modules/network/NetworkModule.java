package com.pulpomatic.pulpomatic.modules.network;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.pulpomatic.pulpomatic.helpers.UserHelper;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Módulo Dagger encargado de obtener lo necesario para que las funciones de red puedan crearse y
 * puedan funcionar de manera correcta
 * Created by Monse Chimal on 10/3/16.
 * monse@parkiller.com
 */
@Module
public class NetworkModule {

    private static final String TAG = NetworkModule.class.getSimpleName();

    public static final String API = "api";
    private static final String CACHE_SIZE = "cache_size";
    private static final String CONNECT_TIMEOUT = "connect_timeout";
    private static final String READ_TIMEOUT = "read_timeout";
    private static final String WRITE_TIMEOUT = "write_timeout";
    private static final String BASE_URL = "base_url";

    /**
     * Crea la instancia de Caché necesaria para guardar de forma temporal las respuestas a llamadas
     * que tiendan a repetirse
     * @param application contexto de Android
     * @param localConfiguration configuración local
     * @return cache configurado
     */
    @Provides
    @Singleton
    public Cache provideCache(
            Application application,
            JsonObject localConfiguration
    ){
        return new Cache(
                application.getCacheDir(),
                localConfiguration.getAsJsonObject(API).getAsJsonPrimitive(CACHE_SIZE).getAsInt()
        );
    }


    /**
     * Crea la instancia OkHttpClient necesaria para las llamadas a backend
     * @param localConfiguration configuración local
     * @param cache cache para guardar datos de forma temporal
     * @param authInterceptor Interceptor que ayuda a poner el AuthToken sin necesidad de tocar la llamada
     * @param authenticator PulpomaticAuthenticator que ayuda a resolver llamadas con respuesta de error (400, 401, 402, etc...)
     * @return OkHttpClient creado para llamadas REST a backend
     * @see AuthInterceptor
     * @see PulpomaticAuthenticator
     */
    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(
            JsonObject localConfiguration,
            Cache cache,
            AuthInterceptor authInterceptor,
            PulpomaticAuthenticator authenticator
    ){
        return new OkHttpClient.Builder()
                .cache(cache)
                .connectTimeout(localConfiguration.getAsJsonObject(API).getAsJsonPrimitive(CONNECT_TIMEOUT).getAsInt(), TimeUnit.SECONDS)
                .readTimeout(localConfiguration.getAsJsonObject(API).getAsJsonPrimitive(READ_TIMEOUT).getAsInt(), TimeUnit.SECONDS)
                .writeTimeout(localConfiguration.getAsJsonObject(API).getAsJsonPrimitive(WRITE_TIMEOUT).getAsInt(), TimeUnit.SECONDS)
                .addNetworkInterceptor(authInterceptor)
                .authenticator(authenticator)
                .build();
    }

    /**
     * Crea la instancia de Retrofit necesaria para crear los objetos que pueden realizar llamadas
     * a backend a partir de la interface Api enviada como clase a la instancia
     * <code>
     *     retrofit.create(ApiClass.class)
     * </code>
     * @param okHttpClient instancia de OkHttpCliente que sirve para hacer la llamada
     * @param localConfiguration configuración local
     * @param gson parser de respuestas de backend
     * @return instancia Retrofit para creación de instancias de interfaces Api
     */
    @Provides
    @Singleton
    public Retrofit provideRetrofit(
            OkHttpClient okHttpClient,
            JsonObject localConfiguration,
            Gson gson
    ){
        return new Retrofit.Builder()
                .baseUrl(localConfiguration.getAsJsonObject(API).getAsJsonPrimitive(BASE_URL).getAsString())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    /**
     * Crea la instancia básica de OkHttpClient que sirve para llamadas que no necesitan de caché
     * o alguna clase que externa de ayuda
     *
     * La anotación @Named ayuda a diferenciar esta instancia de la otra
     *
     * @param configuration configuración local
     * @return instancia OkHttpClient básica
     */
    @Provides
    @Singleton
    @Named(UserHelper.AUTH_ID)
    public OkHttpClient provideAuthOkHttpClient(
            JsonObject configuration
    ){
        return new OkHttpClient.Builder()
                .connectTimeout(configuration.getAsJsonObject(API).getAsJsonPrimitive(CONNECT_TIMEOUT).getAsInt(), TimeUnit.SECONDS)
                .readTimeout(configuration.getAsJsonObject(API).getAsJsonPrimitive(READ_TIMEOUT).getAsInt(), TimeUnit.SECONDS)
                .writeTimeout(configuration.getAsJsonObject(API).getAsJsonPrimitive(WRITE_TIMEOUT).getAsInt(), TimeUnit.SECONDS)
                .build();
    }

    /**
     * Crea la instancia básica de Retrofit que sirve para creación de instancias de las interfaces Api
     * las cuales sirven para llamadas que no requieren de caché o alguna clase externa de ayuda
     *
     * La anotación @Named ayuda a diferenciar esta instancia de la otra
     *
     * @param okHttpClient OkHttpClient básico sin caché ni nada extra
     * @param configuration configuración local
     * @param gson instancia Gson para el parseo de respuestas de backend
     * @return instancia de Retrofit
     */
    @Provides
    @Singleton
    @Named(UserHelper.AUTH_ID)
    public Retrofit provideAuthRetrofit(
            @Named(UserHelper.AUTH_ID) OkHttpClient okHttpClient,
            JsonObject configuration,
            Gson gson
    ){
        return new Retrofit.Builder()
                .baseUrl(configuration.getAsJsonObject(API).getAsJsonPrimitive(BASE_URL).getAsString())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

}