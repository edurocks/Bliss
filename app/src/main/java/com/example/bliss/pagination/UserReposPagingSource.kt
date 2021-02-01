package com.example.bliss.pagination

import androidx.paging.PagingSource
import com.example.bliss.model.UserReposResponse
import com.example.bliss.network.BlissInterface

class UserReposPagingSource(private val blissInterface: BlissInterface,
                            private  val repoName: String) : PagingSource<Int, UserReposResponse>(){
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserReposResponse> {

        return try {
            val currentLoadingPageKey = params.key ?: 1
            val response = blissInterface.getUserRepos(repoName, currentLoadingPageKey, 10)
            val data = response.body()?:ArrayList()

            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1

            LoadResult.Page(data = data,
                    prevKey = prevKey,
                    nextKey = currentLoadingPageKey.plus(1))

        }catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}