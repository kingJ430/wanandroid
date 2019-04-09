package com.zjf.main.remote

import com.zjf.main.bean.ArtList
import com.zjf.main.bean.BannerBean
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @Desc
 * @Author zjf
 * @Date 2019/4/8
 */
interface MainService {

    @GET("/banner/json")
    fun banner(): Observable<List<BannerBean>>

    @GET("/article/list/{page}/json")
    fun artList(@Path("page") pageNo: Int) : Observable<ArtList>

}