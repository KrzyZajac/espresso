package pl.matbos.perform.host;

import timber.log.Timber;

public interface StateShow {
    StateShow EMPTY = new StateShow() {
        @Override
        public void showProgress() {
            Timber.w("Using empty StateShow implementation");
        }

        @Override
        public void hideProgress() {
            Timber.w("Using empty StateShow implementation");
        }

        @Override
        public void errorOccured(String errorMessage) {
            Timber.w("Using empty StateShow implementation");
        }

        @Override
        public void hideNotifications() {
            Timber.w("Using empty StateShow implementation");
        }

        @Override
        public void showEmpty(String message) {
            Timber.w("Using empty StateShow implementation");
        }
    };

    void showProgress();
    void hideProgress();
    void errorOccured(String errorMessage);
    void hideNotifications();
    void showEmpty(String message);
}
