package com.pulpomatic.pulpomatic.api;

import com.pulpomatic.pulpomatic.models.AuthToken;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Interfaz con los end points utilizados para operaciones de usuario
 *
 * Created by Monse Chimal on 10/3/16
 * monse@parkiller.com
 */
public interface UserApi {

    /**
     * Obtiene el token del usuario, el cual es la llave de acceso para cualquier operación o petición
     * con backend
     * @param grantType tipo de acceso concedido desde backend - este parámetro es proporcionado por el api OAuth2 de Laravel
     * @param clientId id del cliente concedido desde backend - este parámetro es proporcionado por el api OAuth2 de Laravel
     * @param clientSecret contraseña del cliente concedido desde backend - este parámetro es proporcionado por el api OAuth2 de Laravel
     * @param username usuario a logear
     * @param password contraseña
     * @return llamada a backend lista para ejecutar que tiene un AuthToken como respuesta posible
     * @see AuthToken
     * @see <a href="https://laravel.com/docs/5.3/session#configuration">Laravel session</a>
     */
    @FormUrlEncoded
    @POST("oauth/access_token")
    Call<AuthToken> getAuthToken(
            @Field("grant_type") String grantType,
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            @Field("username") String username,
            @Field("password") String password
    );

}