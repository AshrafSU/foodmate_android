package com.food.foodmate.viewModel;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class CartDao_Impl implements CartDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CartItem> __insertionAdapterOfCartItem;

  private final EntityDeletionOrUpdateAdapter<CartItem> __deletionAdapterOfCartItem;

  private final SharedSQLiteStatement __preparedStmtOfClearCart;

  public CartDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCartItem = new EntityInsertionAdapter<CartItem>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `CartItem` (`id`,`foodId`,`name`,`quantity`,`price`,`sellPrice`,`imageUrl`,`shopId`,`shopName`,`shopLogo`,`deliveryCharge`,`allowMealOrder`,`allowDine`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CartItem entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getFoodId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getFoodId());
        }
        if (entity.getName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getName());
        }
        statement.bindLong(4, entity.getQuantity());
        statement.bindDouble(5, entity.getPrice());
        statement.bindDouble(6, entity.getSellPrice());
        if (entity.getImageUrl() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getImageUrl());
        }
        if (entity.getShopId() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getShopId());
        }
        if (entity.getShopName() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getShopName());
        }
        if (entity.getShopLogo() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getShopLogo());
        }
        statement.bindDouble(11, entity.getDeliveryCharge());
        final int _tmp = entity.getAllowMealOrder() ? 1 : 0;
        statement.bindLong(12, _tmp);
        final int _tmp_1 = entity.getAllowDine() ? 1 : 0;
        statement.bindLong(13, _tmp_1);
      }
    };
    this.__deletionAdapterOfCartItem = new EntityDeletionOrUpdateAdapter<CartItem>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `CartItem` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CartItem entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__preparedStmtOfClearCart = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM cartitem";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final CartItem item, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfCartItem.insert(item);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final CartItem item, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfCartItem.handle(item);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object clearCart(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearCart.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfClearCart.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<CartItem>> getAllItems() {
    final String _sql = "SELECT * FROM cartitem";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"cartitem"}, new Callable<List<CartItem>>() {
      @Override
      @NonNull
      public List<CartItem> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFoodId = CursorUtil.getColumnIndexOrThrow(_cursor, "foodId");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
          final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
          final int _cursorIndexOfSellPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "sellPrice");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfShopId = CursorUtil.getColumnIndexOrThrow(_cursor, "shopId");
          final int _cursorIndexOfShopName = CursorUtil.getColumnIndexOrThrow(_cursor, "shopName");
          final int _cursorIndexOfShopLogo = CursorUtil.getColumnIndexOrThrow(_cursor, "shopLogo");
          final int _cursorIndexOfDeliveryCharge = CursorUtil.getColumnIndexOrThrow(_cursor, "deliveryCharge");
          final int _cursorIndexOfAllowMealOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "allowMealOrder");
          final int _cursorIndexOfAllowDine = CursorUtil.getColumnIndexOrThrow(_cursor, "allowDine");
          final List<CartItem> _result = new ArrayList<CartItem>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CartItem _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpFoodId;
            if (_cursor.isNull(_cursorIndexOfFoodId)) {
              _tmpFoodId = null;
            } else {
              _tmpFoodId = _cursor.getString(_cursorIndexOfFoodId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final int _tmpQuantity;
            _tmpQuantity = _cursor.getInt(_cursorIndexOfQuantity);
            final double _tmpPrice;
            _tmpPrice = _cursor.getDouble(_cursorIndexOfPrice);
            final double _tmpSellPrice;
            _tmpSellPrice = _cursor.getDouble(_cursorIndexOfSellPrice);
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final String _tmpShopId;
            if (_cursor.isNull(_cursorIndexOfShopId)) {
              _tmpShopId = null;
            } else {
              _tmpShopId = _cursor.getString(_cursorIndexOfShopId);
            }
            final String _tmpShopName;
            if (_cursor.isNull(_cursorIndexOfShopName)) {
              _tmpShopName = null;
            } else {
              _tmpShopName = _cursor.getString(_cursorIndexOfShopName);
            }
            final String _tmpShopLogo;
            if (_cursor.isNull(_cursorIndexOfShopLogo)) {
              _tmpShopLogo = null;
            } else {
              _tmpShopLogo = _cursor.getString(_cursorIndexOfShopLogo);
            }
            final double _tmpDeliveryCharge;
            _tmpDeliveryCharge = _cursor.getDouble(_cursorIndexOfDeliveryCharge);
            final boolean _tmpAllowMealOrder;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfAllowMealOrder);
            _tmpAllowMealOrder = _tmp != 0;
            final boolean _tmpAllowDine;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfAllowDine);
            _tmpAllowDine = _tmp_1 != 0;
            _item = new CartItem(_tmpId,_tmpFoodId,_tmpName,_tmpQuantity,_tmpPrice,_tmpSellPrice,_tmpImageUrl,_tmpShopId,_tmpShopName,_tmpShopLogo,_tmpDeliveryCharge,_tmpAllowMealOrder,_tmpAllowDine);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
