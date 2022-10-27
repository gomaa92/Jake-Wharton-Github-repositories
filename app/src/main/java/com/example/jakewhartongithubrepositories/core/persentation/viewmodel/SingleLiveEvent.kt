package com.example.jakewhartongithubrepositories.core.persentation.viewmodel;

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

/**
 * A lifecycle-aware observable that sends only new updates after subscription, used for events like
 * navigation and Snackbar messages.
 * <p>
 * This avoids a common problem with events: on configuration change (like rotation) an update
 * can be emitted if the observer is active. This LiveData only calls the observable if there's an
 * explicit call to setValue() or call().
 * <p>
 * Note that only one observer is going to be notified of changes.
 */
private const val TAG = "SingleLiveEvent"

public class SingleLiveEvent<T> : MediatorLiveData<T>() {

    private final val mPending: AtomicBoolean = AtomicBoolean(false);

    @MainThread

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, observer)


        if (hasActiveObservers()) {
            Log.d(TAG, "Multiple observers registered but only one will be notified of changes.")
        }

        // Observe the internal MutableLiveData
        super.observe(owner) { value ->
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(value);
            }
        }
    }

    override fun setValue(value: T?) {
        mPending.set(true);
        super.setValue(value);
    }


    /**
     * Used for cases where T is Void, to make calls cleaner.
     */
    @MainThread
    public fun call() {
        value = null;
    }
}