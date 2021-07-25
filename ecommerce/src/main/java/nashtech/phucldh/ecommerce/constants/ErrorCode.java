package nashtech.phucldh.ecommerce.constants;

public class ErrorCode {

    // Category
    public static final String ERR_CATEGORY_NOT_FOUND = "ERR_CATEGORY_NOT_FOUND";
    public static final String ERR_CATEGORY_EXISTED = "ERR_CATEGORY_EXISTED";
    public static final String ERR_CREATE_CATEGORY_FAIL = "ERR_CREATE_CATEGORY_FAIL";
    public static final String ERR_UPDATE_CATEGORY_FAIL = "ERR_UPDATE_CATEGORY_FAIL";
    public static final String ERR_DELETE_CATEGORY_FAIL = "ERR_DELETE_CATEGORY_FAIL";

    // Product
    public static final String ERR_PRODUCT_NOT_FOUND = "ERR_PRODUCT_NOT_FOUND";
    public static final String ERR_PRODUCT_EXISTED = "ERR_PRODUCT_EXISTED";
    public static final String ERR_CREATE_PRODUCT_FAIL = "ERR_CREATE_PRODUCT_FAIL";
    public static final String ERR_UPDATE_PRODUCT_FAIL = "ERR_UPDATE_PRODUCT_FAIL";
    public static final String ERR_DELETE_PRODUCT_FAIL = "ERR_DELETE_PRODUCT_FAIL";

    // Cart
    public static final String ERR_CART_NOT_FOUND = "ERR_CART_NOT_FOUND";
    public static final String ERR_ITEM_CART_NOT_FOUND = "ERR_ITEM_CART_NOT_FOUND";
    public static final String ERR_CREATE_CART_FAIL = "ERR_CREATE_CART_FAIL";
    public static final String ERR_DELETE_CART_FAIL = "ERR_DELETE_CART_FAIL";
    public static final String ERR_DUPLICATE_ITEM_CART = "ERR_DUPLICATE_ITEM_CART";
    public static final String ERR_ADD_ITEM_CART_FAIL = "ERR_ADD_ITEM_CART_FAIL";
    public static final String ERR_UPDATE_CART_FAIL = "ERR_UPDATE_CART_FAIL";
    public static final String ERR_REMOVE_ITEM_CART_FAIL = "ERR_REMOVE_ITEM_CART_FAIL";

    // Account
    public static final String ERR_ACCOUNT_NOT_FOUND = "ERR_ACCOUNT_NOT_FOUND";
    public static final String ERR_ACCOUNT_SIGNUP_FAIL = "ERR_ACCOUNT_SIGNUP_FAIL";
    public static final String ERR_ACCOUNT_EXISTED = "ERR_ACCOUNT_EXISTED"; // should use in service to check exist account
    public static final String ERR_EMAIL_ALREADY_TAKEN = "ERR_EMAIL_ALREADY_TAKEN"; // should use in service to check exist email
    public static final String ERR_USERNAME_ALREADY_TAKEN = "ERR_EMAIL_ALREADY_TAKEN";
    public static final String ERR_CREATE_ACCOUNT_FAIL = "ERR_CREATE_ACCOUNT_FAIL";
    public static final String ERR_UPDATE_ACCOUNT_FAIL = "ERR_UPDATE_ACCOUNT_FAIL";
    public static final String ERR_DELETE_ACCOUNT_FAIL = "ERR_DELETE_ACCOUNT_FAIL";

    // Account Address
    public static final String ERR_ACCOUNT_ADDRESS_NOT_FOUND = "ERR_ACCOUNT_ADDRESS_NOT_FOUND";
    public static final String ERR_ACCOUNT_ADDRESS_EXISTED = "ERR_ACCOUNT_ADDRESS_EXISTED"; // should use in service to check exist account address
    public static final String ERR_CREATE_ACCOUNT_ADDRESS_FAIL = "ERR_CREATE_ACCOUNT_ADDRESS_FAIL";
    public static final String ERR_UPDATE_ACCOUNT_ADDRESS_FAIL = "ERR_UPDATE_ACCOUNT_ADDRESS_FAIL";
    public static final String ERR_DELETE_ACCOUNT_ADDRESS_FAIL = "ERR_DELETE_ACCOUNT_ADDRESS_FAIL";

    // Account Order
    public static final String ERR_ACCOUNT_ORDER_NOT_FOUND = "ERR_ACCOUNT_ORDER_NOT_FOUND";
    public static final String ERR_ACCOUNT_ORDER_EXISTED = "ERR_ACCOUNT_ORDER_EXISTED"; // should use in service to check exist account order
    public static final String ERR_CREATE_ACCOUNT_ORDER_FAIL = "ERR_CREATE_ACCOUNT_ORDER_FAIL";
    public static final String ERR_UPDATE_ACCOUNT_ORDER_FAIL = "ERR_UPDATE_ACCOUNT_ORDER_FAIL";
    public static final String ERR_DELETE_ACCOUNT_ORDER_FAIL = "ERR_DELETE_ACCOUNT_ORDER_FAIL";

    // Account Status
    public static final String ERR_ACCOUNT_STATUS_NOT_FOUND = "ERR_ACCOUNT_STATUS_NOT_FOUND";

