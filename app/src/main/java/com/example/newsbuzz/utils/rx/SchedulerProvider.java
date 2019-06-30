package com.example.newsbuzz.utils.rx;

import io.reactivex.Scheduler;

/**
 * Created by sandeepsaini on 29,June,2019
 */
public interface SchedulerProvider {

    Scheduler ui();

    Scheduler computation();

    Scheduler io();

}
