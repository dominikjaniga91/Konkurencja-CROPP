package model.inditex;

public abstract class AbstractInditex {

    String brandName;
    String productAndPriceClassName;
    String modeloColorClassName;
    int cutFrom;
    int cutTo;
    String langClass;
    String langClassParam;
    String modeloColorAttribute;
    String countryAttribute;

    public abstract String getBrandName();

    public abstract String getModeloColorClassName();

    public abstract String getLangClass();

    public abstract int getCutFrom();

    public abstract int getCutTo();

    public abstract String getProductAndPriceClassName();

    public abstract String getLangClassParam();

    public abstract String getModeloColorAttribute();

    public abstract String getCountryAttribute();
}
