/*
 * Copyright 2021 Google LLC
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

package com.microsoft.samples.google;

import com.google.api.core.ApiFunction;
import com.google.api.core.BetaApi;
import com.google.api.core.InternalExtensionOnly;
import com.google.api.gax.core.GoogleCredentialsProvider;
import com.google.api.gax.core.InstantiatingExecutorProvider;
import com.google.api.gax.grpc.InstantiatingGrpcChannelProvider;
import com.google.api.gax.rpc.*;
import com.google.cloud.speech.v1p1beta1.*;
import com.google.cloud.speech.v1p1beta1.SpeechClient;
import com.google.cloud.speech.v1p1beta1.stub.SpeechStubSettings;
import com.google.longrunning.Operation;
import java.io.IOException;
import java.util.List;
import javax.annotation.Generated;

// AUTO-GENERATED DOCUMENTATION AND CLASS.

/**
 * Settings class to configure an instance of {@link SpeechClient}.
 *
 * <p>The default instance has everything set to sensible defaults:
 *
 * <ul>
 *   <li>The default service address (speech.googleapis.com) and default port (443) are used.
 *   <li>Credentials are acquired automatically through Application Default Credentials.
 *   <li>Retries are configured for idempotent methods but not for non-idempotent methods.
 * </ul>
 *
 * <p>The builder of this class is recursive, so contained classes are themselves builders. When
 * build() is called, the tree of builders is called to create the complete settings object.
 *
 * <p>For example, to set the total timeout of recognize to 30 seconds:
 *
 * <pre>{@code
 * SpeechSettings.Builder speechSettingsBuilder = SpeechSettings.newBuilder();
 * speechSettingsBuilder
 *     .recognizeSettings()
 *     .setRetrySettings(
 *         speechSettingsBuilder
 *             .recognizeSettings()
 *             .getRetrySettings()
 *             .toBuilder()
 *             .setTotalTimeout(Duration.ofSeconds(30))
 *             .build());
 * SpeechSettings speechSettings = speechSettingsBuilder.build();
 * }*</pre>
 */
@BetaApi
@InternalExtensionOnly
@Generated("by gapic-generator-java")
@SuppressWarnings("unchecked")
public class SpeechSettings extends ClientSettings<SpeechSettings> {

    /**
     * Returns the object with the settings used for calls to recognize.  @return the unary call settings
     */
    public UnaryCallSettings<RecognizeRequest, RecognizeResponse> recognizeSettings() {
    return ((SpeechStubSettings) getStubSettings()).recognizeSettings();
  }

    /**
     * Returns the object with the settings used for calls to longRunningRecognize.  @return the unary call settings
     */
    public UnaryCallSettings<LongRunningRecognizeRequest, Operation> longRunningRecognizeSettings() {
    return ((SpeechStubSettings) getStubSettings()).longRunningRecognizeSettings();
  }

    /**
     * Returns the object with the settings used for calls to longRunningRecognize.  @return the operation call settings < long running recognize request , long running recognize response , long running recognize metadata >
     */
    public OperationCallSettings<
          LongRunningRecognizeRequest, LongRunningRecognizeResponse, LongRunningRecognizeMetadata>
      longRunningRecognizeOperationSettings() {
    return ((SpeechStubSettings) getStubSettings()).longRunningRecognizeOperationSettings();
  }

    /**
     * Returns the object with the settings used for calls to streamingRecognize.  @return the streaming call settings
     */
    public StreamingCallSettings<StreamingRecognizeRequest, StreamingRecognizeResponse>
      streamingRecognizeSettings() {
    return ((SpeechStubSettings) getStubSettings()).streamingRecognizeSettings();
  }

    /**
     * Create speech settings.
     *
     * @param stub the stub
     * @return the speech settings
     * @throws IOException the io exception
     */
    public static final SpeechSettings create(SpeechStubSettings stub) throws IOException {
    return new Builder(stub.toBuilder()).build();
  }

    /**
     * Returns a builder for the default ExecutorProvider for this service.  @return the instantiating executor provider . builder
     */
    public static InstantiatingExecutorProvider.Builder defaultExecutorProviderBuilder() {
    return SpeechStubSettings.defaultExecutorProviderBuilder();
  }

    /**
     * Returns the default service endpoint.  @return the default endpoint
     */
    public static String getDefaultEndpoint() {
    return SpeechStubSettings.getDefaultEndpoint();
  }

    /**
     * Returns the default service scopes.  @return the default service scopes
     */
    public static List<String> getDefaultServiceScopes() {
    return SpeechStubSettings.getDefaultServiceScopes();
  }

    /**
     * Returns a builder for the default credentials for this service.  @return the google credentials provider . builder
     */
    public static GoogleCredentialsProvider.Builder defaultCredentialsProviderBuilder() {
    return SpeechStubSettings.defaultCredentialsProviderBuilder();
  }

