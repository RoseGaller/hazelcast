/*
 * Copyright (c) 2008-2021, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.jet.pipeline;

import com.hazelcast.jet.aggregate.AggregateOperation;
import com.hazelcast.jet.datamodel.KeyedWindowResult;
import com.hazelcast.jet.datamodel.Tag;

import javax.annotation.Nonnull;

public interface WindowGroupAggregateBuilder1<T0, K> {

    /**
     * Returns the tag corresponding to the pipeline stage this builder
     * was obtained from. Use this tag to refer to this stage when building
     * the {@code AggregateOperation} that you'll pass to {@link #build
     * build(aggrOp)}.
     */
    @Nonnull
    Tag<T0> tag0();

    /**
     * Adds another stage that will contribute its data to the windowed
     * group-and-aggregate stage being constructed. Returns the tag you'll use
     * to refer to this stage when building the {@code AggregateOperation} that
     * you'll pass to {@link #build build()}.
     */
    @Nonnull
    <T> Tag<T> add(@Nonnull StreamStageWithKey<T, K> stage);

    /**
     * Creates and returns a pipeline stage that performs a windowed
     * cogroup-and-aggregate of the pipeline stages registered with this builder object.
     * The tags you register with the aggregate operation must match the tags
     * you registered with this builder.
     *
     * @see com.hazelcast.jet.aggregate.AggregateOperations AggregateOperations
     * @param aggrOp        the aggregate operation to perform
     * @param <R>           the type of the aggregation result
     * @return a new stage representing the co-aggregation
     */
    @Nonnull
    <R> StreamStage<KeyedWindowResult<K, R>> build(@Nonnull AggregateOperation<?, ? extends R> aggrOp);
}
