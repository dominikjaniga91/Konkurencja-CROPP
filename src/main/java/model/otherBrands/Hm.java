package model.otherBrands;

public class Hm extends OtherBrands {

    private String brandName = "h&m";
    private String productClassName = "item-heading";
    private String priceClassName = "item-price";
    private String modeloColorClassName = "data-articlecode";
    private String modeloColorParam = "data-articlecode";
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

    @Override
    public String getModeloColorParam() {
        return modeloColorParam;
    }
}