    // Brand
    public static final String ERR_BRAND_NOT_FOUND = "ERR_BRAND_NOT_FOUND";
    public static final String ERR_BRAND_EXISTED = "ERR_BRAND_EXISTED";
    public static final String ERR_CREATE_BRAND_FAIL = "ERR_CREATE_BRAND_FAIL";
    public static final String ERR_UPDATE_BRAND_FAIL = "ERR_UPDATE_BRAND_FAIL";
    public static final String ERR_DELETE_BRAND_FAIL = "ERR_DELETE_BRAND_FAIL";

    // Coupons
    public static final String ERR_COUPONS_NOT_FOUND = "ERR_COUPONS_NOT_FOUND";
    public static final String ERR_COUPONS_EXISTED = "ERR_COUPONS_EXISTED";
    public static final String ERR_CREATE_COUPONS_FAIL = "ERR_CREATE_COUPONS_FAIL";
    public static final String ERR_UPDATE_COUPONS_FAIL = "ERR_UPDATE_COUPONS_FAIL";
    public static final String ERR_DELETE_COUPONS_FAIL = "ERR_DELETE_COUPONS_FAIL";

    // Order Detail
    public static final String ERR_ORDER_DETAIL_NOT_FOUND = "ERR_ORDER_DETAIL_NOT_FOUND";
    public static final String ERR_CREATE_ORDER_DETAIL_FAIL = "ERR_CREATE_ORDER_DETAIL_FAIL";
    public static final String ERR_UPDATE_ORDER_DETAIL_FAIL = "ERR_UPDATE_ORDER_DETAIL_FAIL";

    // Order Status
    public static final String ERR_ORDER_STATUS_NOT_FOUND = "ERR_ORDER_STATUS_NOT_FOUND";
    public static final String ERR_UPDATE_ORDER_STATUS_FAIL = "ERR_UPDATE_ORDER_STATUS_FAIL";
    public static final String ERR_DELETE_ORDER_STATUS_FAIL = "ERR_DELETE_ORDER_STATUS_FAIL";

    // Organization
    public static final String ERR_ORGANIZATION_NOT_FOUND = "ERR_ORGANIZATION_NOT_FOUND";
    public static final String ERR_ORGANIZATION_EXISTED = "ERR_ORGANIZATION_EXISTED";
    public static final String ERR_CREATE_ORGANIZATION_FAIL = "ERR_CREATE_ORGANIZATION_FAIL";
    public static final String ERR_UPDATE_ORGANIZATION_FAIL = "ERR_UPDATE_ORGANIZATION_FAIL";
    public static final String ERR_DELETE_ORGANIZATION_FAIL = "ERR_DELETE_ORGANIZATION_FAIL";

    // Organization Address
    public static final String ERR_ORGANIZATION_ADDRESS_NOT_FOUND = "ERR_ORGANIZATION_ADDRESS_NOT_FOUND";
    public static final String ERR_ORGANIZATION_ADDRESS_EXISTED = "ERR_ORGANIZATION_ADDRESS_EXISTED";
    public static final String ERR_CREATE_ORGANIZATION_ADDRESS_FAIL = "ERR_CREATE_ORGANIZATION_ADDRESS_FAIL";
    public static final String ERR_UPDATE_ORGANIZATION_ADDRESS_FAIL = "ERR_UPDATE_ORGANIZATION_ADDRESS_FAIL";
    public static final String ERR_DELETE_ORGANIZATION_ADDRESS_FAIL = "ERR_DELETE_ORGANIZATION_ADDRESS_FAIL";

    // Payment Method
    public static final String ERR_PAYMENT_METHOD_NOT_FOUND = "ERR_PAYMENT_METHOD_NOT_FOUND";
    public static final String ERR_UPDATE_PAYMENT_METHOD_FAIL = "ERR_UPDATE_PAYMENT_METHOD_FAIL";
    public static final String ERR_DELETE_PAYMENT_METHOD_FAIL = "ERR_DELETE_PAYMENT_METHOD_FAIL";

    // Image
    public static final String ERR_IMAGE_NOT_FOUND = "ERR_PAYMENT_METHOD_NOT_FOUND";
    public static final String ERR_IMAGE_EXISTED = "ERR_PAYMENT_METHOD_EXISTED"; // should use in service to check exist image
    public static final String ERR_CREATE_IMAGE_FAIL = "ERR_CREATE_PAYMENT_METHOD_FAIL";
    public static final String ERR_UPDATE_IMAGE_FAIL = "ERR_UPDATE_PAYMENT_METHOD_FAIL";
    public static final String ERR_DELETE_IMAGE_FAIL = "ERR_DELETE_PAYMENT_METHOD_FAIL";

    // Role
    public static final String ERR_ROLE_NOT_FOUND = "ERR_ROLE_NOT_FOUND";

    // Converter
    public static final String ERR_CONVERTER_DTO_ENTITY_FAIL = "ERR_CONVERTER_DTO_ENTITY_FAIL";

    // AUTHENTICATION - AUTHORIZATION
    public static final String ERR_ACCOUNT_LOGIN_FAIL = "ERR_USER_LOGIN_FAIL";

}