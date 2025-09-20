package com.food.foodmate.viewModel;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class RepositoryModule_ProvideCartRepositoryFactory implements Factory<CartRepository> {
  private final Provider<CartDao> cartDaoProvider;

  public RepositoryModule_ProvideCartRepositoryFactory(Provider<CartDao> cartDaoProvider) {
    this.cartDaoProvider = cartDaoProvider;
  }

  @Override
  public CartRepository get() {
    return provideCartRepository(cartDaoProvider.get());
  }

  public static RepositoryModule_ProvideCartRepositoryFactory create(
      Provider<CartDao> cartDaoProvider) {
    return new RepositoryModule_ProvideCartRepositoryFactory(cartDaoProvider);
  }

  public static CartRepository provideCartRepository(CartDao cartDao) {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.provideCartRepository(cartDao));
  }
}
