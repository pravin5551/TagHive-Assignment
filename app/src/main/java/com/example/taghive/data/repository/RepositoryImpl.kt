package com.example.taghive.data.repository



import com.example.taghive.data.remote.TagHiveApi
import com.example.taghive.domain.model.CryptoDataClass
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * RepositoryImpl for observing server data from server and to interact between Api interface and Repo interface
 */
class RepositoryImpl constructor(
    private val api: TagHiveApi,
) : Repository {
    fun <T> subscribeOnIoThread(observable: Observable<T>) =
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override fun getListOfCryptos(): Observable<CryptoDataClass> {
        return subscribeOnIoThread(api.getCryptoList())
    }

}