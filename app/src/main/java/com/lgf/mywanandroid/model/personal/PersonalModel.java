package com.lgf.mywanandroid.model.personal;

import com.lgf.mywanandroid.contract.personal.PersonalContract;

import io.reactivex.annotations.NonNull;

/**
 * Created by Administrator on 2019/6/5 0005.
 * desc :
 */
public class PersonalModel implements PersonalContract.Model {
    @NonNull
    public static PersonalModel newInstance() {
        return new PersonalModel();
    }
}
