package cr.ac.ucr.rickmorty.api;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.Locale;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitBuilder {

    private static String BASE_URL = "https://rickandmortyapi.com/api/";

    private static final OkHttpClient client = buildClient();
    private static final Retrofit retrofit = buildRetrofit(client);

    private static OkHttpClient buildClient(){

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {

                    @NonNull
                    @Override
                    public Response intercept(@NonNull Chain chain) throws IOException {

                        Request original = chain.request();
                        HttpUrl url = original.url();

                        HttpUrl newUrl = url.newBuilder()
                                .build();

                        Request.Builder builder = original.newBuilder().url(newUrl);
                        Request request = builder.build();

                        return chain.proceed(request);
                    }
                })
                ;

        return builder.build();

    }

    private static Retrofit buildRetrofit(@NonNull OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static <T> T createService(final Class<T> service){
        return retrofit.create(service);
    }

    public static Retrofit getRetrofit(){ return retrofit; }
}
