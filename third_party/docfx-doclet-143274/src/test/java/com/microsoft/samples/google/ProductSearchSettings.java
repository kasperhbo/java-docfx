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

import static com.google.cloud.vision.v1.ProductSearchClient.*;

import com.google.api.core.ApiFunction;
import com.google.api.core.BetaApi;
import com.google.api.gax.core.GoogleCredentialsProvider;
import com.google.api.gax.core.InstantiatingExecutorProvider;
import com.google.api.gax.grpc.InstantiatingGrpcChannelProvider;
import com.google.api.gax.rpc.*;
import com.google.cloud.vision.v1.*;
import com.google.cloud.vision.v1.stub.ProductSearchStubSettings;
import com.google.longrunning.Operation;
import com.google.protobuf.Empty;
import java.io.IOException;
import java.util.List;
import javax.annotation.Generated;

// AUTO-GENERATED DOCUMENTATION AND CLASS.

/**
 * Settings class to configure an instance of {@link ProductSearchClient}.
 *
 * <p>The default instance has everything set to sensible defaults:
 *
 * <ul>
 *   <li>The default service address (vision.googleapis.com) and default port (443) are used.
 *   <li>Credentials are acquired automatically through Application Default Credentials.
 *   <li>Retries are configured for idempotent methods but not for non-idempotent methods.
 * </ul>
 *
 * <p>The builder of this class is recursive, so contained classes are themselves builders. When
 * build() is called, the tree of builders is called to create the complete settings object.
 *
 * <p>For example, to set the total timeout of createProductSet to 30 seconds:
 *
 * <pre>{@code
 * ProductSearchSettings.Builder productSearchSettingsBuilder = ProductSearchSettings.newBuilder();
 * productSearchSettingsBuilder
 *     .createProductSetSettings()
 *     .setRetrySettings(
 *         productSearchSettingsBuilder
 *             .createProductSetSettings()
 *             .getRetrySettings()
 *             .toBuilder()
 *             .setTotalTimeout(Duration.ofSeconds(30))
 *             .build());
 * ProductSearchSettings productSearchSettings = productSearchSettingsBuilder.build();
 * }*</pre>
 */
@Generated("by gapic-generator-java")
@SuppressWarnings("unchecked")
public class ProductSearchSettings extends ClientSettings<ProductSearchSettings> {

    /**
     * Returns the object with the settings used for calls to createProductSet.  @return the unary call settings
     */
    public UnaryCallSettings<CreateProductSetRequest, ProductSet> createProductSetSettings() {
    return ((ProductSearchStubSettings) getStubSettings()).createProductSetSettings();
  }

    /**
     * Returns the object with the settings used for calls to listProductSets.  @return the paged call settings < list product sets request , list product sets response , list product sets paged response >
     */
    public PagedCallSettings<
          ListProductSetsRequest, ListProductSetsResponse, ListProductSetsPagedResponse>
      listProductSetsSettings() {
    return ((ProductSearchStubSettings) getStubSettings()).listProductSetsSettings();
  }

    /**
     * Returns the object with the settings used for calls to getProductSet.  @return the product set settings
     */
    public UnaryCallSettings<GetProductSetRequest, ProductSet> getProductSetSettings() {
    return ((ProductSearchStubSettings) getStubSettings()).getProductSetSettings();
  }

    /**
     * Returns the object with the settings used for calls to updateProductSet.  @return the unary call settings
     */
    public UnaryCallSettings<UpdateProductSetRequest, ProductSet> updateProductSetSettings() {
    return ((ProductSearchStubSettings) getStubSettings()).updateProductSetSettings();
  }

    /**
     * Returns the object with the settings used for calls to deleteProductSet.  @return the unary call settings
     */
    public UnaryCallSettings<DeleteProductSetRequest, Empty> deleteProductSetSettings() {
    return ((ProductSearchStubSettings) getStubSettings()).deleteProductSetSettings();
  }

