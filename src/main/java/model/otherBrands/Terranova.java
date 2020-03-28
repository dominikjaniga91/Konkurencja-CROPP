package model.otherBrands;

public class Terranova extends OtherBrands {

    private String brandName = "terranova";
    private String productClassName = "product-item-link";
    private String priceClassName = "price-box price-final_price";
    private String modeloColorClassName = "data-product-sku";
    private String modeloColorParam = "data-product-sku";
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
