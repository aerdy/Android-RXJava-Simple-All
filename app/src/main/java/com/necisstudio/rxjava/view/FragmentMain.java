package com.necisstudio.rxjava.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.necisstudio.rxjava.R;
import com.necisstudio.rxjava.event.EventMessage;
import com.necisstudio.rxjava.manage.AppConfig;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by vim on 01/12/16.
 */

public class FragmentMain extends Fragment {
    TextView txtMessage;
    private Subscription busSubscription;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        txtMessage = (TextView) view.findViewById(R.id.txtMessage);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        autoUnsubBus();
        busSubscription = AppConfig.get().bus().toObserverable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Action1<Object>() {
                            @Override
                            public void call(Object o) {
                                handlerBus(o);
                            }
                        }
                );
    }

    private void handlerBus(Object o) {
        if (o instanceof EventMessage.Message) {
            txtMessage.setText(((EventMessage.Message) o).message);
        }
    }

    private void autoUnsubBus() {
        if (busSubscription != null && !busSubscription.isUnsubscribed()) {
            busSubscription.unsubscribe();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        autoUnsubBus();
    }

    @Override
    public void onDestroy() {
        autoUnsubBus();
        super.onDestroy();
    }
}
