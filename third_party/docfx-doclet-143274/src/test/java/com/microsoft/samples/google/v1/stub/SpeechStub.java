/*
 * Copyright 2023 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.microsoft.samples.google.v1.stub;

import com.google.api.core.BetaApi;
import com.google.api.gax.core.BackgroundResource;
import com.google.api.gax.rpc.BidiStreamingCallable;
import com.google.api.gax.rpc.OperationCallable;
import com.google.api.gax.rpc.UnaryCallable;
import com.google.cloud.speech.v1p1beta1.LongRunningRecognizeMetadata;
import com.google.cloud.speech.v1p1beta1.LongRunningRecognizeRequest;
import com.google.cloud.speech.v1p1beta1.LongRunningRecognizeResponse;
import com.google.cloud.speech.v1p1beta1.RecognizeRequest;
import com.google.cloud.speech.v1p1beta1.RecognizeResponse;
import com.google.cloud.speech.v1p1beta1.StreamingRecognizeRequest;
import com.google.cloud.speech.v1p1beta1.StreamingRecognizeResponse;
import com.google.longrunning.Operation;
import com.google.longrunning.stub.OperationsStub;
import javax.annotation.Generated;

// AUTO-GENERATED DOCUMENTATION AND CLASS.

/**
 * Base stub class for the Speech service API.
 *
 * <p>This class is for advanced usage and reflects the underlying API directly.
 */
@BetaApi
@Generated("by gapic-generator-java")
public abstract class SpeechStub implements BackgroundResource {

    /**
     * Gets operations stub.
     *
     * @return the operations stub
     */
    public OperationsStub getOperationsStub() {
    return null;
  }

    /**
     * Gets http json operations stub.
     *
     * @return the http json operations stub
     */
    public com.google.api.gax.httpjson.longrunning.stub.OperationsStub getHttpJsonOperationsStub() {
    return null;
  }

    /**
     * Recognize callable unary callable.
     *
     * @return the unary callable
     */
    public UnaryCallable<RecognizeRequest, RecognizeResponse> recognizeCallable() {
    throw new UnsupportedOperationException("Not implemented: recognizeCallable()");
  }

    /**
     * Long running recognize operation callable operation callable < long running recognize request , long running recognize response , long running recognize metadata >.
     *
     * @return the operation callable < long running recognize request , long running recognize response , long running recognize metadata >
     */
    public OperationCallable<
          LongRunningRecognizeRequest, LongRunningRecognizeResponse, LongRunningRecognizeMetadata>
      longRunningRecognizeOperationCallable() {
    throw new UnsupportedOperationException(
        "Not implemented: longRunningRecognizeOperationCallable()");
  }

    /**
     * Long running recognize callable unary callable.
     *
     * @return the unary callable
     */
    public UnaryCallable<LongRunningRecognizeRequest, Operation> longRunningRecognizeCallable() {
    throw new UnsupportedOperationException("Not implemented: longRunningRecognizeCallable()");
  }

    /**
     * Streaming recognize callable bidi streaming callable.
     *
     * @return the bidi streaming callable
     */
    public BidiStreamingCallable<StreamingRecognizeRequest, StreamingRecognizeResponse>
      streamingRecognizeCallable() {
    throw new UnsupportedOperationException("Not implemented: streamingRecognizeCallable()");
  }

  @Override
  public abstract void close();
}