    /**
     * Returns the object with the settings used for calls to createProduct.  @return the unary call settings
     */
    public UnaryCallSettings<CreateProductRequest, Product> createProductSettings() {
    return ((ProductSearchStubSettings) getStubSettings()).createProductSettings();
  }

    /**
     * Returns the object with the settings used for calls to listProducts.  @return the paged call settings
     */
    public PagedCallSettings<ListProductsRequest, ListProductsResponse, ListProductsPagedResponse>
      listProductsSettings() {
    return ((ProductSearchStubSettings) getStubSettings()).listProductsSettings();
  }

    /**
     * Returns the object with the settings used for calls to getProduct.  @return the product settings
     */
    public UnaryCallSettings<GetProductRequest, Product> getProductSettings() {
    return ((ProductSearchStubSettings) getStubSettings()).getProductSettings();
  }

    /**
     * Returns the object with the settings used for calls to updateProduct.  @return the unary call settings
     */
    public UnaryCallSettings<UpdateProductRequest, Product> updateProductSettings() {
    return ((ProductSearchStubSettings) getStubSettings()).updateProductSettings();
  }

    /**
     * Returns the object with the settings used for calls to deleteProduct.  @return the unary call settings
     */
    public UnaryCallSettings<DeleteProductRequest, Empty> deleteProductSettings() {
    return ((ProductSearchStubSettings) getStubSettings()).deleteProductSettings();
  }

    /**
     * Returns the object with the settings used for calls to createReferenceImage.  @return the unary call settings
     */
    public UnaryCallSettings<CreateReferenceImageRequest, ReferenceImage>
      createReferenceImageSettings() {
    return ((ProductSearchStubSettings) getStubSettings()).createReferenceImageSettings();
  }

    /**
     * Returns the object with the settings used for calls to deleteReferenceImage.  @return the unary call settings
     */
    public UnaryCallSettings<DeleteReferenceImageRequest, Empty> deleteReferenceImageSettings() {
    return ((ProductSearchStubSettings) getStubSettings()).deleteReferenceImageSettings();
  }

    /**
     * Returns the object with the settings used for calls to listReferenceImages.  @return the paged call settings < list reference images request , list reference images response , list reference images paged response >
     */
    public PagedCallSettings<
          ListReferenceImagesRequest, ListReferenceImagesResponse, ListReferenceImagesPagedResponse>
      listReferenceImagesSettings() {
    return ((ProductSearchStubSettings) getStubSettings()).listReferenceImagesSettings();
  }

    /**
     * Returns the object with the settings used for calls to getReferenceImage.  @return the reference image settings
     */
    public UnaryCallSettings<GetReferenceImageRequest, ReferenceImage> getReferenceImageSettings() {
    return ((ProductSearchStubSettings) getStubSettings()).getReferenceImageSettings();
  }

    /**
     * Returns the object with the settings used for calls to addProductToProductSet.  @return the unary call settings
     */
    public UnaryCallSettings<AddProductToProductSetRequest, Empty> addProductToProductSetSettings() {
    return ((ProductSearchStubSettings) getStubSettings()).addProductToProductSetSettings();
  }

    /**
     * Returns the object with the settings used for calls to removeProductFromProductSet.  @return the unary call settings
     */
    public UnaryCallSettings<RemoveProductFromProductSetRequest, Empty>
      removeProductFromProductSetSettings() {
    return ((ProductSearchStubSettings) getStubSettings()).removeProductFromProductSetSettings();
  }

    /**
     * Returns the object with the settings used for calls to listProductsInProductSet.  @return the paged call settings < list products in product set request , list products in product set response , list products in product set paged response >
     */
    public PagedCallSettings<
          ListProductsInProductSetRequest,
          ListProductsInProductSetResponse,
          ListProductsInProductSetPagedResponse>
      listProductsInProductSetSettings() {
    return ((ProductSearchStubSettings) getStubSettings()).listProductsInProductSetSettings();
  }

    /**
     * Returns the object with the settings used for calls to importProductSets.  @return the unary call settings
     */
    public UnaryCallSettings<ImportProductSetsRequest, Operation> importProductSetsSettings() {
    return ((ProductSearchStubSettings) getStubSettings()).importProductSetsSettings();
  }