    /**
     * Returns a builder for the default ChannelProvider for this service.  @return the instantiating grpc channel provider . builder
     */
    public static InstantiatingGrpcChannelProvider.Builder defaultGrpcTransportProviderBuilder() {
    return SpeechStubSettings.defaultGrpcTransportProviderBuilder();
  }

    /**
     * Default transport channel provider transport channel provider.
     *
     * @return the transport channel provider
     */
    public static TransportChannelProvider defaultTransportChannelProvider() {
    return SpeechStubSettings.defaultTransportChannelProvider();
  }

    /**
     * Default api client header provider builder api client header provider . builder.
     *
     * @return the api client header provider . builder
     */
    @BetaApi("The surface for customizing headers is not stable yet and may change in the future.")
  public static ApiClientHeaderProvider.Builder defaultApiClientHeaderProviderBuilder() {
    return SpeechStubSettings.defaultApiClientHeaderProviderBuilder();
  }

    /**
     * Returns a new builder for this class.  @return the builder
     */
    public static Builder newBuilder() {
    return Builder.createDefault();
  }

    /**
     * Returns a new builder for this class.  @param clientContext the client context
     *
     * @return the builder
     */
    public static Builder newBuilder(ClientContext clientContext) {
    return new Builder(clientContext);
  }

  /** Returns a builder containing all the values of this settings class. */
  public Builder toBuilder() {
    return new Builder(this);
  }

    /**
     * Instantiates a new Speech settings.
     *
     * @param settingsBuilder the settings builder
     * @throws IOException the io exception
     */
    protected SpeechSettings(Builder settingsBuilder) throws IOException {
    super(settingsBuilder);
  }

    /**
     * Builder for SpeechSettings.
     */
    public static class Builder extends ClientSettings.Builder<SpeechSettings, Builder> {

        /**
         * Instantiates a new Builder.
         *
         * @throws IOException the io exception
         */
        protected Builder() throws IOException {
      this(((ClientContext) null));
    }

        /**
         * Instantiates a new Builder.
         *
         * @param clientContext the client context
         */
        protected Builder(ClientContext clientContext) {
      super(SpeechStubSettings.newBuilder(clientContext));
    }

        /**
         * Instantiates a new Builder.
         *
         * @param settings the settings
         */
        protected Builder(SpeechSettings settings) {
      super(settings.getStubSettings().toBuilder());
    }

        /**
         * Instantiates a new Builder.
         *
         * @param stubSettings the stub settings
         */
        protected Builder(SpeechStubSettings.Builder stubSettings) {
      super(stubSettings);
    }

    private static Builder createDefault() {
      return new Builder(SpeechStubSettings.newBuilder());
    }

        /**
         * Gets stub settings builder.
         *
         * @return the stub settings builder
         */
        public SpeechStubSettings.Builder getStubSettingsBuilder() {
      return ((SpeechStubSettings.Builder) getStubSettings());
    }

    // NEXT_MAJOR_VER: remove 'throws Exception'.

        /**
         * Applies the given settings updater function to all of the unary API methods in this service.
         *
         * <p>Note: This method does not support applying settings to streaming methods.
         *
         * @param settingsUpdater the settings updater
         * @return the builder
         * @throws Exception the exception
         */
        public Builder applyToAllUnaryMethods(
        ApiFunction<UnaryCallSettings.Builder<?, ?>, Void> settingsUpdater) throws Exception {
      super.applyToAllUnaryMethods(
          getStubSettingsBuilder().unaryMethodSettingsBuilders(), settingsUpdater);
      return this;
    }

        /**
         * Returns the builder for the settings used for calls to recognize.  @return the unary call settings . builder
         */
        public UnaryCallSettings.Builder<RecognizeRequest, RecognizeResponse> recognizeSettings() {
      return getStubSettingsBuilder().recognizeSettings();
    }

        /**
         * Returns the builder for the settings used for calls to longRunningRecognize.  @return the unary call settings . builder
         */
        public UnaryCallSettings.Builder<LongRunningRecognizeRequest, Operation>
        longRunningRecognizeSettings() {
      return getStubSettingsBuilder().longRunningRecognizeSettings();
    }

        /**
         * Returns the builder for the settings used for calls to longRunningRecognize.  @return the operation call settings . builder < long running recognize request , long running recognize response , long running recognize metadata >
         */
        public OperationCallSettings.Builder<
            LongRunningRecognizeRequest, LongRunningRecognizeResponse, LongRunningRecognizeMetadata>
        longRunningRecognizeOperationSettings() {
      return getStubSettingsBuilder().longRunningRecognizeOperationSettings();
    }

        /**
         * Returns the builder for the settings used for calls to streamingRecognize.  @return the streaming call settings . builder
         */
        public StreamingCallSettings.Builder<StreamingRecognizeRequest, StreamingRecognizeResponse>
        streamingRecognizeSettings() {
      return getStubSettingsBuilder().streamingRecognizeSettings();
    }

    @Override
    public SpeechSettings build() throws IOException {
      return new SpeechSettings(this);
    }
  }
}
