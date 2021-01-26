package com.example.androidpractice.data.api

import com.example.androidpractice.data.vo.Repo
import com.google.gson.annotations.SerializedName

/**
 * 나중에 분석을 해봐야 할것 같지만, 여기 API는 header의 nextpage 값이 필요 없고
 * 아래와 같이 구성되어 있다. 그렇지만 API데이터의 일괄 처리를 위해 nextPage를 null로 만들어 null일 경우 pass 하도록 구성하는 것으로 보인다.
 */
data class RepoSearchResponse (
    @SerializedName("total_count")
    val total: Int = 0,
    @SerializedName("items")
    val items: List<Repo>
) {
    var nextPage: Int? = null
}