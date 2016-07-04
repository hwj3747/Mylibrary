package compartment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

public abstract class ComponentControllerFragment<C> extends Fragment {
    private ComponentControllerDelegate<C> componentDelegate = new ComponentControllerDelegate<>();

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ComponentCache componentCache = (ComponentCache) getActivity().getApplication();
        componentDelegate.onCreate(componentCache, savedInstanceState, componentFactory);
    }

    @Override public void onResume() {
        super.onResume();
        componentDelegate.onResume();
    }

    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        componentDelegate.onSaveInstanceState(outState);
    }

    @Override public void onDestroy() {
        super.onDestroy();
        componentDelegate.onDestroy();
    }

    public C getComponent() {
        return componentDelegate.getComponent();
    }

    protected abstract C onCreateNonConfigurationComponent();

    private ComponentFactory<C> componentFactory = new ComponentFactory<C>() {
        @NonNull @Override public C createComponent() {
            return onCreateNonConfigurationComponent();
        }
    };
}