package pl.matbos.perform.util;

import rx.Subscription;

public final class SubscriptionUtils {
    private SubscriptionUtils() {
    }

    public static void unsubscribe(Subscription subscription) {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
