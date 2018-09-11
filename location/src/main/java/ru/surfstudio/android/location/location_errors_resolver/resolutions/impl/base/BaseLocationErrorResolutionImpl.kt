/*
 * Copyright 2016 Valeriy Shtaits.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ru.surfstudio.android.location.location_errors_resolver.resolutions.impl.base

import ru.surfstudio.android.location.exceptions.ResolutionFailedException
import ru.surfstudio.android.location.location_errors_resolver.resolutions.LocationErrorResolution

/**
 * Основа для решения проблемы получения местоположения.
 */
abstract class BaseLocationErrorResolutionImpl<E : Exception> : LocationErrorResolution<E> {

    protected abstract fun performWithCastedException(
            resolvingException: E,
            onSuccessAction: () -> Unit,
            onFailureAction: (ResolutionFailedException) -> Unit
    )

    final override fun perform(
            resolvingException: Exception,
            onSuccessAction: () -> Unit,
            onFailureAction: (ResolutionFailedException) -> Unit
    ) {
        val castedException = resolvingExceptionClass.cast(resolvingException)
        performWithCastedException(castedException, onSuccessAction, onFailureAction)
    }
}