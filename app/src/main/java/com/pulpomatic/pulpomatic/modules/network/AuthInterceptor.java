package com.pulpomatic.pulpomatic.modules.network;

import com.pulpomatic.pulpomatic.helpers.UserHelper;
import com.pulpomatic.pulpomatic.models.AuthToken;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Clase que implementa Interceptor, la cual sirve para interceptar cualquier llamada
 * que se haga, inserta los headers correspondientes y efectúa la llamada
 *
 * Created by Monse Chimal on 10/3/16.
 * monse@parkiller.com
 * @see Interceptor
 */
@Singleton
public class AuthInterceptor implements Interceptor {

    private UserHelper mUserHelper;

    @Inject
    public AuthInterceptor(UserHelper userHelper) {
        super();

        mUserHelper = userHelper;
    }

    /**
     * Toma la llamada, crea una nueva a partir de la llamada que se efectuará, inserta el AuthToken
     * y procede con la llamada
     *
     * Nos ayuda a no tener que preocuparnos por la gestión del header y poder concentrarnos en solo
     * la llamada y podemos agregar más headers si son necesarios a través de esta misma clase
     *
     * Esto se ejecuta ANTES de cualquier operación que vaya a backend por REST
     * @param chain cadena de la llamada
     * @return respuesta a la llamada con el token agregado
     * @throws IOException si algún error se ha efectuado durante la llamada o durante la inserción del header
     */
    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request originalRequest = chain.request();
        AuthToken authToken = mUserHelper.requestAuthToken().body();
        Request authorisedRequest = originalRequest.newBuilder()
                .header(UserHelper.AUTH_HEADER, authToken.getAccessToken())
                .build();
        return chain.proceed(authorisedRequest);
    }

}
