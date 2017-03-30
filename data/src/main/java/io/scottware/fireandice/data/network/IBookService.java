package io.scottware.fireandice.data.network;


import java.util.List;

import io.reactivex.Observable;
import io.scottware.fireandice.data.network.model.BookNetworkModel;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface IBookService {

    @GET("books")
    Observable<List<BookNetworkModel>> getBooks();

    @GET
    Observable<BookNetworkModel> getBook(@Url String url);

}
