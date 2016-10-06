package com.pulpomatic.pulpomatic.modules.network;

import com.google.gson.JsonObject;
import com.pulpomatic.pulpomatic.helpers.UserHelper;
import com.pulpomatic.pulpomatic.models.AuthToken;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

/**
 * Clase que implementa Authenticator la cual sirve para solucionar respuestas de error
 * dadas por backend como los errores 401 (Unauthorized) y 408 (Request Timeout) que pueden ser resueltas desde la propia aplicación
 * Esta clase estará en constante construcción ya que podremos encontrar la forma de resolver más
 * errores como los mencionados anteriormente
 *
 * Created by Monse Chimal on 10/3/16.
 * nicol@parkiller.com
 * @see Authenticator
 */
public class PulpomaticAuthenticator implements Authenticator {

    private static final String MAX_INTENTS = "max_intents";

    private UserHelper mUserHelper;
    private int mMaxIntents;

    @Inject
    public PulpomaticAuthenticator(UserHelper userHelper, JsonObject localConfiguration) {
        mUserHelper = userHelper;
        mMaxIntents = localConfiguration.getAsJsonObject(NetworkModule.API).getAsJsonPrimitive(MAX_INTENTS).getAsInt();
    }

    /**
     * Cuando se devuelve algún código de error por parte de backend, se llama a este método
     *
     * Crea una llamada similar a la fallida pero cambiando parámetros para volver tener mayor probabilidad
     * de tener éxito al ser llamado
     * @param route ruta de la llamada
     * @param response respuesta de la llamada
     * @return nueva solicitud
     * @throws IOException cuando hay algún error creando la solicitud o modificando los parámetros
     */
    @Override
    public Request authenticate(Route route, Response response) throws IOException {
        if (responseCount(response) >= mMaxIntents) {
            return null;
        }
        Request request = null;
        if(response.code() == 401) {
            retrofit2.Response<AuthToken> tokenResponse = mUserHelper.requestAuthToken();
            if(tokenResponse.isSuccessful()){
                request = response.request().newBuilder()
                        .addHeader(UserHelper.AUTH_HEADER, tokenResponse.body().getAccessToken())
                        .build();
            }
        } else if (response.code() == 408) {
            request = response
                    .request()
                    .newBuilder()
                    .build();
        }
        return request;
    }

    /**
     * Calcula cuántas veces ha sido ejecutada la misma petición
     *
     * Nos sirve para saber cuando ha superado al número máximo de intentos definidos
     * por la configuración local
     * @param response petición a calcular
     * @return numero de intentos efectuados
     */
    private int responseCount(Response response) {
        int result = 1;
        while ((response = response.priorResponse()) != null) {
            result++;
        }
        return result;
    }
}