    /**
     * Returns the object with the settings used for calls to importProductSets.  @return the operation call settings < import product sets request , import product sets response , batch operation metadata >
     */
    public OperationCallSettings<
          ImportProductSetsRequest, ImportProductSetsResponse, BatchOperationMetadata>
      importProductSetsOperationSettings() {
    return ((ProductSearchStubSettings) getStubSettings()).importProductSetsOperationSettings();
  }

    /**
     * Returns the object with the settings used for calls to purgeProducts.  @return the unary call settings
     */
    public UnaryCallSettings<PurgeProductsRequest, Operation> purgeProductsSettings() {
    return ((ProductSearchStubSettings) getStubSettings()).purgeProductsSettings();
  }

    /**
     * Returns the object with the settings used for calls to purgeProducts.  @return the operation call settings
     */
    public OperationCallSettings<PurgeProductsRequest, Empty, BatchOperationMetadata>
      purgeProductsOperationSettings() {
    return ((ProductSearchStubSettings) getStubSettings()).purgeProductsOperationSettings();
  }

    /**
     * Create product search settings.
     *
     * @param stub the stub
     * @return the product search settings
     * @throws IOException the io exception
     */
    public static final ProductSearchSettings create(ProductSearchStubSettings stub)
      throws IOException {
    return new Builder(stub.toBuilder()).build();
  }

    /**
     * Returns a builder for the default ExecutorProvider for this service.  @return the instantiating executor provider . builder
     */
    public static InstantiatingExecutorProvider.Builder defaultExecutorProviderBuilder() {
    return ProductSearchStubSettings.defaultExecutorProviderBuilder();
  }

    /**
     * Returns the default service endpoint.  @return the default endpoint
     */
    public static String getDefaultEndpoint() {
    return ProductSearchStubSettings.getDefaultEndpoint();
  }

    /**
     * Returns the default service scopes.  @return the default service scopes
     */
    public static List<String> getDefaultServiceScopes() {
    return ProductSearchStubSettings.getDefaultServiceScopes();
  }

    /**
     * Returns a builder for the default credentials for this service.  @return the google credentials provider . builder
     */
    public static GoogleCredentialsProvider.Builder defaultCredentialsProviderBuilder() {
    return ProductSearchStubSettings.defaultCredentialsProviderBuilder();
  }

    /**
     * Returns a builder for the default ChannelProvider for this service.  @return the instantiating grpc channel provider . builder
     */
    public static InstantiatingGrpcChannelProvider.Builder defaultGrpcTransportProviderBuilder() {
    return ProductSearchStubSettings.defaultGrpcTransportProviderBuilder();
  }

    /**
     * Default transport channel provider transport channel provider.
     *
     * @return the transport channel provider
     */
    public static TransportChannelProvider defaultTransportChannelProvider() {
    return ProductSearchStubSettings.defaultTransportChannelProvider();
  }

