package com.bridgelabz.factory;

import com.bridgelabz.ComparatorProviderImpl;

public class ComparatorProviderFactory {

    public static ComparatorProviderImpl getComaparatorProvider(){
        return new ComparatorProviderImpl();
    }
}
