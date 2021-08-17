package com.example.trainingintive.what_to_do_feature.data.pagination

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.example.trainingintive.rx.SchedulersProvider
import com.example.trainingintive.what_to_do_feature.data.network.ActivityApiService
import com.example.trainingintive.what_to_do_feature.domain.model.ActivityModel
import javax.inject.Inject

class ActivityPagingSource @Inject constructor(
    private val service: ActivityApiService,
    private val schedulers: SchedulersProvider
) : RxPagingSource<Int, ActivityModel>() {

    override fun loadSingle(params: LoadParams<Int>) =
        service.getActivity()
            .subscribeOn(schedulers.io())
            .map { listOf(it.toDomain()) }
            .map { toLoadResult(it, params) }
            .onErrorReturn { LoadResult.Error(it) }

    private fun toLoadResult(
        data: List<ActivityModel>,
        params: LoadParams<Int>
    ): LoadResult<Int, ActivityModel> {
        val position = params.key ?: 1

        return LoadResult.Page(
            data = data,
            prevKey = null,
            nextKey = if (data.isEmpty()) null else position + (params.loadSize)
        )
    }

    override fun getRefreshKey(state: PagingState<Int, ActivityModel>) = null
}
