package model.otherBrands;

public class House extends OtherBrands {

    String brandName = "house";
    String productClassName = "es-product-name";
    String priceClassName = "es-product-price";
    String modeloColorClassName = "data-sku";
    String modeloColorParam = "data-sku";
    String langClass = "lang";

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
