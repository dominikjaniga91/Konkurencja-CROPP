package model.otherBrands;

public class Reserved extends AbstractBrand {

    private String brandName = "reserved";
    private String productClassName = "es-product-name";
    private String priceClassName = "es-product-price";
    private String modeloColorClassName = "data-sku";
    private String modeloColorParam = "data-sku";
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
