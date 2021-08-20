package com.example.trainingintive.what_to_do_feature.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.trainingintive.what_to_do_feature.domain.model.ActivityModel
import com.example.trainingintive.what_to_do_feature.domain.usecase.GetActivitiesPagingSourceUseCase
import javax.inject.Inject

class ActivitiesViewModel @Inject constructor(
    getActivitiesPagingSourceUseCase: GetActivitiesPagingSourceUseCase
) : ViewModel() {

    val activities: LiveData<PagingData<ActivityModel>> =
        Pager(
            config = PagingConfig(pageSize = 1),
            pagingSourceFactory = { getActivitiesPagingSourceUseCase.execute() }
        ).liveData
}
