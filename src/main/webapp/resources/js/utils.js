/**
 * Created by qian yun on 2018/3/8.
 */
/**表单序列号为json对象*/
(function($){
    $.fn.serializeJson = function(){
        var param = {};

        this.find("input[type != 'checkbox']").each(function(index,item){
            param[$(this).attr("name")] = $(this).val();
        });

        this.find("input[type = 'checkbox']").each(function(index,item){
            param[$(this).attr("name")] = $(this).prop("checked");
        });

        this.find("select").each(function(index,item){
            param[$(this).attr("name")] = $(this).val();
        });

        return param;
    };
})(jQuery);


/** 初始化Select的Options */
(function($){
    $.fn.initSelect = function(selectName, selectItemMap){
        var selectData;
        if (arguments.length > 1) {
            selectData = selectItemMap[selectName];
        } else {
            selectData = selectName;
        }

        var selectOptions = "";
        for(var i = 0; i < selectData.length; i ++) {
            var element = selectData[i];
            if (element == null){
                continue;
            }
            selectOptions += "<option value='" + element.item + "'>" + element.caption + "</option>";
        }
        this.empty();
        this.append(selectOptions);
    }
})(jQuery);

function upload(file, url) {
    var fd = new FormData();
    fd.append("upload", 1);
    fd.append("upfile", file);
    var data = null;
    $.ajax({
        url: url,
        type: "POST",
        async:false,
        processData: false,
        contentType: false,
        data: fd,
        success: function(response) {
            if (response.resultCode == 1) {
                data = response.data;
            }
        }
    });
    return data;
}