    /**
     * Default api client header provider builder api client header provider . builder.
     *
     * @return the api client header provider . builder
     */
    @BetaApi("The surface for customizing headers is not stable yet and may change in the future.")
  public static ApiClientHeaderProvider.Builder defaultApiClientHeaderProviderBuilder() {
    return ProductSearchStubSettings.defaultApiClientHeaderProviderBuilder();
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
     * Instantiates a new Product search settings.
     *
     * @param settingsBuilder the settings builder
     * @throws IOException the io exception
     */
    protected ProductSearchSettings(Builder settingsBuilder) throws IOException {
    super(settingsBuilder);
  }

    /**
     * Builder for ProductSearchSettings.
     */
    public static class Builder extends ClientSettings.Builder<ProductSearchSettings, Builder> {

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
      super(ProductSearchStubSettings.newBuilder(clientContext));
    }

        /**
         * Instantiates a new Builder.
         *
         * @param settings the settings
         */
        protected Builder(ProductSearchSettings settings) {
      super(settings.getStubSettings().toBuilder());
    }

        /**
         * Instantiates a new Builder.
         *
         * @param stubSettings the stub settings
         */
        protected Builder(ProductSearchStubSettings.Builder stubSettings) {
      super(stubSettings);
    }

    private static Builder createDefault() {
      return new Builder(ProductSearchStubSettings.newBuilder());
    }

        /**
         * Gets stub settings builder.
         *
         * @return the stub settings builder
         */
        public ProductSearchStubSettings.Builder getStubSettingsBuilder() {
      return ((ProductSearchStubSettings.Builder) getStubSettings());
    }

        /**
         * Applies the given settings updater function to all of the unary API methods in this service.
         *
         * <p>Note: This method does not support applying settings to streaming methods.
         *
         * @param settingsUpdater the settings updater
         * @return the builder
         */
        public Builder applyToAllUnaryMethods(
        ApiFunction<UnaryCallSettings.Builder<?, ?>, Void> settingsUpdater) {
      super.applyToAllUnaryMethods(
          getStubSettingsBuilder().unaryMethodSettingsBuilders(), settingsUpdater);
      return this;
    }

        /**
         * Returns the builder for the settings used for calls to createProductSet.  @return the unary call settings . builder
         */
        public UnaryCallSettings.Builder<CreateProductSetRequest, ProductSet>
        createProductSetSettings() {
      return getStubSettingsBuilder().createProductSetSettings();
    }

        /**
         * Returns the builder for the settings used for calls to listProductSets.  @return the paged call settings . builder < list product sets request , list product sets response , list product sets paged response >
         */
        public PagedCallSettings.Builder<
            ListProductSetsRequest, ListProductSetsResponse, ListProductSetsPagedResponse>
        listProductSetsSettings() {
      return getStubSettingsBuilder().listProductSetsSettings();
    }

        /**
         * Returns the builder for the settings used for calls to getProductSet.  @return the product set settings
         */
        public UnaryCallSettings.Builder<GetProductSetRequest, ProductSet> getProductSetSettings() {
      return getStubSettingsBuilder().getProductSetSettings();
    }

        /**
         * Returns the builder for the settings used for calls to updateProductSet.  @return the unary call settings . builder
         */
        public UnaryCallSettings.Builder<UpdateProductSetRequest, ProductSet>
        updateProductSetSettings() {
      return getStubSettingsBuilder().updateProductSetSettings();
    }

        /**
         * Returns the builder for the settings used for calls to deleteProductSet.  @return the unary call settings . builder
         */
        public UnaryCallSettings.Builder<DeleteProductSetRequest, Empty> deleteProductSetSettings() {
      return getStubSettingsBuilder().deleteProductSetSettings();
    }

        /**
         * Returns the builder for the settings used for calls to createProduct.  @return the unary call settings . builder
         */
        public UnaryCallSettings.Builder<CreateProductRequest, Product> createProductSettings() {
      return getStubSettingsBuilder().createProductSettings();
    }

        /**
         * Returns the builder for the settings used for calls to listProducts.  @return the paged call settings . builder < list products request , list products response , list products paged response >
         */
        public PagedCallSettings.Builder<
            ListProductsRequest, ListProductsResponse, ListProductsPagedResponse>
        listProductsSettings() {
      return getStubSettingsBuilder().listProductsSettings();
    }

        /**
         * Returns the builder for the settings used for calls to getProduct.  @return the product settings
         */
        public UnaryCallSettings.Builder<GetProductRequest, Product> getProductSettings() {
      return getStubSettingsBuilder().getProductSettings();
    }

        /**
         * Returns the builder for the settings used for calls to updateProduct.  @return the unary call settings . builder
         */
        public UnaryCallSettings.Builder<UpdateProductRequest, Product> updateProductSettings() {
      return getStubSettingsBuilder().updateProductSettings();
    }

        /**
         * Returns the builder for the settings used for calls to deleteProduct.  @return the unary call settings . builder
         */
        public UnaryCallSettings.Builder<DeleteProductRequest, Empty> deleteProductSettings() {
      return getStubSettingsBuilder().deleteProductSettings();
    }

        /**
         * Returns the builder for the settings used for calls to createReferenceImage.  @return the unary call settings . builder
         */
        public UnaryCallSettings.Builder<CreateReferenceImageRequest, ReferenceImage>
        createReferenceImageSettings() {
      return getStubSettingsBuilder().createReferenceImageSettings();
    }

        /**
         * Returns the builder for the settings used for calls to deleteReferenceImage.  @return the unary call settings . builder
         */
        public UnaryCallSettings.Builder<DeleteReferenceImageRequest, Empty>
        deleteReferenceImageSettings() {
      return getStubSettingsBuilder().deleteReferenceImageSettings();
    }

        /**
         * Returns the builder for the settings used for calls to listReferenceImages.  @return the paged call settings . builder < list reference images request , list reference images response , list reference images paged response >
         */
        public PagedCallSettings.Builder<
            ListReferenceImagesRequest,
            ListReferenceImagesResponse,
            ListReferenceImagesPagedResponse>
        listReferenceImagesSettings() {
      return getStubSettingsBuilder().listReferenceImagesSettings();
    }

        /**
         * Returns the builder for the settings used for calls to getReferenceImage.  @return the reference image settings
         */
        public UnaryCallSettings.Builder<GetReferenceImageRequest, ReferenceImage>
        getReferenceImageSettings() {
      return getStubSettingsBuilder().getReferenceImageSettings();
    }

        /**
         * Returns the builder for the settings used for calls to addProductToProductSet.  @return the unary call settings . builder
         */
        public UnaryCallSettings.Builder<AddProductToProductSetRequest, Empty>
        addProductToProductSetSettings() {
      return getStubSettingsBuilder().addProductToProductSetSettings();
    }

        /**
         * Returns the builder for the settings used for calls to removeProductFromProductSet.  @return the unary call settings . builder
         */
        public UnaryCallSettings.Builder<RemoveProductFromProductSetRequest, Empty>
        removeProductFromProductSetSettings() {
      return getStubSettingsBuilder().removeProductFromProductSetSettings();
    }

        /**
         * Returns the builder for the settings used for calls to listProductsInProductSet.  @return the paged call settings . builder < list products in product set request , list products in product set response , list products in product set paged response >
         */
        public PagedCallSettings.Builder<
            ListProductsInProductSetRequest,
            ListProductsInProductSetResponse,
            ListProductsInProductSetPagedResponse>
        listProductsInProductSetSettings() {
      return getStubSettingsBuilder().listProductsInProductSetSettings();
    }

        /**
         * Returns the builder for the settings used for calls to importProductSets.  @return the unary call settings . builder
         */
        public UnaryCallSettings.Builder<ImportProductSetsRequest, Operation>
        importProductSetsSettings() {
      return getStubSettingsBuilder().importProductSetsSettings();
    }

        /**
         * Returns the builder for the settings used for calls to importProductSets.  @return the operation call settings . builder < import product sets request , import product sets response , batch operation metadata >
         */
        public OperationCallSettings.Builder<
            ImportProductSetsRequest, ImportProductSetsResponse, BatchOperationMetadata>
        importProductSetsOperationSettings() {
      return getStubSettingsBuilder().importProductSetsOperationSettings();
    }

        /**
         * Returns the builder for the settings used for calls to purgeProducts.  @return the unary call settings . builder
         */
        public UnaryCallSettings.Builder<PurgeProductsRequest, Operation> purgeProductsSettings() {
      return getStubSettingsBuilder().purgeProductsSettings();
    }

        /**
         * Returns the builder for the settings used for calls to purgeProducts.  @return the operation call settings . builder
         */
        public OperationCallSettings.Builder<PurgeProductsRequest, Empty, BatchOperationMetadata>
        purgeProductsOperationSettings() {
      return getStubSettingsBuilder().purgeProductsOperationSettings();
    }

    @Override
    public ProductSearchSettings build() throws IOException {
      return new ProductSearchSettings(this);
    }
  }
}
