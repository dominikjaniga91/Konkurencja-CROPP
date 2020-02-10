package model.inditex;

public class Bershka extends AbstractInditex {

    private String brandName = "pull&bershka";
    private String productAndPriceClassName = "href";
    private String modeloColorClassName = "alt";
    private String langClass = "http-equiv";
    private String langClassParam = "content-language";
    private String modeloColorAttribute = "src";
    private String countryAttribute = "content";
    private int cutFrom = 50;
    private int cutTo =62;

    @Override
    public String getBrandName() {
        return brandName;
    }

    @Override
    public String getProductAndPriceClassName() {
        return productAndPriceClassName;
    }

    @Override
    public String getModeloColorClassName() {
        return modeloColorClassName;
    }

    @Override
    public String getLangClass() {
        return langClass;
    }

    @Override
    public String getLangClassParam() {
        return langClassParam;
    }

    @Override
    public String getModeloColorAttribute() {
        return modeloColorAttribute;
    }

    @Override
    public String getCountryAttribute() {
        return countryAttribute;
    }

    @Override
    public int getCutFrom() {
        return cutFrom;
    }

    @Override
    public int getCutTo() {
        return cutTo;
    }

}

