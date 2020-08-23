package co.com.android.baseproject.base.state;

import androidx.lifecycle.MutableLiveData;

/**
 * Created By oscar.vergara on 15/08/2020
 */
public class StateLiveData<T> extends MutableLiveData<StateData<T>> {

    public StateLiveData() {
        postValue(new StateData<>());
    }

    public void postLoading() {
        StateData<T> data = getValue();
        if (data == null) {
            data = new StateData<>();
        }
        postValue(data.loading());
    }

    public void postError(Throwable throwable) {
        postValue(getValue().error(throwable));
    }

    public void postSuccess(T data) {
        postValue(getValue().success(data));
    }

    public void postComplete() {
        postValue(getValue().complete());
    }
}