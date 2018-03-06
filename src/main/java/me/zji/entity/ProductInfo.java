package me.zji.entity;

/**
 * Created by imyu on 2017/3/3.
 */
public class ProductInfo extends Id {
    String productCode;
    String productName;
    String taCode;
    String productCategory;
    String productOperation;
    String investRegion;
    String investDirection;
    String moneyType;
    Float manageRatio;
    String chargeType;
    String productStatus;
    String productRiskLevel;
    String bankAcco;
    String dividendMethod;
    String issueStartDate;

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTaCode() {
        return taCode;
    }

    public void setTaCode(String taCode) {
        this.taCode = taCode;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductOperation() {
        return productOperation;
    }

    public void setProductOperation(String productOperation) {
        this.productOperation = productOperation;
    }

    public String getInvestRegion() {
        return investRegion;
    }

    public void setInvestRegion(String investRegion) {
        this.investRegion = investRegion;
    }

    public String getInvestDirection() {
        return investDirection;
    }

    public void setInvestDirection(String investDirection) {
        this.investDirection = investDirection;
    }

    public String getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(String moneyType) {
        this.moneyType = moneyType;
    }

    public Float getManageRatio() {
        return manageRatio;
    }

    public void setManageRatio(Float manageRatio) {
        this.manageRatio = manageRatio;
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    public String getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(String productStatus) {
        this.productStatus = productStatus;
    }

    public String getProductRiskLevel() {
        return productRiskLevel;
    }

    public void setProductRiskLevel(String productRiskLevel) {
        this.productRiskLevel = productRiskLevel;
    }

    public String getBankAcco() {
        return bankAcco;
    }

    public void setBankAcco(String bankAcco) {
        this.bankAcco = bankAcco;
    }

    public String getDividendMethod() {
        return dividendMethod;
    }

    public void setDividendMethod(String dividendMethod) {
        this.dividendMethod = dividendMethod;
    }

    public String getIssueStartDate() {
        return issueStartDate;
    }

    public void setIssueStartDate(String issueStartDate) {
        this.issueStartDate = issueStartDate;
    }
}
