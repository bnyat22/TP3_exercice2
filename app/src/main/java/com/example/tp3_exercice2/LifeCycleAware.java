package com.example.tp3_exercice2;

import android.app.Instrumentation;
import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class LifeCycleAware implements LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void NombreUtilisation()
    {
    Inscription.getCompte();
    }


}
