package jonas.androidparty.base;

import io.reactivex.disposables.CompositeDisposable;

/**
 * class created by jonasseputis on 18/11/18
 */
abstract public class BasePresenter<T> implements Presenter<T> {

    protected final CompositeDisposable subscriptions = new CompositeDisposable();

    private T view;

    public BasePresenter() {

    }

    public boolean hasView() {
        return view != null;
    }

    public T getView() {
        return view;
    }

    @Override
    public void setView(T view) {
        this.view = view;
        if (view == null) {
            subscriptions.clear();
        }
    }
}
