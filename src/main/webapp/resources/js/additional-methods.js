/**
 * Created by imyu on 2017/2/16.
 */

jQuery.validator.addMethod("number", function(value, element) {
    return this.optional(element) || (/^[0-9]*$/.test(value));
}, $.validator.format("请输入数字"));

jQuery.validator.addMethod("numberOrEnglish", function(value, element) {
    return this.optional(element) || (/^[a-zA-Z0-9]*$/.test(value));
}, $.validator.format("请输入字母或者数字"));

jQuery.validator.addMethod("isPhone", function(value, element) {
    return this.optional(element) || (/^((13|14|15|18|17)\d{9})$/.test(value));
}, $.validator.format("请输入正确手机号码"));

jQuery.validator.addMethod("isEmail", function(value, element) {
    return this.optional(element) || (/^[a-zA-Z0-9_\-\.]+@[a-zA-Z0-9\-_]+(\.[a-zA-Z0-9\-_]{2,})+([\.][a-zA-Z\-_]{2,})?$/.test(value));
}, $.validator.format("请输入正确邮箱格式"));

jQuery.validator.addMethod("decimal_5_4", function(value, element) {         //decimal(5,4)
    return this.optional(element) || (/^(0|1)(\.\d{1,4})?$/.test(value));
}, $.validator.format("格式不合法"));

jQuery.validator.addMethod("decimal_19_2", function(value, element) {         //decimal(19,2)
    return this.optional(element) || (/^(\d{1,17})(\.\d{1,2})?$/.test(value));
}, $.validator.format("格式不合法"));
