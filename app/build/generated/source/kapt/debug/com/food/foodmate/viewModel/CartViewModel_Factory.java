package com.food.foodmate.viewModel;

import com.food.foodmate.utility.DataStoreManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class CartViewModel_Factory implements Factory<CartViewModel> {
  private final Provider<CartRepository> repositoryProvider;

  private final Provider<DataStoreManager> dataStoreManagerProvider;

  public CartViewModel_Factory(Provider<CartRepository> repositoryProvider,
      Provider<DataStoreManager> dataStoreManagerProvider) {
    this.repositoryProvider = repositoryProvider;
    this.dataStoreManagerProvider = dataStoreManagerProvider;
  }

  @Override
  public CartViewModel get() {
    return newInstance(repositoryProvider.get(), dataStoreManagerProvider.get());
  }

  public static CartViewModel_Factory create(Provider<CartRepository> repositoryProvider,
      Provider<DataStoreManager> dataStoreManagerProvider) {
    return new CartViewModel_Factory(repositoryProvider, dataStoreManagerProvider);
  }

  public static CartViewModel newInstance(CartRepository repository,
      DataStoreManager dataStoreManager) {
    return new CartViewModel(repository, dataStoreManager);
  }
}
