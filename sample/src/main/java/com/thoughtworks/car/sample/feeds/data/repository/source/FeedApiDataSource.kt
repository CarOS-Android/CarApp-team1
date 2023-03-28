package com.thoughtworks.car.sample.feeds.data.repository.source

import com.thoughtworks.car.core.network.entity.Result
import com.thoughtworks.car.sample.feeds.data.repository.entity.FeedListEntity
import kotlinx.coroutines.flow.Flow

interface FeedApiDataSource {
    suspend fun getFeedList(): Flow<Result<FeedListEntity>>
}