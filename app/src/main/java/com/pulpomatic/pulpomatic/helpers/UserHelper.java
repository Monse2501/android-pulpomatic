package com.pulpomatic.pulpomatic.helpers;

import android.app.Application;
import android.content.SharedPreferences;

import com.google.gson.JsonObject;
import com.pulpomatic.pulpomatic.api.UserApi;
import com.pulpomatic.pulpomatic.models.AuthToken;
import com.pulpomatic.pulpomatic.models.Driver;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Named;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 *
 * Created by Monse Chimal on 10/3/16.
 * monse@parkiller.com
 */
public class UserHelper extends RestHelper<UserApi> {

    public static final String AUTH_ID = "auth";
    public static final String AUTH_HEADER = "Authorization";
    private static final String GRANT_TYPE = "grant_type";
    private static final String CLIENT_ID = "client_id";
    private static final String CLIENT_SECRET = "client_secret";
    private static final String AUTH_USER_KEY = "auth.user";
    private static final String AUTH_PASS_KEY = "auth.pass";

    @Inject
    public UserHelper(
            Application application,
            JsonObject configuration,
            SharedPreferences sharedPreferences,
            @Named(AUTH_ID) Retrofit retrofit,
            NetworkHelper networkHelper) {
        super(application, configuration, sharedPreferences, retrofit, UserApi.class, networkHelper);
    }

    /**
     * Obtiene las credenciales que se encuentren almacenadas en el dispositivo.
     * Manda a llamar al método para obtener el token, enviándole como parámetros las credenciales obtenidas
     * @return respuesta con el token si las credenciales son correctas
     * @throws IOException si icurre un error inesperado en la comunicación con Backend
     */
    public Response<AuthToken> requestAuthToken() throws IOException {

        String user = getSharedPreferences().getString(AUTH_USER_KEY, null);
        String pass = getSharedPreferences().getString(AUTH_PASS_KEY, null);

        return requestAuthToken(user, pass);

    }

    /**
     * Solicita el token de autorización a Backend de forma síncrona mediante las credenciales obtenidas anteriormente y que son pasadas como parámetro a este método
     * @param user usuario almacenado en el dispositivo
     * @param pass contraseña almacenada en el dispositivo
     * @return respuesta con el token si las credenciales son correctas.
     * @throws IOException si ocurre un error inesperado en la comunicación con Backend
     */
    public Response<AuthToken> requestAuthToken(String user, String pass) throws IOException {

        return requestAuthTokenCall(user, pass).execute();
    }

    /**
     * Solicita el token de autorización a Backend de forma asíncrona mediante las credenciales obtenidas anteriormente y que son pasadas como parámetro a este método
     * @param user usuario almacenado en el dispositivo
     * @param pass contraseña almacenada en el dispositivo
     * @return respuesta con el token si las credenciales son correctas.
     * @throws IOException si ocurre un error inesperado en la comunicación con Backend
     */
    public void requestAuthToken(String user, String pass, Callback<AuthToken> callback) {

        requestAuthTokenCall(user, pass).enqueue(callback);
    }

    /**
     * Crea una instancia de la llamada que va hacia backend la cual puede utilizarse de forma sincrona o asincrona
     * @param user usuario
     * @param pass contraseña
     * @return llamada a backend que, si las credenciales son correctas puede regresar el AuthToken
     * @throws IllegalArgumentException si algunos de los parámetros es null
     */
    public Call<AuthToken> requestAuthTokenCall(String user, String pass) {
        if(user == null || pass == null){
            throw new IllegalArgumentException("Auth credentials could not be null.");
        }
        return getApiService().getAuthToken(
                getConfiguration().getAsJsonObject(AUTH_ID).getAsJsonPrimitive(GRANT_TYPE).getAsString(),
                getConfiguration().getAsJsonObject(AUTH_ID).getAsJsonPrimitive(CLIENT_ID).getAsString(),
                getConfiguration().getAsJsonObject(AUTH_ID).getAsJsonPrimitive(CLIENT_SECRET).getAsString(),
                user,
                pass
        );
    }

    /**
     * Obtiene la información del usuario
     * Si el parámetro es nulo enviará una excepción de tipo IllegalArgumentException
     * @param callback callback para la respuesta de backend
     */
    public void getDriverInfo(Callback<Driver> callback){
        if(callback == null) {
            throw new IllegalArgumentException("Callback could not be null.");
        }
        //TODO add implementation
    }

    /**
     * Actualiza la información del usuario
     * @param info información actualizada del usuario a enviar a backend
     * @param callback callback para la respuesta de backend
     * @throws IOException si ocurre un error inesperado en la comunicación con Backend
     */
    public void updateDriverInfo(HashMap<String, Object> info, Callback<Driver> callback) throws IOException {
        if(callback == null) {
            throw new IllegalArgumentException("Callback could not be null.");
        }
        if(info == null) {
            throw new IllegalArgumentException("Info could not be null.");
        }
        //TODO add implementation
    }

    /**
     * Actualiza la foto de perfil del usuario
     * @param pictureFile objeto File que contiene la foto de perfil
     * @param callback callback para la respuesta de backend
     * @throws IOException si ocurre un error inesperado en la comunicación con Backend
     */
    public void updateDriverImage(File pictureFile, Callback<Driver> callback) throws IOException {
        if(callback == null) {
            throw new IllegalArgumentException("Callback could not be null.");
        }
        if(pictureFile == null) {
            throw new IllegalArgumentException("Picture file could not be null.");
        }
        //TODO add implementation
    }

    /**
     * Envía al proceso para recuperar la contraseña, para este proceso es necesario su correo
     * En caso de que alguno de los parámetros sea nulo, enviará una excepción de tipo IllegalArgumentException
     * @param email correo del usuario que ha solicitado recuperar su contraseña
     * @param callback callback para la respuesta de backend
     */
    public void recoverPassword(String email, Callback<String> callback) {
        if(callback == null){
            throw new IllegalArgumentException("Callback could not be null.");
        }
        if(email == null){
            throw new IllegalArgumentException("Email could not be null.");
        }
        //TODO add implementation
    }

    /**
     * Guarda las credenciales básicas de un usuario (correo y contraseña) en el almacenamiento
     * local de la aplicación para inicio de sesión automático
     * @param user usuario
     * @param pass contraseña
     */
    public void setUserCredentials(String user, String pass){
        getSharedPreferences().edit()
                .putString(AUTH_USER_KEY, user)
                .putString(AUTH_PASS_KEY, pass)
                .commit();
    }

}
