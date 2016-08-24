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

    @GET("CAs/{id}")
    Call<CA> getCA(@Path("id")int id);

    @PUT("CAs")
    Call<CA> updateCA(@Body CA ca);

    @POST("CAs")
    Call<CA> createCA(@Body CA ca);

    @DELETE("CAs/{id}")
    Call<Void> deleteCA(@Path("id") int id);

    @GET("CAs/{id}/necesidades?filter=%7B%22order%22%3A%22cantidad%22%7D")
    Call<List<Necesidad>> getCaNecesidades(@Path("id") int id);


    @PUT("CAs/{id}/necesidades")
    Call<Necesidad> addNecesidad(@Path("id") int id);

    @GET("necesidades")
    Call<List<Necesidad>> getNecesidades();

    @GET("necesidades/{id}")
    Call<Necesidad> getNecesidad(@Path("id") int id);

    @DELETE("necesidades/{id}")
    Call<Void> deleteNecesidad(@Path("id") int id);
}
