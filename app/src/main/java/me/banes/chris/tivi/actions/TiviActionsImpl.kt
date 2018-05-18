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

package me.banes.chris.tivi.actions

import me.banes.chris.tivi.jobs.AddToFollowedShows
import me.banes.chris.tivi.jobs.RemoveFromFollowedShows
import me.banes.chris.tivi.jobs.SyncShowWatchedProgress

class TiviActionsImpl : TiviActions {
    override fun followShow(showId: Long) {
        AddToFollowedShows.buildRequest(showId)
                .startNow()
                .build()
                .scheduleAsync()
    }

    override fun unfollowShow(showId: Long) {
        RemoveFromFollowedShows.buildRequest(showId)
                .startNow()
                .build()
                .scheduleAsync()
    }

    override fun syncShowWatchedEpisodes(showId: Long) {
        SyncShowWatchedProgress.buildRequest(showId)
                .startNow()
                .build()
                .scheduleAsync()
    }
}