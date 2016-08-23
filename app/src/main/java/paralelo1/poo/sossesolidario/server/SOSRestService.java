package paralelo1.poo.sossesolidario.server;

import java.util.List;

import paralelo1.poo.sossesolidario.objects.CA;
import paralelo1.poo.sossesolidario.objects.Necesidad;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by FIMCP on 23/08/2016.
 */
public interface SOSRestService {

    @GET("CAs")
    Call<List<CA>> getCAs();

    @PUT("CAs/{id}")
    Call<CA> updateCA(@Path("id") int id, @Body CA ca);

    @POST("CAs")
    Call<CA> createCA(@Body CA ca);

    @GET("CAs/{id}/necesidades")
    Call<List<Necesidad>> getCaNecesidades(@Path("id") int id);

    @DELETE("CAs/{id}")
    Call<Void> deleteCA(@Path("id") int id);


}
