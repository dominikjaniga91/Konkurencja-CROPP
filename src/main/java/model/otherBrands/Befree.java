package model.otherBrands;

public class Befree extends AbstractBrand{

    private String brandName = "befree";
    private String productClassName = "Item__Name-cev3xd-2 feAiUU";
    private String priceClassName = "Item__Price-cev3xd-3 liLswN";
    private String modeloColorClassName = "itemprop";
    private String modeloColorParam = "href";
    private String langClass = "lang";


    @Override
    public String getBrandName() {
        return brandName;
    }

    @Override
    public String getProductClassName() {
        return productClassName;
    }

    @Override
    public String getPriceClassName() {
        return priceClassName;
    }

    @Override
    public String getModeloColorClassName() {
        return modeloColorClassName;
    }

    @Override
    public String getLangClass() {
        return langClass;
    }

    //only for beFree brand
    public String getModeloColorParam() {
        return modeloColorParam;
    }
}
