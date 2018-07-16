/*
 * Copyright 2018 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package app.tivi.interactors

import app.tivi.data.resultentities.RelatedShowEntryWithShow
import app.tivi.data.shows.ShowRepository
import app.tivi.util.AppCoroutineDispatchers
import io.reactivex.Flowable
import kotlinx.coroutines.experimental.CoroutineDispatcher
import javax.inject.Inject

class UpdateRelatedShows @Inject constructor(
    private val dispatchers: AppCoroutineDispatchers,
    private val showRepository: ShowRepository
) : Interactor2<UpdateRelatedShows.Params, List<RelatedShowEntryWithShow>> {
    override val dispatcher: CoroutineDispatcher = dispatchers.io

    override suspend operator fun invoke(param: Params) {
        showRepository.getRelatedShows(param.showId)
    }

    override fun observe(param: Params): Flowable<List<RelatedShowEntryWithShow>> {
        return showRepository.observeRelatedShows(param.showId)
    }

    data class Params(val showId: Long, val forceLoad: Boolean)
}