package paralelo1.poo.sossesolidario.server;

import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by FIMCP on 23/08/2016.
 */
public class Rest {
    private static Rest singleton = null;
    private Retrofit rest;
    private SOSRestService sosRestService;

    protected Rest() {
        rest = new Retrofit.Builder()
                .baseUrl("http://sos-se-solidario.herokuapp.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        sosRestService = rest.create(SOSRestService.class);
    }

    public static synchronized Rest get() {
        if (singleton == null) {

            singleton = new Rest();
        }
        return singleton;
    }

    public SOSRestService service() {
        return sosRestService;
    }
}