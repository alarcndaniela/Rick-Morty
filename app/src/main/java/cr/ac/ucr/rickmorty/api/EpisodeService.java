package cr.ac.ucr.rickmorty.api;

import cr.ac.ucr.rickmorty.models.Episode;
import cr.ac.ucr.rickmorty.models.EpisodeResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EpisodeService {
    @Headers("Content-Type: application/json")
    @GET("episode") //?page=numero
    Call<EpisodeResponse> getEpisodes(@Query("page") int page);

    @Headers("Content-Type: application/json")
    @GET("episode/{id}")
    Call<Episode> getEpisode(@Path("id") int id);
}
